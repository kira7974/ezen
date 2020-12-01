package kr.spring.commentFree.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.commentFree.vo.CommmentFreeVO;

public interface CommentFreeMapper {
	@Insert("INSERT INTO comment_free(free_com_id, mem_num, free_com_content, board_num) VALUES (comment_free_seq.nextval, #{mem_num}, #{free_com_content}, #{board_num})")
	public void insertComment(CommmentFreeVO commentFree);
	@Select("SELECT f.free_com_content,m.id FROM comment_free f left join spboard b on(f.board_num = b.board_num) join spmember m on(f.mem_num = m.mem_num) where b.board_num = #{board_num}")
	public List<CommmentFreeVO> commentFreeList(Integer board_num);
}
