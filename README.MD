# 음악 사이트 크롤링 프로그램

### 업데이트 내역

<2020-05-11>

### 기능

- 크롤링을 쓰레드가 수행하도록 수정
- 시작 버튼 클릭 시 쓰레드가 시작되도록 구현
- `ActionListener` 이벤트 처리 함수 구현(시작 버튼),  `WindowListener` 이벤트 처리 함수 구현

<2020-05-08>

### 기능

- 지니 사이트에 대한 정보 추가
- 새로운 정보를 저장하기 전에 기존 정보를 제거하는 로직 추가
- 클래스 명 변경(`MelonChart` -> `MusicChart`)
- 기본 UI 구성(UI 클래스)
- 음원 사이트에 대한 정보를 저장하는 `MusicSiteInfo` 클래스 추가
- DB 테이블 구성 (`music_site`, `music_chart`)



<2020--05-06>

### 기능

- 초기 커밋