package kr.spring.commentQA.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.commentQA.dao.CommentQAMapper;
import kr.spring.commentQA.vo.CommentQAVO;

@Service("commentQAService")
public class CommentQAServiceImpl implements CommentQAService {

	@Resource
	CommentQAMapper commentQAMapper;
	
	@Override
	public void insertComment(CommentQAVO commentQA) {
		commentQAMapper.insertComment(commentQA);
	}

	@Override
	public List<CommentQAVO> commentQAList(Map<String, Object> map) {
		return commentQAMapper.commentQAList(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return commentQAMapper.selectRowCountReply(map);
	}

	@Override
	public void updateComment(CommentQAVO commentQA) {
		commentQAMapper.updateComment(commentQA);
	}

	@Override
	public void deleteComment(Integer qa_comm_num) {
		commentQAMapper.deleteComment(qa_comm_num);
	}

}
