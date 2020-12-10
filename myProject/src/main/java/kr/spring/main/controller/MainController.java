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

import kr.spring.itemPhone.service.ItemPhoneService;
import kr.spring.itemPhone.vo.ItemPhoneVO;
import kr.spring.util.PagingUtil;

@Controller
public class MainController {
	
private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	ItemPhoneService phoneService;
	
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
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 4, 4, "main.do");
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
		mav.setViewName("main");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
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