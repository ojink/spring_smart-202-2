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
