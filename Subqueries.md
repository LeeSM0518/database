# Subqueries

## Overview

* 다른 SQL 문에 포함 된 쿼리이다.
* 항상 괄호로 묶인다.
* 일반적으로 포함 문 앞에 실행된다.



* **예시) 운영체제의 시수보다 큰 과목을 조회하라.**

  ```sql
  sql=> select *
  sql-> from course
  sql-> where ctime > (
  sql(> select ctime
  sql(> from course
  sql(> where cname='OS');
  
   ccode |  cname   | ctime | croom
  -------+----------+-------+-------
   C004  | Database |     4 | R004
  (1 row)
  ```

  

## Operators

* **Single Row**
  * '=', '>', '>=', '<', '<=', '<>', '!='



* **Multiple Row**
  * IN, NOT IN, ANY, ALL, EXISTS



## Types(1/2)

* **Single-Row / Multiple-Row Subquery**
  * **Single-Row** : SELECT 문장에서 오직 하나의 행을 검색하는 질의
  * **Multiple-Row** : SELECT 문장에서 하나 이상의 행을 검색하는 질의



* **Single-Column / Multiple-Column Subquery**
  * **Single-Column** : SELECT 문장에서 오직 하나의 컬럼을 검색하는 질의
  * **Multiple-Column** : SELECT 문장에서 하나 이상의 컬럼을 검색하는 질의



## Types(2/2)

* **INLINE VIEW**
  * FROM 절에서 사용하는 Subquery
  * VIEW 형태로 동작하여 INLINE VIEW라고 함



* **Noncorrelated(상관 없는) Subqueries**
  * 일반적인 Subquery
* **Correlated Subqueries**
  * 외부(밖) 쿼리의 컬럼 중 하나가 내부(Subquery)의 조건에 활용하는 방식



---

