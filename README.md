# 콘서트 예약 시스템 (Concert Reservation System)

## 1. 프로젝트 개요
콘서트 예약 시스템은 대기열 시스템을 사용하여 사용자가 콘서트 좌석을 예약하고, 결제를 진행하는 REST API 기반 서비스 입니다.
동시성 제어를 통해 동시 예약이나 결제 시 중복으로 처리되지 않도록 설계하였습니다.

## 2. 기술 스택
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
- grafana
- IntelliJ Http Request

---

## 3. 프로젝트 시스템 서비스 설계

### [요구사항 설계](https://github.com/kwantke/concert_reservation-system/blob/develop/docs/REQUIREMENTS.md)
### [시퀀스 다이어그램](https://github.com/kwantke/concert_reservation-system/blob/develop/docs/SEQUENCE_DIAGRAM.md)
### [ERD 설계](https://github.com/kwantke/concert_reservation-system/blob/develop/docs/ERD.md)
### [API 명세](https://github.com/kwantke/concert_reservation-system/blob/develop/docs/API_Specification.md)

---
## 4. 아키텍처 구성도

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


#### [아키텍처 구성 수행](https://github.com/kwantke/concert_reservation-system/blob/develop/docs/ARCHITECTURE_CONFIGURATION_EXCUTION.md)
---







