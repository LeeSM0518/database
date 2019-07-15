# Treansaction

데이터베이스의 상태를 변화시키는 하나의 논리적 기능을 수행하기 위한 작업



## Commit Mode 설정 및 확인

* **Statement Level**

```mariadb
SQL> \echo :AUTOCOMMIT
SQL> BEGIN;
SQL> CREATE TABLE COMMIT_TEST
SQL> INSERT INTO COMMIT_TEST(name) VALUES ('test');
SQL> SELECT * FROM COMMIT_TEST;
SQL> ROLLBACK;
```



* **Session Level**

```mariadb
SQL> \echo : AUTOCOMMIT
SQL> \set : AUTOCOMMIT (on/off)
```



* **Global Session Level**

```mariadb
vim %APPDATA%\Roaming\postgresql\psqlrc.conf
\set AUTOCOMMIT off
```



## Statement

* **Data Definition Language**

|      |      |
| ---- | ---- |
|      |      |
|      |      |
|      |      |



## How to run SQL Statement

* **Using Source Command with SQL Script files**



## Data Model



