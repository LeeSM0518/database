# Table Management

테이블 관리



# Command Line Interface

## 데이터베이스 보기

**시스템에 등록된 데이터베이스 보기**

* PSQL Console

  ```mariadb
  SQL> \list
  ```

* Others

  ```mariadb
  SELECT pg_database.datname, pg_user.usename
    FROM pg_database, pg_user
   WHERE pg_database.datdba = pg_user.usesysid;
  ```

  * pg_database, pg_user

    : PgSQL의 시스템 카탈로그



## 데이터베이스 접속

**psql 모드에서 데이터베이스에 접속하기**

```mariadb
SQL> \c database_name
```

* **database_name** : 데이터베이스 식별자



# 테이블 (생성, 수정, 삭제)

## 테이블이란?

* 테이블은 관계형 데이터베이스의 기본적인 데이터 저장 단위이다.
* 사용자가 접근 가능한 모든 데이터를 보유하며, 행(row)과 열(column)로 구성된다.

<img src="./capture/스크린샷 2019-07-01 오후 8.50.50.png">



## 테이블 생성하기 (1/2)

```mariadb
CREATE TABLE [IF NOT EXISTS] table_name (<column-definition>);
```

* **table_name** : 테이블 식별자
* **IF NOT EXISTS** : 기존에 동일한 이름의 테이블 이름이 있으면 생성하지 않음
* **cloumn-definition** : 테이블을 구성하는 컬럼 이름과 컬럼 도메인 정보 등을 지정



## 테이블 생성하기 (2/2)

```mariadb
<column_name> <datatype>
		[NOT NULL | NULL]
		[CHECK <expression>]
		[DEFAULT <default_value>]
		[UNIQUE [KEY] | [PRIMARY] KEY]
```

* **NOT NULL | NULL** : NULL 값 허용 지정
* **CHECK** : 컬럼의 제약조건 지정
* **DEFAULT** : 컬럼의 기본값 지정
* **UNIQUE KEY | PRIMARY KEY** : 유일 값, 기본 키 지정



## PostgreSQL 데이터 타입

* **숫자형**
  * INT(Small, Big), REAL, Double Precision, Numeric, Decimal, BIT, AND ETC
* **문자형**
  * CHAR, VARCHAR, TEXT, AND ETC
* **날짜형**
  * DATE, TIME, TIMESTAMP, AND ETC
* **기타형**
  * BOOLEAN, SERIAL(Small, Big), JSON, JSONB, AND ETC



**예제) 'exercise' 데이터베이스에 'student' 테이블을 생성하라.**

```
우리 조직의 학생은 학번(id), 성명(kname, 문자 4자리), 생년월일(birthday, 날짜)를 입력하고, 기본 키는 학번으로 지정한다. 단, 학번과 성명은 반드시 입력해야 한다.
```

```mariadb
CEATE TABLE student (
	id INT NOT NULL PRIMARY KEY,
  kname CHAR(4) NOT NULL,
  birthday DATE
);
```



## 테이블 목록 보기

**시스템에 등록된 테이블의 목록 보기**

* PSQL Console

  ```mariadb
  SQL> \dt
  ```

* Others

  ```mariadb
  SELECT *
  FROM pg_tables
  WHERE tableowner = 'scott';	
  ```

  * **username** : 사용자 계정 식별자
  * **pg_tables** : 데이터베이스의 시스템 카탈로그



## 테이블 구조 보기

**시스템에 등록된 테이블의 구조 보기**

* PSQL Console

  ```mariadb
  SQL> \d table_name
  ```

* Others

  ```mariadb
  SELECT column_name, data_type, is_nullable, column_default
    FROM information_schema.columns
   WHERE table_name = 'table_name';
  ```

  * **column_name, table_name** : 컬럼과 테이블 식별자
  * **information_schema** : 데이터베이스의 시스템 카탈로그



## 테이블 수정하기

```mariadb
ALTER TABLE [IF EXISTS] table_name (<column-definition>);
```

* **table_name** : 변경할 테이블 이름을 입력
* **IF EXISTS** : 기존에 동일한 이름의 테이블 이름이 없으면 변경하지 않음
* **column-definition** : 테이블을 구성하는 컬럼 이름과 컬럼 도메인 정보 등을 지정



## 테이블 컬럼 추가하기

```mariadb
ADD COLUMN <column_name> <column_definition> [column_constraint]
```

* **column_name** : 추가할 컬럼 이름 지정
* **column_definition** : 컬럼의 도메인 등을 지정
* **column_constraint** : 컬럼의 제약조건 등을 지정



## 테이블 컬럼 수정하기 (1/2)

```mariadb
RENAME COLUMN <old_name> TO <new_name>
```

* **old_name** : 변경해야 할 컬럼 이름 지정
* **new_name** : 새롭게 변경하는 컬럼 이름 지정



## 테이블 컬럼 수정하기 (2/2)

```mariadb
ALTER COLUMN <column_name>
PGSQL_COMMAN {<column_definition>, [column_constraint]}
```

* **column_name** : 수정할 컬럼 이름 지정
* **column_definition** : 컬럼의 도메인 등을 지정
* **column_constraint** : 컬럼의 제약조건 등을 지정



## 테이블 컬럼 삭제하기

```mariadb
DROP COLUMN <column_name>
```

* **column_name** : 삭제할 컬럼 이름 지정



## 연습 문제

* **'student' 테이블에 성별(gender, varchar(1))을 추가하라.**

  ```mariadb
  ALTER TABLE student
  ADD COLUMN gender varchar(1);
  ```

* **'student' 테이블의 'kname'을 'name'으로 변경하고, 성별 속성을 varchar(2)로 변경하라.**

  ```mariadb
  ALTER TABLE student
  RENAME COLUMN kname TO name;
  ```

  ```mariadb
  ALTER TABLE student
  ALTER COLUMN name TYPE varchar(2);
  ```

* **'student' 테이블에서 성별을 제거하라.**

  ```mariadb
  ALTER TABLE student
  DROP COLUMN gender;
  ```

  

# 테이블 (등록, 조회, 수정, 삭제)

## 데이터 삽입

```mariadb
INSERT INTO <table_name> [(<column_name>[, column_name, ...])]
VALUES ({expression | DEFAULT}, ...) [, (...), ...];
```

* **table_name** : 데이터를 삽입할 테이블 지정
* **VALUES** : 삽입할 데이터 값
* **expression** : 컬럼에 입력할 값을 나열하며, 기본 값을 사용하고 싶을 때에는 DEFAULT 키워드 사용



**예제) 'exercise' 데이터베이스의 'student' 테이블에 다음 정보를 입력하시오.**

* 20110101, 홍길동, 1990-03-01
* 20110201, 일지매, 1991-02-28
* 20120301, 황진이, 1991-02-28

```mariadb
exercise=> \d student
                      Table "public.student"
  Column  |         Type          | Collation | Nullable | Default
----------+-----------------------+-----------+----------+---------
 id       | integer               |           | not null |
 birthday | date                  |           |          |
 name     | character varying(10) |           | not null |
Indexes:
    "student_pkey" PRIMARY KEY, btree (id)
    
INSERT INTO student VALUES
(20110101, '1990-03-01', '홍길동'),
(20110201, '1991-02-28', '일지매'),
(20120301, '1991-02-28', '황진이');
```

> **\d student** 를 통해서 데이터 입력 순서 확인



## 데이터 수정

```mariadb
UPDATE <table_name> SET column_name={expression | DEFAULT}
										[, columnmn_name={expression | DEFAULT}] ...
[WHERE <query_condition>]
```

* **table_name** : 갱신을 수행할 대상 테이블 지정
* **column_name = {expression | DEFAULT}** : 해당 컬럼의 값을 표현식으로 변경하거나 기본 값으로 변경
* **query_condition** : 변경하고자 하는 테이블 내의 대상을 지정하기 위해 사용하므로 매우 중요



**예제) 황진이의 학번을 '20110301' 로 변경하시오.**

```mariadb
UPDATE student SET id = '20110301' WHERE name = '황진이';
```

> SET <변경할 데이터> WHERE <변경 대상>



**예제) 일지매의 생일을 '1991-02-28' 에서 '1991-01-31' 로 변경하시오.**

```mariadb
UPDATE student SET brithday = '1991-01-31' WHERE name = '일지매';
```



## 데이터 삭제

```mariadb
DELETE FROM <table_name> [WHERE <query_condition>];
```

* **table_name** : 테이블 내용을 삭제할 대상 테이블 이름을 입력
* **query_condition** : 이 조건을 생략하면, 테이블의 전체 내용을 제거. 삭제하고자 하는 테이블 내의 대상을 지정하기 위해 사용하므로 매우 중요



## 데이터 조회

```mariadb
SELECT <target_column> FROM <table_name>
[WHERE <query_condition>]
[ORDER BY <column_name>];
```

* **target_column** : 조회할 컬럼 리스트
* **table_name** : 조회할 대상 테이블
* **query_condition** : 조회하고자 하는 테이블 내의 대상을 지정



## 연습 문제

* **'student' 테이블에 등록된 모든 학생을 조회하라.**

  ```mariadb
  SELECT * FROM student;
  ```

* **생일이 1990년 04월 이후에 출생한 학생을 조회하라**

  ```mariadb
  SELECT * FROM student WHERE birthday>'1990-04-30';
  ```

* **'student' 테이블에 등록된 모든 학생을 조회하라. 단, 이름순으로 정렬하여 조회하라.**

  ```mariadb
  SELECT * FROM student ORDER BY name;
  ```



# Simple Client Program with Java

```java
import java.sql.*;

public class tutorial.SampleCode {
  public static void main(final String... args) {
    try {
      Connection conn = DriverManager.getConnection
          ("jdbc:postgresql://localhost:5432/exercise", "scott", "tiger");
      Statement stmt;
      stmt = conn.createStatement();
      ResultSet rs;
      rs = stmt.executeQuery("SELECT * FROM STUDENT");
      if (stmt.execute("SELECT * FROM STUDENT")) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        System.out.print("[학번] " + rs.getString(1) + " || ");
        System.out.print("[이름] " + rs.getString(2) + " || ");
        System.out.println("[생일] " + rs.getString(3));
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException sqex) {
      System.out.println("SQLException: " + sqex.getMessage());
      System.out.println("SQLState: " + sqex.getSQLState());
    }
  }
}
```

**실행 결과**

```
[학번] 20110101 || [이름] 1990-03-01 || [생일] 홍길동
[학번] 20110301 || [이름] 1991-02-28 || [생일] 황진이
[학번] 20110201 || [이름] 1991-01-31 || [생일] 일지매
```

