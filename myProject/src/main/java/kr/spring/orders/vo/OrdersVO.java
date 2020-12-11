package kr.spring.orders.vo;

import java.sql.Date;

public class OrdersVO {
	private int orders_id;	//아이디 - 시퀀스
	private int mem_num;	//구매 요청자
	private int valid;		//거래 진행 여부
	private int market_num;	//중고거래 게시판 글번호
	private String market_title;
	private Date market_date;
	
	public int getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public int getMarket_num() {
		return market_num;
	}
	public void setMarket_num(int market_num) {
		this.market_num = market_num;
	}
	public String getMarket_title() {
		return market_title;
	}
	public void setMarket_title(String market_title) {
		this.market_title = market_title;
	}
	public Date getMarket_date() {
		return market_date;
	}
	public void setMarket_date(Date market_date) {
		this.market_date = market_date;
	}
	@Override
	public String toString() {
		return "OrdersVO [orders_id=" + orders_id + ", mem_num=" + mem_num + ", valid=" + valid + ", market_num="
				+ market_num + ", market_title=" + market_title + ", market_date=" + market_date + "]";
	}
	
}