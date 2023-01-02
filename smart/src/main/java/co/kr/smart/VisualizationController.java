package co.kr.smart;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import visual.VisualServiceImpl;

@Controller
public class VisualizationController {
	@Autowired private VisualServiceImpl service;
	
	//부서별사원수 정보 조회요청
	@ResponseBody @RequestMapping("/visual/department")
	public List<HashMap<String, Object>> department() {
		List<HashMap<String, Object>> list = service.department();
		return list;
	}
	
	//시각화 화면 요청
	@RequestMapping("/list.vi")
	public String list(HttpSession session ) {
		session.setAttribute("category", "vi");
		return "visual/list";
	}
}
