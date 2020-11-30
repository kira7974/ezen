package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

@Controller
public class MemberAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource private MemberService memberService;
	
	@RequestMapping("/member/confirmId.do")
	@ResponseBody	//map을 리턴하면 ResponseBody가 json문자열로 만들어줌
	public Map<String, String> process(@RequestParam("id") String id) {
		if (log.isDebugEnabled()) {
			log.debug("<<아이디 중복 체크>> + " + id);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		MemberVO member = memberService.selectCheckMember(id);
		
		if (member != null) {
			//아이디 중복
			map.put("result", "idDuplicated");
		}
		else {
			//아이디 미중복
			map.put("result", "idNotFound");
		}
		
		return map;
	}
	
}