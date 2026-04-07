# Spring 과제 - 아기사자 관리 REST API 서버

## 🚨 과제 제출 기한
~~2026년 4월 13일 월요일 23:59~~<br>
2026년 5월 1일 금요일 23:59

## 🦁 아기사자 엔티티 필드 구성
제공된 엔티티(`BabyLion.java`)의 필드와 아래 제약사항을 확인하여 DTO와 Validation을 구성하세요.

| 필드명              | 설명   | 제약 사항 (Validation & Business Logic)               |
|:-----------------|:-----|:--------------------------------------------------|
| **id**           | 식별자  | **자동 생성**, **수정 불가**, **중복 불가** (Repository에서 관리) |
| **name**         | 이름   | 최소 **2자** ~ 최대 **12자**                            |
| **studentId**    | 학번   | **수정 불가**, **중복 불가**                              |
| **grade**        | 학년   | **1~4**의 범위를 가짐                                   |
| **email**        | 이메일  | **형식 검증** (`abc@abc.com`), **중복 불가**              |
| **phoneNumber**  | 전화번호 | **형식 검증** (`000-0000-0000`, 정규식 활용)               |
| **introduction** | 자기소개 | **Not Blank** (빈칸 및 공백 허용 안 함)                    |

### 💡 주요 유의사항
* **Immutable Fields**: `id`, `studentId`는 데이터 생성 후 수정이 불가능하도록 로직을 구성해야 합니다.
* **Unique Constraints**: `id`, `studentId`, `email` 필드는 시스템 내에서 중복된 값이 존재할 수 없습니다.
* **Validation**: DTO 단계에서 `@Size`, `@Pattern`, `@Email`, `@NotBlank` 등의 어노테이션을 적극 활용하여 데이터 무결성을 보장하세요.

## 🛠 필수 구현 API 명세
모든 API는 RESTful하게 설계해야 하며, 반환 시 'ResponseEntity'를 사용하여 적절한 상태 코드를 전달해야 합니다.

### 1. 아기사자 등록
* **Endpoint:** `POST /api/v1/babylions`
* **핵심 미션:** 등록 전용 DTO(`CreateBabyLionReqDto`) 설계 및 적용
* **세부 조건:** * `id`를 제외한 모든 필드를 포함합니다.
   * 각 필드 성격에 맞는 **Validation**을 반드시 수행합니다.

### 2. 아기사자 전체 조회 및 학년별 필터링
* **Endpoint:** `GET /api/v1/babylions`
* **핵심 미션:** 쿼리 파라미터(`Query Parameter`)를 활용한 조건부 검색
* **세부 조건:**
   * `?grade=4`와 같이 학년 파라미터가 있으면 해당 학년만 필터링하고, 없으면 전체 리스트를 반환합니다.
   * 응답 시 아기사자의 **이름(name)**과 **자기소개(introduction)** 정보만 포함합니다.
   * **Tip:** `@RequestParam(required = false)`를 활용해 보세요.

### 3. 아기사자 개별 연락처 조회
* **Endpoint:** `GET /api/v1/babylions/{id}`
* **핵심 미션:** 특정 데이터 추출 및 마스킹(Masking) 처리 연습
* **세부 조건:** 개인정보 보호를 위해 **이메일(email)**과 **전화번호(phoneNumber)** 정보만 담은 응답 DTO를 반환합니다.

### 4. 아기사자 정보 수정
* **Endpoint:** `PATCH /api/v1/babylions/{id}`
* **핵심 미션:** 수정 가능 필드만 제한하는 `UpdateBabyLionReqDto` 활용
* **세부 조건:** * 엔티티 주석에서 명시된 **'수정 불가능 필드'**는 DTO 설계에서 제외합니다.
   * 수정 가능한 필드에 대해서도 적절한 **Validation**을 적용합니다.

### 5. 아기사자 삭제 (예제 제공)
* **Endpoint:** `DELETE /api/v1/babylions/{id}`
* **설명:** 제공된 예제 코드를 참고하여 흐름을 이해하고, 나머지 API가 정상 작동하도록 연관 로직을 완성합니다.

## 🎯 과제 핵심 포인트 (필수 조건)
- 계층형 아키텍처 준수: Controller - Service - Repository 계층을 엄격히 분리하여 구현하세요.
- 기본으로 구성되어있는 Entity, Repository는 별도로 수정을 삼가하시기 바랍니다.
- DTO와 Entity의 분리: 컨트롤러에서 엔티티를 직접 반환하거나 받지 마세요. 목적에 맞는 DTO를 반드시 사용해야 합니다.
- DTO에서 Validation을 사용하여 잘못된 데이터가 오지 않도록 방지해야 합니다.
- 예외 처리 활용: 예외가 발생할 부분에서, 제공된 ErrorCode와 CustomException을 사용해 보세요. 또한 ErrorCode를 추가해서 써보도록 합니다.

## 📗 과제 제출 방법
&nbsp; 해당 설명에서 중괄호`{}` 된 부분은 예시이며 본인에게 맞게 바꿔서 작성하셔야 합니다.

**1. 해당 리포지토리 클론하기**
   ```
   git clone https://github.com/Likelion-YeungNam-Univ/14th-be-spring-assignment.git
   ```

**2. 본인 깃허브 이름으로 된 브랜치 생성 - `feature/{깃허브 이름}`**
   ```
   git checkout -b feature/{깃허브 이름}

   # 예시
   git checkout -b feature/chlwjd0803
   ```

**3. IntelliJ에서 해당 프로젝트 열기**

**4. src/ 내 프로젝트 작성**

**5. 작업 완료 후, 본인이 생성한 브랜치로 push**
   ```
   git push origin feature/{깃허브 이름}

   # 예시
   git push origin feature/chlwjd0803
   ```

**6. Pull Request 작성**
\- PR 제목: `5회차 과제 - {이름}`

**7. 코드 리뷰** - 정해준 파트너에게 코드리뷰 필수, 다른 아기사자에게는 자율

### 📝 PR 포함 내용
1. 내가 개발한 기능이나 방법(프로그램의 흐름)
2. 내가 개발할 때 유의깊게 개발한 부분
3. 개발 중 발생한 이슈 및 해결 과정
4. 리뷰어에게 전하는 말