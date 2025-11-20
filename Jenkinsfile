pipeline {
  agent none  //  전체적으로 실행 노드를 할당하지 않음

  stages {
    stage('Checkout') {
        agent any  //  Checkout 단계에서는 노드 할당 필요
        steps {
            git branch: 'develop', url: 'https://github.com/kwantke/concert_reservation-system.git'
        }
    }
    stage('Gradle Build') {
        agent any
        steps {
            sh 'chmod +x gradlew'
            sh './gradlew dependencies --no-daemon'
            sh './gradlew :infrastructure:clean :infrastructure:bootJar -x test --no-daemon --stacktrace'
        }
    }
    stage('Docker Build') {
        when {
            expression { params.DOCKER_BUILD == true }
        }
        agent any  //  Docker 빌드를 실행할 노드 지정
        steps {
            script {
              if (params.DOCKER_IMAGE_TAG != "") {
                  echo "[docker image tag is not null]"
                  imageTag = params.DOCKER_IMAGE_TAG
              } else {
                  echo "[docker image tag is null]"
                  imageTag = env.BUILD_NUMBER
              }
              env.FULL_IMAGE = "kwangko/concert:${params.ENVIRONMENT}_${imageTag}"
            }
            sh """
                docker build \
                    -f infrastructure/Dockerfile \
                    --build-arg ENVIRONMENT=${params.ENVIRONMENT} \
                    -t ${env.FULL_IMAGE} \
                    infrastructure
            """
        }
    }
    stage('Docker Push') {
        when {
            expression { params.DOCKER_PUSH == true }
        }
        agent any  //  Docker Push 실행할 노드 지정
        steps {
            withDockerRegistry(credentialsId: 'dockerhub-signin', url: 'https://index.docker.io/v1/') {
                sh "docker push kwangko/concert:${params.ENVIRONMENT}_${imageTag}"
            }
        }
    }
    stage('k8s push') {
      when { expression { params.K8S_PUSH == true } }
      agent any
      steps {
      withCredentials([usernamePassword(
        credentialsId: 'github-signin',
        usernameVariable: 'GIT_USERNAME',
        passwordVariable: 'GIT_PASSWORD'
      )]) {
        dir('argocd') {
          deleteDir()

          sh('git clone https://' + GIT_USERNAME + ':' + GIT_PASSWORD + '@github.com/kwantke/argocd.git .')

          sh('''
            set -e
            git config --local user.name  "kwantke"
            git config --local user.email "kwantke@users.noreply.github.com"
            git config --global --add safe.directory "$PWD"
            git checkout main || git checkout -b main
            git pull --rebase
          ''')
          // 디버깅: 변경 전후 image 라인 출력
          sh('echo BEFORE && grep -n "image:" -n kubernetes_manifest/deployment.yaml | head -3')

          // sed 부분도 문자열 결합으로 FULL_IMAGE만 주입
          sh('sed -i -E \'s|(^[[:space:]]*-[[:space:]]*image:[[:space:]]*).*$|\\1' + env.FULL_IMAGE + '|\' kubernetes_manifest/deployment.yaml')

          sh('echo AFTER  && grep -n "image:" -n kubernetes_manifest/deployment.yaml | head -3')

          // 커밋/푸시
          script {
                def commitMsg = "update! image: ${env.FULL_IMAGE} by Jenkins"

                sh("""#!/bin/bash
                  set -e
                  if ! git diff --quiet; then
                    git add -A
                    git commit -m "${commitMsg}" || true
                  else
                    echo "No changes to commit."
                  fi
                """)
            }


          // 브랜치명에 따라 push
          // 푸시 직전 최신화 + 재시도(경합에 안전)
          sh('''
            set -e
            tries=0
            until [ $tries -ge 3 ]; do
            if git pull --rebase origin main && git push origin HEAD:main; then
              echo "Push succeeded."
              break
            fi
            echo "Push rejected (non-fast-forward). Retry... ($tries)"
            tries=$((tries+1))
            sleep 1
            done
            [ $tries -lt 3 ] || { echo "Push failed after retries."; exit 1; }
          ''')
          }
        }
      }
    }
    stage('argocd sync') {
      when {
        expression { params.ARGOCD_SYNC == true }
      }
      agent any
      steps {
        withCredentials([string(credentialsId: 'argocd-token', variable: 'ARGOCD_TOKEN')]) {
          sh '''
            curl -k -L \
            -X POST \
            -H "Authorization: Bearer $ARGOCD_TOKEN" \
            -H "Content-Type: application/json" \
            https://192.168.0.23:31081/api/v1/applications/concert/sync \
            -d '{
              "revision": "HEAD",
              "prune": false,
              "dryRun": false,
              "strategy": {
              "hook": {
                "force": false
              }
              },
              "resources": []
            }'
          '''
        }
      }
    }
  }
}