package kr.spring.commentFree.controller;

import java.util.Collections;
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
import kr.spring.commentFree.vo.CommentFreeVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class CommentFreeAjaxController {
	private Logger log = Logger.getLogger(this.getClass());

	private int rowCount = 10;
	private int pageCount = 10;

	@Resource
	private CommentFreeService commentFreeService;

	//댓글 등록
	@RequestMapping("/board/commentFreeWrite.do")
	@ResponseBody
	public Map<String,String> submitComment(CommentFreeVO freeVO, HttpSession session) {


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
	public Map<String,Object> commentList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam("board_num") int board_num) {
		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("board_num", board_num);

		//총 글의 갯수
		int count = commentFreeService.selectRowCountReply(map);

		PagingUtil page = new PagingUtil(currentPage,count,
				rowCount,pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());



		List<CommentFreeVO> list = null;
		if(count > 0) {
			list = commentFreeService.commentFreeList(map);
		}else {
			list = Collections.emptyList();
		}

		Map<String,Object> mapJson = 
				new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);

		return mapJson;
	}

	//댓글 삭제
	@RequestMapping("/board/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteReply(
			@RequestParam("free_com_id") int free_com_id,
			@RequestParam("mem_num") int mem_num,
			HttpSession session){

		if(log.isDebugEnabled()) {
			log.debug("<<free_com_id>> : " + free_com_id);
			log.debug("<<mem_num>> : " + mem_num);
		}

		Map<String,String> map = 
				new HashMap<String,String>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			//로그인이 되어있지 않음
			map.put("result", "logout");
		}else if(user!=null && user.getMem_num()==mem_num) {
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			commentFreeService.deleteComment(free_com_id);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}		
		return map;
	}
	//댓글 수정
	@RequestMapping("/board/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(
			CommentFreeVO commentFreeVO,
			HttpSession session,
			HttpServletRequest request){

		if(log.isDebugEnabled()) {
			log.debug("<<boardReplyVO>> : " + 
					commentFreeVO);
		}

		Map<String,String> map = 
				new HashMap<String,String>();

		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		}else if(user!=null && user.getMem_num()==commentFreeVO.getMem_num()){
			//로그인 회원 번호와 작성자 회원번호 일치

			//댓글 수정
			commentFreeService.updateComment(commentFreeVO);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}

		return map;
	}

}
