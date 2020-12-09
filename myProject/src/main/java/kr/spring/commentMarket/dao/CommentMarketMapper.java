package kr.spring.commentMarket.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.commentMarket.vo.CommentMarketVO;


public interface CommentMarketMapper {
	@Insert("INSERT INTO comment_market(market_comm_num, mem_num, market_comm_content, market_num) VALUES (comment_market_seq.nextval, #{mem_num}, #{market_comm_content}, #{market_num})")
	public void insertComment(CommentMarketVO marketVO);
	public List<CommentMarketVO> commentMarketList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM comment_market m JOIN member b ON m.mem_num=b.mem_num WHERE market_num=#{market_num}")
	public int selectRowCountReply(Map<String,Object> map);
	@Update("UPDATE comment_market SET market_comm_content=#{market_comm_content} WHERE market_comm_num=#{market_comm_num}")
	public void updateComment(CommentMarketVO marketVO);
	@Delete("DELETE FROM comment_market WHERE market_comm_num=#{market_comm_num}")
	public void deleteComment(Integer market_comm_num);
	
	@Delete("DELETE FROM comment_market WHERE market_num=#{market_num}")
	public void deleteReplyByMarketNum(Integer market_num);
}
