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

- [ ] 로그인 사용자와 질문한 사람이 같은 경우 삭제는 Question 도메인에서 진행한다.
  - [x] 로그인 사용자와 질문한 사용자가 다른 경우 Exception이 발생해야 한다.
- [ ] 질문자와 답변자가 같은지 체크 Answer 도메인에서 진행한다.
  - [x] 질문자와 답변자가 다른 경우 Exception이 발생해야 한다.
- [ ] Answers 일급 컬랙션에서 모든 Answer에 대해 질문자 답변자 다른 경우를 체크한다.
- [ ] 답변의 상태를 삭제 상태로 변경한다.
- [ ] 삭제 시 삭제의 기록을 deletehistory에 저장한다.
- [ ] Question을 delete하고 delete된 결과를 DeleteHistory 객체로 반환해야 한다.
- [ ] Answer을 delete하고 delete된 결과를 DeleteHistory 객체로 반환해야 한다.