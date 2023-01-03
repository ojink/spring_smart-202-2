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
	
	//상위3개부서의 년도별 채용인원수 정보 조회요청
	@ResponseBody @RequestMapping("/visual/hirement/top3/year")
	public Object hirement_top3_year() {
		return service.hirement_top3_year();
	}
	//상위3개부서의 월별 채용인원수 정보 조회요청
	@ResponseBody @RequestMapping("/visual/hirement/top3/month")
	public Object hirement_top3_month() {
		return service.hirement_top3_month();
	}
	
	//년도별 채용인원수 정보 조회요청
	@ResponseBody @RequestMapping("/visual/hirement/year")
	public Object hirement_year() {
//		List<HashMap<String, Object>> list = service.hirement_year();
//		return list;
		return service.hirement_year();
	}
	
	//월별 채용인원수 정보 조회요청
	@ResponseBody @RequestMapping("/visual/hirement/month")
	public Object hirement_month() {
		return service.hirement_month();
	}
	
	
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
