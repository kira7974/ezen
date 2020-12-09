package kr.spring.commentQA.service;

import java.util.List;
import java.util.Map;

import kr.spring.commentQA.vo.CommentQAVO;


public interface CommentQAService {
	public void insertComment(CommentQAVO commentQA);
	public List<CommentQAVO> commentQAList(Map<String,Object> map);
	public int selectRowCountReply(Map<String,Object> map);
	public void updateComment(CommentQAVO commentQA);
	public void deleteComment(Integer qa_comm_num);
}
