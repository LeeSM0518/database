* 총계 함수

```
select SUM(CTIME), MIN(CTIME), MAX(CTIME), COUNT(CTIME), ROUND(AVG(CTIME), 2)  FROM COURSE;
```



* 그룹핑

```
select pdept, count(*)
from professor
group by pdept;
```



* Distinct 유사

```
select count(*) as CNT
from (select dd_device_id
from device_data
group by dd_device_id) A;
```



* Distinct

```
select count(distinct dd_device_id) as CNT
from device_data;
```



* null

````
select null = null, null <> null, 1 = null, 1 <> null, 1 < null, 1 > null;
select null is null, 1 is null, 1 is not null;
````



* Sub

```
select cname
from course
where ctime > (
select ctime
from course
where cname='OS') order by cname;
```



* Multiple Row

```
IN, NOT IN, ANY, ALL, EXISTS
```



* views (가상 테이블)

```
create view TestView 
as (select * 
from device_data
where dd_device_id in ('11111','22222') 
order by dd_device_id);
```



* Join
  * N - 1개의 join 조건

```
select *
From A [INNER] join B
ON A.ID = B.iD;

select *
FROM A, B
WHERE A.ID = B.ID;
```



예제

```
select d_d.dd_device_id,
count(*), 
sum(d_d.dd_device_data),
round(avg(d_d.dd_device_data), 2)
from device d, device_data d_d
where d_d.dd_device_id = d.device_id
and d.device_loc in ('C501', 'C503')
group by d.device_id;
```



```
select d.device_id, d.device_loc,
sum(dd.dd_device_data),
round(avg(dd.dd_device_data), 2)
from device d, device_data dd
where d.device_id = dd.dd_device_id and 
(select * from dd
group by dd.dd_device_id
having sum(dd.dd_device_data) >= 280);
```



```
select p.pcode, p.pname, p.pphone, c.croom                                                    from professor p, course c, lecture l                                                                    where l.lccode = c.ccode and l.lpcode = p.pcode and c.cname = 'Database';
```



```
select sname, sphone from student order by sname
intersect
select cname, ctime, croom from course order by cname desc
```

