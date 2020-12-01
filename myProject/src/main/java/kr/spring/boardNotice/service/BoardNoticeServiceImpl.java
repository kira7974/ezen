package kr.spring.boardNotice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.boardNotice.dao.BoardNoticeMapper;
import kr.spring.boardNotice.vo.BoardNoticeVO;

@Service("noticeService")
public class BoardNoticeServiceImpl implements BoardNoticeService {
	
	@Resource
	BoardNoticeMapper noticeMapper;

	@Override
	public List<BoardNoticeVO> selectList(Map<String, Object> map) {
		return noticeMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return noticeMapper.selectRowCount(map);
	}

	@Override
	public void insertNotice(BoardNoticeVO notice) {
		noticeMapper.insertNotice(notice);
		
	}

	@Override
	public BoardNoticeVO selectNotice(Integer notice_num) {
		return noticeMapper.selectNotice(notice_num);
	}

	@Override
	public void updateNotice(BoardNoticeVO notice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNotice(Integer notice_num) {
		// TODO Auto-generated method stub
		
	}

}
