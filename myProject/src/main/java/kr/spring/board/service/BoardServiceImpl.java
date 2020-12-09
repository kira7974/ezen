package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardVO;
import kr.spring.commentFree.dao.CommentFreeMapper;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Resource
	BoardMapper boardMapper;
	
	@Resource
	CommentFreeMapper commentFreeMapper;
	
	@Override
	public List<BoardVO> selectList(Map<String, Object> map) {
		return boardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return boardMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(BoardVO board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardVO selectBoard(Integer board_num) {
		return boardMapper.selectBoard(board_num);
	}

	@Override
	public void updateHit(Integer board_num) {
		boardMapper.updateHit(board_num);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(Integer board_num) {
		commentFreeMapper.deleteReplyByBoardNum(board_num);
		boardMapper.deleteBoard(board_num);
	}

	@Override
	public List<BoardVO> selectListMember(Map<String, Object> map) {
		return boardMapper.selectListMember(map);
	}

	@Override
	public int selectRowCountMember(Map<String, Object> map) {
		return boardMapper.selectRowCountMember(map);
	}

}