package co.kr.smart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
