package kr.spring.commentMarket.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.commentMarket.service.CommentMarketService;
import kr.spring.commentMarket.vo.CommentMarketVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class CommentMarketAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private CommentMarketService commentMarketService;
	
	//댓글 등록
	@RequestMapping("/boardMarket/commentMarketWrite.do")
	@ResponseBody
	public Map<String, String> submitComment(CommentMarketVO marketVO, HttpSession session) {
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user!=null) {
			marketVO.setMem_num(user.getMem_num());
			commentMarketService.insertComment(marketVO);
			
			map.put("result", "success");
		} else {
			map.put("result", "logout");
		}
		return map;
	}
	
	//댓글 출력
	@RequestMapping("/boardMarket/commentMarketList.do")
	@ResponseBody
	public Map<String,Object> commentList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam("market_num") int market_num) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("market_num", market_num);
		
		//총 글의 갯수
		int count = commentMarketService.selectRowCountReply(map);
		
		PagingUtil page = new PagingUtil(currentPage,count,
				rowCount,pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<CommentMarketVO> list = null;
		if(count > 0) {
			list = commentMarketService.commentMarketList(map);
		} else {
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
	@RequestMapping("/boardMarket/deleteReply.do")
	@ResponseBody
	public Map<String,String> deleteReply(
			@RequestParam("market_comm_num") int market_comm_num,
			@RequestParam("mem_num") int mem_num,
			HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<market_comm_num>> : " + market_comm_num);
			log.debug("<<mem_num>> : " + mem_num);
		}
		Map<String,String> map = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			//로그인이 되어있지 않음
			map.put("result", "logout");
		} else if (user!=null && user.getMem_num()==mem_num) {
			//로그인 되어 있고 로그인한 아이디와 작성자 아이디 일치
			commentMarketService.deleteComment(market_comm_num);
			map.put("result", "success");
		} else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	
	//댓글 수정
	@RequestMapping("/boardMarket/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(CommentMarketVO marketVO, HttpSession session, HttpServletRequest request) {
		if(log.isDebugEnabled()) {
			log.debug("<<CommentMarketVO>> : " + marketVO);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			//로그인이 안 되어있는 경우
			map.put("result", "logout");
		} else if(user!=null && user.getMem_num()==marketVO.getMem_num()) {
			//로그인 회원 번호와 작성자 회원번호 일치

			//댓글 수정
			commentMarketService.updateComment(marketVO);
			map.put("result", "success");
		} else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		return map;
	}
}
