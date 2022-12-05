package com.hanul.web01;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	
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
