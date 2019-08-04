# Set Theory

## INTERSECTION

- **집합**

  <img src="/Users/sangminlee/database/capture/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202019-08-03%20%EC%98%A4%ED%9B%84%205.08.31.png" width=300>

  > 두 집합 A와 B가 있을 때, A와 B에 모두 속하는 원소의 집합



- **Query Expression**

  ```sql
  SELECT *
  FROM A INNER JOIN B
  ON A.ID = B.ID;
  ```

- **예시**

  ```sql
  sql=> select professor.pcode, lecture.lccode, professor.pname
  sql-> from professor inner join lecture
  sql-> on professor.pcode = lecture.lpcode;
  
   pcode | lccode | pname
  -------+--------+--------
   P002  | C002   | 김정호
   P002  | C006   | 김정호
   P003  | C005   | 이창석
   P004  | C003   | 박현주
   P004  | C004   | 박현주
   P006  | C001   | 김은기
  (6 rows)
  ```

  

## UNION

- **집합**

  <img src="/Users/sangminlee/database/capture/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202019-08-03%20%EC%98%A4%ED%9B%84%205.14.34.png" width=300>

  > 두 집합 A와 B가 있을 때, A에 속하거나 B에 속하는 원소의 집합 **두 집합의 중복값을 제거함**



- **Query Expression**

  ```sql
  SELECT *
  FROM A FULL OUTER JOIN B
  ON A.ID = B.ID;
  ```

- **예시**

  ```sql
  sql=> select *
  sql-> from professor full outer join lecture
  sql-> on professor.pcode = lecture.lpcode;
  
   pcode | pname  |     pdept      |  pphone  | lpcode | lccode
  -------+--------+----------------+----------+--------+--------
   P002  | 김정호 | 컴퓨터공학과   | 821-1102 | P002   | C002
   P002  | 김정호 | 컴퓨터공학과   | 821-1102 | P002   | C006
   P003  | 이창석 | 전파공학과     | 821-1201 | P003   | C005
   P004  | 박현주 | 전파공학과     | 821-1202 | P004   | C003
   P004  | 박현주sql | 전파공학과     | 821-1202 | P004   | C004
   P006  | 김은기 | 정보통신공학과 | 821-1302 | P006   | C001
   P001  | 안기홍 | 컴퓨터공학과   | 821-1101 |        |
   P005  | 김응규 | 정보통신공학과 | 821-1301 |        |
  (8 rows)
  ```



## Relative Complement

- **집합**

  <img src="/Users/sangminlee/database/capture/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202019-08-03%20%EC%98%A4%ED%9B%84%206.42.53.png" width=300>

  > 집합에 포함되면서 다른 집합에 포함되지 않는 원소들의 집합. **어떤 집합의 원소에서 다른 집합의 원소를 뺀 집합.**



- **Query Expression**

  ```sql
  (A) 교집합 (B 여집합)
  SELECT *
  FROM A LEFT OUTER JOIN B
  ON A.ID = B.ID
  WHERE B.ID IS NULL;
  
  (A 여집합) 교집합 (B)
  SELECT *
  FROM A RIGHT OUTER JOIN B
  ON A.ID = B.ID
  WHERE A.ID IS NULL;
  ```

- **예시**

  ```sql
  (A) 교집합 (B 여집합)
  sql=> select *
  sql-> from student left outer join register
  sql-> on student.scode = register.rscode
  sql-> where register.rscode is null;
  
   scode | sname  |    sdept     |  sphone  | rscode | rccode
  -------+--------+--------------+----------+--------+--------
   S001  | 이정혁 | 전파공학과   | 123-4567 |        |
   S002  | 오창세 | 전파공학과   | 234-5678 |        |
   S003  | 오라클 | 컴퓨터공학과 | 345-6789 |        |
   S004  | 전현식 | 컴퓨터공학과 | 456-7890 |        |
  (4 rows)
  
  (A 여집합) 교집합 (B)
  sql=> select *
  sql-> from student right outer join register
  sql-> on student.scode = register.rscode
  sql-> where student.scode is null;
  
   scode | sname | sdept | sphone | rscode | rccode
  -------+-------+-------+--------+--------+--------
  (0 rows)
  ```



## Symmetric difference

- **집합**

  <img src="/Users/sangminlee/database/capture/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202019-08-04%20%EC%98%A4%ED%9B%84%204.31.06.png" width=300>

  > 두 집합 A와 B 둘 중 하나에 포함되지만, 두 집합 모두에 포함되지 않는 원소들의 모임으로 간단하게, 두 차집합의 합집합이라고 볼 수 있다.



- **Query Expression**

  ```sql
  SELECT *
  FROM A FULL OUTER JOIN B
  ON A.ID = B.ID
  WHERE A.ID IS NULL OR B.ID IS NULL;
  ```

- **예시**

  ```sql
  sql=> select *
  sql-> from advise full outer join professor
  sql-> on advise.apcode = professor.pcode
  sql-> where advise.apcode is null or professor.pcode is null;
  
   apcode | ascode | pcode | pname  |     pdept      |  pphone
  --------+--------+-------+--------+----------------+----------
          |        | P005  | 김응규 | 정보통신공학과 | 821-1301
          |        | P004  | 박현주 | 전파공학과     | 821-1202
  (2 rows)
  ```

  

# Set Operation

## UNION

- **UNION**
  - 집합의 합집합을 추출
  - 중복을 제거함
- **UNION ALL**
  - 집합의 합집합을 추출
  - 중복을 제거하지 않음

- **예시**

  ```sql
  SELECT 1 NUM UNION [ALL]
  SELECT 2 UNION [ALL]
  SELECT 2;
  ```



## INTERSECT, EXCEPT

- **INTERSECT**

  - 집합의 교집합을 추출

    ```sql
    SELECT 1 NUM INTERSECT SELECT 1;
    ```

- **EXCEPT**

  - 집합의 차집합을 추출

    ```sql
    SELECT 1 NUM EXCEPT SELECT 1;
    ```