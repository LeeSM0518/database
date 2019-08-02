# Aggregation & Grouping

**집합과 그룹화**



## Aggregation

* **집합 기능들**

| 함수  |         내용          |
| :---: | :-------------------: |
|  AVG  |  열의 평균값을 계산   |
| COUNT | 테이블의 행 수를 결정 |
|  MAX  |  열의 최대 값을 결정  |
|  MIN  |  열의 최소값을 결정   |
|  SUM  |  열의 값 합계를 계산  |



* **예시**

  ```sql
  sql=> SELECT * FROM COURSE ;
  
   ccode |  cname   | ctime | croom
  -------+----------+-------+-------
   C001  | TCP/IP   |     3 | R001
   C002  | HTML     |     3 | R002
   C003  | OS       |     3 | R003
   C004  | Database |     4 | R004
   C005  | Java     |     3 | Lab1
   C006  | C        |     3 | Lab1
  (6 rows)
  
  sql=> SELECT SUM(CTIME), MIN(CTIME), MAX(CTIME), COUNT(CTIME), AVG(CTIME) FROM COURSE;
  
   sum | min | max | count |        avg
  -----+-----+-----+-------+--------------------
    19 |   3 |   4 |     6 | 3.1666666666666667
  (1 row)
  
  sql=> SELECT SUM(CTIME), MIN(CTIME), MAX(CTIME), COUNT(CTIME), ROUND(AVG(CTIME), 2) FROM COURSE;
  
   sum | min | max | count | round
  -----+-----+-----+-------+-------
    19 |   3 |   4 |     6 |  3.17
  (1 row)
  ```



## Grouping

* 공통된 특징을 공유하는 그룹을 만든다.
* SELECT 명령의 계산은 전체 그룹에 대해 수행된다.



* **예시**

  ```sql
  sql=> SELECT * FROM PROFESSOR;
  
   pcode | pname  |     pdept      |  pphone
  -------+--------+----------------+----------
   P002  | 김정호 | 컴퓨터공학과   | 821-1102
   P003  | 이창석 | 전파공학과     | 821-1201
   P004  | 박현주 | 전파공학과     | 821-1202
   P005  | 김응규 | 정보통신공학과 | 821-1301
   P006  | 김은기 | 정보통신공학과 | 821-1302
   P001  | 안기홍 | 컴퓨터공학과   | 821-1101
  (6 rows)
  
  sql=> SELECT PDEPT, COUNT(*)
  sql-> FROM PROFESSOR
  sql-> GROUP BY PDEPT;
  
       pdept      | count
  ----------------+-------
   전파공학과        |     2
   컴퓨터공학과				|     2
   정보통신공학과			|     2
  (3 rows)
  ```



## Practice problem

* **DEVICE ID 별로 수집된 데이터의 개수, 총합 평균을 조회**

  ```sql
  sql=> select dd_device_id, count(dd_device_data), sum(dd_device_data), round(avg(dd_device_data), 2)
  sql-> from device_data
  sql-> group by dd_device_id
  sql-> order by dd_device_id;
  
   dd_device_id | count | sum | round
  --------------+-------+-----+-------
   11111        |    11 | 149 | 13.55
   22222        |    11 | 273 | 24.82
   33333        |    11 | 387 | 35.18
   44444        |    11 | 501 | 45.55
   55555        |    11 | 605 | 55.00
  (5 rows)
  ```



* **C501, C503 에서 수집된 데이터를 장치 아이디 별로 합계와 평균을 조회**

  ```sql
  sql=> select dd.dd_device_id, count(dd.dd_device_id), sum(dd.dd_device_data), round(avg(dd.dd_device_data), 2)
  sql-> from device d, device_data dd
  sql-> where d.device_id = dd.dd_device_id
  sql-> and d.device_loc in ('C501', 'C503')
  sql-> group by dd.dd_device_id;
  
   dd_device_id | count | sum | round
  --------------+-------+-----+-------
   11111        |    11 | 149 | 13.55
   33333        |    11 | 387 | 35.18
  (2 rows)
  ```



* **장치 아이디 별로 수집한 데이터의 총합이 280 이상인 디바이스ID, 디바이스 위치, 데이터의 총합과 평균을 조회**

  ```sql
  sql=> select dd.dd_device_id, d.device_loc, sum(dd.dd_device_data), round(avg(dd.dd_device_data), 2)                                  
  sql-> from device d, device_data dd
  sql-> where d.device_id = dd.dd_device_id
  sql-> group by dd.dd_device_id, d.device_loc
  sql-> having sum(dd.dd_device_data) >= 280
  sql-> order by dd.dd_device_id;
  
   dd_device_id | device_loc | sum | round
  --------------+------------+-----+-------
   33333        | C503       | 387 | 35.18
   44444        | C504       | 501 | 45.55
   55555        | C505       | 605 | 55.00
  (3 rows)
  ```



## Distinct

* 중복 행을 제거할 때 사용
* count() 함수를 사용하여 각 그룹의 구성원 수를 결정한다.



* **예시)**

  ```sql
  sql=> select distinct dd_device_id from device_data;
  
  dd_device_id
  --------------
   44444
   33333
   22222
   11111
   55555
  (5 rows)
  ```



## NULL

* 누락 된 정보 및 적용 할 수없는 정보이다.
* 데이터 값이 존재하지 않는다는 것을 지시하는데 사용하는 특별한 표시어



* **예시**

  ```sql
  sql=> select null = null, null <> null, 1 = null, 1 <> null, 1 < null, 1 > null;
  
   ?column? | ?column? | ?column? | ?column? | ?column? | ?column?
  ----------+----------+----------+----------+----------+----------
            |          |          |          |          |
  (1 row)
  
  sql=> select null is null, 1 is null, 1 is not null;
  
   ?column? | ?column? | ?column?
  ----------+----------+----------
   t        | f        | t
  (1 row)
  ```