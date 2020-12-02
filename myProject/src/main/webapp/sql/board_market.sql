create table board_market(
	market_num number(5) not null,
	mem_num number(5) not null,
	market_title varchar2(100) not null,
	market_content clob not null,
	market_file	blob,
	market_filename varchar2(100),
	market_date	date default sysdate not null,
	market_itemname varchar2(50) not null,
	market_price number(20) not null,
	constraint board_market_pk primary key (market_num),
	constraint board_market_fk foreign key (mem_num) references spmember(mem_num)
);

create sequence market_seq;