package kr.spring.itemPhone.controller;

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

import kr.spring.itemPhone.service.ItemPhoneService;
import kr.spring.itemPhone.vo.ItemPhoneVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class ItemPhoneController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	ItemPhoneService phoneService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public ItemPhoneVO initCommand() {
		return new ItemPhoneVO();
	}
	
	//스마트폰 리스트
	@RequestMapping("/itemPhone/listPhone.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")int currentPage, 
								@RequestParam(value="keyfield",defaultValue="")	String keyfield,
								@RequestParam(value="keyword",defaultValue="") String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
			
		//질문 갯수
		int count = phoneService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 20, 20, "listPhone.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<ItemPhoneVO> list = null;
		if(count > 0) {
			list = phoneService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<스마트폰 목록>> : " + list);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("itemPhoneList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//스마트폰 정보 등록 폼
	@RequestMapping(value="/itemPhone/writePhone.do", method=RequestMethod.GET)
	public String form() {
		return "itemPhoneWrite";
	}
	
	//스마트폰 정보 등록
	@RequestMapping(value="/itemPhone/writePhone.do", method=RequestMethod.POST)
	public String submit(@Valid ItemPhoneVO phoneVO, BindingResult result, HttpServletRequest request, HttpSession session) {
		if(log.isDebugEnabled()) {
			log.debug("<<스마트폰 글 저장>> : " + phoneVO);
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "itemPhoneWrite";
		}
		
		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		phoneVO.setMem_num(user.getMem_num());
		
		//글쓰기
		phoneService.insertPhone(phoneVO);
		
		return "redirect:/itemPhone/listPhone.do";
	}
	
	//스마트폰 상세
	@RequestMapping(value="/itemPhone/detailPhone.do",method=RequestMethod.GET)
	public ModelAndView process(@RequestParam int phone_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<스마트폰 상세>> : " + phone_num);
		}
		
		ItemPhoneVO phone = phoneService.selectPhone(phone_num);
		
		return new ModelAndView("itemPhoneView", "phone", phone);
	}
	
	
	//이미지 출력
	@RequestMapping("/itemPhone/imageView.do")
	public ModelAndView imageView(@RequestParam int phone_num) {
		ItemPhoneVO phone = phoneService.selectPhone(phone_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", phone.getPhone_contentimg());
		mav.addObject("filename", phone.getPhone_contentimgname());
		
		return mav;
	}
	
	//리스트 이미지 출력
	@RequestMapping("/itemPhone/imageListView.do")
	public ModelAndView imageListView(@RequestParam int phone_num) {
		ItemPhoneVO phone = phoneService.selectPhone(phone_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", phone.getPhone_titleimg());
		mav.addObject("filename", phone.getPhone_titleimgname());
		
		return mav;
	}
	
	//수정 폼 호출
	@RequestMapping(value="/itemPhone/updatePhone.do",method=RequestMethod.GET)
	public String form(@RequestParam int phone_num, Model model) {
		ItemPhoneVO phone = phoneService.selectPhone(phone_num);
		
		model.addAttribute("itemPhoneVO", phone);
		
		return "itemPhoneModify";
	}
	
	//정보 수정
	@RequestMapping(value="/itemPhone/updatePhone.do",method=RequestMethod.POST)
	public String submitUpdate(@Valid ItemPhoneVO phoneVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		if(log.isDebugEnabled()) {
			log.debug("<<정보 수정>> : " + phoneVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "itemPhoneModify";
		}
		
		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		phoneVO.setMem_num(user.getMem_num());
		
		//질문 수정
		phoneService.updatePhone(phoneVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "스마트폰 정보 수정");
		model.addAttribute("url", request.getContextPath()+"/itemPhone/listPhone.do");

		//타일스 설정에 아래 뷰이름이 없으면 단독으로 JSP 호출
		return "common/result";
	}
	
	//질문 삭제
	@RequestMapping("/itemPhone/deletePhone.do")
	public String submitDelete(@RequestParam int phone_num, Model model, HttpServletRequest request) {
		if(log.isDebugEnabled()) {
			log.debug("<<정보 삭제>> : " + phone_num);
		}
		//삭제
		phoneService.deletePhone(phone_num);
		
		model.addAttribute("messages", "정보 삭제");
		model.addAttribute("url", request.getContextPath()+"/itemPhone/listPhone.do");
		
		return "common/result";
	}
}
