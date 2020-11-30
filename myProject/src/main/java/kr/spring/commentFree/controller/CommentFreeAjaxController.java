package kr.spring.commentFree.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.vo.BoardVO;
import kr.spring.commentFree.service.CommentFreeService;
import kr.spring.commentFree.vo.CommmentFreeVO;
import kr.spring.member.vo.MemberVO;

@Controller
public class CommentFreeAjaxController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private CommentFreeService commentFreeService;
	
	//댓글 등록
	@RequestMapping("/board/commentFreeWrite.do")
	@ResponseBody
	public Map<String,String> submitComment(CommmentFreeVO freeVO, HttpSession session) {

		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user!=null) {
			freeVO.setMem_num(user.getMem_num());
			commentFreeService.insertComment(freeVO);
			
			map.put("result", "success");
			
		}else {
			map.put("result", "logout");
		}
		return map;
	} 
	
	//댓글 출력
	@RequestMapping("/board/commentFreeList.do")
	@ResponseBody
	public List<CommmentFreeVO> commentFreeList(@RequestParam int board_num) {
		List<CommmentFreeVO> list = commentFreeService.commentFreeList(board_num);
		return list;
	}
	
	//댓글 수정
	
	//댓글 삭제

}
