# 콩떡몰
<p align="center"><a href="http://beanricecakemall.store/"><img src="https://github.com/GoniGoniiiii/BeanRiceCakeMall/assets/51067529/6f6df39e-6ba5-4716-8902-7ee1fd17b6b7" width="300" height="200"></a></p>
<p align="center">로고를 누르시면 콩떡몰로 바로 이동해요!~</p>

# 목차
- [프로젝트 소개](#프로젝트-소개)
- [프로젝트 목표](#프로젝트-목표)
- [개발환경](#개발환경)
- [사용기술](#사용기술)
- [디렉토리 구조](#디렉토리-구조)
- [E-R 다이어그램](#E-R-다이어그램)
- [주요기능](#주요-기능)
- [화면구성](#화면구성)

## 프로젝트 소개
요즘은 반려동물을 가족처럼 여기는 문화가 점점 확산되고 있습니다. 이러한 변화에 따라 반려동물의 행복과 건강을 최우선 가치로 여기며, 그에 맞는 전용 제품 및 서비스 시장이 급속도로 성장하고 있습니다. 이런 시대적 흐름에 발맞추어, 반려견 '콩떡'이를 모델로 한 반려견쇼핑몰을 운영하며 신뢰할 수 있는 서비스를 제공하고자 합니다.


## 프로젝트 목표
- **반려동물과 반려인의 삶의 질 향상:** 반려동물과 함께하는 가족이 더 나은 삶의 질을 누릴 수 있도록 신중하게 선별된 고품질 제품과 서비스를 제공하는 것입니다. 이를 통해 반려동물의 건강과 행복을 증진시키고, 반려인들이 더욱 편리하고 만족스러운 삶을 즐길 수 있도록 돕고자 합니다.


## 개발환경
  <div style="margin:0 ; text-align: left;">
          <img src="https://img.shields.io/badge/IntelliJ IDea-FF5A5F?style=for-the-badge&logo=IntelliJ IDea&logoColor=white">  
          <img src="https://img.shields.io/badge/VS code-0078C0?style=for-the-badge&logo=VS Code&logoColor=white"> 
          <img src="https://img.shields.io/badge/Github-FFDF6F?style=for-the-badge&logo=Github&logoColor=white">
          <img src="https://img.shields.io/badge/Dbeaver-1997B5?style=for-the-badge&logo=DBeaver&logoColor=white">
  </div>

<br>

- IntelliJ
- Visual Studio Code
- GitHub
- DBeaver

## 사용기술

### 백엔드
<div>
  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white">
  <img src="https://img.shields.io/badge/Spring Boot-578B34?style=for-the-badge&logo=Spring Boot&logoColor=white">
  <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
  <img src="https://img.shields.io/badge/JPA-90E59A?style=for-the-badge&logo=JPA&logoColor=white">
  <img src="https://img.shields.io/badge/Lombok-9999FF?style=for-the-badge&logo=Lombok&logoColor=white">
  <img src="https://img.shields.io/badge/Oauth2-5395FD?style=for-the-badge&logo=Oauth2&logoColor=white">
</div>

<br>


- Java 17
- Spring Boot 3.2.5
- Spring Security6
- Spring Data JPA
- Lombok
- Oauth2.0

### 프론트엔드
<div>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white">
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white">
  <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white">
  <img src="https://img.shields.io/badge/Javascript-F7DF1E?style=for-the-badge&logo=Javascript&logoColor=white">
  <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=white">
</div>

<br>


- HTML5/css3
- JavaScript
- Thymeleaf
- Bootstrap 5.3.3

### 빌드 툴
<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white">

- Gradle 8.7

### 데이터베이스
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">

  
- Mysql

### 인프라
<div>
    <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon Ec2&logoColor=white">
    <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white">
</div>
<br>

- AWS EC2
- AWS RDS

## 디렉토리 구조
```plaintext
main
├── java
│   └── com
│       └── example
│           └── beanricecakemall
│               ├── BeanRiceCakeMallApplication.java
│               ├── MainController.java
│               ├── config
│               │   ├── SecurityConfig.java
│               │   └── WebConfig.java
│               ├── controller
│               │   ├── CartController.java
│               │   ├── OrderController.java
│               │   ├── ProductController.java
│               │   └── UserController.java
│               ├── customDTO
│               │   ├── CustomOAuth2User.java
│               │   ├── CustomUserDetails.java
│               │   ├── GoogleResponse.java
│               │   ├── NaverResponse.java
│               │   └── OAuth2Response.java
│               ├── dto
│               │   ├── CartDTO.java
│               │   ├── CategoryDTO.java
│               │   ├── DeliveryDTO.java
│               │   ├── OrderDTO.java
│               │   ├── ProductDTO.java
│               │   ├── ReviewDTO.java
│               │   └── UserDTO.java
│               ├── entity
│               │   ├── CartEntity.java
│               │   ├── CategoryEntity.java
│               │   ├── DeliveryEntity.java
│               │   ├── FileEntity.java
│               │   ├── OrderEntity.java
│               │   ├── OrderProductEntity.java
│               │   ├── ProductEntity.java
│               │   ├── ReviewEntity.java
│               │   └── UserEntity.java
│               ├── oauth2
│               │   ├── CustomClientRegistrationRepository.java
│               │   └── SocialClientRegistration.java
│               ├── repository
│               │   ├── CartRepository.java
│               │   ├── CategoryRepository.java
│               │   ├── DeliveryRepository.java
│               │   ├── FileRepository.java
│               │   ├── OrderProductRepository.java
│               │   ├── OrderRepository.java
│               │   ├── ProductRepository.java
│               │   ├── ReviewRepository.java
│               │   └── UserRepository.java
│               └── service
│                   ├── CartService.java
│                   ├── CategoryService.java
│                   ├── CustomOAuth2UserService.java
│                   ├── CustomUserDetailsService.java
│                   ├── DeliveryService.java
│                   ├── OrderProductService.java
│                   ├── OrderService.java
│                   ├── ProductService.java
│                   ├── ReviewService.java
│                   └── UserService.java
└── resources
    ├── application.properties
    ├── static
    │   ├── css
    │   │   ├── findId.css
    │   │   ├── findPw.css
    │   │   ├── header.css
    │   │   ├── index.css
    │   │   ├── join.css
    │   │   ├── login.css
    │   │   ├── memberInfo.css
    │   │   ├── orderList.css
    │   │   ├── orderListDetail.css
    │   │   ├── payment.css
    │   │   ├── paymentCompleted.css
    │   │   ├── paymentFailed.css
    │   │   ├── productDetail.css
    │   │   ├── productList.css
    │   │   ├── shoppingBag.css
    │   │   └── updateProduct.css
    │   ├── img
    │   │   ├── error.jpg
    │   │   ├── google.png
    │   │   ├── img1.png
    │   │   ├── img2.png
    │   │   ├── img3.png
    │   │   ├── img4.png
    │   │   ├── img5.png
    │   │   ├── img6.png
    │   │   ├── kakao.png
    │   │   ├── logo.png
    │   │   ├── logo2.png
    │   │   └── naver.png
    ├── js
    │   ├── findId.js
    │   ├── findPw.js
    │   ├── index.js
    │   ├── join.js
    │   ├── login.js
    │   ├── memberInfo.js
    │   ├── orderList.js
    │   ├── orderListDetail.js
    │   ├── payment.js
    │   ├── paymentCompleted.js
    │   ├── productDetail.js
    │   ├── productList.js
    │   ├── shoppingBag.js
    │   ├── updateProduct.js
    │   └── uploadProduct.js
    └── templates
        └── views
            ├── error.html
            ├── footer.html
            ├── header.html
            ├── index.html
            ├── admin
            │   ├── updateProduct.html
            │   └── uploadProduct.html
            ├── product
            │   ├── payment.html
            │   ├── paymentCompleted.html
            │   ├── paymentFailed.html
            │   ├── productDetail.html
            │   ├── productList.html
            │   └── shoppingBag.html
            └── user
                ├── findId.html
                ├── findPw.html
                ├── join.html
                ├── login.html
                ├── memberInfo.html
                ├── orderList.html
                └── orderListDetail.html
test
└── java
    └── com
        └── example
            └── beanricecakemall
                └── BeanRiceCakeMallApplicationTests.java
```

## E-R 다이어그램
<br>

![콩떡몰 erd2](https://github.com/user-attachments/assets/0acaa5dd-bff0-41ba-8023-ace926f6b826)


## 주요 기능
- 상품 구매
- 상품 검색
- 회원가입(로그인, 아이디찾기 , 비밀번호 찾기)
- 장바구니
- 나의 주문내역 확인
- 리뷰작성
- 소셜로그인(사업자 인증을 받지 못해 현재는 불가능합니다ㅠ.ㅠ)

## 화면구성
<table>
  <tr>
    <td align="center">메인 페이지</td>
    <td align="center">상품 상세 페이지</td>
    <td align="center">장바구니</td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/7e3c3486-5a32-4c73-ab05-554b10a53e01" width="336" height="275"></td>
    <td><img src="https://github.com/user-attachments/assets/c5fe9040-33b6-42ad-bcae-d2ea7ef2c5ef" width="336" height="275"></td>
    <td><img src="https://github.com/user-attachments/assets/afc9f40e-e7ee-44bd-82a5-773bb58b4c7b" width="336" height="275"></td>
  </tr>  
   <tr>
    <td align="center">로그인</td>
    <td align="center">회원가입</td>
    <td align="center">ID.PW찾기</td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/0eba6e7d-59e4-457b-9fd5-e18c09aaed5f" width="336" height="275"></td>
    <td><img src="https://github.com/user-attachments/assets/706a1293-10e5-4a92-bd70-6a329a3656fe" width="336" height="275"></td>
    <td><img src="https://github.com/user-attachments/assets/ed376c92-4884-4cb0-96b0-cddce05b4676" width="336" height="275"></td>
  </tr>  
   <tr>
    <td align="center">주문 페이지</td>
    <td align="center">주문 내역</td>
    <td align="center">주문 상세</td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/32f0a1c0-0e62-4b07-b9cc-6632cfb119b9" width="336" height="275"></td>
    <td><img src="https://github.com/user-attachments/assets/edfd0d7e-4e38-46ed-acb7-bcf48a17c957" width="336" height="275"></td>
    <td><img src="https://github.com/user-attachments/assets/ce665b44-4407-4a18-8631-c457755df15b" width="336" height="275"></td>
  </tr>  
   <tr>
    <td align="center">리뷰 작성</td>
    <td align="center">검색</td>
    <td align="center">마이페이지</td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/ca945287-5426-47b5-96db-941358b3a2c9" width="336" height="275"></td>
    <td><img src="https://github.com/user-attachments/assets/1cbf272e-c8c2-4fe3-9855-e8d3a60caea7" width="336" height="275"></td>
    <td><img src="https://github.com/user-attachments/assets/fd56b835-09b2-4a7d-a128-9b70336378d8" width="336" height="275"></td>
</tr>  
</table>
