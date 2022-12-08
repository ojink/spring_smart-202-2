package co.kr.smart;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import customer.CustomerService;
import customer.CustomerVO;

@Controller
public class CustomerController {
//	@Autowired private CustomerService service;
	private CustomerService service; //CustomerServiceImpl 는 CustomerService를 구현하고 있다
	public CustomerController(CustomerService customer) {
		this.service = customer;
	}
	//Command 패턴 -> Service
	
	//신규고객정보 저장처리 요청
	@RequestMapping("/insert.cu")
	public String insert(CustomerVO vo) {
		//비지니스로직
		//화면에서 입력한 정보를 DB에 신규저장처리한 후
		service.customer_insert(vo);
		//응답화면연결
		return "redirect:list.cu";
	}
	
	
	//신규고객정보 등록화면 요청
	@RequestMapping("/new.cu")
	public String customer() {
		//응답화면연결
		return "customer/new";
	}
	
	//고객정보 삭제처리 요청
	@RequestMapping("/delete.cu")
	public String delete(int id) {
		//비지니스로직
		//선택한 고객정보를 DB에서 삭제한 후
		service.customer_delete(id);
		//응답화면연결
		return "redirect:list.cu";
	}
	
	//고객정보 수정저장처리 요청
	@RequestMapping("/update.cu")
	public String update(CustomerVO vo) {
		//비지니스로직
		//화면에서 입력한 정보를 DB에 변경저장한 후
		service.customer_update(vo);
		//응답화면연결
		return "redirect:info.cu?id=" + vo.getId();
	}
	
	
	//고객정보 수정화면 요청
	@RequestMapping("/modify.cu")
	public String modify(int id, Model model) {
		//비지니스로직
		//선택한 고객정보를 DB에서 조회해와
		CustomerVO vo = service.customer_info(id);		
		//수정화면에 출력할 수 있도록 Model에 attribute로 담는다
		model.addAttribute("vo", vo);
		//응답화면연결
		return "customer/modify";
	}
	
	//고객정보화면 요청
	@RequestMapping("/info.cu")
	public String info(int id, Model model) {
		//비지니스로직
		//선택한 고객정보를 DB에서 조회해와
		CustomerVO vo = service.customer_info(id);
		//화면에 출력할 수 있도록 Model에 attribute로 담는다
		model.addAttribute("vo", vo);
		//응답화면연결
		return "customer/info";
	}
	
	
	
	//고객목록화면 요청
	@RequestMapping("/list.cu")
	public String list(HttpSession session, Model model) {
		session.setAttribute("category", "cu");
		//비지니스로직
		//DB에서 고객목록을 조회해와
		List<CustomerVO> list = service.customer_list();
		//화면에 출력할 수 있도록 Model 에 attribute로 담는다
		model.addAttribute("list", list);
		//응답화면연결
		return "customer/list";
	}
}
