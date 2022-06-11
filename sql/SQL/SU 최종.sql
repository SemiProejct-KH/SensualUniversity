select * from test;

CREATE TABLE su_department (
	department_no	varchar2(100)	NOT NULL,
	deparment_name	varchar2(100)	NOT NULL,
    
    constraint pk_department_no primary key(department_no)
);

CREATE TABLE su_member (
	member_no	number	NOT NULL,
	department_no	varchar2(100)	NOT NULL,
	member_id	varchar2(25)	NOT NULL,
	member_pw	varchar2(25)	NOT NULL,
	member_name	varchar2(25)	NOT NULL,
	member_birth	date	NOT NULL,
	member_phone	char(11)	NOT NULL,
	member_email	varchar2(200)	NOT NULL,
	member_img	varchar2(500)	NULL,
	member_role	char(1)	NOT NULL,
    -- member_role 기본값 S ?
	member_level	char(1)	NULL,
    enroll_date date default sysdate,
    
    constraint pk_member_no primary key(member_no),
    constraint fk_member_department_no foreign key(department_no) references su_department(department_no) on delete set null,
    constraint ck_member_role check(member_role in ('A', 'P', 'S')),
    constraint ck_member_level check(member_level in('1', '2', '3', '4')),
    constraint uq_member_id unique(member_id)
);

-- 채팅 부분은 생각해보기
CREATE TABLE su_chatroom (
	chatroom_no	number	NOT NULL,
	member_no	number	NOT NULL,
    
    constraint pk_chatroom_group primary key(chatroom_no, member_no), -- 복합 pk
    constraint fk_chatroom_member_no foreign key(member_no) references su_member(member_no) on delete set null
);

CREATE TABLE su_chatcontent (
    chatcontent_no	number	NOT NULL,
	chatroom_no	number	NOT NULL,
	member_no	number	NOT NULL,
	chatcontent_content	varchar2(1000)	NULL,
	chatcontent_date	date	NULL,
    
    constraint pk_chatcontent_no primary key(chatcontent_no),
    -- 복합 fk
    constraint fk_chatcontent_chatroom_group foreign key(chatroom_no, member_no) references su_chatroom(chatroom_no, member_no) on delete set null
--    constraint fk_chatroom_chatroom_no foreign key(chatroom_no) references  su_chatroom(chatroom_no) on delete cascade,
--    constraint fk_chatroom_member_no foreign key(member_no) references  su_chatroom(memberm_no) on delete set null
);

--------------------------------------------------------
CREATE TABLE su_subject (
    subject_no    varchar2(100)    NOT NULL,
    member_no    number    NOT NULL,
    department_no    varchar2(100)    NOT NULL,
    subject_name    varchar2(100)    NULL,
    subject_time    varchar2(100)    NULL,
    subject_classroom    varchar2(25)    NULL,
    subject_credit    char(1)    NULL,
    subject_lebel    char(1)    NULL,
    subject_term    varchar2(25)    NULL,

    constraint pk_subject_no primary key(subject_no),
    constraint fk_subject_member_no foreign key(member_no) references su_member(member_no) on delete set null,
    constraint fk_subject_department_no foreign key(department_no) references su_department(department_no) on delete set null
);

CREATE TABLE su_register (
    register_no    number    NULL,
    subject_no    varchar2(100)    NOT NULL,
    member_no    number    NOT NULL,

    constraint pk_register_no primary key(register_no),
    constraint fk_register_subject_no foreign key(subject_no) references su_subject (subject_no) on delete set null,
    constraint fk_register_member_no foreign key(member_no) references su_member (member_no) on delete set null
);

CREATE TABLE su_grade (
    register_no    number    NULL,
    grade_middle    number    NULL,
    grade_final    number    NULL,
    grade_assignment    number    NULL,
    grade_attend    number    NULL,

    constraint fk_grade_register_no foreign key(register_no) references su_register (register_no) on delete set null
);

-------------------------------------------------------------------------------------------------------

-- 게시판 테이블
create table su_board (
    board_no    number    not null,
    member_no    number    not null,
    board_title    varchar2(300)    not null,
    board_content    varchar2(4000)    null,
    board_date    date    null,
    board_read_count    number    null,
    board_categori    varchar2(100)    not null,
    constraint pk_board_no primary key (board_no),
    constraint fk_board_member_no foreign key(member_no) references su_member(member_no) on delete set null
);
-- 게시판 시퀀스 ?? 이거 게시판 카테고리별로 나눠서 작업해야하는것같은데 어케하죠?ㅎ
--create sequence seq_board_no;

-- 게시판 첨부파일
create table su_board_attachment (
    board_attachment_no    number    null,
    board_no    number    not null,
    board_attachment_original_filename    varchar2(255)    not null,
    board_attachment_renamed_filename    varchar(255)    not null,
    constraint pk_board_attachment_no primary key(board_attachment_no),
    constraint fk_board_attachment_board_no foreign key(board_no) references su_board(board_no) on delete cascade 
);
-- 게시판 첨부파일 시퀀스
--create sequence seq_attachment_no;

-- 댓글 테이블 생성
create table su_board_comment (
    comment_no    number    null,
    board_no    number    not null,
    member_no    number    not null,
    comment_date    date    null,
    constraint pk_board_comment_no primary key(comment_no),
    constraint fk_board_comment_member_no foreign key(member_no) references su_member(member_no) on delete set null,
    constraint fk_board_comment_board_no foreign key(board_no) references su_board(board_no) on delete cascade
);
 
-- 공지사항 테이블
create table su_notice (
    notice_no    number    not null,
    member_no    number    not null,
    notice_title    varchar2(300)    not null,
    notice_content    varchar2(4000)    null,
    notice_date    date    null,
    notice_read_count    number    null,
    constraint pk_notice_no primary key (notice_no),
    constraint fk_notice_member_no foreign key(member_no) references su_member(member_no) on delete set null 
);
-- 공지사항 시퀀스
--create sequence seq_notice_no;

-- 공지사항 첨부파일
create table su_notice_attachment (
    notice_attachment_no    number    null,
    notice_no    number    not null,
    notice_attachment_original_filename    varchar2(255)    not null,
    notice_attachment_rename_filename    varchar2(255)    not null,
    constraint pk_notice_attachment_no primary key(notice_attachment_no),
    constraint fk_notice_attachment_notice_no foreign key(notice_no) references su_notice(notice_no) on delete cascade
);


-- 공지사항 첨부파일 시퀀스
--create sequence seq_notice_attachment;

select * from user_indexes;
select * from user_ind_columns; 
select * from user_sequences;

-- 과목추가
-- 13 14 15
-- 크레딧 1~3
-- 레벨 1 ~ 3
insert into su_subject values('D4-2201S1', '13', 'D4', '트레이닝방법론', '1교시~3교시', '501호', '3', '3', '2022년도 2학기');
insert into su_subject values('D4-2201S2', '13', 'D4', '스포츠사회학', '4교시~6교시', '501호', '3', '3', '2022년도 2학기');
insert into su_subject values('D4-2201S3', '13', 'D4', '현대생활과스포츠', '1교시~3교시', '502호', '3', '4', '2022년도 2학기');

insert into su_subject values('D4-2201S4', '14', 'D4', '배드민턴', '1교시~3교시', '503호', '2', '2', '2022년도 2학기');
insert into su_subject values('D4-2201S5', '14', 'D4', '탁구', '4교시~6교시', '502호', '2', '2', '2022년도 2학기');
insert into su_subject values('D4-2201S6', '14', 'D4', '유아체육', '1교시~3교시', '504호', '3', '3', '2022년도 2학기');

insert into su_subject values('D4-2201S7', '15', 'D4', '수영', '1교시~3교시', '505호', '2', '4', '2022년도 2학기');
insert into su_subject values('D4-2201S8', '15', 'D4', '댄스스포츠', '4교시~6교시', '505호', '2', '2', '2022년도 2학기');
insert into su_subject values('D4-2201S9', '15', 'D4', '유도', '1교시~3교시', '506호', '3', '2', '2022년도 2학기');

--------------------------------------------------------------------------

insert into su_subject values('D4-2202S1', '13', 'D4', '댄스스포츠', '1교시~3교시', '501호', '3', '3', '2022년도 1학기');
insert into su_subject values('D4-2202S2', '13', 'D4', '육상', '4교시~6교시', '501호', '3', '3', '2022년도 1학기');
insert into su_subject values('D4-2202S3', '13', 'D4', '윈드서핑', '1교시~3교시', '502호', '3', '4', '2022년도 1학기');

insert into su_subject values('D4-2202S4', '14', 'D4', '태권도', '1교시~3교시', '503호', '2', '2', '2022년도 1학기');
insert into su_subject values('D4-2202S5', '14', 'D4', '운동생리학', '4교시~6교시', '502호', '2', '2', '2022년도 1학기');
insert into su_subject values('D4-2202S6', '14', 'D4', '실버체육', '1교시~3교시', '504호', '3', '3', '2022년도 1학기');

insert into su_subject values('D4-2202S7', '15', 'D4', '교육학개론', '1교시~3교시', '505호', '2', '4', '2022년도 1학기');
insert into su_subject values('D4-2202S8', '15', 'D4', '구급 및 안전관리', '4교시~6교시', '505호', '2', '2', '2022년도 1학기');
insert into su_subject values('D4-2202S9', '15', 'D4', '실기교육방법론', '1교시~3교시', '506호', '3', '2', '2022년도 1학기');