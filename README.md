# Phone Sales Management System

## 프로젝트 개요

LG U+ 유레카 미니 프로젝트로 개발한 **스마트폰 판매 관리 시스템**입니다. 이 프로젝트에서는 **재고 현황 조회, 주문 조회, 스마트폰 구매 및 등록** 등의 기능을 제공합니다. Java Swing을 이용하여 UI를 구성하였으며, MySQL을 활용하여 데이터베이스를 관리합니다.

https://github.com/user-attachments/assets/2516113c-4948-4654-8652-fe670fb08b43

## 기술 스택

- **언어**: Java
- **UI 프레임워크**: Swing
- **데이터베이스**: MySQL
- **데이터베이스 연동**: JDBC

## 주요 기능

### 1. 관리자 모드

- **스마트폰 재고 조회**: 현재 판매 중인 스마트폰 목록 및 재고 현황 확인, 해당 스마트폰의 상세 정보 확인
- **스마트폰 등록**: 새로운 스마트폰 정보를 데이터베이스에 추가
- **주문 내역 조회**: 사용자의 주문 기록을 확인

### 2. 일반 사용자 모드

- **회원 가입**: 새로운 사용자 등록
- **잔액 충전**: 스마트폰 구매를 위한 잔액 충전 기능
- **스마트폰 구매**: 사용 가능한 잔액 내에서 스마트폰 구매 가능

## 데이터베이스 테이블 설계

![Image](https://github.com/user-attachments/assets/de4f83e7-08ff-440a-a4d0-1f010b6911c2)

### 1. `phone`

| 필드명          | 타입          | 설명         |
| ------------ | ----------- | ---------- |
| phone\_id    | integer     | 스마트폰 고유 ID |
| model        | varchar(50) | 스마트폰 모델명   |
| brand        | varchar(50) | 제조사        |
| released\_at | date        | 출시일        |
| price        | integer     | 스마트폰 가격    |
| stock        | integer     | 재고 수량      |

### 2. `phone_detail`

| 필드명       | 타입          | 설명                         |
| --------- | ----------- | -------------------------- |
| phone\_id | integer     | 스마트폰 고유 ID (phone 테이블과 연결) |
| processor | varchar(50) | 프로세서 정보                    |
| ram       | varchar(20) | RAM 용량                     |
| storage   | varchar(20) | 저장 용량                      |
| battery   | integer     | 배터리 용량                     |
| weight    | integer     | 무게                         |

### 3. `users`

| 필드명      | 타입          | 설명        |
| -------- | ----------- | --------- |
| user\_id | integer     | 사용자 고유 ID |
| name     | varchar(40) | 사용자 이름    |
| username | varchar     | 사용자 계정 ID |
| password | varchar     | 비밀번호      |
| amount   | integer     | 계정 잔액     |

### 4. `orders`

| 필드명         | 타입      | 설명          |
| ----------- | ------- | ----------- |
| order\_id   | integer | 주문 고유 ID    |
| user\_id    | integer | 주문한 사용자 ID  |
| phone\_id   | integer | 주문한 스마트폰 ID |
| sale\_price | integer | 판매 가격       |
| order\_date | date    | 주문 날짜       |

## 설치 및 실행 방법

### 1. 데이터베이스 설정

1. MySQL을 설치하고 실행합니다.
2. `phone_sales_sotre` 데이터베이스를 생성합니다.
3. 프로젝트의 `schema.sql`을 실행하여 테이블을 생성합니다.
4. `src/common/DBManger.java` 파일에서 MySQL 접속 정보를 설정합니다.

### 2. 프로젝트 실행

1. 프로젝트를 클론합니다.
   ```sh
   git clone https://github.com/jklee3409/phone-sales-management.git
   ```
2. Java를 실행할 수 있는 환경(JDK 11 이상)을 구성합니다.
3. IDE(IntelliJ 또는 Eclipse)에서 `Main.java` 실행합니다.
