package kr.spring.commentMarket.vo;

public class CommentMarketVO {
	private int market_comm_num;
	private int mem_num;
	private String market_comm_content;
	private String market_comm_date;
	private int market_num;
	private String id;
	
	
	public int getMarket_comm_num() {
		return market_comm_num;
	}
	public void setMarket_comm_num(int market_comm_num) {
		this.market_comm_num = market_comm_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMarket_comm_content() {
		return market_comm_content;
	}
	public void setMarket_comm_content(String market_comm_content) {
		this.market_comm_content = market_comm_content;
	}
	public String getMarket_comm_date() {
		return market_comm_date;
	}
	public void setMarket_comm_date(String market_comm_date) {
		this.market_comm_date = market_comm_date;
	}
	public int getMarket_num() {
		return market_num;
	}
	public void setMarket_num(int market_num) {
		this.market_num = market_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "CommentMarketVO [market_comm_num=" + market_comm_num + ", mem_num=" + mem_num + ", market_comm_content="
				+ market_comm_content + ", market_comm_date=" + market_comm_date + ", market_num=" + market_num
				+ ", id=" + id + "]";
	}	
	
}
