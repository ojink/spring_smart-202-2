package com.hanul.web01;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.MemberVO;

@Controller
public class TestController {
	
	//로그인처리 요청
	@RequestMapping("/login_result")
	public String login(String id, String pw) {
		//id: admin, pw: 1234 이면 성공
		//로그인 성공 - 웰컴화면으로
		if( id.equals("admin") && pw.equals("1234") ) {
			//return "home"; //forward
			return "redirect:/";	//redirect
		}else {
		//로그인 실패 - 로그인화면으로
			//return "member/login"; //forward
			return "redirect:login"; //redirect
		}
	}
	
	//로그인화면 요청
	@RequestMapping("/login")
	public String login() {
		//응답화면연결
		return "member/login";
	}
	
	//경로의 형태로 데이터를 접근
	@RequestMapping("/joinPath/{name}/{g}/{email}")
	public String join(@PathVariable String name, Model model
						, @PathVariable("g") String gender
						, @PathVariable String email) {
		//비니지스로직 - 경로를 통해 받은 정보를 화면에 출력할 수 있도록 담는다
		model.addAttribute("name", name);
		model.addAttribute("gender", gender);
		model.addAttribute("email", email);
		model.addAttribute("method", "@PathVariable 방식");
		
		//응답화면연결
		return "member/info";
	}
	
	//데이터객체 방식으로 파라미터 접근
	@RequestMapping("/joinDataObject")
	public String join(Model model, MemberVO vo) {
		//비니지스로직 - 파라미터 정보를 화면에 출력할 수 있도록 담는다
		model.addAttribute("method", "데이터객체 방식");
		model.addAttribute("vo", vo);
		//응답화면연결
		return "member/info";
	}
	
	
	//@RequestParam 방식으로 파라미터 접근
	@RequestMapping("/joinParam")
	public String join(String name
						, @RequestParam("email") String mail
						, @RequestParam("gender") String code, Model model) {
		//비니지스로직 - 파라미터 정보를 화면에 출력할 수 있도록 담는다
		model.addAttribute("name", name);
		model.addAttribute("gender", code);
		model.addAttribute("email", mail);
		model.addAttribute("method", "@RequestParam 방식");
		//응답화면연결
		return "member/info";
	}

	//HttpServletRequest 방식으로 파라미터 접근
	@RequestMapping("/joinRequest")
	public String join(HttpServletRequest request, Model model) {
		//비니지스로직 - 파라미터 정보를 화면에 출력할 수 있도록 담는다
		model.addAttribute("name", request.getParameter("name") );
		model.addAttribute("gender", request.getParameter("gender") );
		String email = request.getParameter("email");
		model.addAttribute("email", email);
		model.addAttribute("method", "HttpServletRequest 방식");
		//응답화면연결
		return "member/info";
	}
	
	
	@RequestMapping("/member")
	public String member() {
		return "member/join";
	}
	
	
	@RequestMapping("/third")
	public ModelAndView third() {
		//비지니스 로직
		//현재 날짜, 시각을 화면에 출력할 수 있도록 한다.
		ModelAndView model = new ModelAndView();
		String today
		= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		model.addObject("todayNow", today); //데이터 저장
		model.setViewName("index");  //화면연결
		return model;
	}
	
	
	@RequestMapping("/second")
	public String second(Model model) {
		//비지니스 로직
		//현재 시각을 화면에 출력할 수 있도록 Model타입의 변수에 attribute 로 담는다
		String now
		= new SimpleDateFormat("HH:mm:ss").format(new Date());
		model.addAttribute("now", now);
		return "index";
	}
	
	//웹브라우저 주소에 /first 라고 요청할 때 이 메소드를 실행하게 하고자 한다.
	//요청에 대해 이 메소드를 연결: @RequestMapping
	@RequestMapping("/first")
	public String first( Model model ) {
		//비지니스 로직
		//오늘 날짜를 화면에 출력할 수 있도록 저장한다
		//: Model타입에 attribute로 저장(addAttribute)
		String today 
		= new SimpleDateFormat("yyyy-MM-dd").format( new Date() );
		model.addAttribute("today", today);
		
		//응답화면연결
		return "index";
	}
	
}
