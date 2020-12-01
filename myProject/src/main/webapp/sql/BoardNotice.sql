create table board_notice(
	notice_num number(5) not null,
	mem_num number(5) not null,
	notice_title varchar2(100) not null,
	notice_content clob not null,
	notice_file	blob,
	notice_filename varchar2(100),
	notice_date	date default sysdate not null,
	constraint board_notice_pk primary key (notice_num),
	constraint board_notice_fk foreign key (mem_num) references spmember(mem_num)
);

create sequence notice_seq;