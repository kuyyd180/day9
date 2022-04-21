SELECT a.employee_id, a.emp_name
      ,a.department_id, b.department_name
FROM employees a
    ,departments b
where a.department_id = b.department_id;

CREATE OR REPLACE VIEW emp_dept AS
SELECT a.employee_id, a.emp_name
      ,a.department_id, b.department_name
FROM employees a
    ,departments b
where a.department_id = b.department_id;

-- 뷰 생성 권한 필요 (sys계정에서 권한부여)
GRANT CREATE VIEW TO java;
--emp_dept 조회할수 있는 권한 부여
GRANT SELECT ON emp_dept TO study;

SELECT *
FROM emp_dept;

--계정생성
CREATE USER study IDENTIFIED BY study;
--접속 권한 부여
GRANT CONNECT TO study;

--(1) sys계정에서 study 계정생성 및 접속권한부여
CREATE USER study IDENTIFIED BY study;
--접속 권한 부여
GRANT CONNECT TO study;
--(2) java 계정에서 만든 view 객체를 조회할수 있는 권한을 study계정에게 부여
--emp_dept 조회할수 있는 권한 부여
GRANT SELECT ON emp_dept TO study;
--(3) study 계정에서 emp_dept 조회가능
SELECT * FROM java.emp_dept;

CREATE OR REPLACE VIEW tb_hak as
select 학번 as hak_no
      ,이름 as han_nm
from 학생;
GRANT SELECT ON tb_hak TO study;
GRANT INSERT ON tb_hak TO study;
/* VIEW 뷰는 실제 데이터는 테이블에 있지만 마치 테이블 처럼사용 
   (1) 자주 사용하는 SQL을 뷰로 만들어 편리하게 이용가능
   (2)데이터 보안 측면에서 중요한 컬럼은 감출 수 있다.
   단순 뷰
   - 테이블 하나로 생성하여 insert/update/dalete 가능
   - 그룹함수 불가능
   복합 뷰
   - 여러개 테이블로 생성 insert/update/delete 불가능
   - 그룹함수 가능
   
   CONNECT, RESOUECE, DBA (롤)
   롤: 다수 사용자와 다양한 권한으 효과적으로 관리하기 위해 권한을 그룹화 한 개념
   CONNECT: 사용자가 데이터베이스 접속할수 있는 권한을 그룹화한 롤 
   RESOURCE: 테이블, 시퀀스, 트리거와 같은 객체 생성 권한을 그룹화한 롤 
   DBA: 모든권한.
   
*/
-- 계정 롤권한 조회
SELECT *
FROM DBA_ROLE_PRIVS
WHERE GRANTEE = 'JAVA';
--
SELECT *
FROM DBA_SYS_PRIVS
WHERE GRANTEE = 'JAVA';

SELECT *
FROM DBA_SYS_PRIVS
WHERE GRANTEE = 'RESOURCE';


/* 시노님 Synonym '동의어'란 뜻으로 객체 각자의 고유한 이름에 대한 동의어를 만드는 것
    PUBLIC 모든 사용자 접근가능
    PRIVATE 특정 사용자만 사용 (디폴트) 
*/
--시노님 생성 권한 부여
GRANT CREATE SYNONYM TO java;
-- channels 테이블 이름에 별칭을 syn_channel로 부여
CREATE OR REPLACE SYNONYM syn_channel
FOR channels;
GRANT SELECT ON syn_channel TO study;
-- pulic 시노님 부여 및 삭제는 DBA 권한이 있어야함.

CREATE OR REPLACE PUBLIC SYNONYM hak
FOR java.학생;
GRANT SELECT ON hak TO study;

DROP synonym hak;
drop view emp_dept;

select *
from user_constraints
where constraint_name like '%pk%';

--commant
COMMENT ON TABLE TB_INFO IS '4월반';
COMMENT ON COLUMN TB_INFO.PC_NO IS '컴퓨터번호';
COMMENT ON COLUMN TB_INFO.info_NO IS '기본번호';
COMMENT ON COLUMN TB_INFO.NM IS '이름';
COMMENT ON COLUMN TB_INFO.HOBBY IS '취미';


/* 시퀀스 객체 SEQUENCE
   자동 순번을 반환하는 객체로 (CURRVAL, NEXTVAL)사용
*/
CREATE SEQUENCE my_seq
INCREMENT BY 1  -- 증강숫자
START WITH   1  --시작숫자
MINVALUE     1  --최솟값
MAXVALUE 999999 -- 최댓값
nocycle         -- 디폴트 NOCYCLE 최대,최소 도달중지
nocache ;       -- 디폴트 NOCACHE 메모리에 값 미리 할당 여부
SELECT my_seq.nextval   -- 값증가
      ,my_seq.currval   -- 현재값 
FROM dual;

CREATE TABLE ex9_1 (
    seq number
    ,dt timestamp default systimestamp
);

CREATE TABLE ex9_2 (
    seq VARCHAR2(10)
    ,dt timestamp default systimestamp
);
-- ex9_2 테이블에 seq값이 000000001 ~ 0000010000
-- 과 같은 형태로 저장되도록 INSERT문을 작성하시오

CREATE TABLE ex9_2 (
     seq VARCHAR2(10) primary key
    ,dt timestamp default systimestamp);

SELECT LPAD(MY_SEQ.nextval,10,'0')
FROM dual;
INSERT INTO ex9_2 (seq) VALUES(

INSERT INTO ex9_2 (seq) VALUES (LPAD(my_seq.nextval,10,'0'));
select *
from ex9_1;

SELECT nvl(max(seq),0) +1
FROM ex9_1;
INSERT INTO ex9_1(seq) VALUES((SELECT nvl(max(seq),0) +1 from ex9_1));


select *
from ex9_2;

/* 과목 테이블에 머신러닝 과목이 있으면 학점을 6으로 업데이트
                            없으면 번호를 생성하여 인서트
                            (과목이름:머신러닝,학점:3)                    
*/

MERGE INTO과목 ;           --대상테이블
USING DUAL                 -- 비교테이블
ON (a.과목이름='머신러닝')    -- 비교내용
WHEN MATCHED THEN          --비교내용이 TRUE
UPDATE SET a.학점 = 6
WHEN NOT MATCHED THEN      --비교내용이 FALSE
INSERT (a.과목번호, a.과목이름, a.학점)
VALUES ((SELECT NVL(MAX(과목번호),0) + 1
        FROM 과목)
         ,'머신러닝'
         , 3);
from 과목;

-- 이탈리아의 2000년 연평균 매출보다
--                 월평균 매출이 높은 월과 평균 월평균 매출액을 출력하시오
--sales a, customers b, countries c 사용
--sales_month, amount_sold, country_id, country_name, cust_id
/*select 월평균.월, 월평균.값
  from ()월평균
        ()년평균
 where 월평균.값 > 년평균.값
*/

SELECT emp_name, salary
FROM employees
WHERE salary >= (SELECT AVG(salary)
                FROM customers);
                
select *
     
from sales;


SELECT *
FROM sales
    ,customers
    ,countries
WHERE countries.COUNTRY_NAME = sales.CUST_ID;
AND 수강내역.과목번호 = 과목.과목번호;


select a.sales_month
       ,b.amount_sold
       ,b.country_id

from   sales       a
       ,customers  b
       ,countries  c
where a.sales_month = b.amount_sold
and a.salary >= 15000;
       

select a.employee_id
    ,a.emp_name
    ,a.salary
    ,a.job_id
    ,b.job_id
    ,b.job_title