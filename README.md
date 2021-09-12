# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

# 🚀 1단계 - 질문 삭제하기 기능 리팩토링

## default

- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와답변글의모든답변자같은경우삭제가가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
  한다.
- 질문자와답변자가다른경우답변을삭제할수없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## programming default

- qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.

## todo

### question

## todo

### question

- [x] 로그인 사용자와 질문한 사람이 같은 경우 삭제는 Question 도메인에서 진행한다.
  - [x] 로그인 사용자와 질문한 사용자가 다른 경우 Exception이 발생해야 한다.
- [x] 질문자와 답변자가 같은지 체크 Answer 도메인에서 진행한다.
  - [x] 질문자와 답변자가 다른 경우 Exception이 발생해야 한다.
- [x] Answers 일급 컬랙션에서 모든 Answer에 대해 질문자 답변자 다른 경우를 체크한다.
- [x] 답변의 상태를 삭제 상태로 변경한다.
- [x] 삭제 시 삭제의 기록을 deletehistory에 저장한다.
- [x] Question을 delete하고 delete된 결과를 DeleteHistory 객체로 반환해야 한다.
- [x] Answer을 delete하고 delete된 결과를 DeleteHistory 객체로 반환해야 한다.

# 🚀 2단계 - 볼링 점수판(그리기)

## default

#### 기능 요구사항

- 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. **1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.**
- 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
  - 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  - 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  - 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
  - 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
- 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

## todo

### user

- 유저의 이름을 받을 수 있다.
  - [x] 유저의 이름은 영어가 들어와야 한다.
  - [x] 유저의 이름은 3글자 이하로 들어오지 않으면 Exception이 발생해야 한다.
  - [x] 유저의 이름은 blank, null이 들어올 수 없다.

### bowling

- 볼링을 던지면 몇개가 맞췄는지 확인할 수 있다.
  - [ ] 볼링을 던졌을 때 0 ~ 10개의 핀을 쓰러뜨릴 수 있다.
- 볼링 라운드 정보를 저장할 수 있다.
  - [ ] 첫번째 프레임에서 스트라이크를 던질 경우 해당 프레임이 종료된다.
  - [ ] 두번째 프레임에서 핀이 남을 경우 한번 더 프레임을 진행할 수 있다.
- pin
  - [x] 첫번째 결과와 두번째 결과에 대한 핀의 개수를 저장할 수 있다.
  - [x] 핀은 0~10사이의 수만 저장할 수 있다.
  - [x] 두번째 핀 저장시 첫번째 핀이 쓰러뜨리고 남은 범위의 수만 저장할 수 있다.
- 프레임 정보를 저장할 수 있다
  - [ ] 프레임에는 pin번호가 저장되어 있어야 한다.
  - [ ] 1~9 프레임은 NomalFrame, 10 프레임은 FinalFrame으로 따로 구현되어야 한다.
  - [ ] 현재 프레임에서 다음 프레임을 만들 수 있다.
  - [ ] 이전 프레임 정보를 저장할 수 있다.

