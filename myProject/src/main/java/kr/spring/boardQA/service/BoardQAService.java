package kr.spring.boardQA.service;

import java.util.List;
import java.util.Map;

import kr.spring.boardQA.vo.BoardQAVO;

public interface BoardQAService {
	public List<BoardQAVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertQA(BoardQAVO qa);
	public BoardQAVO selectQA(Integer qa_num);
	public void updateQA(BoardQAVO qa);
	public void deleteQA(Integer qa_num);
	public List<BoardQAVO> selectQAListMember(Map<String,Object> map);
	public int selectRowCountMember(Map<String,Object> map);
}