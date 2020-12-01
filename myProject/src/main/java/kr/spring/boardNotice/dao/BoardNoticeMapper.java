package kr.spring.boardNotice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.boardNotice.vo.BoardNoticeVO;

public interface BoardNoticeMapper {
	public List<BoardNoticeVO> selectList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM board_notice b JOIN spmember m ON b.mem_num = m.mem_num")
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO board_notice (notice_num, mem_num, notice_title, notice_content, notice_file, notice_filename) VALUES (notice_seq.nextval, #{mem_num}, #{notice_title}, #{notice_content}, #{notice_file}, #{notice_filename})")
	public void insertNotice(BoardNoticeVO notice);
	@Select("SELECT * FROM board_notice b JOIN spmember m ON b.mem_num=m.mem_num WHERE b.notice_num=#{notice_num}")
	public BoardNoticeVO selectNotice(Integer notice_num);
	public void updateNotice(BoardNoticeVO notice);
	public void deleteNotice(Integer notice_num);
}
