package co.kr.smart;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import customer.CustomerServiceImpl;

@Controller
public class CustomerController {
	//@Autowired 
	private CustomerServiceImpl service;
	public CustomerController(CustomerServiceImpl service) {
		this.service = service;
	}
	
	//고객목록화면 요청
	@RequestMapping("/list.cu")
	public String list(HttpSession session) {
		session.setAttribute("category", "cu");
		//비지니스로직
		//DB에서 고객목록을 조회해와 화면에 출력할 수 있도록 Model 에 attribute로 담는다
		//응답화면연결
		return "customer/list";
	}
}
