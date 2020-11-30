package kr.spring.commentFree.service;

import java.util.List;

import kr.spring.commentFree.vo.CommmentFreeVO;

public interface CommentFreeService {
	public void insertComment(CommmentFreeVO commentFree);
	public List<CommmentFreeVO> commentFreeList(Integer board_num);
}
