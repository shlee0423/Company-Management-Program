# 회사 관리 프로그램 (24.09.06 16:09)

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
|login|/auth/login|GET|X|로그인 페이지
|register|/auth/register|GET|X|회원가입 페이지
|register|/auth/register|POST|X|회원가입 페이지 입력정보 받아오기
|checkAvailableId|/auth/register/{employeeId}|GET|X|아이디 중복 체크
|selectMemoByEmployeeId|/menu/memo/{employeeId}|GET|/auth/login|직원 별 메모 페이지 + 검색
|insertMemoByEmployeeId|/menu/memo/{employeeId}|POST|/menu/memo/{employeeId}|직원 별 메모 입력
|updateMemoByEmployeeIdMemoNo|/menu/memo/{employeeId}l/{memoNo}|POST|X|직원 별 메모 수정
|deteleMemoByEmployeeIdMemoNo|/menu/memo/{employeeId}/{memoNo}|DELETE|X|직원 별 메모 석제
|index|/main/index|GET|/auth/login|메인 페이지
|profile|/main/profile/{employeeId}|GET|/auth/login|프로필 페이지

### 추가해야할 API 
공지 + 챗 + 조직도 + 비품 + 예약 + 스케쥴

## 수정해야할 것
+ 공지 Service Mapper 분리
+ File 처리
+ CSS 깨짐 수정, 미디어 쿼리 추가

