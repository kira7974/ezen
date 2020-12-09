package kr.spring.commentMarket.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.commentMarket.dao.CommentMarketMapper;
import kr.spring.commentMarket.vo.CommentMarketVO;

@Service("commentMarketService")
public class CommentMarketServiceImpl implements CommentMarketService {
	
	@Resource
	CommentMarketMapper commentMarketMapper;

	@Override
	public void insertComment(CommentMarketVO marketVO) {
		commentMarketMapper.insertComment(marketVO);		
	}

	@Override
	public List<CommentMarketVO> commentMarketList(Map<String, Object> map) {
		return commentMarketMapper.commentMarketList(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return commentMarketMapper.selectRowCountReply(map);
	}

	@Override
	public void updateComment(CommentMarketVO marketVO) {
		commentMarketMapper.updateComment(marketVO);		
	}

	@Override
	public void deleteComment(Integer market_comm_num) {
		commentMarketMapper.deleteComment(market_comm_num);		
	}

}
