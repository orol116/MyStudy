SELECT COUNT(*) FROM MEMBER
;

SELECT MEMBER_NICK FROM MEMBER
WHERE MEMBER_EMAIL = 'user01@kh.or.kr'
AND SECESSION_FL = 'N'
;

SELECT * FROM MEMBER;

-- MEMBER 테이블을 복제해서 MEMBER_S 테이블을 생성

-- 테이블 컬럼 복제(NOT NULL 제약조건만 복제된다.)
CREATE TABLE MEMBER_S
AS SELECT * FROM MEMBER
WHERE 1 = 0;

-- 컬럼 기본값 지정
ALTER TABLE MEMBER_S 
MODIFY ENROLL_DT DEFAULT SYSDATE;

ALTER TABLE MEMBER_S 
MODIFY SECESSION_FL DEFAULT 'N';

-- PK 제약조건 추가
ALTER TABLE MEMBER_S
ADD CONSTRAINT MEMBER_S_PK PRIMARY KEY(MEMBER_NO);

-- CHECK 제약조건 추가
ALTER TABLE MEMBER_S
ADD CONSTRAINT MEMBER_S_CHK CHECK(SECESSION_FL IN('Y', 'N'));

-- 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO_S NOCACHE;

-- 샘플데이터 추가
INSERT INTO MEMBER_S VALUES(
    SEQ_MEMBER_NO_S.NEXTVAL,
    'test01@naver.com',
    'pass01!',
    '테스트1',
    '01012341234',
    DEFAULT, DEFAULT, DEFAULT, DEFAULT
);

commit;

-- 아이디, 비밀번호가 일치하는 회원의 전화번호 조회
-- 단, 정상 회원만
SELECT * FROM MEMBER_S
WHERE MEMBER_EMAIL = 'test01@naver.com'
AND MEMBER_PW = 'pass01!'
AND SECESSION_FL = 'N';

-- 로그인
SELECT MEMBER_NO,MEMBER_EMAIL,MEMBER_NICK ,MEMBER_TEL,
    MEMBER_ADDR, PROFILE_IMG, 
    TO_CHAR( ENROLL_DT, 'YYYY-MM-DD HH24:MI:SS') AS ENROLL_DT
FROM MEMBER_S
WHERE MEMBER_EMAIL = 'test01@naver.com'
AND MEMBER_PW = 'pass01!'
AND SECESSION_FL = 'N';

-- 비밀번호 업데이트
UPDATE MEMBER_S SET MEMBER_PW = '$2a$10$X7bf2C5F0fGSq9XC7IwCC.Y49UEma6/50AfHs6Xw6wDSiKelQO9bi';


-- 이메일 중복 검사 (1 중복, 0 사용가능)
SELECT COUNT(*) FROM MEMBER_S
WHERE MEMBER_EMAIL = 'test01@naver.com'
AND SECESSION_FL = 'N';

-- 제약조건 조회
SELECT * FROM ALL_CONSTRAINTS
WHERE TABLE_NAME = 'BOARD';

-- 제약조건 삭제
ALTER TABLE BOARD
DROP CONSTRAINT FK_BOARD_MEMBER;

-- BOARD 테이블에서 1번 회원이 작성하지 않은 글 삭제
DELETE FROM BOARD WHERE MEMBER_NO != 1;

-- 제약조건 추가 (BOARD -> MEMBER_S)
ALTER TABLE BOARD ADD CONSTRAINT FLK_BOARD_MEMBER_S
FOREIGN KEY(MEMBER_NO)
REFERENCES MEMBER_S
ON DELETE SET NULL;

-- BOARD 테이블 샘플데이터
INSERT INTO BOARD VALUES(
    SEQ_BOARD_NO.NEXTVAL, '스프링 테스트', '테스트 내용 입니다.', 
    DEFAULT, DEFAULT, DEFAULT, DEFAULT, 1, 1
);

DELETE FROM BOARD_IMG
WHERE BOARD_NO NOT IN(
    SELECT BOARD_NO FROM BOARD
);

-- 이미지 샘플 데이터
INSERT INTO BOARD_IMG VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/sample1.jpg', 'sample1.jpg', 0, SEQ_BOARD_NO.CURRVAL);
INSERT INTO BOARD_IMG VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/sample2.jpg', 'sample1.jpg', 1, SEQ_BOARD_NO.CURRVAL);
INSERT INTO BOARD_IMG VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/sample3.jpg', 'sample1.jpg', 2, SEQ_BOARD_NO.CURRVAL);
INSERT INTO BOARD_IMG VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/sample4.jpg', 'sample1.jpg', 3, SEQ_BOARD_NO.CURRVAL);
INSERT INTO BOARD_IMG VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/sample5.jpg', 'sample1.jpg', 4, SEQ_BOARD_NO.CURRVAL);

commit;

SELECT * FROM BOARD
ORDER BY 1 DESC;

-- 게시글 목록 조회
SELECT BOARD_NO, BOARD_TITLE, MEMBER_NICK, READ_COUNT,
    CASE WHEN SYSDATE - CREATE_DT < 1
        THEN TO_CHAR(CREATE_DT, 'HH:MI')
        ELSE TO_CHAR(CREATE_DT, 'YYYY-MM-DD')
    END CREATE_DT, 
    
    (SELECT IMG_RENAME FROM BOARD_IMG 
    WHERE BOARD.BOARD_NO = BOARD_IMG.BOARD_NO
    AND IMG_LEVEL = 0) THUMBNAIL
    
FROM BOARD
JOIN BOARD_TYPE USING(BOARD_CD)
JOIN MEMBER_S USING(MEMBER_NO)
WHERE BOARD_ST = 'N'
AND BOARD_CD = ${boardCode}
ORDER BY BOARD_NO DESC;


-- 서브쿼리르 이용한 INSERT
--> SELECT 조회 결과를 BOARD_IMG 테이블에 삽입

-- 조건 1 : 서브쿼리로 조회된 RESULT SET의 컬럼과 BOARD_IMG 컬럼명이 같아야 한다. 
-- 조건 2 : UNION ALL로 합쳐진 서브쿼리에서는 시퀀스를 사용할 수 없다
INSERT INTO BOARD_IMG;

SELECT SEQ_IMG_NO.NEXTVAL, A.* FROM (
    SELECT --SEQ_IMG_NO.NEXTVAL IMG_NO, 
        '변경된 파일명1' IMG_RENAME,
        '원본 파일명1'   IMG_ORIGINAL,
        '0'              IMG_LEVEL,
        '1563'           BOARD_NO
    FROM DUAL

    UNION ALL

    SELECT --SEQ_IMG_NO.NEXTVAL IMG_NO, 
        '변경된 파일명1' IMG_RENAME,
        '원본 파일명1'   IMG_ORIGINAL,
        '0'              IMG_LEVEL,
        '1563'           BOARD_NO
    FROM DUAL
    
    UNION ALL

    SELECT --SEQ_IMG_NO.NEXTVAL IMG_NO, 
        '변경된 파일명1' IMG_RENAME,
        '원본 파일명1'   IMG_ORIGINAL,
        '0'              IMG_LEVEL,
        '1563'           BOARD_NO
    FROM DUAL
) A;

select * from v$version;

-- 댓글 테이블           
CREATE TABLE "REPLY_S" (
    "REPLY_NO"   NUMBER      PRIMARY KEY,
    "REPLY_CONTENT"   VARCHAR2(1000)      NOT NULL,
    "REPLY_ST"   CHAR(1) DEFAULT 'N' CHECK ("REPLY_ST" IN ('Y','N')),
    "CREATE_DT"   DATE   DEFAULT SYSDATE   NOT NULL,
    "MEMBER_NO"   NUMBER   REFERENCES MEMBER_S,
    "BOARD_NO"   NUMBER   REFERENCES BOARD,
    "PARENT_REPLY_NO" NUMBER REFERENCES REPLY_S
);

COMMENT ON COLUMN "REPLY_S"."REPLY_NO" IS '댓글 번호';
COMMENT ON COLUMN "REPLY_S"."REPLY_CONTENT" IS '댓글 내용';
COMMENT ON COLUMN "REPLY_S"."REPLY_ST" IS '댓글 상태';   
COMMENT ON COLUMN "REPLY_S"."CREATE_DT" IS '댓글 작성일';
COMMENT ON COLUMN "REPLY_S"."MEMBER_NO" IS '회원 번호';
COMMENT ON COLUMN "REPLY_S"."BOARD_NO" IS '게시글 번호';
COMMENT ON COLUMN "REPLY_S"."PARENT_REPLY_NO" IS '부모 댓글 번호';        

CREATE SEQUENCE SEQ_REPLY_NO_S; -- 댓글 번호 시퀀스

SELECT BOARD_NO FROM BOARD
WHERE BOARD_CD = 1
AND BOARD_ST = 'N'
ORDER BY 1 DESC;

INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모1', DEFAULT, DEFAULT, 3, 1562, NULL);
INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모1-자식1', DEFAULT, DEFAULT, 3, 1562, 27);
INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모1-자식2', DEFAULT, DEFAULT, 3, 1562, 27);
INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모1-자식3', DEFAULT, DEFAULT, 3, 1562, 27);

INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모2', DEFAULT, DEFAULT, 3, 1562, NULL);
INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모3', DEFAULT, DEFAULT, 3, 1562, NULL);
INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모4', DEFAULT, DEFAULT, 3, 1562, NULL);

INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모2-자식1', DEFAULT, DEFAULT, 3, 1562, 35);
INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모2-자식2', DEFAULT, DEFAULT, 3, 1562, 35);
INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모2-자식3', DEFAULT, DEFAULT, 3, 1562, 35);


INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모1-자식3-손자1', DEFAULT, DEFAULT, 3, 1562, 34);
INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모1-자식3-손자2', DEFAULT, DEFAULT, 3, 1562, 34);
INSERT INTO REPLY_S VALUES(SEQ_REPLY_NO_S.NEXTVAL, '부모1-자식3-손자3', DEFAULT, DEFAULT, 3, 1562, 34);

COMMIT;

/* 계층형 쿼리(START WITH, CONNECT BY, ORDER SIBLINGS BY)

 - 상위 타입과 하위 타입간의 관계를 계층식으로 표현할 수 있게 하는 질의어 (SELECT)

 - START WITH : 상위 타입(최상위 부모)으로 사용될 행을 지정(서브쿼리로 지정 가능)

 - CONNECT BY
   -> 상위 타입과 하위 타입 사이의 관계를 규정
   -> PRIOR 연산자와 같이 사용
      현재 행 이전에 상위 타입 또는 하위 타입이 있을지 규정

      1) 부모 -> 자식 계층 구조
         CONNECT BY PRIOR 자식 컬럼 = 부모 컬럼

      2) 자식 -> 부모 계층 구조
         CONNECT BY PRIOR 부모 컬럼 = 자식 컬럼

 - ORDER SIBLINGS BY : 계층 구조 정렬

 ** 계층형 쿼리가 적용 SELECT 해석 순서 **

 5: SELCT
 1: FROM
 4: WHERE
 2: START WITH
 3: CONNECT BY
 6: ORDER SIBLINGS BY

 - WHERE 절이 계층형 쿼리보다 해석 순서가 늦기 때문에
   먼저 조건을 반영하고 싶은 경우 FROM 절에 서브쿼리(인라인뷰)를 이용한다.

*/

-- 특정 게시글의 댓글 목록 조회
SELECT R.* FROM
    (SELECT REPLY_NO, REPLY_CONTENT,
        TO_CHAR(CREATE_DT, 'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"') CREATE_DT,
        BOARD_NO, MEMBER_NO, MEMBER_NICK, PROFILE_IMG, PARENT_REPLY_NO, REPLY_ST
    FROM REPLY_S
    JOIN MEMBER_S USING(MEMBER_NO)
    WHERE BOARD_NO = 1562) R

-- 계층형 쿼리로 정렬 후 조건을 이용해서 조회할 행을 제어
WHERE REPLY_ST = 'N'

-- PARENT_REPLY_NO의 값이 NULL인 행이 최상위 부모
START WITH PARENT_REPLY_NO IS NULL

-- 부모 -> 자식 계층 구조
CONNECT BY PRIOR REPLY_NO = PARENT_REPLY_NO
ORDER SIBLINGS BY REPLY_NO
;