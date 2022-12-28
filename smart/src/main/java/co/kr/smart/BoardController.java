package co.kr.smart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import board.BoardFileVO;
import board.BoardPageVO;
import board.BoardServiceImpl;
import board.BoardVO;
import common.CommonService;

@Controller
public class BoardController {
	@Autowired private BoardServiceImpl service;
	@Autowired private CommonService common;
	
	//방명록 수정저장처리 요청
	@RequestMapping("/update.bo")
	public String update(BoardPageVO page, BoardVO vo, Model model) {
		//화면에서 입력한 정보로 DB에 변경저장한 후
		//응답화면연결
		model.addAttribute("id", vo.getId());
		model.addAttribute("url", "info.bo");
		model.addAttribute("page", page);
		model.addAttribute("download", false);
		return "board/redirect";
	}
	
	
	//방명록 수정화면 요청
	@RequestMapping("/modify.bo")
	public String modify(Model model, int id, BoardPageVO page) {
		//선택한 글의 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", service.board_info(id));
		model.addAttribute("page", page);
		return "board/modify";
	}
	
	
	//방명록 글 삭제처리 요청
	@RequestMapping("/remove.bo")
	public String remove(int id, BoardPageVO page, HttpServletRequest request, Model model) {
		//첨부파일정보를 DB에서 조회해 온다.
		List<BoardFileVO> files = service.board_info(id).getFileList();
		
		//해당 글의 정보를 DB에서 삭제
		//DB에서는 on delete cascade 에 의해 첨부파일 정보를 삭제되지만
		//물리적인 파일을 존재하므로 물리적파일도 삭제되도록 한다
		if( service.board_delete(id)==1 ) {
			for(BoardFileVO file : files) {
				common.fileDelete(file.getFilepath(), request);
			}
		}
		//응답화면연결 - 목록화면
		//return "redirect:list.bo";
		model.addAttribute("url", "list.bo");
		model.addAttribute("page", page);
		model.addAttribute("download", false);
		return "board/redirect";
	}
	
	//첨부파일 다운로드 요청
	//@ResponseBody 
	//@RequestMapping(value="/download.bo", produces="text/html; charset=utf-8")
	@RequestMapping(value="/download.bo")
	public String download(int file, String url, int id, BoardPageVO page, Model model
						, HttpServletRequest request
						, HttpServletResponse response) throws Exception{
		//선택한 파일정보를 DB에서 조회해온다
		BoardFileVO vo = service.board_file_info(file);
		
		//다운로드: 서버시스템의 물리적위치의 파일을 복사해서 클라이언트시스템에 저장한다
		boolean download 
		= common.fileDownload(vo.getFilename(), vo.getFilepath(), request, response);
		/*
		if( download ) {
			return null;
		}else {
			StringBuffer msg = new StringBuffer();
			msg.append("<script>"); 
			msg.append("alert('삭제할 파일이 없습니다!'); location='")
				.append(url).append("?id=").append(id).append("'"); 
			msg.append("</script>"); 
			return msg.toString();
		}
		*/
		model.addAttribute("url", "info.bo");
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		model.addAttribute("download", true);
		return "board/redirect";		
	}
	
	//방명록상세화면 요청
	@RequestMapping("/info.bo")
	public String info(int id, BoardPageVO vo, Model model) {
		//조회수 증가처리
		service.board_read(id);
		//선택한 방명록 글의 정보를 DB에서 조회해와 화면에 출력한다
		model.addAttribute("vo", service.board_info(id) );
		model.addAttribute("page", vo);
		return "board/info";
	}
	
	
	private void attachedFile(BoardVO vo, MultipartFile file[], HttpServletRequest request) {
		List<BoardFileVO> files = null;
		for(MultipartFile attached : file) {
			if( attached.isEmpty() ) continue;
			if( files==null ) files = new ArrayList<BoardFileVO>();
			
			BoardFileVO fileVO = new BoardFileVO();
			fileVO.setFilename( attached.getOriginalFilename() );
			fileVO.setFilepath( common.fileUpload("board", attached, request) );
			files.add(fileVO);
		}
		vo.setFileList(files);
	}
	
	//방명록 새글저장처리 요청
	@RequestMapping("/insert.bo")
	public String insert(BoardVO vo, MultipartFile file[], HttpServletRequest request) {
		//첨부파일이 있는 경우
		//첨부하지 않은 경우 파일태그가 1개, 1개 첨부한 경우 파일태그가 2개
		if( file.length > 1 ) { 
			attachedFile(vo, file, request);
		}
		
		//화면에서 입력한 정보를 DB에 신규저장
		service.board_insert(vo);
		//응답화면연결
		return "redirect:list.bo";
	}
	
	//방명록 글쓰기화면 요청
	@RequestMapping("/new.bo")
	public String board() {
		return "board/new";
	}
	
	//방명록 목록화면 요청
	@RequestMapping("/list.bo")
	public String list(HttpSession session, Model model, BoardPageVO page) {
		session.setAttribute("category", "bo");	
		//비지니스로직 - DB에서 방명록 목록을 조회해와 화면에 출력한다
		model.addAttribute("page",  service.board_list(page));
		return "board/list";
	}
}
