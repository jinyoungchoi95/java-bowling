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
  - [ ] 유저의 이름은 blank, null이 들어올 수 없다.

### bowling

- pin
- [x] pin은 0~10 사이의 값만 들어올 수 있다.
- [x] pin number를 캐싱할 수 있다.

- score
- [x] score는 다음 pin을 저장할 수 있다.
- [x] score는 다음 pin을 저장할 수 있는지 확인할 수 있다.

- [x] 한 프레임의 score판은 NormalScore와 FinalScore로 나뉠 수 있다.
- Normal Score
- [x] NormalScore는 핀을 두번 쓰러뜨릴 수 있다.
- [x] 빈 NormalScore를 생성할 수 있다.
- [x] 첫번째 핀을 저장할 수 있다.  
- [x] 첫번째 핀이 스트라이크가 될 경우 NormalScore에서 다음 핀을 칠 수 없다.
- [x] 첫번째 핀이 스트라이크가 되지 않을 경우 남은 핀만 NormalScore에 넣을 수 있다.
- Final Score
- [x] FinalScore는 핀을 최대 3번 쓰러뜨릴 수 있다.
- [x] 첫번째 핀이 스트라이크가 되지 않을 경우 남은 핀만 FinalScore에 넣을 수 있다.
- [x] 첫번째 핀이 스트라이크가 될 경우 다음 핀을 칠 수 있다.
- [x] 첫번째 핀이 스트라이크가 될 경우 다음 핀은 10개부터 새로 칠 수 있다.
- [x] 보너스 가능 여부는 두번째 핀이 스페어이거나 스트라이크여야한다.
- [x] 두번째 핀이 스페어거나 스트라이크가 아닌데 보너스핀을 치면 Exception이 발생해야 한다.
- [x] 보너스 핀은 10개부터 새로 칠 수 있다.

- Frame
- [x] 현재 스코어 정보를 반환할 수 있다.
- [x] 현재 스코어에서 추가 저장 유무를 확인할 수 있다.
- [x] 스코어를 업데이트할 수 있다.
- [x] 다음 프레임을 반환할 수 있다.
- [x] 다음 프레임을 생성할 수 있다.

- Normal Frame
- [x] 첫번째 프레임을 생성할 수 있다.
- [x] 현재 프레임을 가지고 다음 프레임을 생성할 수 있다.
- [x] 다음 라운드가 final round일 경우 final frame을 생성해야한다.

- [x] score를 받아 저장할 수 있다.

- Final Frame
- [x] 다음 프레임을 생성할 수 없다.
- [x] 다음 프레임을 받을 수 없다.


### 볼링 게임 점수 계산법

스페어를 할 경우 다음 라운드의 점수가 현재 라운드 점수에 + 된다.

스트라이크를 할 경우 다음 두번의 라운드 점수가 현재 라운드 점수에 +된다.

단, 스트라이크의 경우 한칸만 차지하므로 스트라이크 > 스트라이크 > 스트라이크 일 경우 첫라운드는 총 30점을 차지한다.

따라서 현재 Frame의 점수를 계산하기 위해서는 다음 Frame의 정보가 필수로 필요하다.

# 🚀 3단계 - 볼링 점수판(점수 계산)

### 기능 요구사항

- **사용자 1명의 볼링 게임 점수를 관리할 수 있는 프로그램을 구현한다.**
- 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1"과 같이 출력하도록 구현한다.
  - 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  - 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  - 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
  - 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
- **스트라이크는 다음 2번의 투구까지 점수를 합산해야 한다. 스페어는 다음 1번의 투구까지 점수를 합산해야 한다.**
- 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.


## todo

### score

- [ ] score는 현재의 점수와 스트라이크, 스페어시 추가 점수 횟수를 저장할 수 있다.
- [ ] 스트라이크시 2회의 추가 점수 횟수를 저장할 수 있다.
- [ ] 스페어시 1회의 추가 점수 횟수를 저장할 수 있다.

### state

- [ ] 현재 frame을 상태를 저장할 수 있다
- [ ] pin을 받아서 다음 상태를 반환할 수 있다.
- [ ] 현재 state가 종료되었는지 확인할 수 있다.
- [ ] 현재 상태를 가지고 새로운 score를 반환할 수 있다.

#### running
- [ ] 현재 상태를 가지고 새로운 score 결과를 반환할 수 없다.
- [ ] 현재 state는 종료되지 않는다.
- ready
- [x] 핀을 가지고 다음 상태를 반환할 수 있다.
- [x] 현재 핀이 strike인 경우 `STRIKE` 상태를 반환해야 한다.
- [x] 현재 핀이 strike가 아닌 경우 `FIRST BOWLING` 상태를 반환해야 한다.
- first bowling
- [ ] 핀을 가지고 다음 상태를 반환할 수 있다.
- [ ] 현재 핀이 기존 핀과 합쳐서 spare인 경우 `SPARE`상태를 반환해야 한다.
- [ ] 현재 핀이 기존 핀과 합쳐서 spare가 아닌 경우 `MISS`상태를 반환해야 한다.

#### finished

- [ ] 현재 상태를 가지고 새로운 score 결과를 반환할 수 있다.
- [ ] 현재 state는 종료 상태이다.
- [ ] 핀이 들어와 bowling을 할 수 없다.

- miss
- strike
- spare
- [ ] 현재 state는 종료된다
- [ ] 현재 상태를 가지고 새로운 score결과를 반환할 수 있다.
- [ ] 새로운 pin을 받을 수 없다.

### Frame
- [ ] 현재 frame의 state를 저장할 수 있다.
- [ ] 현재 frame의 score를 저장할 수 있다.
- [ ] 현재 frame의 점수가 계산 가능한지 확인할 수 있다.
- [ ] 현재 frame의 점수를 계산하여 반환할 수 있다.