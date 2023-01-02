고객관리
create table customer(
	id          number constraint customer_id_pk primary key,
	name        varchar2(50) not null,
	gender      varchar2(3) default '남' not null,
	email       varchar2(50),
	phone       varchar2(13)
); 

customer 테이블의 pk인 id에 적용할 시퀀스
create sequence seq_customer
start with 1 increment by 1 nocache; 

insert into customer(id, name, email)
values(seq_customer.nextval, '홍길동', 'hong@naver.com');

insert into customer(id, name, email)
values(seq_customer.nextval, '홍길동', 'hong@naver.com');

insert into customer(name, email)
values( '심청', 'sim@naver.com');
commit;

select * from customer;

create or replace trigger trg_customer 
	before insert on customer
	for each row
begin
	select seq_customer.nextval into :new.id from dual; 
end;
/



create table member(
	userid     varchar2(50) constraint member_userid_pk primary key,
	userpw     varchar2(300),
	name       varchar2(50) not null,
	gender     varchar2(3) default '여' not null,
	email      varchar2(50),
	birth      date,
	phone      varchar2(13),
	post       varchar2(5),
	address    varchar2(300),
	admin      varchar2(1) default 'N' ,
    social     varchar2(1) /*N:네이버, K:카카오*/,
    profile    varchar2(300)  /*프로필 이미지*/
);
alter table member add(profile varchar2(300) );
alter table member add (social     varchar2(1));
alter table member modify( email null, userpw null);


insert into member(userid, userpw, name, email, admin)
values ('admin','manager','관리자','admin@hanuledu.co.kr','Y');
insert into member(userid, userpw, name, email, admin)
values ('admin2','manager','운영자','admin2@hanuledu.co.kr','Y');
commit;
insert into member(userid, userpw, name, email)
values ('hong2022','Hong2022','홍길동','hong2022@naver.com');
select * from member where userid='admin' and userpw='manager2';

-- id 존재여부확인
select count(userid)
from member 
where userid='admin3';

select * from member;

--공지글관리 
create table notice (
id        number, 				/*PK*/
title     varchar2(300) not null, 		/*제목*/
content   varchar2(4000) not null, 		/*내용*/
writer    varchar2(50) not null,			/*작성자의 id*/
writedate date default sysdate, /*작성일자*/
readcnt   number default 0,     /*조회수*/
filename  varchar2(300),   /*첨부파일명*/ 
filepath  varchar2(300),   /*실제업로드된첨부파일*/ 
root      number,  /*답글:원글의 root*/
step      number default 0,  /*답글:순서*/
indent    number default 0,  /*답글:들여쓰기*/
constraint notice_id_pk primary key(id),
constraint notice_writer_fk foreign key(writer) references member(userid)
);

--답글관련 컬럼 추가
alter table notice add (
root      number,  /*답글:원글의 root*/
step      number default 0,  /*답글:순서*/
indent    number default 0  /*답글:들여쓰기*/
);

select id, root, step, indent
from notice;
--답글처리를 위해 원글에 해당하는 기존글들의 root 를 id로 변경해둔다
update notice set root = id;
commit;

--첨부파일관련 컬럼 추가
alter table notice add (
filename  varchar2(300),   /*첨부파일명*/ 
filepath  varchar2(300),   /*실제업로드된첨부파일*/ 
);

alter table notice modify (title not null, content not null, writer not null);

--notice 테이블의 pk인 id 컬럼에 적용할 시퀀스
create sequence seq_notice
start with 1 increment by 1 nocache;		
		
alter table notice 
add constraint notice_writer_fk foreign key(writer) references member(userid);        

insert into notice(id, title, content, writer)
values (seq_notice.nextval, '테스트 공지글', '테스트용 공지글입니다', 'admin');
commit;



insert into notice(title, content, writer)
values ('두번째 공지글', '두번째 공지글입니다', 'admin');
commit;
select * from notice;

--notice 테이블의 pk인 id 컬럼에 시퀀스값 자동적용
create or replace trigger trg_notice
	before insert on notice
	for each row
begin
	select seq_notice.nextval into :new.id from dual;
end;
/

insert into notice(title, content, writer)
values ('세번째 공지글', '세번째 공지글입니다', 'admin2');
commit;


update member set userpw = 'admin'
where userpw is not null;
commit;

select userid, email, social, userpw, salt from member ; --where userid='admin' and userpw='manager1';
update member set email='ojink2@naver.com' where userid='hong2022';
commit;

delete from member where social='N';
commit;
update member set email = 'ojink2@naver.com' where admin ='Y';
commit;

alter table member add ( salt varchar2(300) );
desc member;

--조회한 순서에 해당하는 컬럼: rownum
select rownum no, n.* 
from (select n.*,  name
        from notice n left outer join member m on n.writer = m.userid
        order by n.id) n
order by no desc    ;

--순위:  rank() over(기준), dense_rank() over(기준)
--순서: row_number() over(기준)
select row_number() over(order by n.id) no, n.*, name 
from notice n left outer join member m on n.writer = m.userid
order by no desc
;

desc notice;






--페이지처리를 위해 데이터를 여러건 저장해둔다
insert into notice(title, content, writer)
select title, content, writer from notice;
commit;


select * 
from (select row_number() over(order by n.id) no, n.*, name 
      from notice n left outer join member m on n.writer = m.userid)
where no between 343 and 352
order by no desc
;







