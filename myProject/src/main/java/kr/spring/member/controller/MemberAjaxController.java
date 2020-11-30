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
	
	//프로필 사진 업데이트
	@RequestMapping("/member/updateMyPhoto.do")
	@ResponseBody
	public Map<String, String> processProfile(MemberVO memberVO, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if (user == null) {
			//로그인이 되어있지 않은 경우
			map.put("result", "logout");
		}
		else {
			//로그인이 된 경우
			memberVO.setMem_num(user.getMem_num());
			memberService.updateProfile(memberVO);
			
			//이미지를 업로드한 후 세션에 저장된 회원정보의 이미지 이름을 교체
			user.setPhotoname(memberVO.getPhotoname());
			
			map.put("result", "success");
		}
		
		return map;
	}
	
}
/*
	pom.xml에 ajax 라이브러리 추가
	MemberAjaxController클래스 추가
	아이디 중복체크 부분 작성
*/