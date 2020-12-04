package kr.spring.boardQA.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.vo.BoardVO;
import kr.spring.boardQA.service.BoardQAService;
import kr.spring.boardQA.vo.BoardQAVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class BoardQAController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	BoardQAService qaService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public BoardQAVO initCommand() {
		return new BoardQAVO();
	}

	//질문리스트
	@RequestMapping("/boardQA/listQA.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")int currentPage, 
			@RequestParam(value="keyfield",defaultValue="")	String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//질문 갯수
		int count = qaService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 10, 10, "listQA.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<BoardQAVO> list = null;
		if(count > 0) {
			list = qaService.selectList(map);

			if(log.isDebugEnabled()) {
				log.debug("<<질문 목록>> : " + list);
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardQAList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}

	//질문 작성 폼
	@RequestMapping(value="/boardQA/writeQA.do", method=RequestMethod.GET)
	public String form() {
		return "boardQAWrite";
	}

	//질문 등록
	@RequestMapping(value="/boardQA/writeQA.do", method=RequestMethod.POST)
	public String submit(@Valid BoardQAVO qaVO, BindingResult result, HttpServletRequest request, HttpSession session) {
		if(log.isDebugEnabled()) {
			log.debug("<<공지사항 글 저장>> : " + qaVO);
		}

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "boardQAWrite";
		}

		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		qaVO.setMem_num(user.getMem_num());

		//글쓰기
		qaService.insertQA(qaVO);

		return "redirect:/boardNotice/listQA.do";
	}

	//질문 상세
	@RequestMapping(value="/boardQA/detailQA.do",method=RequestMethod.GET)
	public ModelAndView process(@RequestParam int qa_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<글 상세>> : " + qa_num);
		}

		BoardQAVO qa = qaService.selectQA(qa_num);

		return new ModelAndView("boardQAView", "qa", qa);
	}

	//이미지 출력
	@RequestMapping("/boardQA/imageView.do")
	public ModelAndView imageView(@RequestParam int qa_num) {
		BoardQAVO qa = qaService.selectQA(qa_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", qa.getQa_file());
		mav.addObject("filename", qa.getQa_filename());

		return mav;
	}

	//질문 수정 폼
	@RequestMapping(value="/boardQA/updateQA.do",method=RequestMethod.GET)
	public String form(@RequestParam int qa_num, Model model) {
		BoardQAVO qaVO = qaService.selectQA(qa_num);

		model.addAttribute("boardQAVO", qaVO);

		return "boardQAModify";
	}

	//질문 수정
	@RequestMapping(value="/boardQA/updateQA.do",method=RequestMethod.POST)
	public String submitUpdate(@Valid BoardQAVO qaVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		if(log.isDebugEnabled()) {
			log.debug("<<질문 수정>> : " + qaVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "boardQAModify";
		}

		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		qaVO.setMem_num(user.getMem_num());

		//질문 수정
		qaService.updateQA(qaVO);

		//View에 표시할 메시지
		model.addAttribute("message", "질문내역 수정");
		model.addAttribute("url", request.getContextPath()+"/boardQA/listQA.do");

		//타일스 설정에 아래 뷰이름이 없으면 단독으로 JSP 호출
		return "common/result";
	}

	//질문 삭제
	@RequestMapping("/boardQA/deleteQA.do")
	public String submitDelete(@RequestParam int qa_num, Model model, HttpServletRequest request) {
		if(log.isDebugEnabled()) {
			log.debug("<<질문 삭제>> : " + qa_num);
		}

		//삭제
		qaService.deleteQA(qa_num);

		model.addAttribute("messages", "질문 삭제");
		model.addAttribute("url", request.getContextPath()+"/boardQA/listQA.do");

		return "common/result";
	}
	
	//마이페이지 질문게시판 작성내역
	@RequestMapping("/member/myBoardQA.do")
	public ModelAndView processMyBoardQA(@RequestParam(value="pageNum",defaultValue="1")int currentPage, HttpSession session) {

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		map.put("mem_num", vo.getMem_num());

		//총 글의 갯수 또는 검색된 글의 갯수
		int count = qaService.selectRowCountMember(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(currentPage,count,10,10,"myBoardQA.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<BoardQAVO> list = null;
		if(count > 0) {
			list = qaService.selectQAListMember(map);

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