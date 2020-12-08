package kr.spring.itemPhone.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ItemPhoneVO {
	private int phone_num;
	private String phone_name;
	private String phone_date;
	private String phone_apu;
	private String phone_ram;
	private String phone_memory;
	private String phone_display;
	private String phone_company;
	private String phone_os;
	private String phone_type;
	private String phone_content;
	private byte[] phone_titleimg;
	private String phone_titleimgname;
	private byte[] phone_contentimg;
	private String phone_contentimgname;
	private Date reg_date;
	private int mem_num;
	private String id;
	
	//이미지 업로드 파일 처리
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 반환
		setPhone_contentimg(upload.getBytes());
		//파일명 구하기
		setPhone_contentimgname(upload.getOriginalFilename());
	}
	
	public void setUpload2(MultipartFile upload)throws IOException {
		//MultipartFile -> byte[] 반환
		setPhone_titleimg(upload.getBytes());
		//파일명 구하기
		setPhone_titleimgname(upload.getOriginalFilename());
	}
	
	public int getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(int phone_num) {
		this.phone_num = phone_num;
	}
	public String getPhone_name() {
		return phone_name;
	}
	public void setPhone_name(String phone_name) {
		this.phone_name = phone_name;
	}
	
	
	public String getPhone_date() {
		return phone_date;
	}

	public void setPhone_date(String phone_date) {
		this.phone_date = phone_date;
	}

	public String getPhone_apu() {
		return phone_apu;
	}
	public void setPhone_apu(String phone_apu) {
		this.phone_apu = phone_apu;
	}
	public String getPhone_ram() {
		return phone_ram;
	}
	public void setPhone_ram(String phone_ram) {
		this.phone_ram = phone_ram;
	}
	public String getPhone_memory() {
		return phone_memory;
	}
	public void setPhone_memory(String phone_memory) {
		this.phone_memory = phone_memory;
	}
	public String getPhone_display() {
		return phone_display;
	}
	public void setPhone_display(String phone_display) {
		this.phone_display = phone_display;
	}
	public String getPhone_company() {
		return phone_company;
	}
	public void setPhone_company(String phone_company) {
		this.phone_company = phone_company;
	}
	public String getPhone_os() {
		return phone_os;
	}
	public void setPhone_os(String phone_os) {
		this.phone_os = phone_os;
	}
	public String getPhone_type() {
		return phone_type;
	}
	public void setPhone_type(String phone_type) {
		this.phone_type = phone_type;
	}
	public String getPhone_content() {
		return phone_content;
	}
	public void setPhone_content(String phone_content) {
		this.phone_content = phone_content;
	}
	public byte[] getPhone_titleimg() {
		return phone_titleimg;
	}
	public void setPhone_titleimg(byte[] phone_titleimg) {
		this.phone_titleimg = phone_titleimg;
	}
	public String getPhone_titleimgname() {
		return phone_titleimgname;
	}
	public void setPhone_titleimgname(String phone_titleimgname) {
		this.phone_titleimgname = phone_titleimgname;
	}
	public byte[] getPhone_contentimg() {
		return phone_contentimg;
	}
	public void setPhone_contentimg(byte[] phone_contentimg) {
		this.phone_contentimg = phone_contentimg;
	}
	public String getPhone_contentimgname() {
		return phone_contentimgname;
	}
	public void setPhone_contentimgname(String phone_contentimgname) {
		this.phone_contentimgname = phone_contentimgname;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ItemPhoneVO [phone_num=" + phone_num + ", phone_name=" + phone_name + ", phone_date=" + phone_date
				+ ", phone_apu=" + phone_apu + ", phone_ram=" + phone_ram + ", phone_memory=" + phone_memory
				+ ", phone_display=" + phone_display + ", phone_company=" + phone_company + ", phone_os=" + phone_os
				+ ", phone_type=" + phone_type + ", phone_content=" + phone_content + ", phone_titleimgname="
				+ phone_titleimgname + ", phone_contentimgname=" + phone_contentimgname + ", reg_date=" + reg_date
				+ ", mem_num=" + mem_num + ", id=" + id + "]";
	}
	
	
	
	
}
