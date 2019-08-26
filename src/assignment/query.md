1. HNU Entertainment의 부서 코드, 이름, 위치를 검색하시오

   ```sql
   select *
   from department;
   ```

   

2. HNU Entertainment의 연예관계자 코드, 이름, 관리자, 급여를 검색하시오

   ```sql
   select emp_code, emp_name, emp_name, emp_sal
   from employee e
   ```
   

   
3. HNU Entertainment에서 제작한 드라마의 코드와 이름을 검색하시오.

   ```sql
   select drm_code, drm_name
   from drama
   where drm_prd like '%HNU%';
   ```

   

4. 드라마 방영사가 KBC이거나 SBC인 드라마를 검색하시오.

   ```sql
   select *
   from drama
   where drm_brd in ('KBC', 'SBC');
   ```

   

5. 드라마 제작사를 검색하시오. 단, 중복된 값이 있으면 제거하시오.

   ```sql
   select drm_prd
   from drama
   group by drm_prd;
   ```

   

6. 연예관계자들의 급여의 총합과 평균 급여액을 계산하시오.

   ```sql
   select sum(emp_sal), round(avg(emp_sal), 2)
   from employee;
   ```

   

7. 방영일자가 아직 확정되지 않은 드라마의 이름을 검색하시오.

   ```sql
   select drm_name
   from drama
   where drm_opdate is null;
   ```

   

8. 연예관계자에 대해 연예관계자의 이름과 직속 상사의 이름을 검색하시오.

   ```sql
   select e1.emp_name, e2.emp_name
   from employee e1, employee e2
   where e1.emp_mgt = e2.emp_code;
   ```

   

1.  연예관계자에 대해 이름과 급여를 출력하고, 급여의 내림차순으로 정렬하시오. 단, 동일 급여일 때는 이름의 오름차순으로 정렬하시오.

   ```sql
   select emp_name, emp_sal
   from employee
   order by emp_sal desc, emp_name;
   ```

   

10. 모든 연예관계자를 직급별로 그룹화하고, 평균 급여액이 5000 이상인 직급에 대해 연예 관계자의 직급, 평균 급여액, 최소 급여액, 최대 급여액을 검색하시오.

   ```sql
   select emp_rcode, round(avg(emp_sal), 2), min(emp_sal), max(emp_sal)
   from employee
   group by emp_rcode
   having 5000 <= avg(emp_sal)
   order by emp_rcode;
   ```

   

3. 모든 연예관계자의 평균 급여액보다 많은 급여를 받는 연예관계자의 이름과 급여를 검색하라.

   ```sql
   select emp_name, emp_sal
   from employee
   where emp_sal > (
     select avg(emp_sal)
     from employee
   );
   ```

   

1. 방영일자가 확정되지 않은 드라마의 방영일자가 2013-05-01로 편성되었습니다. 알맞게 변경하시오.

   ```sql
   update drama set drm_opdate = '2013-05-01'
   where drm_opdate is null;
   ```

   

2. 연예관계자 김수현 씨가 대리에서 실장으로 승진하고 급여가 20% 증가되었습니다. 알맞게 변경하시오.

   ```sql
   update employee set emp_rcode = (
     select emp_rcode
     from emp_role
     where emp_rname = '실장'
   ),
   emp_sal = emp_sal * 1.2
   where emp_name = '김수현';
   ```

   

1. 우리 회사에 한 명의 임원이 등록되었습니다. 코드는 E903, 이름은 손진현, 관리자는 E901, 급여는 4000입니다. 알맞게 등록하시오.

   ```sql
   insert into employee values
   ('E903', '손진현', 'E901', 4000, 'R005');
   ```

   

2. 연예관계자인 손진현님이 퇴직했습니다. 연예관계자 목록에서 제거하시오.

   ```sql
   delete from employee where emp_name = '손진현';
   ```

   