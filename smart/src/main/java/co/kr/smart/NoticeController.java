package co.kr.smart;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	
	//신규공지글쓰기화면 요청
	@RequestMapping("/new.no")
	public String notice() {
		return "notice/new";
	}
	
	//공지글수정화면 요청
	@RequestMapping("/modify.no")
	public String modify() {
		return "notice/modify";
	}
	
	//공지글안내화면 요청
	@RequestMapping("/info.no")
	public String info() {
		return "notice/info";
	}
	
	//공지글목록화면 요청
	@RequestMapping("/list.no")
	public String list(HttpSession session) {
		//공지글관리 정보를 session에 담는다
		session.setAttribute("category", "no");
		//비지니스로직-DB에서 공지글목록을 조회한다. -> 목록화면에 출력하도록 Model에 attribute로 담는다
		//응답화면연결
		return "notice/list";
	}
}
