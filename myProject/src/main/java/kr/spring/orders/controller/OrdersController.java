package kr.spring.orders.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	//마이페이지 중고거래 작성내역 - 구매요청
	@RequestMapping("/member/myBoardOrderBuy.do")
	public ModelAndView processMyOrder(@RequestParam(value="pageNum",defaultValue="1")int currentPage, HttpSession session) {

		Map<String,Object> map = new HashMap<String,Object>();
		MemberVO vo = (MemberVO)session.getAttribute("user");
		map.put("mem_num", vo.getMem_num());

		//검색된 글의 수
		int count = ordersService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(currentPage,count,10,10,"myBoardOrderBuy.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<OrdersVO> list = null;
		if(count > 0) {
			list = ordersService.selectOrdersList(map);

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