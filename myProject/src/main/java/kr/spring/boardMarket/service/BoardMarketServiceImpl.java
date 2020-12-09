package kr.spring.boardMarket.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.boardMarket.dao.BoardMarketMapper;
import kr.spring.boardMarket.vo.BoardMarketVO;
import kr.spring.commentMarket.dao.CommentMarketMapper;

@Service("marketService")
public class BoardMarketServiceImpl implements BoardMarketService {
	
	@Resource
	BoardMarketMapper marketMapper;
	
	@Resource
	CommentMarketMapper commentMarketMapper;

	@Override
	public List<BoardMarketVO> selectList(Map<String, Object> map) {
		return marketMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return marketMapper.selectRowCount(map);
	}

	@Override
	public void insertMarket(BoardMarketVO market) {
		marketMapper.insertMarket(market);
	}

	@Override
	public BoardMarketVO selectMarket(Integer market_num) {
		return marketMapper.selectMarket(market_num);
	}

	@Override
	public void updateMarket(BoardMarketVO market) {
		marketMapper.updateMarket(market);
	}

	@Override
	public void deleteMarket(Integer market_num) {
		commentMarketMapper.deleteReplyByMarketNum(market_num);
		marketMapper.deleteMarket(market_num);
	}

	@Override
	public List<BoardMarketVO> selectMarketListMember(Map<String, Object> map) {
		return marketMapper.selectMarketListMember(map);
	}

	@Override
	public int selectRowCountMember(Map<String, Object> map) {
		return marketMapper.selectRowCountMember(map);
	}

}