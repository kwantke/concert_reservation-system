### Jenkins Pipline 수행 결과
- **Jenkins Pipeline**(Jenkinsfile)을 이용해 소스 빌드 → Docker 이미지 생성 → Kubernetes 배포까지 자동화 합니다.
- [Jenkinsfile](https://github.com/kwantke/concert_reservation-system/blob/develop/Jenkinsfile)
<img width="806" height="454" alt="concert_jenkins_pipline" src="https://github.com/user-attachments/assets/de77d2cd-c553-431e-b463-a52a4c7c7000" />

### ArgoCD Sync 확인
- [Kubernetes Manifest 파일](https://github.com/kwantke/argocd/tree/main/kubernetes_manifest)
<img width="806" height="454" alt="concert_argocd2" src="https://github.com/user-attachments/assets/4d4ee797-a831-46ef-8c05-35aa3f96d76d" />

### Grafana 대시보드
- Kubernetes 클러스터에 Helm Chart을 이용하여 Prometheus와 Grfana 를 배포합니다.
- Prometheus에서 수집한 메트릭 데이터를 Grafana 통해 시각화하여 보여줍니다.
<img width="806" height="454" alt="kubernetes_grafana" src="https://github.com/user-attachments/assets/0f78037e-dd3b-4291-a77c-ae3ace1119c7" />

### Kibana 대시보드
- Kafka를 통해 로그 스트림을 선 수집한 후 Logstash와 Elasticsearch에서 전처리와 저장하여 Kibana로 시각화 합니다.
<img width="806" height="454" alt="kibana_log2" src="https://github.com/user-attachments/assets/efb9c2ba-4046-4714-bbe4-eec76f1b5365" />
