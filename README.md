# 🎨 [취미 모임 관리 웹사이트](https://www.notion.so/teamsparta/21-5f3e6a5d16e84de48916ea9904b4fc91)
​
같은 취미를 가진 사람들과 모임을 쉽게 만들고 관리할 수 있는 웹사이트입니다. 사용자는 모임을 생성하고, 멤버를 관리하며, 실시간 소통과 일정 관리를 할 수 있습니다.
​
---
## 📑 목차
-   [개요](#-개요)
-   [배경](#-배경)
-   [프로젝트 멤버 및 기간](#-프로젝트-멤버-및-기간)
-   [SWAGGER 사용법](#-swagger-사용)
-   [API 명세서](#api-명세서)
-   [ERD](#erd)
-   [인프라 설계도](#인프라-설계도)
-   [와이어프레임](#와이어프레임)
-   [핵심 기능](#-핵심-기능)
-   [추가 기능](#-추가-기능)
-   [기술 스택](#-기술-스택)
-   [핵심 기능](#-핵심-기능)
-   [트러블 슈팅](#-트러블-슈팅)
    ​
---
### 🔍 개요
​
이 웹사이트는 취미를 중심으로 모임을 쉽게 생성하고 관리할 수 있는 플랫폼입니다. 사용자는 다음과 같은 기능을 이용할 수 있습니다:
​
-   **모임 정보 입력**
-   **회원 모집**
-   **실시간 소통**
-   **일정 및 모임 관리**
    ​
### 🌟 배경

사람들의 관심사가 다양해지면서 비슷한 관심을 가진 사람들과 경험을 공유하려는 욕구가 커지고 있습니다. 하지만 취미 모임을 직접 조직하고 관리하는 일은 홍보, 소통, 일정 관리 등 여러 면에서 어려움을 겪을 수 있습니다. 이 웹사이트은 이러한 과정을 간소화하는 데 목적이 있습니다.


---
### 🤗 프로젝트 멤버 및 기간

2024-10-21 ~ 2024-11-22

송민지/조은형/김정현/고결/변영덕

---
### 💨 SWAGGER 사용

#### [Swagger URL](http://localhost:8080/swagger-ui/index.html)

---
### ⚙️ 핵심 기능
1.  **🔐 회원가입 및 로그인**
    -   **이메일 회원가입**: 이메일을 통해 회원가입
    -   **카카오 회원가입**: 카카오를 통한 회원가입
    -   **로그인**: 이메일 또는 카카오를 통한 로그인
    -   **회원 탈퇴**: 비밀번호 입력 후 소프트 딜리트
2.  **👤 프로필 관리 (CUD)**
    -   프로필 정보 조회 (이미지, 닉네임)
    -   프로필 이미지 등록
    -   프로필 이미지 및 닉네임 변경
3.  **🎉 모임 관리 (CRUD)**
    -   **생성, 수정, 삭제**: 소규모 취미 모임 생성 및 수정, 삭제
    -   **카테고리별 조회**: 선택한 카테고리별로 모임 조회
    -   **해시태그 조회**: 해시태그를 통해 모임 검색
4.  **📝 공지(게시판) 관리 (CUD)**
    -   게시판에서 공지 작성, 수정, 삭제
5.  **💬 댓글 관리 (CUD)**
    -   게시글에 댓글 작성, 수정, 삭제
6.  **🏷️ 해시태그 (CD)**
    -   콘텐츠를 분류할 수 있는 태그 생성 및 삭제
        ​
---
### 🚀 추가 기능

### 1️⃣ 위치 기반 모임 추천 서비스
- **추천 알고리즘**:  
  사용자의 위치를 기준으로 **반경 10km 이내**의 모임을 추천합니다.  
  프론트에서 사용자의 위치를 서버로 전송하면, 서버는 **하버사인(Haversine) 공식**을 통해 거리를 계산해 반경 내 모임을 리스트로 제공합니다.

- **확장성 고려**:  
  추후 **더 넓은 거리 추천**을 위해 확장성이 높은 하버사인 공식을 채택하였습니다.

- **모임 랭킹**:
캐시 기반 랭킹 시스템으로 생성이 많이 된 지역 순으로 랭킹 확인을 할 수 있습니다.

### 2️⃣ 실시간 채팅 서비스
- **데이터 흐름**:  
  동일한 채팅방에 입장한 유저들의 대화 내용은 ChatController → ChatService 경로를 거쳐 **MySQL**에 저장됩니다. 이후 **Redis Publisher** 기능을 통해 대화 내용이 Redis 메모리에 저장되며, **Subscriber**들에게 실시간으로 전달됩니다.



---
### [API 명세서](https://www.notion.so/teamsparta/21-5f3e6a5d16e84de48916ea9904b4fc91)


### 유저

| Method | Endpoint                 | Description | Authorization | Request Body                                                                                                  | Response                                                                                                                                                                                              |
|--------|--------------------------|-------------|---------------|---------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST   | `/api/users/signup`      | 회원가입        | None          | `{ "email": "test@example.com", "nickName": "홍길동", "password": "password123!A", "identityProvider": "NONE" }` | `{ "statusCode": 200, "message": "회원가입이 완료되었습니다. 로그인 화면으로 이동합니다.", "data": null }`                                                                                                                    |
| POST   | `/api/auth/login`        | 로그인         | None          | `{ "email": "test@example.com", "password": "password123!A" }`                                                | `Authorization: Bearer <Token>`                                                                                                                                                                       |
| PATCH  | `/api/users/me/delete`   | 회원 탈퇴       | Bearer Token  | None                                                                                                          | `{ "statusCode": 200, "message": "회원 탈퇴가 완료되었습니다. 이용해 주셔서 감사합니다.", "data": null }`                                                                                                                    |
| PATCH  | `/api/users/me/password` | 비밀번호 변경     | Bearer Token  | `{ "oldPassword": "password123!A", "newPassword": "password456@B" }`                                          | `{ "statusCode": 200, "message": "비밀번호 변경이 성공적으로 완료되었습니다.", "data": null }`                                                                                                                           |
| PATCH  | `/api/users/me/nickname` | 닉네임 변경      | Bearer Token  | `{ "newNickName": "김길동" }`                                                                                    | `{ "statusCode": 200, "message": "닉네임 변경이 성공적으로 완료되었습니다.", "data": null }`                                                                                                                            |
| GET    | `/api/users/me/profile`  | 본인 프로필 조회   | Bearer Token  | None                                                                                                          | `{ "statusCode": 200, "message": "프로필 조회가 성공적으로 완료되었습니다.", "data": { "id": "10520c8d-ac1f-4a40-8bc0-70e42854a532", "email": "test@example.com", "nickName": "홍길동", "profileImage": "https://…주소" } }` |

---

### 댓글

| Method | Endpoint                              | Description | Authorization | Request Body             | Response |
|--------|---------------------------------------|-------------|---------------|--------------------------|----------|
| POST   | `/api/schedule/{scheduleId}/comments` | 댓글 생성       | Bearer Token  | `{ "comment": "댓글 내용" }` | -        |
| PATCH  | `/api/comments/{commentId}`           | 댓글 수정       | Bearer Token  | `{ "comment": "댓글 내용" }` | -        |
| DELETE | `/api/comments/{commentId}`           | 댓글 삭제       | Bearer Token  | None                     | -        |
| GET    | `/api/comment/`                       | 댓글 조회       | None          | None                     | -        |

---

### 공지(게시판)

| Method | Endpoint                                | Description | Authorization | Request Body                                            | Response |
|--------|-----------------------------------------|-------------|---------------|---------------------------------------------------------|----------|
| POST   | `/api/{gatheringsId}/boards`            | 보드 생성       | Bearer Token  | `{ "title": "제목", "contents": "내용" }`                   | -        |
| PATCH  | `/api/{gatheringsId}/boards/{boardsId}` | 보드 수정       | Bearer Token  | `{ "boardsId": "id", "title": "제목", "contents": "내용" }` | -        |
| DELETE | `/api/{gatheringsId}/boards/{boardsId}` | 보드 삭제       | Bearer Token  | `{ "boardsId": "id", "isDeleted": true }`               | -        |

---

### 일정

| Method | Endpoint                                      | Description | Authorization | Request Body                                               | Response |
|--------|-----------------------------------------------|-------------|---------------|------------------------------------------------------------|----------|
| POST   | `/api/{gatheringsId}/schedules`               | 일정 생성       | Bearer Token  | `{ "title": "제목", "contents": "내용" }`                      | -        |
| PATCH  | `/api/{gatheringsId}/schedules/{schedulesId}` | 일정 수정       | Bearer Token  | `{ "schedulesId": "id", "title": "제목", "contents": "내용" }` | -        |
| DELETE | `/api/{gatheringsId}/schedules/{schedulesId}` | 일정 삭제       | Bearer Token  | `{ "schedulesId": "id", "isDeleted": true }`               | -        |

---

### 모임

| Method | Endpoint                  | Description | Authorization | Request Body            | Response                                          |
|--------|---------------------------|-------------|---------------|-------------------------|---------------------------------------------------|
| POST   | `/api/gathers`            | 모임 생성       | Bearer Token  | `{ "title": "모임1" }`    | -                                                 |
| GET    | `/api/gathers`            | 모임 보기       | Bearer Token  | None                    | `[ { "title": "모임1" }, { "title": "모임2" }, ... ]` |
| PATCH  | `/api/gathers/{gatherId}` | 모임 내용 수정    | Bearer Token  | `{ "title": "모임1 수정" }` | -                                                 |
| DELETE | `/api/gathers/{gatherId}` | 모임 삭제       | Bearer Token  | None                    | -                                                 |

---

### 멤버

| Method | Endpoint                             | Description | Authorization | Request Body | Response |
|--------|--------------------------------------|-------------|---------------|--------------|----------|
| GET    | `/api/members/{gatherId}`            | 멤버 조회       | Bearer Token  | None         | -        |
| POST   | `/api/member/{userId}/{gatherId}`    | 멤버 가입 신청    | Bearer Token  | None         | -        |
| PATCH  | `/api/member/{memberId}/{gatherId}`  | 멤버 가입 승인    | Bearer Token  | None         | -        |
| PATCH  | `/api/members/{memberId}/{gatherId}` | 멤버 가입 거절    | Bearer Token  | None         | -        |
| DELETE | `/api/members/{memberId}`            | 멤버 탈퇴       | Bearer Token  | None         | -        |

---

### 카테고리

| Method | Endpoint                       | Description | Authorization | Request Body               | Response                                                                                 |
|--------|--------------------------------|-------------|---------------|----------------------------|------------------------------------------------------------------------------------------|
| POST   | `/api/categories`              | 카테고리 생성     | Bearer Token  | `{ "categoryName": "운동" }` | -                                                                                        |
| GET    | `/api/categories`              | 카테고리 조회     | None          | None                       | `[ { "categoryId": "", "categoryName": "" }, { "categoryId": "", "categoryName": "" } ]` |
| PATCH  | `/api/categories/{categoryId}` | 카테고리 수정     | Bearer Token  | `{ "categoryName": "운동" }` | -                                                                                        |
| DELETE | `/api/categories/{categoryId}` | 카테고리 삭제     | Bearer Token  | None                       | -                                                                                        |

---

### 해시태그

| Method | Endpoint                                            | Description | Authorization | Request Body              | Response                                                                                                            |
|--------|-----------------------------------------------------|-------------|---------------|---------------------------|---------------------------------------------------------------------------------------------------------------------|
| POST   | `/api/gatherings/{gatheringId}/hashtags`            | 해시태그 생성     | Bearer Token  | `{ "hashTagName": "풋살" }` | -                                                                                                                   |
| GET    | `/api/gathering/{gatheringId}/hashtags`             | 해시태그 조회     | Bearer Token  | None                      | `{ "gatherId": "", "hashTag": [ { "hashTagId": "", "hashTagName": "" }, { "hashTagId": "", "hashTagName": "" } ] }` |
| DELETE | `/api/gathering/{gatheringId}/hashtags/{hashtagId}` | 해시태그 삭제     | Bearer Token  | None                      | -                                                                                                                   |

---

### 프로필 이미지 

| Method | Endpoint                           | Description | Authorization | Request Body | Response |
|--------|------------------------------------|-------------|---------------|--------------|----------|
| PATCH  | `/api/user/{userId}/profile-image` | 이미지 수정      | Bearer Token  | file         | -        |
| GET    | `/api/user/{userId}/profile-image` | 이미지 조회      | Bearer Token  | None         | -        |
| DELETE | `/api/user/{userId}/profile-image` | 이미지 삭제      | Bearer Token  | None         | -        |

---
### [ERD](https://www.notion.so/teamsparta/21-5f3e6a5d16e84de48916ea9904b4fc91)
![final_proj](https://github.com/user-attachments/assets/97a7ef45-5aa3-4f9e-b95c-9937605e7044)

---
### [인프라 설계도](https://www.notion.so/teamsparta/21-5f3e6a5d16e84de48916ea9904b4fc91)
<img width="816" alt="gathering_A" src="https://github.com/user-attachments/assets/dcda6316-4ba8-46ac-83c5-8a024ad0e27e">


- **CI/CD 파이프라인**:  
  GitHub Actions를 이용해 GitHub에 코드가 병합되면 **Docker Hub**에 Docker 이미지가 자동으로 업로드됩니다. 해당 이미지를 **Amazon EC2** 인스턴스에 배포하여 서버를 구동합니다.

- **Database**:  
  메인 데이터베이스로 **MySQL**을 사용하며, **실시간 채팅, 랭킹, 이메일 인증 서비스**는 Redis로 처리합니다.

- **SSL 인증**:  
  SSL 인증서를 통해 클라이언트와 **안전한 통신**을 제공합니다.

---
### [와이어프레임](https://www.notion.so/teamsparta/21-5f3e6a5d16e84de48916ea9904b4fc91)
![final_project](https://github.com/user-attachments/assets/22b8469f-c568-4686-99fc-e5fa25bf5c65)


---

### 🛠️ 기술 스택
![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) 
![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) 
![](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![](https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white)
![](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![](https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)
![](https://img.shields.io/badge/Kakao-FFCD00?style=for-the-badge&logo=Kakao&logoColor=white)
![](https://img.shields.io/badge/Amazon_AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![](https://img.shields.io/badge/redis-%23DD0031.svg?&style=for-the-badge&logo=redis&logoColor=white)
![](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white) 
![](https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white)
![](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
​
---
### 🔔 핵심 기능

### 1️⃣ 위치 기반 모임 추천 서비스

- **기능 설명**  
사용자의 위치를 기준으로 **반경 10km 이내**의 모임을 추천합니다.

- **구현 방법**:  
  사용자의 **위도와 경도** 정보를 받아 서버에서 **하버사인(Haversine) 공식**을 사용해 거리 계산을 수행합니다.

<img width="389" alt="스크린샷 2024-11-06 오후 1 18 13" src="https://github.com/user-attachments/assets/fe573d87-3599-4a1e-8a9e-b3d005e4b9b9">


- 하버사인 공식은 정확한 거리 계산이 가능해 추후 더 넓은 거리 추천에도 유연하게 대응할 수 있습니다.

###  2️⃣ 실시간 채팅 서비스

- **기능 설명**  
동일한 채팅방에 입장한 유저들이 실시간 대화를 주고받을 수 있습니다.

- **구현 방법**:
사용자가 채팅 메시지를 전송하면 ChatController에서 수신.
ChatService에서 메시지를 MySQL에 저장.
Redis Publisher로 메시지를 Redis 메모리에 저장.

<img width="371" alt="스크린샷 2024-11-06 오후 1 18 26" src="https://github.com/user-attachments/assets/19feae42-8697-432b-bfeb-821fd5392610">

- Redis는 빠른 인메모리 데이터 처리와 Pub/Sub 모델을 통해 실시간 메시지 전송을 지원하며, 대규모 트래픽을 효과적으로 분산할 수 있습니다.

---
### 💥 트러블 슈팅

###  1️⃣ GatherResponse 내부 클래스 적용

기존에는 `GatherResponse` 클래스에서 `boardInfos`와 `scheduleInfos`에 개별 데이터(예: `id`, `title`)를 필드로 사용하였습니다. 이 방식은 다음과 같은 문제를 발생시켰습니다:

1. **데이터 일관성 문제**:  
   각 필드를 개별적으로 가져올 때 일관성이 떨어지거나 중복되는 문제가 발생할 수 있습니다.

2. **코드 유지보수성 저하**:  
   관련 필드를 추가하거나 수정할 때 코드가 분산되면서 유지보수가 어려워졌습니다.


#### 해결 방안: Inner Class 적용
이를 해결하기 위해, `GatherResponse` 내부에 **`BoardInfo`**와 **`ScheduleInfo`**라는 내부 클래스를 생성하였습니다. 이를 통해 데이터 일관성을 보장하고, 관련 데이터를 객체로 묶어 관리할 수 있게 되었습니다.

<img width="814" alt="스크린샷 2024-11-06 오후 12 49 23" src="https://github.com/user-attachments/assets/36651312-718c-465b-930a-70d8b0612dd1">

BoardInfo와 ScheduleInfo 객체로 데이터를 일관성 있게 관리하며, 추후 확장이나 수정이 용이해졌습니다.

###  2️⃣ Redis ZSET Operation을 활용한 서버 부하 최적화

### 기존 구조의 문제점

기존 시스템에서는 **MySQL**에서 시/군/구 단위로 모임 데이터를 가져와 정렬하고, 이를 스케줄링 작업(`@Scheduled`)으로 클라이언트에 제공하였습니다. 이 방식은 다음과 같은 문제가 발생했습니다:

1. **서버 부하 증가**: 스케줄링 작업이 많아질수록 데이터베이스 부하가 증가하여 성능이 저하될 수 있었습니다.
2. **비효율적인 데이터 정렬**: 시/군/구 데이터를 직접 집계 및 정렬하는 작업이 반복되면서 **처리 속도가 저하**되었습니다.

### 개선 방안: Redis ZSET Operation 사용

**Redis ZSET(정렬된 집합) 구조**를 활용하여 효율적으로 데이터를 관리하고 클라이언트에 제공하도록 개선하였습니다.

- **ZSET 특징**: ZSET은 각 데이터에 고유한 `score` 값을 할당하여 자동으로 정렬된 순서를 유지합니다. 이를 통해 추가적인 정렬 작업 없이도 **순위를 쉽게 확인**할 수 있습니다.

### 새로운 데이터 흐름

1. **데이터 집계 및 스케줄링 작업 간소화**:
   Redis ZSET을 사용하여 별도의 스케줄링 작업 없이도 **정렬된 데이터**를 실시간으로 관리할 수 있습니다.

2. **트래픽 분산**:
   Redis의 고속 데이터 처리 특성을 이용해 MySQL 대신 Redis에서 순위를 관리함으로써 **서버 부하를 효과적으로 분산**시킬 수 있었습니다.

3. **구조도**:
   아래의 구조도는 새로운 Redis ZSET Operation을 통해 서버 부하를 최적화한 구조를 보여줍니다.

<img width="818" alt="스크린샷 2024-11-06 오후 1 09 55" src="https://github.com/user-attachments/assets/d034e32b-2d2f-4c30-b081-0a74a1f1b33c">

- **상단**: 기존 MySQL 기반 데이터 흐름 (비효율적인 집계 방식)
- **하단**: Redis ZSET Operation 적용 후 데이터 흐름 (최적화된 집계 방식)

  **설명**: MySQL에서 직접 데이터 정렬 및 집계하던 작업을 Redis ZSET으로 옮겨, 서버 부하를 줄이고 실시간 데이터 접근이 가능하도록 최적화하였습니다.
