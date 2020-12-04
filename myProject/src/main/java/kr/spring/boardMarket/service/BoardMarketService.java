package kr.spring.boardMarket.service;

import java.util.List;
import java.util.Map;

import kr.spring.boardMarket.vo.BoardMarketVO;

public interface BoardMarketService {
	public List<BoardMarketVO> selectList(Map<String, Object>map);
	public int selectRowCount(Map<String,Object> map);
	public void insertMarket(BoardMarketVO market);
	public BoardMarketVO selectMarket(Integer market_num);
	public void updateMarket(BoardMarketVO market);
	public void deleteMarket(Integer market_num);
}
