package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.LoginCheckException;

@Controller
public class MemberController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource private MemberService memberService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//회원가입 폼 
	@RequestMapping(value="/member/registerUser.do", method=RequestMethod.GET)
	public String form() {
		return "memberRegister";
	}
	
	//회원가입 처리
	@RequestMapping(value="/member/registerUser.do", method=RequestMethod.POST)
	public String submit(@Valid MemberVO memberVO, BindingResult result) {
		if (log.isDebugEnabled()) {
			log.debug("<<회원 가입>> : " + memberVO);
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			return form();
		}
		
		//회원 가입
		memberService.insertMember(memberVO);
		
		return "redirect:/main/main.do";
	}
	
	//로그인 폼
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String formLogin() {
		return "memberLogin";
	}
	
	//로그인 처리
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String submitLogin(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		if (log.isDebugEnabled()) {
			log.debug("<<MemberVO>>: " + memberVO);
		}
		
		//id와 passwd 필드만 체크
		if (result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		
		//로그인 체크(id와 비밀번호 일치 여부 체크)
		try {
			MemberVO member = memberService.selectCheckMember(memberVO.getId());
			log.debug("<<member>>: "+member);
			boolean check = false;
			
			if (member!=null) {
				//비밀번호 일치 여부 체크
				log.debug("<<진입>>");
				check = member.isCheckedPasswd(memberVO.getPasswd());
				log.debug("<<check값>>: "+check);
			}
			
			if (check) {
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				
				return "redirect:/main/main.do";
			}
			else {
				//인증 실패
				throw new LoginCheckException();
			}
			
		} catch(LoginCheckException e) {
			//인증 실패로 로그인폼 호출
			result.reject("invalidIdOrPassword");
			
			return formLogin();
		}
	}
	
	//로그아웃
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		//로그아웃 처리
		session.invalidate();
		
		return "redirect:/main/main.do";
	}
	
	//회원 상세 정보
	@RequestMapping("/member/myPage.do")
	public String process(HttpSession session, Model model) {
		//회원번호를 얻기 위해 세션에 저장된 회원 정보를 반환
		MemberVO vo = (MemberVO)session.getAttribute("user");
		//글 번호를 받아 한 건의 레코드를 가져옴
		MemberVO member = memberService.selectMember(vo.getMem_num());
		if (log.isDebugEnabled()) {
			log.debug("<<회원 상세 정보>>: " + member);
		}
		model.addAttribute("member", member);
		
		return "memberView";
	}
	
	//회원정보 수정 폼
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET)
	public String formUpdate(HttpSession session, Model model) {
		//회원번호를 구하기 위해 세션에 저장된 회원정보를 반환
		MemberVO vo = (MemberVO)session.getAttribute("user");
		MemberVO memberVO = memberService.selectMember(vo.getMem_num());
		
		//모델에 저장
		model.addAttribute("memberVO", memberVO);
		
		return "memberModify";
	}
	
	//회원정보 수정 처리
	@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
	public String submitUpdate(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		if (log.isDebugEnabled()) {
			log.debug("<<회원정보 수정 처리>> : " + memberVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if (result.hasErrors()) {
			
			return "memberModify";
		}
		
		//회원번호를 얻기 위해 세션에 저장된 회원정보 반환
		MemberVO vo = (MemberVO)session.getAttribute("user");
		//전송된 데이터가 저장된 자바빈에 회원번호를 저장
		memberVO.setMem_num(vo.getMem_num());
		
		//회원 정보 수정
		memberService.updateMember(memberVO);
		
		return "redirect:/member/myPage.do";
	}
	
	//비밀번호 변경 폼
	@RequestMapping(value="/member/changePassword.do", method=RequestMethod.GET)
	public String formChangePassword() {
		return "memberChangePassword";
	}
	
	//비밀번호 변경 처리
	@RequestMapping(value="/member/changePassword.do", method=RequestMethod.POST)
	public String submitChangePassword(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		
		if (log.isDebugEnabled()) {
			log.debug("<<비밀번호 변경 처리>> : " + memberVO);
		}
		
		//현재 비밀번호와 변경할 비밀번호가 전송되었는지 확인
		if (result.hasFieldErrors("now_passwd") || result.hasFieldErrors("passwd")) {
			return "memberChangePassword";
		}
		
		//회원번호를 얻기 위해서 세션에 저장된 회원정보 반환
		MemberVO vo = (MemberVO)session.getAttribute("user");
		//현재 비밀번호와 변경할 비밀번호가 저장된 자바빈에 회원번호를 저장
		memberVO.setMem_num(vo.getMem_num());
		
		//회원번호를 통해 회원정보를 DB로부터 읽어와 입력한 현재 비밀번호와 DB에서 읽어온 현재 비밀번호가 일치하는지 여부 체크
		//불일치하면 돌려보냄
		MemberVO member = memberService.selectMember(memberVO.getMem_num());
		if (!member.getPasswd().equals(memberVO.getNow_passwd())) {
			//불일치하면
			result.rejectValue("now_passwd", "invalidPassword");
			
			return "memberChangePassword";
		}
		
		//비밀번호 수정 처리
		memberService.updatePassword(memberVO);
		
		return "redirect:/member/myPage.do";
	}
	
	//회원탈퇴 폼
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public String formDelete() {
		return "memberDelete";
	}
	
	//회원탈퇴 처리
	@RequestMapping(value="/member/delete.do", method=RequestMethod.POST)
	public String submitDelete(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		
		if (log.isDebugEnabled()) {
			log.debug("<<회원탈퇴>>" + memberVO);
		}
		//아이디와 비밀번호만 유효성 체크 수행
		if (result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return "memberDelete";
		}
		
		//회원번호를 얻기 위해 세션에 저장된 회원정보 반환
		MemberVO vo = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(vo.getMem_num());	//전송된 정보는 아이디와 비밀번호
		
		//비밀번호 일치 여부 체크
		//회원번호를 이용해 회원정보를 읽음, 한 건의 레코드 가져오기
		MemberVO member = memberService.selectMember(memberVO.getMem_num());
		boolean check = false;
		//vo.getId()는 세션에 저장된 아이디, memberVO.getId()는 전송된 아이디
		if (member != null && memberVO.getId().equals(vo.getId())) {
			//비밀번호 일치 여부 체크
			check = member.isCheckedPasswd(memberVO.getPasswd());
		}
		
		if (check) {
			//인증 성공, 회원정보 삭제
			memberService.deleteMember(memberVO.getMem_num());
			//로그아웃
			session.invalidate();
			
			return "redirect:/main/main.do";	//탈퇴 후 로그아웃하고 메인으로
		}
		else {
			//인증 실패
			result.reject("invalidIdOrPassword");
			
			return "memberDelete";
		}
		
	}
	
}