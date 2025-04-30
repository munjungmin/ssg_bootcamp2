--오라클 최고 권한자로 로그인하여, 새로운 프로젝트에 대비하여 데이터베이스를 구축하고, 새로운 계정도 만들어보자
SQL> show user;
USER is "SYS"

--오라클은 데이터베이스라는 용어보다는 tablespace라는 용어를 사용하여 여러 데이터베이스 파일을 묶는 논리적 개념
SQL> --데이터베이스 신규 구축
SQL> create tablespace javaspace
  2  datafile 'C:\oraclexe\app\oracle\oradata\XE\JAVASPACE.dbf'
  3  size 2m;

Tablespace created.

SQL> --유저를 생성한다
SQL> create user js
  2  identified by 1234
  3  default tablespace javaspace
  4  quota unlimited on javaspace;

User created.

SQL> --접속할 수 있는 권한
SQL> --SQL문의 3가지 DDL, DCL, DML
SQL> grant create session to js;

Grant succeeded.

SQL> --이 시점부터 이 유저로 로그인 가능함
SQL> --로그인은 가능하지만, 테이블 권한이 없다
SQL> --주의) 명령어가 create table 이라고 해서 만드는 권한만 부여하는게 아니라 생성, 수정, 삭제 모든 권한을 부여하는 것
SQL> grant create table to js;

Grant succeeded.

SQL> --이 시점부터 접속 유저를 전환하자
SQL> --즉 새로운 접속 프로그램을 실행하자는 뜻이 아니라, 현재 프로그램은 유지하고 접속 유저만 변경
SQL> connect js/1234
Connected.
SQL> show user;
USER is "JS"
SQL> create table membership(
  2  id varchar2(20),
  3  pwd varchar2(64),
  4  name varchar2(20),
  5  age number
  6  );

Table created.

SQL> ed
Wrote file afiedt.buf

  1  create table membership(
  2  id varchar2(20),
  3  pwd varchar2(64),
  4  name varchar2(20),
  5  age number
  6* )
SQL> /
create table membership(
             *
ERROR at line 1:
ORA-00955: name is already used by an existing object 


SQL> --레코드 1건 넣기
SQL> insert into membership(id, pwd, name, age) values('adams', '0000', 'AD', 25);

1 row created.

SQL> -- 1건의 레코드가 들어간 내용을 출력해보자
SQL> select * from membership;

ID                                                                              
----------------------------------------                                        
PWD                                                                             
--------------------------------------------------------------------------------
NAME                                            AGE                             
---------------------------------------- ----------                             
adams                                                                           
0000                                                                            
AD                                               25                             
                                                                                

SQL> --sqlplus 툴 자체 명령어 중, 한 줄에 몇개의 철자가 올지를 결정짓는 기능
SQL> --주의) sql문 아니고 그냥 툴 명령이다
SQL> set line 400
SQL> select * from membership;

ID                                       PWD                                                                                                                              NAME                                            AGE                                                                                                                                                                                   
---------------------------------------- -------------------------------------------------------------------------------------------------------------------------------- ---------------------------------------- ----------                                                                                                                                                                                   
adams                                    0000                                                                                                                             AD                                               25                                                                                                                                                                                   

SQL> insert into membership(id, pwd, name, age) values('adams', '0000', 'AD', 25);

1 row created.

SQL> set line 200
SQL> select * from membership;

ID                                       PWD                                                                                                                                                            
---------------------------------------- --------------------------------------------------------------------------------------------------------------------------------                               
NAME                                            AGE                                                                                                                                                     
---------------------------------------- ----------                                                                                                                                                     
adams                                    0000                                                                                                                                                           
AD                                               25                                                                                                                                                     
                                                                                                                                                                                                        
adams                                    0000                                                                                                                                                           
AD                                               25                                                                                                                                                     
                                                                                                                                                                                                        

SQL> set line 400
SQL> select * from membership;

ID                                       PWD                                                                                                                              NAME                                            AGE                                                                                                                                                                                   
---------------------------------------- -------------------------------------------------------------------------------------------------------------------------------- ---------------------------------------- ----------                                                                                                                                                                                   
adams                                    0000                                                                                                                             AD                                               25                                                                                                                                                                                   
adams                                    0000                                                                                                                             AD                                               25                                                                                                                                                                                   

SQL> --기존에 생성한 membership 테이블은 검증되지 않고 결점이 많은 데이터를 허용하므로, 데이터의 신뢰성이 없다.
SQL> --테이블 생성 시 각 컬럼에 들어갈 데이터를 까다롭게 각종 제한을 가하는 데이터베이스의 조건을 가리켜 제약조건(Constraints)라고 한다.
SQL> create table member(
  2  id varchar2(20) unique,
  3  name varchar2(20) not null,
  4  gender char(2),
  5  regdate date default sysdate
  6  , constraint chk_member check (gender='남' or gender='여')
  7  );

Table created.

SQL> --Rkekfhqrp wpdirwhrjsdmf qndugks ekdma xptmxmgoqhrl
SQL> --까다롭게 제약조건을 부여한 ㅏ음 테스트 해보기
SQL> --unique 제약조건이 동작하는지 테스트
SQL> insert into member(id, name, gender) values('batman', '배트맨', '여');
insert into member(id, name, gender) values('batman', '배트맨', '여')
                                                                   *
ERROR at line 1:
ORA-12899: value too large for column "JS"."MEMBER"."GENDER" (actual: 3, maximum: 2) 


SQL> --성별 컬럼을 너무 작게 부여함. 용량을 늘려야 하지만 오늘은 컬럼을 제거한 다음 추가해보자
SQL> alter table member
  2  drop column gender;

Table altered.

SQL> --제거된 컬럼을 다시 추가하자
SQL> --회사에서는 이렇게 하면 큰일난다.
SQL> alter table member
  2  add gender varchar(8) check (gender='남' or gender='여');

Table altered.

SQL> insert into member(id, name, gender) values('batman', '배트맨', '여');

1 row created.

SQL> insert into member(id, name, gender) values('batman', '배트맨', '여');
insert into member(id, name, gender) values('batman', '배트맨', '여')
*
ERROR at line 1:
ORA-00001: unique constraint (JS.SYS_C007001) violated 


SQL> spool off;
