package com.and.middle;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.and.middle.HomeController.TempDTO;
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	// Android 직접 DB에 접속을 할수가없음.(예외 mysql )
	// Android => Spring,(Eclipse servlet) ... 미들웨어 => 오라클
	// Android에서 필요한것은 데이터 ( 페이지 x )
	
	// request = 요청
	// response = 응답
	@ResponseBody // <= res.getWriter().println("")
	@RequestMapping(value = "/")
	public String home() {	
		return "KYM"; // <= 응답을 바로 x , forward (페이지) home.jsp
	}
	// GET :  URL에 ?뒤에 파라메터의 이름과 값을 담아서 넘김 (파라메터가 노출 O)
	// POST : form태그나 기타 전송용 객체가 필요함.(파라메터가 노출 x )
	@ResponseBody // 응답 내가함  , jsp x
	@RequestMapping("/login")
	public String login(String id , String pw) {
		System.out.println(id);
		if(id.equals("admin") && pw.equals("admin1234")) {
			return "로그인";
		}
		return "실패"; // 포워드 (기본)
	}
	
	//REST API : 
	
	@ResponseBody
	@RequestMapping("/jsontest")
	public String jsonTest() {
		TempDTO dto = new TempDTO("data123");
		Gson gson = new Gson();
		List<TempDTO> list = new ArrayList<>();
		list.add(dto);
		list.add(new TempDTO("aaaaa"));
		
		return gson.toJson(list);//<=
	}
	@ResponseBody
	@RequestMapping("/andTest")// POST , GET 둘다 가능 
	public String andTest(String id) {// Android가 post방식으로 요청할때 파라메터로 보내준것.
		System.out.println(id);
		return "KYM";
	}
	
	
	public class TempDTO{
		String data ;
		public TempDTO(String data) {
			this.data = data;
		}
	}
	
	
	// json <=
	// [] : Arr 값 여러개(List,Arr)
	// {} : Obj 값(객체)
	
	// id와pw를 url을 통해서 입력받은 다음
	// id가 admin pw가 admin1234일때는 로그인 되었습니다가 바로 페이지에 나오게 처리
	// 그외에는 비밀번호 틀림 이라는 메세지가 나오게 하면 됨.
	
	
}
