/*테이블 생성*/
/*회원관리*/
create table member(
  mem_num number not null,
  id varchar2(12) unique not null,
  auth number(1) default 2 not null,	/*0탈퇴회원, 1일반회원, 2관리자*/
  constraint member_pk primary key (mem_num)
);

create table member_detail(
  mem_num number not null,
  name varchar2(30) not null,
  passwd varchar2(35) not null,
  phone varchar2(15) not null,
  email varchar2(50) not null,
  zipcode varchar2(5) not null,
  address varchar2(90) not null,
  reg_date date default sysdate not null,
  constraint member_detail_pk primary key (mem_num),
  constraint member_detail_fk foreign key (mem_num) references member (mem_num)
);

/*제품정보*/
/*스마트폰*/
create table item_phone(
	phone_num number(5) not null,
	phone_name varchar2(50) not null,
	phone_date varchar2(20) not null,
	phone_apu varchar2(20) not null,
	phone_ram varchar2(10) not null,
	phone_memory varchar2(50) not null,
	phone_display varchar2(10) not null,
	phone_company varchar2(15) not null,
	phone_os varchar2(30) not null,
	phone_type varchar2(10) not null,
	phone_content clob not null,
	phone_titleimg blob,
	phone_titleimgname varchar2(100),
	phone_contentimg blob,
	phone_contentimgname varchar2(100),
	reg_date date default sysdate not null,
	mem_num number not null,
	constraint item_phone_pk primary key (phone_num), 
	constraint item_phone_fk foreign key (mem_num) references member (mem_num)
);



/*태블릿*/
create table item_tablet(
	tablet_id number(5)	not null,
	tablet_name	varchar2(50) not null,
	tablet_date	date not null,
	tablet_apu varchar2(20) not null,
	tablet_ram varchar2(10) not null,
	tablet_memory varchar2(50) not null,
	tablet_display varchar2(10) not null,
	tablet_company varchar2(15)	not null,
	tablet_os varchar2(30) not null,
	tablet_type	varchar2(10) not null,
	tablet_content clob not null,
	tablet_titleImg	blob,		
	tablet_contentImg blob,
	constraint item_tablet_pk primary key (tablet_id)
);

/*스마트워치*/
create table item_swatch(
	swatch_id number(5)	not null,
	swatch_name varchar2(50) not null,
	swatch_date date not null,
	swatch_apu varchar2(20)	not null,
	swatch_ram varchar2(10) not null,
	swatch_memory varchar2(50) not null,
	swatch_display varchar2(10)	not null,
	swatch_company varchar2(15)	not null,
	swatch_os varchar2(30) not null,
	swatch_content clob	not null,
	swatch_titleImg blob,
	swatch_contentImg blob,
	constraint item_swatch_pk primary key (swatch_id)
);

/*게시판*/
/*공지사항 게시판*/
create table board_notice(
	notice_num number(5) not null,
	mem_num number(5) not null,
	notice_title varchar2(100) not null,
	notice_content clob not null,
	notice_file	blob,
	notice_filename varchar2(100),
	notice_date	date default sysdate not null,
	constraint board_notice_pk primary key (notice_num),
	constraint board_notice_fk foreign key (mem_num) references member(mem_num)
);

/*자유게시판*/
create table spboard(
	board_num number(5) not null,
	mem_num number(5) not null,
	title varchar2(100) not null,
	content clob not null,
	hit number(5) default 0 not null,
	reg_date date default sysdate not null,
	modify_date date default sysdate not null,
	uploadfile blob,
	filename varchar2(100),
	ip varchar2(40) not null,
	constraint board_pk primary key (board_num),
	constraint board_fk foreign key (mem_num) references member (mem_num)
);

/*질문게시판*/
create table board_qa(
	qa_num number(5) not null,
	mem_num number(5) not null,
	qa_title varchar2(100) not null,
	qa_content clob not null,
	qa_file	blob,
	qa_filename varchar2(100),
	qa_date	date default sysdate not null,
	qa_secret varchar2(10),
	constraint board_qa_pk primary key (qa_num),
	constraint board_qa_fk foreign key (mem_num) references member(mem_num)
);

/*중고거래 게시판*/
create table board_market(
	market_num number(5) not null,
	mem_num number(5) not null,
	market_title varchar2(100) not null,
	market_content clob not null,
	market_file	blob,
	market_filename varchar2(100),
	market_date	date default sysdate not null,
	market_itemname varchar2(50) not null,
	market_price varchar2(20) not null,
	constraint board_market_pk primary key (market_num),
	constraint board_market_fk foreign key (mem_num) references member(mem_num)
);

/*댓글*/
/*자유게시판 댓글*/
create table spcomment(
	comm_num number(5) not null,
	mem_num number(5) not null,
	comm_content varchar2(200) not null,
	comm_date date default sysdate not null,
	board_num number(5) not null,
	constraint comment_pk primary key (comm_num),
	constraint comment_fk1 foreign key (mem_num) references member (mem_num),
	constraint comment_fk2 foreign key (board_num) references spboard (board_num)
);

/*질문게시판 댓글*/
create table comment_qa(
	qa_comm_num number(5) not null,
	mem_num number(5) not null,
	qa_comm_content varchar2(200) not null,
	qa_comm_date date default sysdate not null,
	qa_num number(5) not null,
	constraint qa_comment_pk primary key (qa_comm_num),
	constraint qa_comment_fk1 foreign key (mem_num) references member (mem_num),
	constraint qa_comment_fk2 foreign key (qa_num) references board_qa (qa_num)
);

/*중고거래게시판 댓글*/
create table comment_market(
	market_comm_num number(5) not null,
	mem_num number(5) not null,
	market_comm_content varchar2(200) not null,
	market_comm_date date default sysdate not null,
	market_num number(5) not null,
	constraint market_comment_pk primary key (market_comm_num),
	constraint market_comment_fk1 foreign key (mem_num) references member (mem_num),
	constraint market_comment_fk2 foreign key (market_num) references board_market (market_num)
);

/*시퀀스 생성*/
create sequence member_seq start with 100;
create sequence board_seq;
create sequence notice_seq;
create sequence qa_seq;
create sequence market_seq;
create sequence board_comment_seq;
create sequence board_qa_comment_seq;
create sequence board_market_comment_seq;
create sequence phone_seq;