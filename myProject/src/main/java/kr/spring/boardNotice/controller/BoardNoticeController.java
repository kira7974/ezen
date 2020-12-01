package kr.spring.boardNotice.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.boardNotice.service.BoardNoticeService;
import kr.spring.boardNotice.vo.BoardNoticeVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class BoardNoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	BoardNoticeService noticeService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public BoardNoticeVO initCommand() {
		return new BoardNoticeVO();
	}
	
	//공지사항 목록
	@RequestMapping("/boardNotice/listNotice.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")int currentPage, 
								@RequestParam(value="keyfield",defaultValue="")	String keyfield,
								@RequestParam(value="keyword",defaultValue="") String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(keyfield, "keyfield");
		map.put(keyword, "keyword");
		
		//공지사항 갯수
		int count = noticeService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 10, 10, "listNotice.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<BoardNoticeVO> list = null;
		if(count > 0) {
			list = noticeService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<공지사항 목록>> : " + list);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardNoticeList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//공지사항 등록 폼
	@RequestMapping(value="/boardNotice/writeNotice.do", method=RequestMethod.GET)
	public String form() {
		return "boardNoticeWrite";
	}
	
	//공지사항 등록
	@RequestMapping(value="/boardNotice/writeNotice.do", method=RequestMethod.POST)
	public String submit(@Valid BoardNoticeVO noticeVO, BindingResult result, HttpServletRequest request, HttpSession session) {
		if(log.isDebugEnabled()) {
			log.debug("<<공지사항 글 저장>> : " + noticeVO);
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "boardNoticeWrite";
		}
		
		//회원 번호 셋팅
		MemberVO vo = (MemberVO)session.getAttribute("user");
		noticeVO.setMem_num(vo.getMem_num());
		
		//글쓰기
		noticeService.insertNotice(noticeVO);
		
		return "redirect:/boardNotice/listNotice.do";
	}
	
	//공지사항 상세
	@RequestMapping(value="/boardNotice/detailNotice.do",method=RequestMethod.GET)
	public ModelAndView process(@RequestParam int notice_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<글 상세>> : " + notice_num);
		}
		
		BoardNoticeVO notice = noticeService.selectNotice(notice_num);
		
		return new ModelAndView("boardNoticeView", "notice", notice);
	}
}
