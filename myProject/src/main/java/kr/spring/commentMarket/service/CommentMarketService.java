package kr.spring.commentMarket.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.commentMarket.vo.CommentMarketVO;



public interface CommentMarketService {
	public void insertComment(CommentMarketVO marketVO);
	public List<CommentMarketVO> commentMarketList(Map<String,Object> map);
	public int selectRowCountReply(Map<String,Object> map);
	public void updateComment(CommentMarketVO marketVO);
	public void deleteComment(Integer market_comm_num);
}
