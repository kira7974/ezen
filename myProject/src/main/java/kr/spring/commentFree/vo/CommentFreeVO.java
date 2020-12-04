package kr.spring.commentFree.vo;

import java.sql.Date;

public class CommentFreeVO {
	private int free_com_id;
	private int mem_num;
	private String free_com_content;
	private String free_com_date;
	private int board_num;
	private String id;
	  
	public int getFree_com_id() {
		return free_com_id;
	}
	public void setFree_com_id(int free_com_id) {
		this.free_com_id = free_com_id;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getFree_com_content() {
		return free_com_content;
	}
	public void setFree_com_content(String free_com_content) {
		this.free_com_content = free_com_content;
	}
	
	public String getFree_com_date() {
		return free_com_date;
	}
	public void setFree_com_date(String free_com_date) {
		this.free_com_date = free_com_date;
	}
	public int getBoard_num() {     
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CommmentFreeVO [free_com_id=" + free_com_id + ", mem_num=" + mem_num + ", free_com_content="
				+ free_com_content + ", free_com_date=" + free_com_date + ", board_num=" + board_num + ", id=" + id
				+ "]";
	}
	
	
	
	
}
