package kr.spring.commentFree.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import kr.spring.commentFree.vo.CommmentFreeVO;

public interface CommentFreeMapper {
	@Insert("INSERT INTO comment_free(free_com_id, mem_num, free_com_content, board_num) VALUES (comment_free_seq.nextval, #{mem_num}, #{free_com_content}, #{board_num})")
	public void insertComment(CommmentFreeVO commentFree);
	public List<CommmentFreeVO> commentFreeList(Integer board_num);
}
