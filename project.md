# Project

## Table

* **부서(department)**

  ```sql
  create table department (
  dept_code varchar primary key,
  dept_name varchar,
  dept_loc varchar
  );
  ```

* **연예관계자 직급(emp_role)**

  ```sql
  create table emp_role (
  emp_rcode varchar primary key,
  emp_rname varchar
  );
  ```

* **영화(movie)**

  ```sql
  create table movie(
  mov_code varchar primary key,
  mov_name varchar,
  mov_mpaa varchar,
  mov_pddate date,
  mov_opdate date
  );
  ```

* **드라마(drama)**

  ```sql
  create table drama(
  drm_code varchar primary key,
  drm_name varchar,
  drm_prd varchar,
  drm_brd varchar,
  drm_opdate date
  );
  ```

* **음반(music)**

  ```sql
  create table music(
  msc_code varchar primary key,
  msc_name varchar,
  msc_date date,
  msc_price int,
  msc_csf varchar
  );
  ```

* **연예관계자(employee)**

  ```sql
  create table employee(
  emp_code varchar primary key,
  emp_name varchar,
  emp_mgt varchar,
  emp_sal int,
  emp_rcode varchar not null,
  constraint emp_rcode_fk
  foreign key (emp_rcode)
  references emp_role(emp_rcode)
  );
  ```

* **소속부서(rel_department)**

  ```sql
  create table rel_department(
  rd_emp_code varchar not null,
  rd_dept_code varchar not null,
  primary key(rd_emp_code, rd_dept_code),
  constraint rd_emp_code_fk
  foreign key (rd_emp_code)
  references employee(emp_code),
  constraint rd_dept_code_fk
  foreign key (rd_dept_code)
  references department(dept_code)
  );
  ```

* **영화출연(part_movie)**

  ```sql
  create table part_movie(
  pm_mov_code varchar,
  emp_code varchar,
  pm_emp_role varchar,
  pm_mov_fee int,
  constraint emp_code_fk
  foreign key (emp_code)
  references employee(emp_code),
  constraint pm_mov_code_fk
  foreign key (pm_mov_code)
  references movie(mov_code)
  );
  ```

* **드라마출연(part_drama)**

  ```sql
  create table part_drama(
  pd_drm_code varchar references drama(drm_code),
  pd_emp_code varchar references employee(emp_code),
  pd_emp_role varchar,
  pd_emp_fee int
  );
  ```

* **음반참여(part_music)**

  ```sql
  create table part_music(
  pm_msc_code varchar references music(msc_code),
  pm_emp_code varchar references employee(emp_code),
  pm_emp_role varchar,
  pm_emp_fee int
  );
  ```



## Data

* **부서(department)**

  ```sql
  insert into department values
  ('D001', '배우', '서울특별시'),
  ('D002', '뮤지컬배우', '서울특별시'),
  ('D003', '가수(솔로)', '서울특별시'),
  ('D004', '가수(그룹)', '서울특별시'),
  ('D005', '코미디언', '서울특별시'),
  ('D101', '드라마제작', '서울특별시'),
  ('D102', '영화제작', '대전광역시'),
  ('D103', '음반제작', '수원시'),
  ('D104', '예능제작', '서울특별시'),
  ('D201', '스태프', null),
  ('D301', '임원', '서울특별시');
  
  insert into department values
  (array['D001', 'D002'], '배우, 뮤지컬배우'),
  (array['D001', 'D003'], '배우, 가수(솔로)');
  ```

* **연예관계자 직급(emp_role)**

  ```sql
  insert into emp_role values
  ('R001', '엔터테이너'),
  ('R002', '국장'),
  ('R003', '실장'),
  ('R004', '대리'),
  ('R005', '사원'),
  ('R006', '이사'),
  ('R007', '사장');
  ```

* **영화(movie)**

  ```sql
  insert into movie values
  ('MOV01', '모래가 흐르는 바다', 'A', '2013.01.01', '2013.01.01'),
  ('MOV02', '프랜드', '18', '2013.01.15', '2013.01.15'),
  ('MOV03', '5급 공무원', '15', '2013.02.01', '2013.02.01'),
  ('MOV04', '사람', '18', '2013.02.01', '2013.02.01'),
  ('MOV05', '킬러', '18', '2013.02.08', '2013.02.08'),
  ('MOV06', '스토커', '18', '2013.02.28', '2013.02.28'),
  ('MOV07', '더 울버린', '15', '2013.07.25', null),
  ('MOV08', '여름', '15', '2013.07.31', null),
  ('MOV09', '봄', 'A', '2013.03.01', '2013.03.01'),
  ('MOV10', '저스트 어 이어', '12', '2013.05.01', null);
  ```

* **드라마(drama)**

  ```sql
  insert into drama values
  ('DRM01', '왕의 게임', 'TG', 'SBC', '2013.01.01'),
  ('DRM02', '아이러시', 'SN', 'KBC', '2013.01.01'),
  ('DRM03', '야킹', 'TG', 'SBC', '2013.02.01'),
  ('DRM04', '닥터 호', 'HNU-E', 'MBS', '2013.02.01'),
  ('DRM05', '5급 사무관', 'SN', 'MBS', '2013.02.15'),
  ('DRM06', '그 사람', 'XTS', 'XTS', '2013.02.15'),
  ('DRM07', '여왕의 꿈', 'HNU-E', 'KBC', '2013.03.15'),
  ('DRM08', '머니의 화신', 'TG', 'SBC', '2013.03.15'),
  ('DRM09', '회사의 신', 'SN', 'KBC', null),
  ('DRM10', '수의사', 'HNU-E', 'XTS', null);
  ```

* **음반(music)**

  ```sql
  insert into music values
  ('MSC01', '소년시대 2013', '2013.01.01', 8000, '싱글'),
  ('MSC02', '하이퍼주니어 4집', '2013.01.05', 15500, '정규'),
  ('MSC03', '이승모 연인', '2013.01.31', 7000, '싱글'),
  ('MSC04', '박장현 사랑', '2013.02.01', 7000, '싱글'),
  ('MSC05', '김건훈 5집', '2013.02.08', 12500, '정규'),
  ('MSC06', '윈더우먼 봄', '2013.02.28', 9500, '싱글'),
  ('MSC07', '슈퍼맨 안녕', '2013.03.25', 9500, '싱글'), 
  ('MSC08', '소년시대 4집', '2013.04.01', 13500, '정규'),
  ('MSC09', '핑키 러브', '2013.04.01', 10500, '싱글'),
  ('MSC10', '신승모 6집', '2013.04.02', 18500, '정규');
  ```

* **연예관계자(employee)**

  ```sql
  insert into employee values
  ('E001', '김민훈', 'E202', 5500, 'R001'),
  ('E002', '손지민', 'E201', 4500, 'R001'),
  ('E003', '이순신', 'E203', 9500, 'R001'),
  ('E004', '강혁민', 'E201', 3500, 'R001'),
  ('E005', '옥주인', 'E201', 3500, 'R001'),
  ('E006', '신승모', 'E202', 7500, 'R001'),
  ('E007', '김건훈', 'E202', 7500, 'R001'),
  ('E008', '소년시대', 'E203', 8500, 'R001'),
  ('E009', '유재동', 'E203', 8500, 'R001'),
  ('E101', '강동민', 'E902', 7500, 'R002'),
  ('E102', '문성준', 'E902', 7500, 'R002'),
  ('E103', '한동화', 'E902', 7500, 'R002'),
  ('E201', '홍길동', 'E902', 3000, 'R003'),
  ('E202', '일지매', 'E101', 2750, 'R004'),
  ('E203', '김수현', 'E102', 2750, 'R004'),
  ('E204', '신용주', 'E103', 2500, 'R005'),
  ('E901', '이수민', null , 5000, 'R007'),
  ('E902', '김형석', 'E901', 4000, 'R006');
  ```

* **소속부서(rel_department)**

  ```sql
  insert into rel_department values
  ('E001', array['D001', 'D002']),
  ('E002', 'D001'),
  ('E003', array['D001', 'D003']),
  ('E004', array['D001', 'D002']),
  ('E005', 'D002'),
  ('E006', 'D003'),
  ('E007', 'D003'),
  ('E008', 'D004'),
  ('E009', 'D005'),
  ('E101', 'D101'),
  ('E102', 'D102'),
  ('E103', 'D103'),
  ('E201', 'D201'),
  ('E202', 'D201'),
  ('E203', 'D201'),
  ('E204', 'D201'),
  ('E901', 'D301'),
  ('E902', 'D301');
  ```

* **영화출연(part_movie)**

  ```sql
  insert into part_movie values
  ('MOV01', null, null, null),
  ('MOV02', null, null, null),
  ('MOV03', 'E003', '주연', 13500),
  ('MOV03', 'E001', '조연', 7500),
  ('MOV02', null, null, null),
  ('MOV02', 'E002', '단역', 3500),
  ('MOV02', null, null, null),
  ('MOV02', null, null, null),
  ('MOV02', 'E004', '단역', 3500),
  ('MOV02', 'E001', '조연', 8000),
  ('MOV02', 'E004', '단역', 3000),
  ('MOV02', null, null, null);
  ```

* **드라마출연(part_drama)**

  ```sql
  insert into part_drama values
  ('DRM01', null, null, null),
  ('DRM02', 'E003', '주연', 13500),
  ('DRM02', 'E001', '조연', 7500),
  ('DRM02', 'E004', '단역', 3500),
  ('DRM03', null, null, null),
  ('DRM04', null, null, null),
  ('DRM05', 'E001', '주연', 7500),
  ('DRM05', 'E004', '단역', 0),
  ('DRM05', 'E002', '단역', 0),
  ('DRM06', null, null, null),
  ('DRM07', null, null, null),
  ('DRM08', 'E002', '조연', 6500),
  ('DRM09', null, null, null),
  ('DRM10', 'E003', '주연', 15000);
  ```

* **음반참여(part_music)**

  ```sql
  insert into part_music values
  ('MSC01', 'E008', '메인',  8500),
  ('MSC02', null, null, null),
  ('MSC03', 'E007', '피처링', 1500),
  ('MSC04', 'E003', '피처링', 1500),
  ('MSC05', null, null, null),
  ('MSC06', null, null, null),
  ('MSC07', null, null, null),
  ('MSC08', 'E008', '메인', 13500),
  ('MSC09', null, null, null),
  ('MSC10', 'E006', '메인', 15500);
  ```



## 조회

```sql
select * from department;
select * from drama;
select * from emp_role;
select * from employee;
select * from movie;
select * from music;
select * from part_drama;
select * from part_movie;
select * from part_music;
select * from rel_department;
```

