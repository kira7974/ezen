package kr.spring.orders.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.boardMarket.vo.BoardMarketVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.orders.service.OrdersService;
import kr.spring.orders.vo.OrdersVO;
import kr.spring.util.PagingUtil;

@Controller
public class OrdersController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private OrdersService ordersService;

	@ModelAttribute
	public OrdersVO initCommand() {
		return new OrdersVO();
	}

	//마이페이지 중고거래 작성내역 - 판매글
	@RequestMapping("/member/myBoardOrder.do")
	public ModelAndView processMyBoardMarket(@RequestParam(value="pageNum",defaultValue="1")int currentPage, HttpSession session) {

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		map.put("mem_num", vo.getMem_num());

		//검색된 글의 수
		int count = ordersService.selectRowCountMember(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(currentPage,count,10,10,"myBoardOrder.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<OrdersVO> list = null;
		if(count > 0) {
			list = ordersService.selectOrdersListMember(map);

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

	//마이페이지 중고거래 작성내역 - 구매요청
	@RequestMapping("/member/myBoardOrderBuy.do")
	public ModelAndView processMyOrder(@RequestParam(value="pageNum",defaultValue="1")int currentPage, HttpSession session) {

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		map.put("mem_num", vo.getMem_num());

		//검색된 글의 수
		int count = ordersService.selectRowCountMemberBuy(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(currentPage,count,10,10,"myBoardOrderBuy.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<OrdersVO> list = null;
		if(count > 0) {
			list = ordersService.selectOrdersListMemberBuy(map);

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
	
	//구매 요청
	@RequestMapping("/boardMarket/insertOrder.do")
	public String submitOrders(@Valid OrdersVO ordersVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		if(log.isDebugEnabled()) {
			log.debug("<<거래요청>> : " + ordersVO);
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "redirect:/boardMarket/listMarket.do";
		}
		
		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		ordersVO.setMem_num(user.getMem_num());
		/*//게시글 번호 셋팅
		BoardMarketVO market = (BoardMarketVO)session.getAttribute("market");
		ordersVO.setMarket_num(market.getMarket_num())*/;
		
		//구매요청
		ordersService.insertOrders(ordersVO);
		
		model.addAttribute("messages", "구매요청 완료");
		model.addAttribute("url", request.getContextPath()+"/boardMarket/listMarket.do");
		
		return "common/result";
	}
	
	@RequestMapping("/member/shipping.do")
	public String submitShipping(@Valid OrdersVO ordersVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		if(log.isDebugEnabled()) {
			log.debug("<<배송>> : " + ordersVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "memberBoardOrder";
		}
		
		//배송중으로 변경
		ordersService.updateOrdersValidOne(ordersVO);
		
		model.addAttribute("message", "출고 완료");
		model.addAttribute("url", request.getContextPath()+"/member/myBoardOrder.do");
		
		return "common/result";
	}
	
	@RequestMapping("/member/shippingEnd.do")
	public String submitShippingEnd(@Valid OrdersVO ordersVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		if(log.isDebugEnabled()) {
			log.debug("<<구매확정>> : " + ordersVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "memberBoardOrderBuy";
		}
		
		
		ordersService.updateOrdersValidTwo(ordersVO);
		
		model.addAttribute("message", "구매확정 완료");
		model.addAttribute("url", request.getContextPath()+"/member/myBoardOrderBuy.do");
		
		return "common/result";
	}
}