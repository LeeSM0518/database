# JOIN

관계형 데이터베이스 관리 시스템(RDBMS)의 핵심 요소



## About Join

- **About JOIN**
  - 관계형 데이터베이스(Relational Database, RDB)의 꽃
  - Join을 명확하게 이해하기 위해서는 관계형 데이터베이스에 대한 이해가 필요



- **조인이 왜 필요한가?**
  - 관계형 데이터베이스의 구조적 특징으로 말미암아 의미 있는 데이터의 집합으로 테이블이 구성되고, 각 테이블끼리는 관계(Relationshop)를 가짐 **(관계형 데이터베이스는 저장 공간의 효율성과 확장성이 향상)**
  - 서로 관계 있는 데이터가 여러 테이블로 나뉘어 저장되므로, 각 테이블에 저장된 데이터를 효과적으로 검색하기 위해 방법이 필요 **(각 테이블 간 의미 있는 데이터(행)를 연결하는 데 활용되는 메커니즘)**



## Key Point

- **SQL 문장의 의미를 제대로 파악하라.**
  - 잘못 사용하면 심각한 문제를 야기한다.
- **조인 조건을 명확하게 제공해야 한다.**
  - CROSS JOIN(Cartesian product)이 발생할 가능성이 있음
- **조인을 적용한 후 반드시 테스트를 수행하여 검증하라.**



## Cartesian product(1/2)

- **조인(Join)에 참여한 테이블들의 모든 데이터가 RETURN**
  - **발생 이유**
    - Join 조건이 잘못된 경우
    - Join 조건을 정의하지 않았을 때
    - 첫 번째 테이블의 모든 행과 두 번째 테이블의 모든 행이 조인되는 경우
  - **해결 방법**
    - 테이블 개수가 N개라면, 적어도 N-1개의 JOIN 조건을 질의 안에 포함해야 한다.



## Cartesian product(2/2)

- **Cartesian product example**

<img src="/Users/sangminlee/database/capture/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202019-08-03%20%EC%98%A4%ED%9B%84%204.43.25.png" width=600>



## 내부 조인 Inner Join, EQUI JOIN

- **가장 일반적인 조인 형태**
- **둘 이상의 테이블에 존재하는 공통 컬럼의 값이 같은 것을 결과로 추출**

- **예시) Inner Join, Equi Join**

  Inner Join

  ```sql
  SELECT *
  FROM A INNER JOIN B
  ON A.ID = B.ID;
  ```

  Equi Join

  ```sql
  SELECT *
  FROM A, B
  WHERE A.ID = B.ID
  ```



## 연습 문제

- **교수코드 P004의 교수이름, 블로그, 홈페이지, 페이스북을 조회하라.**

  ```sql
  sql=> select p.pname, pf.blog, pf.homepage, pf.facebook
  sql-> from professor p, prof_website pf
  sql-> where p.pcode = 'P004'
  sql-> and p.pcode = pf.pwcode;
  
  pname  |             blog             |           homepage           |       facebook
  --------+------------------------------+------------------------------+-----------------------
   박현주 | http://blog.hanbat.ac.kr/phj | http://home.hanbat.ac.kr/phj | http://www.fb.com/phj
  (1 row)
  ```



## Outer Join(1/4)

- **OUTER JOIN**

  - **LEFT OUTER JOIN(⟕)** 

    : 오른쪽 테이블에 조인할 컬럼의 값이 없는 경우에 사용함

  - **RIGHT OUTER JOIN(⟖)**

    : 왼쪽 테이블에 조인할 컬럼의 값이 없는 경우에 사용함

  - **FULL OUTER JOIN(⟗)**

    : 양쪽 테이블 모두 OUTER JOIN이 필요할 때 사용함



## Outer Join(2/4)

- **LEFT OUTER JOIN**

<img src="/Users/sangminlee/database/capture/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202019-08-03%20%EC%98%A4%ED%9B%84%205.04.50.png" width=600>



## Outer Join(3/4)

- **RIGHT OUTER JOIN**

<img src="/Users/sangminlee/database/capture/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202019-08-03%20%EC%98%A4%ED%9B%84%205.06.09.png" width=600>



## Outer Join(4/4)

- **FULL OUTER JOIN**

<img src="/Users/sangminlee/database/capture/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202019-08-03%20%EC%98%A4%ED%9B%84%205.06.50.png" width=600>



# 