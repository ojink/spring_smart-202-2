package co.kr.smart;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;
import notice.NoticeService;
import notice.NoticeVO;

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
	
	//공지글삭제처리 요청
	@RequestMapping("/delete.no")
	public String delete(int id) {
		//선택한 공지글을 DB에서 삭제한다
		notice.notice_delete(id);
		//응답화면연결 - 목록화면
		return "redirect:list.no";
	}
	
	
	//공지글등록(신규저장)처리 요청
	@RequestMapping("/insert.no")
	public String insert(NoticeVO vo, MultipartFile file, HttpServletRequest request) {
		//첨부된 파일이 있는 경우
		if( ! file.isEmpty() ) {
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( common.fileUpload("notice", file, request) );
		}
		//화면에서 입력한 공지글정보를 DB에 신규저장한다
		notice.notice_insert(vo);
		//응답화면연결 - 목록화면
		return "redirect:list.no";
	}
	
	
	//공지글수정저장처리 요청
	@RequestMapping("/update.no")
	public String update(NoticeVO vo) {
		//화면에서 입력한 공지글정보를 DB에 변경저장한다
		notice.notice_update(vo);
		//응답화면연결
		return "redirect:info.no?id=" + vo.getId();
	}
	
	//공지글수정화면 요청
	@RequestMapping("/modify.no")
	public String modify(Model model, int id) {
		//DB에서 해당 공지글을 조회한다 ->화면에 출력하도록 Model에 attribute로 담는다
		model.addAttribute("vo", notice.notice_info(id) );
		return "notice/modify";
	}
	
	//공지글안내화면 요청
	@RequestMapping("/info.no")
	public String info(Model model, int id) {
		//화면에서 사용할 수 있도록 enter키값을 담아둔다
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		//조회수 증가처리
		notice.notice_read(id);
		//DB에서 해당 공지글을 조회한다 ->화면에 출력하도록 Model에 attribute로 담는다
		model.addAttribute("vo", notice.notice_info(id) );
		return "notice/info";
	}
	
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonService common;
	
	//공지글목록화면 요청
	@RequestMapping("/list.no")
	public String list(HttpSession session, Model model) {
		//테스트를 위한 임시로그인처리 -----------
		String userid = "admin", userpw = "Manager1";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", userid);
		//입력한 비번을 salt 를 사용해 암호화한 후 map에 담는다
		String salt = member.member_salt(userid);
		userpw = common.getEncrypt(salt, userpw);
		map.put("pw", userpw);
		MemberVO vo = member.member_login(map);
		session.setAttribute("loginInfo", vo);
		//---------------------------------
		
		//공지글관리 정보를 session에 담는다
		session.setAttribute("category", "no");
		//비지니스로직-DB에서 공지글목록을 조회한다. -> 목록화면에 출력하도록 Model에 attribute로 담는다
		model.addAttribute("list", notice.notice_list() );
		//응답화면연결
		return "notice/list";
	}
}
