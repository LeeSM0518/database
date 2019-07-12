# Database Tutorial

# Mac OS

## Step 01. 설치

* **설치**
  * brew install postgresql



* **구성**
  * brew services start postgresql
  * pg_ctl -D /usr/local/var/postgres start



## Step 02. 설정

* **기본 사용자 추가**

  * /usr/local/Cellar/postgresql/11.2/bin/createuser –s postgres

* **환경 설정**

  * /usr/local/var/postgres/postgresql.conf 

    listen_addresses = '*' 

  * /usr/local/var/postgres/pg_hba.conf
    host all all 0.0.0.0/0 md5 # ipv4 area



## Step 03. 실행

psql –U postgres –d postgres

## 목차
1. [사용자 관리](https://github.com/LeeSM0518/database/blob/master/UserManagement.md)
2. [테이블 관리](https://github.com/LeeSM0518/database/blob/master/TableManagement.md)
