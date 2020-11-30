package kr.spring.boardNotice.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardNoticeController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("/boardNotice/listNotice.do")
	public String form() {
		return "boardNoticeList";
	}
	
	@RequestMapping(value="/boardNotice/writeNotice.do", method=RequestMethod.GET)
	public String form2() {
		return "boardNoticeWrite";
	}
}
