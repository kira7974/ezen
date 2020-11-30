package kr.spring.boardQA.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class BoardQAController {
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/boardQA/listQA.do")
	public String form() {
		return "boardQAList";
	}

	@RequestMapping(value="/boardQA/writeQA.do", method=RequestMethod.GET)
	public String form2() {
		return "boardQAWrite";
	}

	//마이페이지 질문게시판 작성내역
	@RequestMapping("/member/myBoardQA.do")
	public ModelAndView processMyBoardQA(@RequestParam(value="pageNum",defaultValue="1")int currentPage, HttpSession session) {

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		map.put("mem_num", vo.getMem_num());

		//총 글의 갯수 또는 검색된 글의 갯수
		int count = boardQAService.selectRowCountMember(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(currentPage,count,10,10,"myBoardQA.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<BoardVO> list = null;
		if(count > 0) {
			list = boardQAService.selectListMember(map);

			if(log.isDebugEnabled()) {
				log.debug("<<글 목록>> : " + list);
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("memberBoardQA");
		mav.addObject("count",count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	
}