package kr.spring.commentQA.vo;

public class CommentQAVO {
	private int qa_comm_num;
	private int mem_num;
	private String qa_comm_content;
	private String qa_comm_date;
	private int qa_num;
	private String id;
	
	
	
	public int getQa_comm_num() {
		return qa_comm_num;
	}
	public void setQa_comm_num(int qa_comm_num) {
		this.qa_comm_num = qa_comm_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getQa_comm_content() {
		return qa_comm_content;
	}
	public void setQa_comm_content(String qa_comm_content) {
		this.qa_comm_content = qa_comm_content;
	}
	public String getQa_comm_date() {
		return qa_comm_date;
	}
	public void setQa_comm_date(String qa_comm_date) {
		this.qa_comm_date = qa_comm_date;
	}
	public int getQa_num() {
		return qa_num;
	}
	public void setQa_num(int qa_num) {
		this.qa_num = qa_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CommentQAVO [qa_comm_num=" + qa_comm_num + ", mem_num=" + mem_num + ", qa_comm_content="
				+ qa_comm_content + ", qa_comm_date=" + qa_comm_date + ", qa_num=" + qa_num + ", id=" + id + "]";
	}
	
	
	
	
	
}
