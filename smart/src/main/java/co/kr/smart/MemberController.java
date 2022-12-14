package co.kr.smart;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import member.MemberService;
import member.MemberVO;

@Controller
public class MemberController {
	@Autowired private MemberService member;
//	@Autowired private MemberServiceImpl service;
//	private MemberService member;
//	public MemberController(MemberService member) {
//		this.member = member;
//	}
	
	//로그아웃처리 요청
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//비지니스로직: 세션에 있는 로그인정보를 삭제한다
		session.removeAttribute("loginInfo");
		//응답화면연결
		return "redirect:/";
	}
	
	//로그인처리 요청
	@ResponseBody @RequestMapping("/smartLogin")
	public boolean login(String id, String pw, HttpSession session) {
		//비지니스로직-화면에서 입력한 아이디/비번이 일치하는 회원정보를 DB에서 조회한다
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		MemberVO vo = member.member_login(map);
		//          화면엣 출력할 수 있도록 세션에 attribute로 담는다
		session.setAttribute("loginInfo", vo);
		return vo==null ? false : true;
	}
	
	
	//로그인화면 요청
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("category", "login");
		return "default/member/login";
	}
}
