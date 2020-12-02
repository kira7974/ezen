package kr.spring.boardQA.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class BoardQAVO {
	private int qa_num;
	private int mem_num;
	@NotEmpty
	private String qa_title;
	@NotEmpty
	private String qa_content;
	private byte[] qa_file;
	private String qa_filename;
	private Date qa_date;
	private int qs_secret;
	private String id;
	
	//이미지 업로드 파일 처리
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 반환
		setQa_file(upload.getBytes());
		//파일명 구하기
		setQa_filename(upload.getOriginalFilename());
	}

	
	public int getQa_num() {
		return qa_num;
	}
	public void setQa_num(int qa_num) {
		this.qa_num = qa_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getQa_title() {
		return qa_title;
	}
	public void setQa_title(String qa_title) {
		this.qa_title = qa_title;
	}
	public String getQa_content() {
		return qa_content;
	}
	public void setQa_content(String qa_content) {
		this.qa_content = qa_content;
	}
	public byte[] getQa_file() {
		return qa_file;
	}
	public void setQa_file(byte[] qa_file) {
		this.qa_file = qa_file;
	}
	public String getQa_filename() {
		return qa_filename;
	}
	public void setQa_filename(String qa_filename) {
		this.qa_filename = qa_filename;
	}
	public Date getQa_date() {
		return qa_date;
	}
	public void setQa_date(Date qa_date) {
		this.qa_date = qa_date;
	}
	public int getQs_secret() {
		return qs_secret;
	}
	public void setQs_secret(int qs_secret) {
		this.qs_secret = qs_secret;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "BoardQAVO [qa_num=" + qa_num + ", mem_num=" + mem_num + ", qa_title=" + qa_title + ", qa_content="
				+ qa_content + ", qa_filename=" + qa_filename + ", qa_date=" + qa_date + ", qs_secret=" + qs_secret
				+ ", id=" + id + "]";
	}
	
}
