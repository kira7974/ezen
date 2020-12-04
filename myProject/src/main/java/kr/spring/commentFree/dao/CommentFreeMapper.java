package kr.spring.commentFree.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.commentFree.vo.CommentFreeVO;

public interface CommentFreeMapper {
	@Insert("INSERT INTO comment_free(free_com_id, mem_num, free_com_content, board_num) VALUES (comment_free_seq.nextval, #{mem_num}, #{free_com_content}, #{board_num})")
	public void insertComment(CommentFreeVO commentFree);
	public List<CommentFreeVO> commentFreeList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM comment_free f JOIN member b ON f.mem_num=b.mem_num WHERE board_num=#{board_num}")
	public int selectRowCountReply(Map<String,Object> map);
	@Update("UPDATE comment_free SET free_com_content=#{free_com_content} WHERE free_com_id=#{free_com_id}")
	public void updateComment(CommentFreeVO free);
	@Delete("DELETE FROM comment_free WHERE free_com_id=#{free_com_id}")
	public void deleteComment(Integer free_com_id);
	
	@Delete("DELETE FROM comment_free WHERE board_num=#{board_num}")
	public void deleteReplyByBoardNum(Integer board_num);
}
