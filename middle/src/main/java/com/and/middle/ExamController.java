package com.and.middle;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import member.MemberVO;

//Controller : 요청을 받아서 대부분=> 페이지 전환을 하기 위한 것(Ajax 비동기처리 또는 데이터만 필요할때)
// @ResponseBody

//@RestController : 데이터만 필요한 경우 사용하는 컨트롤러 (@ResponseBody 생략 가능)
// go(공공 데이터)

@RestController // <= 컨트롤러 어노테이션 :
public class ExamController {
	//크롬=>톰캣=>컨트롤러
	@RequestMapping(value = "/test1", produces = "text/html;charset=utf-8")
	public void test1() {
		System.out.println("요청받음");
	}
	//요청=> RequestMapping하위 메소드 실행.
	@RequestMapping(value = "/test2", produces = "text/html;charset=utf-8")
	public void test2(String str , int intval , double dval) {//어떤것이든 파라메터로 사용가능.
		System.out.println(str);// 요청할때 요청하는 쪽에서 보내주는 데이터
		System.out.println(intval);
		System.out.println(dval);
		System.out.println("요청받음");
	}
	
	// test3을 요청받는 맵핑을 만들고 해당하는 맵핑 요청시 크롬창에 나의 이니셜이 찍히게 해보기.(Spring , Chrome)
	@RequestMapping(value = "/test3", produces = "text/html;charset=utf-8")
	public String test3() {
		return "kym";
	}
	
	@RequestMapping(value = "/test4", produces = "text/html;charset=utf-8")
	public String test4() {// Android에서 VO값이 필요함. Spring이 응답으로 값을 줘야하는 상황
		VO vo = new VO();
		vo.setdVal(3.5);
		vo.setiVal(5);
		vo.setsVal("abc");
		return new Gson().toJson(vo);
	}
	
	@RequestMapping(value = "/test5", produces = "text/html;charset=utf-8")
	public String test5() {
		ArrayList<VO> list = new ArrayList<ExamController.VO>();
		for(int i = 0 ; i<10 ; i++) {
			VO vo = new VO();
			vo.setdVal(3.5);
			vo.setiVal(5);
			vo.setsVal("abc");
			list.add(vo);
		}
		return new Gson().toJson(list);//<=
	}
	// test5요청시 Vo를 10건 묶어놓은 Array가 크롬창에 써지게 처리해보기.
	
	//------------------- ↑
	// test5 요청시 숫자값을 파라메터로 보낸다. 해당하는 숫자의 크기만큼 Array를 크롬창에 써지게 처리해보게.
	// (※ 단 숫자 외에 값을 입력하면 오류라는 글씨가 크롬창에 써진다. ) <= 파라메터를 String으로 받으면 가능.
	
	
	
	
	public class VO{
		private int iVal ; String sVal ;  double dVal;


		public int getiVal() {
			return iVal;
		}


		public void setiVal(int iVal) {
			this.iVal = iVal;
		}


		public String getsVal() {
			return sVal;
		}


		public void setsVal(String sVal) {
			this.sVal = sVal;
		}


		public double getdVal() {
			return dVal;
		}


		public void setdVal(double dVal) {
			this.dVal = dVal;
		}
		
		
	}
	
}
