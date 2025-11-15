# 콘서트 예매 시스템 (Concert Reservation System)

## 프로젝트 개요
콘서트 예매 시스템은 사용자가 콘서트 좌석을 예매하고 결제 하는 REST API 기반 서비스 입니다.

## 기술 스택
- Spring Boot 3.2.9
- Spring Data JPA
- Java 17
- Gradle
- MySQL 8.0
- Docker
- Redis
- Kafka
- Junit5
- swagger
- Jenkins Pipline
- Kubernetes
- Argocd
- Prometheus
- grafaka
- K6
- IntelliJ Http Request

---

### 아키텍처 구성도

<img width="945" height="459" alt="architecture2" src="https://github.com/user-attachments/assets/2049d230-93fc-4fe5-9e81-44df481031fe" />


- **CI/CD 자동화**
  - **Jenkins Pipeline**(Jenkinsfile)을 이용해 소스 빌드 → Docker 이미지 생성 → Kubernetes 배포까지 자동화 합니다.
  - ArgoCD을 적용하여 안정적이고 재현 가능한 배포 환경을 구축합니다.
- **모니터링 & 운영 관리**
  - Prometheus로 메트릭을 수집합니다.
  - Grafana 대시보드를 통해 Kubernetes 노드·파드·애플리케이션 지표를 실시간 시각화 합니다.
- **로그 수집 및 분석 시스템**
  - Kafka를 통해 애플리케이션 로그 스트림을 안정적으로 수집합니다.
  - Logstash에서 로그 전처리를 수행한 후 Elasticsearch에 저장하여 Kibana로 검색·시각화 및 운영 로그 분석 환경 제공합니다.
- **Redis 기반 성능 최적화 및 동시성 제어**
  - Redis 캐시를 활용해 데이터 조회 성능 향상 및 서버 부하 감소시킵니다.
  - Redis 분산락(Redisson 기반)을 적용해서 중복 예약 방지, Race Condition 방지합니다.
- **Kafka 기반 데이터 병령 처리**
  - 이벤트 기반 아키텍처로 대량 데이터 처리 시 높은 처리량을 확보합니다.
  - 비동기 이벤트 기반 설계로 애플리케이션 부하를 최소화합니다.
  - Consumer Group 기반 멀티 인스턴스 Consumer 로 메시지를 분산 처리합니다.

[아키텍처 구성 수행 확인]()
---
### Jenkins Pipline 수행 결과
- 
<img width="520" height="252" alt="concert_jenkins_pipline" src="https://github.com/user-attachments/assets/de77d2cd-c553-431e-b463-a52a4c7c7000" />

### ArgoCD Sync 확인
- [Kubernetes Manifest 파일](https://github.com/kwantke/argocd/tree/main/kubernetes_manifest)
<img width="763" height="327" alt="concert_argocd2" src="https://github.com/user-attachments/assets/4d4ee797-a831-46ef-8c05-35aa3f96d76d" />

### Grafana 대시보드
<img width="806" height="454" alt="kubernetes_grafana" src="https://github.com/user-attachments/assets/0f78037e-dd3b-4291-a77c-ae3ace1119c7" />

### Kibana 대시보드
<img width="1908" height="812" alt="kibana_log2" src="https://github.com/user-attachments/assets/efb9c2ba-4046-4714-bbe4-eec76f1b5365" />


### 1. [Sequence Diagram](https://github.com/kwantke/concert_reservation-system/blob/develop/docs/SEQUENCE_DIAGRAM.md)


