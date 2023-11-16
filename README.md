# study-circuit-breaker

## server-1

- 사용자 정보 조회
- 사용자 정보 조회 시 사용자의 잔여 포인트 내역도 조회 (server-2 호출)
- server-2 호출 시 circuit breaker 적용

## server-2

- 사용자 포인트 조회
- 테스트를 위해 특정 id 로 호출 시 강제 exception 발생

- test