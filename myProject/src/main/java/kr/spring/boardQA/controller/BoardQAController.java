package kr.spring.boardQA.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
