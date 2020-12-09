package kr.spring.commentQA.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.commentQA.vo.CommentQAVO;

public interface CommentQAMapper {
	@Insert("INSERT INTO comment_qa(qa_comm_num, mem_num, qa_comm_content, qa_num) VALUES (comment_qa_seq.nextval, #{mem_num}, #{qa_comm_content}, #{qa_num})")
	public void insertComment(CommentQAVO commentQA);
	public List<CommentQAVO> commentQAList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM comment_qa q JOIN spmember b ON q.mem_num=b.mem_num WHERE qa_num=#{qa_num}")
	public int selectRowCountReply(Map<String,Object> map);
	@Update("UPDATE comment_qa SET qa_comm_content=#{qa_comm_content} WHERE qa_comm_num=#{qa_comm_num}")
	public void updateComment(CommentQAVO commentQA);
	@Delete("DELETE FROM comment_qa WHERE qa_comm_num=#{qa_comm_num}")
	public void deleteComment(Integer qa_comm_num);
	
	@Delete("DELETE FROM comment_qa WHERE qa_num=#{qa_num}")
	public void deleteReplyByQANum(Integer qa_num);
}
