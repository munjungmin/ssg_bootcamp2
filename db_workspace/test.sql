--1) 
select deptno, count(empno) from emp
group by deptno
having count(empno) >= 5;


--2) 
select count(*) 
from emp e
where e.deptno = (
        select deptno 
        from emp
        where ename = 'SMITH'
    )

--3)
select deptno, dname, loc
from dept
where deptno in(
    select deptno 
    from emp
    group by deptno 
    having count(*) > (
        select count(*) 
        from emp 
        where deptno = (
            select deptno
            from emp
            where ename = 'SMITH'
        )
    )
);



having count(*) > (
    select count(*) 
    from emp 
    where deptno = (
        select deptno
        from emp
        where ename = 'SMITH'
    )
);





--4)
select empno, ename, hiredate, d.deptno, d.loc
from emp e, dept d
where e.deptno = d.deptno and hiredate < (
    select hiredate from emp where ename = 'FORD'
) 
order by hiredate;




--5) 사원수가 없는 경우도 표시
select count(*) 
from emp
group by deptno;

--6) 


