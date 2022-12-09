package co.kr.smart;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	//org.apache.commons.dbcp2.BasicDataSource
	//org.mybatis.spring.SqlSessionFactoryBean
	//org.mybatis.spring.SqlSessionTemplate
	//org.springframework.web.servlet.view.tiles3.TilesViewResolver
	//org.springframework.web.servlet.view.tiles3.TilesConfigurer
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		//session.setAttribute("category", "");
		session.removeAttribute("category");
		return "home";
	}
	
}
