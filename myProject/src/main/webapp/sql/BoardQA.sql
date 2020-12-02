create table board_qa(
	qa_num number(5) not null,
	mem_num number(5) not null,
	qa_title varchar2(100) not null,
	qa_content clob not null,
	qa_file	blob,
	qa_filename varchar2(100),
	qa_date	date default sysdate not null,
	qa_secret number(5),
	constraint board_qa_pk primary key (qa_num),
	constraint board_qa_fk foreign key (mem_num) references spmember(mem_num)
);

create sequence qa_seq;