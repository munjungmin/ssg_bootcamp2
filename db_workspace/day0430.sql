SQL> --현재 유저가 보유한 테이블을 보고싶은 경우 mysql은 show tables이지만, 오라클은 복잡
SQL> --oracle은 시스템의 정보를 얻기 위해서는 사전이라 불리는, 즉 dictionary를 조회할 줄 알아야함
SQL> -- 수백여개의 딕셔너리를 개발자가 암기해야 하나?
SQL> -- 하지만, 딕셔너리는 약간의 명명 규칙이 있다
SQL> -- 현재 접속한 사용자 계정이 일반 계정인 경우 접두어가 user_ 로 시작
SQL> -- 관리자 계정 sys, system인 경우 dba_ 로 시작, v$_ 접두어로 시작
SQL> select table_name from user_tables;

TABLE_NAME                                                                      
------------------------------------------------------------                    
MEMBERSHIP                                                                      
MEMBER                                                                          

SQL> --DDL 중 제거하는 명령어 drop vs delete
SQL> drop table membership;

Table dropped.

SQL> select table_name from user_tables;

TABLE_NAME                                                                      
------------------------------------------------------------                    
MEMBER                                                                          

SQL> --앞으로 테이블 만들때는 까다롭게 제한을 가하자
SQL> --제약조건을 부여하자
SQL> --레코드는 유일한 것만 받아들이겠다 unique
SQL> --null을 허용하지 않겠다. not null
SQL> -- 지정된 값만 허용하겠다 check
SQL> --아무런 값을 넣지 않으면 기본으로 값을 자동 지정하겠다. default
SQL> --위의 모든 제약 조건을 부여해서 테이블 실습
SQL> create table member(
  2  id varchar2(20) unique,
  3  name varchar2(30) not null,
  4  gender varchar2(6),
  5  regdate date default sysdate,
  6  constraint chk_member check (gender='남' or gender='여')
  7  );
create table member(
             *
ERROR at line 1:
ORA-00955: name is already used by an existing object 


SQL> drop table member;

Table dropped.

SQL> ed
Wrote file afiedt.buf

  1* drop table member
SQL> create table member(
  2    id varchar2(20) unique,
  3    name varchar2(30) not null,
  4    gender varchar2(6),
  5    regdate date default sysdate,
  6    constraint chk_member check (gender='남' or gender='여')
  7  );

Table created.

SQL> --제약조건 동작하는지 테스트
SQL> insert into member(id, name, gender) values('adams', 'A', '념');
insert into member(id, name, gender) values('adams', 'A', '념')
*
ERROR at line 1:
ORA-02290: check constraint (JS.CHK_MEMBER) violated 


SQL> /
insert into member(id, name, gender) values('adams', 'A', '념')
*
ERROR at line 1:
ORA-02290: check constraint (JS.CHK_MEMBER) violated 


SQL> spool off;
