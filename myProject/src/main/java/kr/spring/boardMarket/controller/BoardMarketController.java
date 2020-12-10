package kr.spring.boardMarket.controller;

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

import kr.spring.boardMarket.dao.BoardMarketMapper;
import kr.spring.boardMarket.service.BoardMarketService;
import kr.spring.boardMarket.vo.BoardMarketVO;
import kr.spring.boardQA.vo.BoardQAVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class BoardMarketController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	BoardMarketService marketService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public BoardMarketVO initCommand() {
		return new BoardMarketVO();
	}
	
	//중고거래 리스트
	@RequestMapping("/boardMarket/listMarket.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")int currentPage, 
								@RequestParam(value="keyfield",defaultValue="")	String keyfield,
								@RequestParam(value="keyword",defaultValue="") String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//중고거래 갯수
		int count = marketService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 10, 10, "listMarket.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<BoardMarketVO> list = null;
		if(count > 0) {
			list = marketService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<중고거래 목록>> : " + list);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardMarketList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//중고거래 작성 폼
	@RequestMapping(value="/boardMarket/writeMarket.do", method=RequestMethod.GET)
	public String form() {
		return "boardMarketWrite";
	}
	
	//중고거래 등록
	@RequestMapping(value="/boardMarket/writeMarket.do", method=RequestMethod.POST)
	public String submit(@Valid BoardMarketVO marketVO, BindingResult result, HttpServletRequest request, HttpSession session) {
		if(log.isDebugEnabled()) {
			log.debug("<<중고거래 글 저장>> : " + marketVO);
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "boardMarketWrite";
		}
		
		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		marketVO.setMem_num(user.getMem_num());
		
		//글쓰기
		marketService.insertMarket(marketVO);
		
		return "redirect:/boardMarket/listMarket.do";
	}
	
	//중고거래 상세보기
	@RequestMapping(value="/boardMarket/detailMarket.do",method=RequestMethod.GET)
	public ModelAndView process(@RequestParam int market_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<중고거래 상세>> : " + market_num);
		}
		
		BoardMarketVO market = marketService.selectMarket(market_num);
		
		return new ModelAndView("boardMarketView", "market", market);
	}
	
	//이미지 출력
	@RequestMapping("/boardMarket/imageView.do")
	public ModelAndView imageView(@RequestParam int market_num) {
		BoardMarketVO market = marketService.selectMarket(market_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		mav.addObject("imageFile", market.getMarket_file());
		mav.addObject("filename", market.getMarket_filename());
		
		return mav;
	}
	
	//중고거래 수정폼
	@RequestMapping(value="/boardMarket/updateMarket.do",method=RequestMethod.GET)
	public String form(@RequestParam int market_num, Model model) {
		BoardMarketVO marketVO = marketService.selectMarket(market_num);
		
		model.addAttribute("boardMarketVO", marketVO);
		
		return "boardMarketModify";
	}
	
	//중고거래 수정
	@RequestMapping(value="/boardMarket/updateMarket.do",method=RequestMethod.POST)
	public String submitUpdate(@Valid BoardMarketVO marketVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		if(log.isDebugEnabled()) {
			log.debug("<<중고거래 수정>> : " + marketVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "boardMarketModify";
		}
		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		marketVO.setMem_num(user.getMem_num());
		
		//질문 수정
		marketService.updateMarket(marketVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "중고거래 내역 수정");
		model.addAttribute("url", request.getContextPath()+"/boardMarket/listMarket.do");
		
		//타일스 설정에 아래 뷰이름이 없으면 단독으로 JSP 호출
		return "common/result";
	}
	
	//중고거래 삭제
	@RequestMapping("/boardMarket/deleteMarket.do")
	public String submitDelete(@RequestParam int market_num, Model model, HttpServletRequest request) {
		if(log.isDebugEnabled()) {
			log.debug("<<중고거래글 삭제>> : " + market_num);
		}
		
		//삭제
		marketService.deleteMarket(market_num);
		
		model.addAttribute("messages", "중고거래 삭제");
		model.addAttribute("url", request.getContextPath()+"/boardMarket/listMarket.do");
		
		return "common/result";
	}
	
	//마이페이지 중고거래 작성내역 - 판매글
	@RequestMapping("/member/myBoardOrder.do")
	public ModelAndView processMyBoardMarket(@RequestParam(value="pageNum",defaultValue="1")int currentPage, HttpSession session) {

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		map.put("mem_num", vo.getMem_num());

		//검색된 글의 갯수
		int count = marketService.selectRowCountMember(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(currentPage,count,10,10,"myBoardOrder.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<BoardMarketVO> list = null;
		if(count > 0) {
			list = marketService.selectMarketListMember(map);

			if(log.isDebugEnabled()) {
				log.debug("<<글 목록>> : " + list);
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("memberBoardOrder");
		mav.addObject("count",count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	
	//마이페이지 중고거래 작성내역 - 구매요청글
	@RequestMapping("/member/myBoardOrderBuy.do")
	public ModelAndView processMyBoardMarketBuy(@RequestParam(value="pageNum",defaultValue="1")int currentPage, HttpSession session) {

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		map.put("mem_num", vo.getMem_num());

		//검색된 글의 갯수
		int count = marketService.selectRowCountMemberBuy(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(currentPage,count,10,10,"myBoardOrderBuy.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<BoardMarketVO> list = null;
		if(count > 0) {
			list = marketService.selectMarketListMemberBuy(map);

			if(log.isDebugEnabled()) {
				log.debug("<<글 목록>> : " + list);
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("memberBoardOrderBuy");
		mav.addObject("count",count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}
	
}