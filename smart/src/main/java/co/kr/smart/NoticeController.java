package co.kr.smart;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import notice.NoticeService;

@Controller
public class NoticeController {
	//@Autowired private NoticeServiceImpl notice;
	private NoticeService notice;
	public NoticeController(NoticeService notice) {
		this.notice = notice; 
	}
	
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
	public String info(Model model, int id) {
		//DB에서 해당 공지글을 조회한다 ->화면에 출력하도록 Model에 attribute로 담는다
		model.addAttribute("vo", notice.notice_info(id) );
		return "notice/info";
	}
	
	//공지글목록화면 요청
	@RequestMapping("/list.no")
	public String list(HttpSession session, Model model) {
		//공지글관리 정보를 session에 담는다
		session.setAttribute("category", "no");
		//비지니스로직-DB에서 공지글목록을 조회한다. -> 목록화면에 출력하도록 Model에 attribute로 담는다
		model.addAttribute("list", notice.notice_list() );
		//응답화면연결
		return "notice/list";
	}
}
