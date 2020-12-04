package kr.spring.commentFree.service;

import java.util.List;
import java.util.Map;

import kr.spring.commentFree.vo.CommentFreeVO;

public interface CommentFreeService {
	public void insertComment(CommentFreeVO commentFree);
	public List<CommentFreeVO> commentFreeList(Map<String,Object> map);
	public int selectRowCountReply(Map<String,Object> map);
	public void updateComment(CommentFreeVO free);
	public void deleteComment(Integer free_com_id);
}
