create table member(
  mem_num number not null,
  id varchar2(12) unique not null,
  auth number(1) default 2 not null,/*0탈퇴회원, 1일반회원, 2관리자*/
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
