package kr.spring.boardQA.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.boardQA.dao.BoardQAMapper;
import kr.spring.boardQA.vo.BoardQAVO;

@Service("qaService")
public class BoardQAServiceImpl implements BoardQAService{
	
	@Resource
	BoardQAMapper qaMapper;

	@Override
	public List<BoardQAVO> selectList(Map<String, Object> map) {
		return qaMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return qaMapper.selectRowCount(map);
	}

	@Override
	public void insertQA(BoardQAVO qa) {
		qaMapper.insertQA(qa);
	}

	@Override
	public BoardQAVO selectQA(Integer qa_num) {
		return qaMapper.selectQA(qa_num);
	}

	@Override
	public void updateQA(BoardQAVO qa) {
		qaMapper.updateQA(qa);		
	}

	@Override
	public void deleteQA(Integer qa_num) {
		qaMapper.deleteQA(qa_num);		
	}

	@Override
	public List<BoardQAVO> selectQAListMember(Map<String, Object> map) {
		return qaMapper.selectQAListMember(map);
	}

	@Override
	public int selectRowCountMember(Map<String, Object> map) {
		return qaMapper.selectRowCountMember(map);
	}
	
}