package kr.spring.boardNotice.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class BoardNoticeVO {
	private int notice_num; //게시판 번호
	private int mem_num;//회원 번호
	@NotEmpty
	private String notice_title; //제목
	@NotEmpty
	private String notice_content; //내용
	private Date notice_date; //등록일
	private byte[] notice_file;//이미지 파일
	private String notice_filename;//파일명
	private String id;//회원 아이디
	
	//이미지 업로드 파일 처리
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 반환
		setNotice_file(upload.getBytes());
		//파일명 구하기
		setNotice_filename(upload.getOriginalFilename());
	}
	
	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	public byte[] getNotice_file() {
		return notice_file;
	}
	public void setNotice_file(byte[] notice_file) {
		this.notice_file = notice_file;
	}
	public String getNotice_filename() {
		return notice_filename;
	}
	public void setNotice_filename(String notice_filename) {
		this.notice_filename = notice_filename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "BoardNoticeVO [notice_num=" + notice_num + ", mem_num=" + mem_num + ", notice_title=" + notice_title
				+ ", notice_content=" + notice_content + ", notice_date=" + notice_date + ", notice_filename="
				+ notice_filename + ", id=" + id + "]";
	}
	
	
}
