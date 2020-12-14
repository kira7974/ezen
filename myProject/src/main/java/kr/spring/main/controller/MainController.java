package kr.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.boardMarket.service.BoardMarketService;
import kr.spring.boardMarket.vo.BoardMarketVO;
import kr.spring.boardNotice.service.BoardNoticeService;
import kr.spring.boardNotice.vo.BoardNoticeVO;
import kr.spring.boardQA.service.BoardQAService;
import kr.spring.boardQA.vo.BoardQAVO;
import kr.spring.itemPhone.service.ItemPhoneService;
import kr.spring.itemPhone.vo.ItemPhoneVO;
import kr.spring.util.PagingUtil;

@Controller
public class MainController {
	
private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	ItemPhoneService phoneService;
	
	@Resource
	BoardService boardService;
	
	@Resource
	BoardNoticeService noticeService;
	
	@Resource
	BoardQAService qaService;
	
	@Resource
	BoardMarketService marketService;
	
	/*@RequestMapping("/main/main.do")
	public String getMain() {
		return "main";
	}*/
	
	@RequestMapping("/main/main.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")int currentPage, 
								@RequestParam(value="keyfield",defaultValue="")	String keyfield,
								@RequestParam(value="keyword",defaultValue="") String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//스마트폰 갯수
		int count = phoneService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		//자유게시판 갯수
		int countBoard = boardService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<countBoard>> : " + countBoard);
		}
		
		//공지사항 갯수
		int countNotice = noticeService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<countNotcie>> : " + countNotice);
		}
		
		//QA 갯수
		int countQA = qaService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<countQA>> : " + countQA);
		}
		
		//중고장터 갯수
		int countMarket = marketService.selectRowCount(map);
				
		if(log.isDebugEnabled()) {
			log.debug("<<countMarket>> : " + countMarket);
		}
		
		//스마트폰 페이징 유틸
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 4, 4, "main.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		//자유게시판 페이징 유틸
		PagingUtil page2 = new PagingUtil(keyfield, keyword, currentPage, countBoard, 4, 4, "main.do");
		map.put("start", page2.getStartCount());
		map.put("end", page2.getEndCount());
		
		//공지 페이징
		PagingUtil page3 = new PagingUtil(keyfield, keyword, currentPage, countNotice, 4, 4, "main.do");
		map.put("start", page3.getStartCount());
		map.put("end", page3.getEndCount());
		
		//qa 페이징
		PagingUtil page4 = new PagingUtil(keyfield, keyword, currentPage, countQA, 4, 4, "main.do");
		map.put("start", page4.getStartCount());
		map.put("end", page4.getEndCount());
		
		//장터 페이징
		PagingUtil page5 = new PagingUtil(keyfield, keyword, currentPage, countMarket, 4, 4, "main.do");
		map.put("start", page5.getStartCount());
		map.put("end", page5.getEndCount());
		
		
		//스마트폰 리스트
		List<ItemPhoneVO> list = null;
		if(count > 0) {
			list = phoneService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<스마트폰 목록>> : " + list);
			}
		}
		
		//자유게시판 리스트
		List<BoardVO> listBoard = null;
		if(countBoard > 0) {
			listBoard = boardService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<자유게시판 목록>> : " + listBoard);
			}
		}
		
		//공지 리스트
		List<BoardNoticeVO> listNotice = null;
		if(countNotice > 0) {
			listNotice = noticeService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<공지 목록>> : " + listNotice);
			}
		}
		
		//qa 리스트
		List<BoardQAVO> listQA = null;
		if(countQA > 0) {
			listQA = qaService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<QA 목록>> : " + listQA);
			}
		}
		
		//장터 리스트
		List<BoardMarketVO> listMarket = null;
		if(countMarket > 0) {
			listMarket = marketService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<장터 목록>> : " + listMarket);
			}
		}
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		//스마트폰
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		//자유게시판
		mav.addObject("countBoard", countBoard);
		mav.addObject("listBoard", listBoard);
		mav.addObject("pagingHtmlBoard", page2.getPagingHtml());
		//공지
		mav.addObject("countNotice", countNotice);
		mav.addObject("listNotice", listNotice);
		mav.addObject("pagingHtmlNotice", page3.getPagingHtml());
		//qa
		mav.addObject("countQA", countQA);
		mav.addObject("listQA", listQA);
		mav.addObject("pagingHtmlQA", page4.getPagingHtml());
		//장터
		mav.addObject("countMarket", countMarket);
		mav.addObject("listMarket", listMarket);
		mav.addObject("pagingHtmlMarket", page5.getPagingHtml());
		return mav;
	}
	
	//리스트 이미지 출력
		@RequestMapping("/main/imageListView.do")
		public ModelAndView imageListView(@RequestParam int phone_num) {
			ItemPhoneVO phone = phoneService.selectPhone(phone_num);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
			mav.addObject("imageFile", phone.getPhone_titleimg());
			mav.addObject("filename", phone.getPhone_titleimgname());
			
			return mav;
		}
}