# Views

## Overview

- **Virtual Table (가상 테이블)**
  - 데이터를 쿼리하기 위한 단순한 메커니즘 이다.
- **테이블과 달리 뷰에는 데이터 저장이 필요없다.**
  - 뷰로 인해 디스크 공간에 채워지는 것에 대해 걱정할 필요없다.



## View의 장점

- **보안** : 사용자에게 필요 없는 정보를 숨길 수 있다.
- **복잡한 질의를 간단한 명령으로 단순하게 사용할 수 있다.**
- **데이터 모델(설계)이 변경되어도 기존 질의의 변경을 최소화할 수 있다.**



## View

- **Syntax(구문)**

  ```sql
  CREATE [OR REPLACE] VIEW view_name [(column_list)]
  AS select_statement
  ```

  - **viewname** : 생성할 뷰(View) 명을 입력
  - **OR REPLACE** : 기존에 동일한 이름의 뷰 명이 있으면 다시 생성
  - **column_list** : 뷰를 구성하는 컬럼 목록을 지정



- **연습 문제) 박현주 교수님께서 강의하는 과목명, 강의 시수, 그리고 강의실을 조회하라.**

  ```sql
  sql=> select c.cname, c.ctime, c.croom
  sql-> from course c, lecture l, professor p
  sql-> where p.pcode = 'P004'
  sql-> and p.pcode = l.lpcode
  sql-> and l.lccode = c.ccode;
  
  cname   | ctime | croom
  ----------+-------+-------
   OS       |     3 | R003
   Database |     4 | R004
  (2 rows)
  ```