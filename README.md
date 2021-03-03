# spring-is-powerful
Spring(Boot)의 기반을 탄탄히 다지는 개인 프로젝트

# 개발 순서 

1. ERD 설계
1. Table 생성
1. Entity 생성
    - enumClass 적용
1. Repository 생성
1. Repository Test
    - Table과 Entity의 mapping 확인
1. 연관관계 설정
    - OneToMany, ManyToOne 설정
1. request, response format 설정
    - Header와 Data의 분리
1. CRUD 개발
    - Controller > Service > repository > DB Access

# DB schemas

| Table | 의미 | 관계 |
|:------|:----|:----|
| admin_user | 관리자 | - |
| user | 사용자 | user : order_group = 1 : N |
| order_group | 주문 묶음 | order_group : user = N : 1<br> order_group : order_detail = 1 : N |
| order_detail | 개별 주문 | order_detail : order_group = N : 1<br> order_detail : item = N : 1 |
| item | 상품 | item : order_detail = 1 : N<br> item : partner = N : 1 |
| partner | 공급자 | partner : item = 1 : N<br> partner : category = N : 1|
| category | 카테고리 | category : partner = 1 : N |

![spring-is-powerful-DB-schema](/assets/images/spring-is-powerful-DB-schema.jpg) 

## Class Diagram

- CrudInterface는 CRUD 추상 메서드를 제공합니다.
- CrudController는 CRUD 메서드를 구현합니다.
    - 모든 Controller에서 기본적인 CRUD의 중복을 막기 위함입니다.
    - CrudController의 CRUD 메서드는 BaseService의 CRUD를 각각 호출합니다.
- BaseService를 상속받은 ItemApiLogicservice는 CRUD를 구현합니다.
- ItemApiController에 메서드가 추가된다면 ItemApiLogicService에서 로직을 처리합니다.

![class-diagram](/assets/images/class-diagram.jpg) 

# Swagger Example

![swagger-basic-crud](/assets/images/swagger-basic-crud.jpg) 

# 고려사항

- 객체지향설계 5대 원칙
    1. 단일 책임 원칙 (Single Responsiblity Principle)
        - 모든 클래스는 각각 하나의 책임만 가져야 한다.
    2. 개방-폐쇄 원칙 (Open Closed Principle) 
        - 확장에는 열려있고 수정에는 닫혀있다.
        - 추가 기능 개발을 기존 코드 수정 없이 진행가능하도록 한다.
    3. 리스코프 치환 원칙 (Liskov Substitution Principle)
        - 자식 클래스가 언제나 부모 클래스를 대체할 수 있다.
    4. 인터페이스 분리 원칙 (Interface Segregation Principle)
        - 한 클래스는 자신이 사용하지 않는 인터페이스는 구현하지 않는다.
    5. 의존 역전 원칙 (Dependency Inversion Principle)
        - 의존 관계를 맺을 때 일반 클래스보다 인터페이스 또는 추상 클래스를 대상으로 한다. 