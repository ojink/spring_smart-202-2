--부서별 사원수 조회
-- 개발부 5
-- 영업부 10

select department_id, nvl(department_name,'소속없음') department_name, count(employee_id) count
from employees e left outer join departments d using(department_id)
group by department_id, department_name
order by 1
;

--년도별 채용인원수 조회
-- 2020년 5
-- 2021년 10
select to_char(hire_date,'yyyy')||'년' unit , count(employee_id) count
from employees
group by to_char(hire_date,'yyyy')
order by 1;

select to_char(hire_date,'mm')||'월' unit , count(employee_id) count
from employees
group by to_char(hire_date,'mm')
order by 1;


--상위3위부서의 년도별 채용인원수 조회

--상위3위부서
select rank, department_id, '(TOP'||rank||')' || department_name department_name
from  (select department_id, dense_rank() over(order by count(*) desc) rank
        from employees
        group by department_id) e left outer join departments d using(department_id)
where rank <= 3 ;

--상위3위부서  년도별 채용인원수
select to_char(hire_date,'yyyy') unit, count(*) count
from employees e inner join (select rank, department_id, '(TOP'||rank||')' || department_name department_name
                            from  (select department_id, dense_rank() over(order by count(*) desc) rank
                                    from employees
                                    group by department_id) e left outer join departments d using(department_id)
                            where rank <= 3) r
group by  to_char(hire_date,'yyyy')
;



