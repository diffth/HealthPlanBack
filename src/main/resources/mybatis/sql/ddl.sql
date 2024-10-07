-- 권한
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO HEALTH IDENTIFIED BY 1234;
ALTER USER HEALTH DEFAULT TABLESPACE USERS;
ALTER USER HEALTH TEMPORARY TABLESPACE TEMP;
CONNECT HEALTH/1234


-- 회원 테이블
CREATE TABLE MEMBER (
    MNO NUMBER NOT NULL, -- 회원번호
    UUID VARCHAR2(230), -- 아이디
    UPW VARCHAR2(1000), -- 비밀번호
    PHONE VARCHAR2(100), -- 전화번호
    NAME VARCHAR2(220), -- 이름
    MTYPE CHAR(1), -- 회원타입
    EMAIL VARCHAR2(100), -- 이메일
    REGDATE DATE, -- 가입일
    SESSIONKEY VARCHAR2(250), -- 세션키
    SESSIONLIMIT TIMESTAMP, -- 세션만료시간
    SSTYPE CHAR(1), -- 구독타입
    PRIMARY KEY (MNO)
);

-- 보드(챌린지, FAQ) 테이블
CREATE TABLE BOARD (
    BNO NUMBER NOT NULL, -- 보드번호
    MNO NUMBER, -- 회원번호
    BTYPE CHAR(1), -- 게시물종류
    TITLE VARCHAR2(100), -- 글제목
    BCONTENTS VARCHAR2(2000), -- 글내용
    WDATE DATE, -- 작성일자
    BCOUNTS NUMBER, -- 조회수
    PRIMARY KEY (BNO),
    FOREIGN KEY (MNO) REFERENCES MEMBER(MNO)
);

-- 보드 댓글
CREATE TABLE BREPLY (
    RNO NUMBER NOT NULL, -- 댓글번호
    MNO NUMBER, -- 회원번호
    BNO NUMBER, -- 보드번호
    RCOMMENT VARCHAR2(1000), -- 댓글내용
    RREGDATE DATE, -- 댓글작성일
    PRIMARY KEY (RNO),
    FOREIGN KEY (BNO) REFERENCES BOARD(BNO),
    FOREIGN KEY (MNO) REFERENCES MEMBER(MNO)
);


-- 구독 테이블
CREATE TABLE SUBSCRIBE (
    SNO NUMBER NOT NULL, -- 구독번호
    UUID VARCHAR2(230), -- 아이디
    MNO NUMBER NOT NULL, -- 회원번호
    LNO NUMBER, -- 구독상품번호
    STYPE CHAR(1), -- 구독상품타입
    TITLE VARCHAR2(100), -- 글제목
    CONTENTS VARCHAR2(2000), -- 글내용
    WDATE DATE, -- 작성일자
    COUNTS NUMBER, -- 조회수
    FILEID VARCHAR2(250), -- 파일ID
    FILENAME VARCHAR2(250), -- 파일이름
    REPLYCNT NUMBER, -- 댓글수
    SPOINT NUMBER, -- 포인트
    PRIMARY KEY (SNO),
    FOREIGN KEY (MNO) REFERENCES MEMBER(MNO)
);

-- 구독 댓글
CREATE TABLE SREPLY (
    RNO NUMBER NOT NULL, -- 댓글번호
    MNO NUMBER, -- 회원번호
    SNO NUMBER, -- 구독번호
    RCOMMENT VARCHAR2(1000), -- 댓글내용
    RREGDATE DATE, -- 댓글작성일
    PRIMARY KEY (RNO),
    FOREIGN KEY (SNO) REFERENCES SUBSCRIBE(SNO),
    FOREIGN KEY (MNO) REFERENCES MEMBER(MNO)
);



-- 커뮤니티 테이블
CREATE TABLE COMMUNITY (
    CNO NUMBER NOT NULL, -- 커뮤니티번호
    MNO NUMBER, -- 회원번호
    CTYPE CHAR(1), -- 커뮤니티소분류
    TITLE VARCHAR2(100), -- 글제목
    CONTENTS VARCHAR2(2000), -- 글내용
    WDATE DATE, -- 작성일자
    COUNTS NUMBER, -- 조회수
    REPLYCNT NUMBER, -- 댓글수
    PRIMARY KEY (CNO)
);


-- 커뮤니티 댓글
CREATE TABLE CREPLY (
    RNO NUMBER NOT NULL, -- 댓글번호
    MNO NUMBER, -- 회원번호
    CNO NUMBER, -- 커뮤니티번호
    RCOMMENT VARCHAR2(1000), -- 댓글내용
    RREGDATE DATE, -- 댓글작성일
    PRIMARY KEY (RNO),
    FOREIGN KEY (CNO) REFERENCES COMMUNITY(CNO),
    FOREIGN KEY (MNO) REFERENCES MEMBER(MNO)
);

-- 포인트 테이블
CREATE TABLE POINT (
    PNO NUMBER NOT NULL, -- 포인트번호
    MNO NUMBER, -- 회원번호
    PSOURCE VARCHAR2(100), -- 포인트출처
    PCOUNT NUMBER,
    PDATE DATE, -- 포인트생성일
    PRIMARY KEY (PNO),
    FOREIGN KEY (MNO) REFERENCES MEMBER(MNO)
);

-- 주문 테이블
CREATE TABLE ORDERS (
    ONO NUMBER NOT NULL, -- 주문번호
    MNO NUMBER, -- 회원번호
    OTYPE CHAR(1), -- 주문타입
    STATUS CHAR(1), -- 상태
    AMOUNT NUMBER, -- 금액
    PRICE NUMBER, -- 가격
    OCOMMENT VARCHAR2(250), -- 주문코멘트
    ODATE DATE, -- 주문일자
    PRIMARY KEY (ONO),
    FOREIGN KEY (MNO) REFERENCES MEMBER(MNO)
);

-- 첨부
CREATE TABLE NATTACH (
    UUID VARCHAR2(200)
  , IMGNAME VARCHAR2(200)
  , SNO NUMBER
  , ANO NUMBER
  , PATH VARCHAR2(200)
  , IMAGEURL VARCHAR2(200)
  , IMGTYPE CHAR(1 BYTE)
  , REGDATE DATE
);


-- 시퀀스
CREATE SEQUENCE ACNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE ANO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE ATTACHED_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE BOARD_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE BREPLY_RNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE BREPLY_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE CNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE CRNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE DNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE ENO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE FBNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE MNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE PNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE RNOSEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE RNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE SEQ_BOARD_BNO
    MINVALUE 1
    MAXVALUE 9999999999999999999999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE SEQUENCE SNO_SEQ
    MINVALUE 1
    MAXVALUE 9999999999
    INCREMENT BY 1
    START WITH 1
    NOCACHE;
