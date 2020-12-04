package kr.spring.boardMarket.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class BoardMarketVO {
	private int market_num;
	private int mem_num;
	@NotEmpty
	private String market_title;
	@NotEmpty
	private String market_content;
	private byte[] market_file;
	private String market_filename;
	private Date market_date;
	private String market_itemname;
	private String market_price;
	private String id;

	//이미지 업로드 파일 처리
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 반환
		setMarket_file(upload.getBytes());
		//파일명 구하기
		setMarket_filename(upload.getOriginalFilename());
	}

	public int getMarket_num() {
		return market_num;
	}
	public void setMarket_num(int market_num) {
		this.market_num = market_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMarket_title() {
		return market_title;
	}
	public void setMarket_title(String market_title) {
		this.market_title = market_title;
	}
	public String getMarket_content() {
		return market_content;
	}
	public void setMarket_content(String market_content) {
		this.market_content = market_content;
	}
	public byte[] getMarket_file() {
		return market_file;
	}
	public void setMarket_file(byte[] market_file) {
		this.market_file = market_file;
	}
	public String getMarket_filename() {
		return market_filename;
	}
	public void setMarket_filename(String market_filename) {
		this.market_filename = market_filename;
	}
	public Date getMarket_date() {
		return market_date;
	}
	public void setMarket_date(Date market_date) {
		this.market_date = market_date;
	}
	public String getMarket_itemname() {
		return market_itemname;
	}
	public void setMarket_itemname(String market_itemname) {
		this.market_itemname = market_itemname;
	}
	public String getMarket_price() {
		return market_price;
	}
	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BoardMarketVO [market_num=" + market_num + ", mem_num=" + mem_num + ", market_title=" + market_title
				+ ", market_content=" + market_content + ", market_filename=" + market_filename + ", market_date="
				+ market_date + ", market_itemname=" + market_itemname + ", market_price=" + market_price + ", id=" + id
				+ "]";
	}


}
