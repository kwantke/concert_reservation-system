## 목록

---
1. [대기열 발급 요청](#1-대기열-발급-요청)
2. [대기열 조회 요청](#2-대기열-조회-요청)
3. [콘서트 예약 가능 회차(날짜) 조회](#3-콘서트-예약-가능-회차-조회)
4. [콘서트 회차 좌석 조회](#4-콘서트-회차-좌석-조회)
5. [콘서트 회차의 좌석 조회](#5-콘서트-회차의-좌석-예약)
6. [결제](#6-결제)
<br>

### 1. 대기열 발급 요청
---

```mermaid
sequenceDiagram
  actor 사용자 as 사용자
  participant UseCase as UseCase
  participant Queue as Queue
  participant QueueService as QueueService

  사용자 ->> UseCase: 대기열 토큰 발급 요청


  UseCase ->> Queue: 대기열 토큰 발급 요청 (userId)
  Queue ->> QueueService: 토큰 생성 요청
  QueueService -->> Queue: 생성된 토큰 반환
  Queue ->> Queue: 신규 대기열 저장 (대기 상태로 저장)
  Queue -->> UseCase: 신규 대기열 반환
  UseCase -->> 사용자: 대기열 정보 반환

```

### Description
대기열 토큰 발급 요청에 시퀀스 다이어그램입니다.
- 사용자가 대기열 토큰을 발급 요청하면, 시스템은 토큰을 생성합니다.
- 발급한 토큰을 대기열에 젖아합니다. 이때, 대기열 상태는 `대기`로 저장합니다.
- 대기열 정보를 반환합니다.

<br>

### 2. 대기열 조회 요청
---
```mermaid
sequenceDiagram
    actor 사용자
    participant UseCase
    participant Queue

    사용자->>UseCase: 대기열 정보 조회 요청 (token)
    UseCase->>Queue: 대기열 토큰 조회

    alt 유효하지 않은 토큰
        Queue-->>사용자: Error "유효하지 않은 토큰입니다."
    else 유효한 토큰
        Queue-->>UseCase: 대기열 정보 반환 (토큰ID, 상태, 대기 번호)
        UseCase-->>사용자: 대기열 정보 반환
    end
```
### Description
대기열 정보 조회 요청에 대한 시퀀스 다이어그램입니다.

- 대기열 정보를 조회하여 대기 순서, 상태 등의 정보를 사용자에게 반환합니다.

<br>

### 3. 콘서트 예약 가능 회차 조회
---
```mermaid
sequenceDiagram
    actor 사용자
    participant UseCase
    participant Concert
    participant Queue


    사용자->>UseCase: 예약 가능한 회차 조회 요청 (token)
    UseCase->>Queue: 대기열 토큰 체크 요청

    alt 유효하지 않은 토큰
        Queue-->>사용자: Error "유효하지 않은 토큰입니다."
    else 유효한 토큰
        Queue-->>UseCase: 유효한 토큰
        UseCase->>Concert: 예약 가능한 회차 조회
        Concert-->>UseCase: 예약 가능한 회차 목록 반환
        UseCase-->>사용자: 예약 가능한 회차 목록 반환
    end
```

### Description
콘서트 예약 가능 날짜 조회 요청에 대한 시퀀스 다이어그램입니다.
- 사용자가 대가열 토큰을 포함해 예약 가능한 회차를 조회 요청하면, 시스템은 해당 토큰이 유효한지 확인합니다.
- 토큰이 유효하지 않은 경우 에러 메시지를 반환합니다.
- 예약 가능 회차 목록을 조회하여 사용자에게 반환합니다.

<br>

### 4. 콘서트 회차 좌석 조회
---
```mermaid
sequenceDiagram
    actor 사용자
    participant UseCase
    participant Concert
    participant Queue

    사용자->>UseCase: 특정 회차의 좌석 조회 요청 (token)
    UseCase->>Queue: 대기열 토큰 체크 요청

    alt 유효하지 않은 토큰(존재x, 활성 상태가 아님)
        Queue-->>사용자: Error "유효하지 않은 토큰입니다."
    else 유효한 토큰
        Queue-->>UseCase: 유효한 토큰
        UseCase->>Concert: 특정 회차의 좌석 조회
        Concert-->>UseCase: 특정 회차의 좌석 목록 반환
        UseCase-->>사용자: 특정 회차의 좌석 목록 반환(예약 가능한 좌석 정보 포함)
    end
```
### Description
콘서트 회차의 촤석 조회 요청에 대한 시퀀스 다이어그램입니다.
- 사용자가 대기열 토큰을 포함해 특정 회차의 좌석을 조회 요청하면, 시스템은 해당 토큰이 유효한지 확인합니다.
- 토큰이 유효하지 않거나 활성 상태가 아닌 경우 에러 메시지를 반환합니다.
- 예약 가능한 회차의 좌석 목록을 조회하여 사용자에게 반환합니다.

<br>

### 5. 콘서트 회차의 좌석 예약
---
```mermaid
sequenceDiagram
    actor 사용자
    participant UseCase
    participant Concert
    participant Queue

    사용자->>UseCase: 특정 회차의 좌석 예약 요청 (token, seatId)
    UseCase->>Queue: 대기열 토큰 체크 요청

    alt 유효하지 않은 토큰(존재x, 활성 상태가 아님, 유저의 토큰이 아님)
        Queue-->>사용자: Error "유효하지 않은 토큰입니다."
    else 유효한 토큰
        Queue-->>UseCase: 유효한 토큰
        UseCase->>Concert: 특정 회차의 좌석 예약 요청
        Concert->>Concert: 좌석 조회

        alt 이미 예약된 경우
            Concert-->>사용자: Error "좌석이 이미 예약되었습니다."
        else 예약 가능한 경우
            Concert->>Concert: 좌석 임시 예약
            Concert-->>UseCase: 좌석 임시 예약 성공
            UseCase-->>사용자: 좌석 임시 예약 성공
        end
    end
```

### Description
콘서트 회차의 좌석 예약 요청에 대한 시퀀스 다이어그램입니다.
- 사용자가 대기열 토큰을 포함해 특정 회차의 좌석을 예약 요청하면, 시스템은 해당 토큰이 유효한지 확인합니다.
- 토큰이 유효하지 않은 경우 에러 메시지를 반환합니다.
- 토큰이 활성 상태가 아닌 경우 에러 메시지를 반환합니다.
- 좌석이 이미 예약된 경우 에러 메시지를 반환합니다.
- 좌석이 예약 가능한 경우, 좌석을 임시 예약하고 사용자에게 성공 메시지를 반환합니다.
- 좌석 임시 예약은 다른 사용자가 예약할 수 없도록 잠금 처리됩니다.

<br>

### 6. 결제
---
```mermaid
sequenceDiagram
    actor 사용자
    participant UseCase
    participant Payment
    participant Reservation
    participant Queue

    사용자->>UseCase: 결제 요청 (token, reservationId)
    UseCase->>Queue: 대기열 토큰 체크 요청

    alt 유효하지 않은 토큰(존재x, 유저의 토큰이 아님)
        Queue-->>사용자: Error "유효하지 않은 토큰입니다."
    else 유효한 토큰
        UseCase ->> Reservation: 예약 내역 확인
        alt 임시 예약 상태 아닌 경우
            Reservation -->> 사용자: "임시 예약 상태가 아닙니다."
            
        else 임시 예약 상태인 경우
            Reservation -->> UseCase: 예약 내역 반환
            UseCase ->> Payment: 결제 요청
            Payment ->> Payment: 결제 내역 저장
            UseCase ->> Reservation: 좌석 예약 확정 요청
            Reservation ->> Reservation: 좌석 예약 확정
            Reservation -->> UseCase: 좌석 예약 확정 성공
            UseCase ->> Queue: 대기열 토큰 만료
            Queue ->> Queue: 대기열 토큰 만료 처리
            Queue -->> UseCase: 대기열 토큰 만료 처리 성공
            UseCase -->> 사용자: 결제 완료 정보 반환
        end
    end

```
### Description
결제 요청에 대한 시퀀스 다이어그램입니다.
- 사용자가 대기열 토큰을 포함해 예약한 좌석을 결제 요청하면, 시스템은 해당 토큰이 유효한지 확인합니다.
- 토큰이 유효하지 않은 경우 에러 메시지를 반환합니다.
- 임시 예약 상태를 확인 요청하면, 임시 예약 상태인지 확인하여 아닌 경우 에러 메시지를 반환합니다.
- 결제 요청을 받은 시스템은 결제 완료를 합니다.
- 임시 예약 상태를 `확정` 상태로 변경합니다.
- 결제 완료 후, 대기열 토큰을 만료 처리합니다.
- 결제 완료 정보를 사용자에게 반환합니다.

<br>

<br>


