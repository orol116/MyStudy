/* SELECT (DML 또는 DQL) : 조회

 - 데이터를 조회(SELECT)하면 조건에 맞는 행들이 조회됨
   이때, 조회된 행들의 집합을 "RESULT SET"이라고 한다.

 - RESULT SET에는 0개 이상의 행이 포함될 수 있다.
   왜 0개이상인가?? -> 조건에 맞는 행이 없을수도 있기 때문이다.
*/

-- [ SELECT * FROM 테이블명 ]
-- EMPLOYEE 테이블에서 모든 사원의 정보를 조회
-- '*' : ALL, 모든, 전부
SELECT * FROM EMPLOYEE;

-- [ SELECT 컬럼명 FROM 테이블명 ]
-- EMPLOYEE 테이블에서 모든 사원의 이름만 조회
SELECT EMP_NAME FROM EMPLOYEE;

-- [ SELECT 컬럼명, 컬럼명, ... FROM 테이블명 ]
-- EMPLOYEE 테이블에서 모든 사원의 사번, 이름, 전화번호 조회
SELECT EMP_ID, EMP_NAME, PHONE FROM EMPLOYEE;

-- EMPLOYEE 테이블에서 모든 사원의 사번, 이름, 이메일, 입사일 조회
SELECT EMP_ID, EMP_NAME, EMAIL, HIRE_DATE FROM EMPLOYEE;

-- DEPARTMENT 테이블에 있는 모든 행 조회
SELECT * FROM DEPARTMENT;

--------------------------------------------------------------------------------
-- < 컬럼 값 산술 연산 >
-- 컬럼 값 : 테이블의 한 칸(== 한 셀)에 작성된 값(DATA)

-- SELECT문 작성 시 컬럼명에 산술 연산을 작성하면 
-- 조회되는 결과 컬럼 값에 산술 연산이 반영된다.

-- EMPLOYEE 테이블에서 모든 사원이 사번, 이름, 급여, 급여 + 1000000 을 조회
SELECT EMP_ID, EMP_NAME, SALARY, SALARY + 1000000 FROM EMPLOYEE;

-- EMPLOYEE 테이블에서 모든 사원의 이름, 급여, 연봉(급여 * 12개월)을 조회
SELECT EMP_NAME, SALARY, SALARY * 12 FROM EMPLOYEE;  

--------------------------------------------------------------------------------
--(중요) < 오늘 날짜 조회 >
SELECT SYSDATE FROM DUAL;

-- SYSDATE : 시스템상의 현재 날짜
--           (년, 월, 일, 시, 분, 초 단위까지 표현 가능하지만,
--            디벨로퍼의 날짜 표기 방법이 년/월/일 로 지정되어있다.

-- DUAL(Dummy Table) : 가짜 테이블(임시 테이블, 단순 조회 테이블)

 -- ** DB는 날짜 데이터의 연산(+,-)이 가능하다 **
 SELECT SYSDATE, SYSDATE + 1 FROM DUAL;
 
 -- EMPLOYEE 테이블에서 이름, 입사일, 오늘까지 근무한 날짜 조회
 SELECT EMP_NAME, HIRE_DATE, (SYSDATE - HIRE_DATE) / 365 FROM EMPLOYEE;
 
 -------------------------------------------------------------------------------
 -- < 컬럼 별칭 지정 >
 -- SELECT 조회 결과의 집합인 RESULT SET에 컬럼명을 지정
 
 /*
 1) 컬럼명 AS 별칭 : 띄어쓰기X, 특수문자X, 문자O
 2) 컬럼명 별칭 : 1)번에서 AS 생략만 한 것
 
 3) 컬럼명 AS "별칭" : 띄어쓰기O, 특수문자O, 문자O
 4) 컬럼명 "별칭" : 3번에서 AS만 생략한 것
 */
 
 -- EMPLOYEE 테이블에서
 -- 사번, 이름, 급여(원), 근무 일수를 모두 조회
 SELECT EMP_ID AS 사번, 
        EMP_NAME 이름,
        SALARY AS "급여(원)", 
        SYSDATE - HIRE_DATE "근무 일수"
FROM EMPLOYEE;

--------------------------------------------------------------------------------
-- 리터럴 : 값 자체
-- DB에서의 리터럴 : 임의로 지정한 값을 기존 테이블에 존재하는 값처럼 사용
--> 리터럴 표기법 '' (홑따옴표)
SELECT EMP_NAME, SALARY, '원' AS 단위 FROM EMPLOYEE;
 
 -------------------------------------------------------------------------------
 -- DISTINCT : 조회 시 컬럼에 포함된 중복 값을 한번만 표시할 때 사용
 
 -- (주의사항)
 -- 1) DISTINCT는 SELECT문에 딱 한번만 작성할 수 있다.
 -- 2) DISTINCT는 SELECT문 가장 맨앞에 작성되어야 한다.
 
 -- EMPLOYEE 테이블에 저장된 직원들이 속해있는 부서 코드 종류 조회
 SELECT DEPT_CODE FROM EMPLOYEE;
 
 -------------------------------------------------------------------------------
 -- WHERE절
 -- 테이블에서 조건을 충족하는 값을 가진 행만 조회하고자 할때 사용
 
 -- 비교 연산자 : >, <, >=, <=, = ( 같다 -> 등호가 1개 ), !=, <>(같지 않다)
 
 -- EMPLOYEE 테이블에서 급여가 3백만원 초과인 직원의 
 -- 사번, 이름, 급여, 부서코드를 조회
/*해석 순서*/
/*3*/ SELECT EMP_ID, EMP_NAME, SALARY, DEPT_CODE
/*1*/ FROM EMPLOYEE 
/*2*/ WHERE SALARY > 3000000;

-- EMPLOYEE 테이블에서
-- 부서코드가 'D9'인 직원의
-- 사번, 이름, 부서코드, 전화번호 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, PHONE FROM EMPLOYEE WHERE DEPT_CODE = 'D9';

--------------------------------------------------------------------------------
논리 연산자(AND, OR)

-- EMPLOYEE 테이블에서 급여가 200만 이상이고
-- 부서 코드가 'D6'인 직원의 이름, 급여, 부서코드를 조회
SELECT EMP_NAME, SALARY, DEPT_CODE 
FROM EMPLOYEE
WHERE SALARY >= 2000000
AND DEPT_CODE = 'D6';

-- EMPLOYEE 테이블에서
-- 급여가 300만이상, 500만 이하인 직원의
-- 사번, 이름, 급여 조회
SELECT EMP_ID, EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SALARY >= 3000000 AND SALARY <= 5000000;

-- EMPLOYEE 테이블에서
-- 부서코드가 'D6' 또는 'D9'인 직원의
-- 사번, 이름, 부서코드 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE FROM EMPLOYEE
WHERE DEPT_CODE = 'D6' OR DEPT_CODE = 'D9';

--------------------------------------------------------------------------------
-- 컬럼명 BETWEEN A AND B : 컬럼 값이 A이상 B이하인 경우

-- EMPLOYEE 테이블에서
-- 급여가 300만이상, 500만 이하인 직원의
-- 사번, 이름, 급여 조회
SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE
WHERE SALARY BETWEEN 3000000 AND 50000000;

-- 컬럼명 NOT BETWEEN A AND B : 컬럼 값이 A이상 B이하가 아닌 경우
SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE
WHERE SALARY NOT BETWEEN 3000000 AND 50000000;

/* BETWEEN을 이용한 날짜 비교 */
-- EMPLOYEE 테이블에서
-- 입사일이 90/01/01 ~ 99/12/31 (90년도 입사자)인
-- 직원의 사번, 입사일 조회
SELECT EMP_ID, EMP_NAME, HIRE_DATE FROM EMPLOYEE
WHERE HIRE_DATE BETWEEN '1990/01/01' AND '1999/12/31';

/*** '1990/01/01' 날짜를 문자열 형식으로 작성하게 되면
      DB가 알아서 판단하여 날짜타입(DATE)으로 형변환을 진행함 ***/
