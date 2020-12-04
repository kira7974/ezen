package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;

public interface BoardMapper {
	public List<BoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO spboard (board_num, title, content, uploadfile, filename, ip, mem_num) VALUES (board_seq.nextval,#{title},#{content},#{uploadfile},#{filename},#{ip},#{mem_num})")
	public void insertBoard(BoardVO board);
	@Select("SELECT * FROM spboard b JOIN member m ON b.mem_num=m.mem_num WHERE b.board_num=#{board_num}")
	public BoardVO selectBoard(Integer board_num);
	@Update("UPDATE spboard SET hit=hit+1 WHERE board_num=#{board_num}")
	public void updateHit(Integer board_num);
	public void updateBoard(BoardVO board);
	@Delete("DELETE FROM spboard WHERE board_num=#{board_num}")
	public void deleteBoard(Integer board_num);
	public List<BoardVO> selectListMember(Map<String,Object> map);
	public int selectRowCountMember(Map<String,Object> map);
}