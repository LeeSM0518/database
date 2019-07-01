# User Management

사용자 관리



# 데이터베이스 생성과 삭제

## 데이터베이스 생성

```mariadb
CREATE DATABASE database_name ENCODING 'encoding_type';
```

* **database_name** : 데이터베이스 식별자
* **encoding_type** : 데이터베이스 인코딩 타입으로 'UFT-8' 로 설정



**예제) exercise 와 test 데이터베이스를 생성하시오.**

```mariadb
CREATE DATABASE exercise ENCODING 'UTF-8';
CREATE DATABASE test ENCODING 'UTF-8';
```



## 데이터베이스 삭제

```mariadb
DROP DATABASE database_name;
```

* **database_name** : 데이터베이스 식별자



**예제) 'test' 데이터베이스를 제거하시오.**

```mariadb
DROP DATABASE test;
```



# 사용자 생성과 삭제

## 사용자 계정 생성

```mariadb
CREATE USER username WITH PASSWORD 'password'
```

* **username** : 사용자 계정
* **password** : 비밀번호



**예제) 다음 요구사항을 만족하도록 사용자를 등록하시오.**

* 아이디 : scott, 비밀번호 : tiger
* 아이디 : test, 비밀번호 : test1004

```mariadb
CREATE USER scott WITH PASSWORD 'tiger';
CREATE USER test WITH PASSWORD 'test1004';
```



## 사용자 계정 삭제

```mariadb
DROP USER username;
```

* **username** : 사용자 계정(아이디)



**예제) 사용자 아이디 'test'를 제거하시오.**

```mariadb
DROP USER test;
```



## 사용자 계정 권한 부여

```mariadb
GRANT <permission> ON DATABASE <database_name> TO <username>
WITH GRANT OPTION;
```

* **permission** : 사용자에게 부여할 권한 지정
* **database_name** : 데이터베이스 식별자
* **username** : 사용자 계정



**예제) 'scott' 계정에 'exercise' 데이터베이스의 모든 권한을 부여하시오.**

```mariadb
GRANT ALL ON DATABASE exercise TO scott WITH GRANT OPTION;
```

* **ALL** : 모든 권한
* **exercise** : database_name
* **scott** : username



## 데이터베이스 접속

```bash
$ psql -U username -d database_name
```

* **username** : 사용자 계정
* **database_name** : 데이터베이스 식별자



**예제) 'scott' 계정으로 'exercise' 데이터베이스에 접속하시오.**

```bash
$ psql -U scott -d exercise
```



## 사용자 계정 권환 회수

```mariadb
REVOKE <permission> ON DATABASE <database_name> FROM <username>
```

* **permission** : 사용자에게 부여할 권한 지정
* **database_name** : 데이터베이스 식별자
* **username** : 사용자 계정



**예제) 'scott' 계정에 'exercise' 데이터베이스의 모든 권한을 회수하라**

```mariadb
REVOKE ALL ON DATABASE exercise FROM scott
```















