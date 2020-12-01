package kr.spring.boardNotice.service;

import java.util.List;
import java.util.Map;

import kr.spring.boardNotice.vo.BoardNoticeVO;

public interface BoardNoticeService {
	public List<BoardNoticeVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertNotice(BoardNoticeVO notice);
	public BoardNoticeVO selectNotice(Integer notice_num);
	public void updateNotice(BoardNoticeVO notice);
	public void deleteNotice(Integer notice_num);
}
