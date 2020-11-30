package kr.spring.commentFree.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.commentFree.dao.CommentFreeMapper;
import kr.spring.commentFree.vo.CommmentFreeVO;

@Service("commentFreeService")
public class CommentFreeServiceImpl implements CommentFreeService {
	
	@Resource
	CommentFreeMapper commentFreeMapper;

	@Override
	public void insertComment(CommmentFreeVO commentFree) {
		commentFreeMapper.insertComment(commentFree);
	}

	@Override
	public List<CommmentFreeVO> commentFreeList(Integer board_num) {
		return commentFreeMapper.commentFreeList(board_num);
	}

}
