package kr.spring.boardMarket.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.boardMarket.vo.BoardMarketVO;
import kr.spring.boardQA.vo.BoardQAVO;

public interface BoardMarketMapper {
	public List<BoardMarketVO> selectList(Map<String, Object>map);
	@Select("SELECT COUNT(*) FROM board_market b JOIN member m ON b.mem_num = m.mem_num")
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO board_market (market_num, mem_num, market_title, market_content, market_file, market_filename, market_itemname, market_price) VALUES (market_seq.nextval, #{mem_num}, #{market_title}, #{market_content}, #{market_file}, #{market_filename}, #{market_itemname}, #{market_price})")
	public void insertMarket(BoardMarketVO market);
	@Select("SELECT * FROM board_market b JOIN member m ON b.mem_num=m.mem_num WHERE b.market_num=#{market_num}")
	public BoardMarketVO selectMarket(Integer market_num);
	public void updateMarket(BoardMarketVO market);
	@Delete("DELETE FROM board_market WHERE market_num=#{market_num}")
	public void deleteMarket(Integer market_num);
	public List<BoardMarketVO> selectMarketListMember(Map<String,Object> map);
	public int selectRowCountMember(Map<String,Object> map);
	
	public List<BoardMarketVO> selectMarketListMemberBuy(Map<String,Object> map);
	public int selectRowCountMemberBuy(Map<String,Object> map);
}