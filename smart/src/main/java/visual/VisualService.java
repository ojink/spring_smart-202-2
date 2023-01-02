package visual;

import java.util.HashMap;
import java.util.List;

public interface VisualService {
	List<HashMap<String, Object>> department(); //부서별 사원수조회
	List<HashMap<String, Object>> hirement_year();//채용인원수(년도별/월별)조회
	List<HashMap<String, Object>> hirement_month();
	
}
