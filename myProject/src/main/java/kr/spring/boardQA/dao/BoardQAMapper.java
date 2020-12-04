package kr.spring.boardQA.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.boardQA.vo.BoardQAVO;

public interface BoardQAMapper {
	public List<BoardQAVO> selectList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM board_qa b JOIN member m ON b.mem_num = m.mem_num")
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO board_qa (qa_num, mem_num, qa_title, qa_content, qa_file, qa_filename, qa_secret) VALUES (qa_seq.nextval, #{mem_num}, #{qa_title}, #{qa_content}, #{qa_file}, #{qa_filename}, #{qa_secret})")
	public void insertQA(BoardQAVO qa);
	@Select("SELECT * FROM board_qa b JOIN spmember m ON b.mem_num=m.mem_num WHERE b.qa_num=#{qa_num}")
	public BoardQAVO selectQA(Integer qa_num);
	public void updateQA(BoardQAVO qa);
	@Delete("DELETE FROM board_qa WHERE qa_num=#{qa_num}")
	public void deleteQA(Integer qa_num);
	public List<BoardQAVO> selectQAListMember(Map<String,Object> map);
	public int selectRowCountMember(Map<String,Object> map);
}
