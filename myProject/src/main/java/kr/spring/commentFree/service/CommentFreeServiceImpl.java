package kr.spring.commentFree.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.commentFree.dao.CommentFreeMapper;
import kr.spring.commentFree.vo.CommentFreeVO;

@Service("commentFreeService")
public class CommentFreeServiceImpl implements CommentFreeService {
	
	@Resource
	CommentFreeMapper commentFreeMapper;

	@Override
	public void insertComment(CommentFreeVO commentFree) {
		commentFreeMapper.insertComment(commentFree);
	}

	@Override
	public List<CommentFreeVO> commentFreeList(Map<String,Object> map) {
		return commentFreeMapper.commentFreeList(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return commentFreeMapper.selectRowCountReply(map);
	}

	@Override
	public void updateComment(CommentFreeVO free) {
		commentFreeMapper.updateComment(free);		
	}

	@Override
	public void deleteComment(Integer free_com_id) {
		commentFreeMapper.deleteComment(free_com_id);
	}


}
