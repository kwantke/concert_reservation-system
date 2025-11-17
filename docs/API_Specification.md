# 콘서트 예매 시스템 API 명세

## API 문서 (Swagger)

<img width="900" height="700" alt="swagger_image" src="https://github.com/user-attachments/assets/c81fc01f-aac6-4127-9108-1473344c4c4b" />


## API 상세
1. [대기열 토큰 발급](#1-대기열-토큰-발급)
2. [대기열 정보 조회](#2-대기열-정보-조회)
3. [콘서트 회차 조회](#3-콘서트-회차-조회)
4. [콘서트 회차 좌석 조회](#4-콘서트-회차-좌석-조회)
5. [콘서트 회차 좌석 예약](#5-콘서트-회차-좌석-예약)
6. [결제](#6-결제)

## 1. 대기열 토큰 발급
---

### Description
- 사용자 대기열 토큰을 발급합니다.

### Request
- **Method**: POST
- **URL**: `/api/v1/waiting-queues`
- **Headers**:
  - `Content-Type`: application/json
- **Request Body**:
  ```json
  {
    "userId": 1
  }
  ```
### Response
- **Status Code**: 201
- **Response Body**:
  ```json
  {
    "userId": 1,
    "token": "95946a42-4949-418a-8577-8c78a22d942f",
    "createdAt": "2025-11-16 10:00:00"
  }
  ```

<br>

## 2. 대기열 정보 조회

---
### Description
- 사용자의 대기열 정보를 조회합니다.

### Request
- **Method**: GET
- **URL**: `/api/v1/waiting-queues`
- **Header**:
  - `Content-Type`: application/json
  - `QUEUE-TOKEN`: String (대기열 토큰)
- **Response Body**:
  ```json
  {
    "userId": 1,
    "status": "대기",
    "waitingNum": 100
  }
  ```

### Error
- 유효하지 않는 토큰일 경우
  - **Status Code**: 400
 
  
## 3. 콘서트 회차 조회

---
### Description
- 특정 콘서트의 예약 가능한 일정을 조회합니다.

### Request
- **Method**: GET
- **URL**: `/api/v1/concerts/{concertId}/sessions`
- **Header**:
  - `Content-Type`: application/json
  - `QUEUE-TOKEN`: String (대기열 토큰)
- **Path Params**:
  - `concertId`: Long (콘서트 ID)
### Response
- **Status Code**: 200
- **Response Body**:
  ```json
  [
    {
      "sessionId": 1,
      "startDate": "2025-12-15T13:00:00"
    },
    {
      "sessionId": 2,
      "startDate": "2025-12-16T17:00:00"
    }
  ]
  ```
  
### Error
- 유효하지 않는 토큰일 경우
  - **Status Code**: 400

<br>

## 4. 콘서트 회차 좌석 조회

---
### Description
- 특정 콘서트의 좌석 목록을 조회합니다.

### Request
- **Method**: GET
- **URL**: `/api/v1/concerts/sessions/{concertSessionId}/seats`
- **Header**:
  - `Content-Type`: application/json
  - `QUEUE-TOKEN`: String (대기열 토큰)
- **Path Params**:
  - `concertSessionId`: Long (콘서트 회차 ID)

### Response
- **Status Code**: 200
- **Response Body**:
  ```json
  [
    {
      "id": 1,
      "concertSessionId": 1,
      "seatId": 1,
      "seatNum": "A1",
      "reserved": false
    },
    {
      "id": 2,
      "concertSessionId": 1,
      "seatId": 2,
      "seatNum": "A2",
      "reserved": false
    },
    {
      "id": 3,
      "concertSessionId": 1,
      "seatId": 3,
      "seatNum": "A3",
      "reserved": true
    }
  ]
  ```
  
### Error
- 유효하지 않는 토큰일 경우
  - **Status Code**: 400

<br>

## 5. 콘서트 회차 좌석 예약

---
### Description
- 사용자가 특정 콘서트의 좌석을 예약합니다.

### Request
- **Method**: POST
- **URL**: `/api/v1/concerts/session/reservations`
- **Header**:
  - `Content-Type`: application/json
  - `QUEUE-TOKEN`: String (대기열 토큰)
- **Request Body**:
  ```json
  {
    "concertSessionId": 1,
    "userId": 1,
    "seatIds": [1,2]
  }
  ```
### Response
- **Status Code**: 200
- **Response Body**:
  ```json
  {
    "concertSessionId": 1,
    "userId": 1,
    "totalPrice": 4000,
    "status": "임시 예약",
    "reservedSeats": [
      {
        "SeatNum": "A1",
        "seatPrice": 2000
      },
      {
        "SeatNum": "A2",
        "seatPrice": 2000
      }
    ]
  }
  ```
  
### Error
- 유효하지 않는 토큰일 경우
  - **Status Code**: 400
- 예약 가능한 좌석이 아닐 경우
  - **Status Code**: 400

<br>

## 6. 결제

---
### Description
- 예약에 대한 결제를 진행합니다.
- 임시 예약인 상태일때만 결제가 가능합니다.
- 결제가 완료되면 대기열 토큰을 만료시킵니다.

### Request
- **Method**: POST
- **URL**: `/api/v1/payments`
- **Header**:
  - `Content-Type`: application/json
  - `QUEUE-TOKEN`: String (대기열 토큰)
- **Request Body**:
  ```json
  {
    "reservationId": 20,
    "userId": 1
  }
  ``` 
### Response
- **Status Code**: 201

### Error
- 유효하지 않는 토큰일 경우
  - **Status Code**: 400
- 예약 가능한 좌석이 아닐 경우
  - **Status Code**: 400
  
