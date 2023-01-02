rename board to staff_board;

--방명록 관리
create table board (
id          number constraint boardid_pk primary key,
title       varchar2(300) not null,
content     varchar2(4000) not null,
writer      varchar2(50) constraint board_writer_fk references member(userid)
                                            --on delete set null,
                                            on delete cascade,
writedate   date default sysdate,
readcnt     number default 0
);

select constraint_name, table_name
from user_constraints
where lower(constraint_name) in  ( 'board_id_pk', 'board_writer_fk');

--방명록 board 의 PK인 id 에 지정할 시퀀스
create sequence seq_board start with 1 increment by 1 nocache;

--방명록 board 의 PK인 id에 시퀀스값 자동지정할 트리거
create or replace trigger trg_board
    before insert  on board
    for each row
begin
    select seq_board.nextval into :new.id from dual;
end;
/

--방명록 첨부파일정보 관리
create table board_file (
id         number constraint board_file_id_pk primary key,
board_id   number constraint board_file_fk references board(id) on delete cascade,
filename   varchar2(300) not null,
filepath   varchar2(500) not null
);

-- 방명록 첨부파일 id에 지정할 시퀀스
create sequence seq_board_file start with 1 increment by 1 nocache;

-- 방명록 첨부파일 id에 시퀀스 자동지정 트리거
create or replace trigger trg_board_file
    before insert on board_file
    for each row
begin
    select seq_board_file.nextval into :new.id from dual;
end;
/


select * from board_file;

-- 댓글정보관리
create table board_comment (
id          number constraint board_comment_id_pk primary key, 
board_id    number constraint board_comment_board_id_fk references board(id) on delete cascade,
content    varchar2(4000) not null,
writer     varchar2(50) not null constraint board_comment_writer_fk 
                references member(userid) on delete cascade,
writedate  date default sysdate
);

--board_comment 테이블의 식별자인 id에 적용할 시퀀스
create sequence seq_board_comment start with 1 increment by 1 nocache;

--board_comment 테이블의 식별자인 id에 시퀀스 자동적용시킬 트리거
create or replace trigger trg_board_comment
    before insert on board_comment
    for each row
begin
    select seq_board_comment.nextval into :new.id from dual;
end;
/







