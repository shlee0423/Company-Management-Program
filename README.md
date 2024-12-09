# 회사 관리 프로그램 (24.12.09)

## 프로젝트 소개
회사에서 사용하는 업무들을 하나의 웹 페이지에서 다 처리할 수 있도록 하고자 만든 웹 프로그램입니다. Spring Boot를 활용하여 웹 페이지를 제작하였습니다.


## 개발 기간
+ 2024.06.03 ~ 2024.07.19
+ 06.03 ~ 06.07 피그마를 활용한 프론트 엔드 프로토타입 디자인, ERD 설계
+ 06.10 ~ 06.14 HTML, CSS, JS, Spring 요청 구현
+ 06.17 ~ 06.28 기능 구현
+ 07.01 ~ 07.05 오류 해결, 리펙터링 테스팅
+ 07.15 ~ 07.19 Merge 및 최종 수정 후 배포


## 사용된 기술
HTML5, CSS, JS,
Spring Boot, Mybatis, MySQL, NCloud Platform, Docker


## 팀원
+ 황예슬 (팀장, BE/FE)
+ 김동희 (팀원, BE/FE)
+ 이승훈 (팀원, BE/FE)
+ 김동환 (팀원, BE/FE)



## 관련 링크

피그마 

<https://www.figma.com/design/TGvM2dImT8bcUV7OS5wBHJ/Team_Project_Company?node-id=0-1&m=dev&t=BMU8BYz8rVJELotL-1>

DB 설계

<https://www.erdcloud.com/p/qMrgPjHM5Fo5P7bBw>


## 기능 구현
+ 로그인 및 회원가입 : Security를 이용한 폼 로그인 구현
+ 조직도 : 사내 조직을 직관적으로 볼 수 있는 조직도 구현
+ 비품 관리 : 사내 비품에 대한 CRUD 기능 구현
+ 물품 대여 : 사내 도서, 노트북과 같은 물품에 대한 대여 및 예약 기능 구현
+ 캘린더 : 회사 일정 및 개인 일정을 공유할 수 있는 캘린더 구현
+ 게시판 : 부서별 공지 기능을 할 수 있는 게시판 구현

## 미기능 구현
+ 공유 드라이브
+ 네비게이터
+ 채팅

## API 명세
|name|Path|Method|Redirect|Info|
|:----:|:----:|:----:|:----:|:--------:|
|getManageProduct|/product|GET|X|비품 관리 페이지
|getRegisterProduct|/product/register_product|GET|X|비품 등록 페이지
|postRegisterProduct|/product/register_product|POST|product/manage_product|비품 등록
|getProduct|/product/{productNo}|GET|X|비품 확인 페이지
|postUpdateProduct|/product/{productNo}|POST|/product|비품 수정
|deleteProduct|/product|DELETE|X|비품 삭제
|getCalendar|/schedule|GET|X|일정 관리 페이지
|getSchedules|/schedule/calendar/{employeeId}|GET|X|개인 일정 불러오기
|postInsertSchedule|/schedule|POST|X|일정 추가
|getSchedule|/schedule/{no}|GET|X|일정 확인
|updateSchedule|/schedule/{no}|PUT|X|일정 수정
|deleteSchedule|/schedule/{no}|DELETE|X|일정 삭제

### 추가해야할 API 
공지 + 챗 + 조직도 + 예약

