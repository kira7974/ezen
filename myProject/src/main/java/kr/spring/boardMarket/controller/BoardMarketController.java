package kr.spring.boardMarket.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardMarketController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("/boardMarket/listMarket.do")
	public String form() {
		return "boardMarketList";
	}
	
	@RequestMapping(value="/boardMarket/writeMarket.do", method=RequestMethod.GET)
	public String form2() {
		return "boardMarketWrite";
	}
}
