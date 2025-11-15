# ERD 설계



<img width="1910" height="636" alt="ERD_kr" src="https://github.com/user-attachments/assets/20e483b1-0186-4dc5-a868-9f56d01d956e" />

### 도메인 흐름도
**1. 콘서트 등록**
- `concert`에 콘서트 기본 정보 저장합니다.
- `venue`에 공연장 정보 저장합니다.

**2. 회차 & 좌석 구성**
- `concert_session`에 각 콘서트의 날짜/공연장 회차 생성합니다.
- `seat`에 공연장별 기본 좌석 정보 저장합니다.
- 회차별 판매 좌석은 `concert_session_seat`에 저장합니다.

**3. 예약 & 좌석 선택**
- 유저(`user`)가 콘서트 회차(`concert_session`)를 선택합니다.
- 특정 좌석들(`concsert_session_seat`)을 선택하여 예약(`reservation`) 생성합니다.
- 예약별 죄석 목록은 `reserved_seat`에 저장합니다.

**4. 결제**
- `payment`에 결제 내역 저장합니다.

**5. 이벤트 발행**
- 예약/결제 변경을 outbox에 기록합니다.

