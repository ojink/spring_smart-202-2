package hr;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class HrDAO implements HrService {
	//@Autowired private SqlSession sql;
	private SqlSession sql;
	public HrDAO(@Qualifier("hr") SqlSession sql) {
		this.sql = sql;
	}
	
	@Override
	public void employee_insert(EmployeeVO vo) {
		sql.insert("hr.insert", vo);
	}

	@Override
	public List<EmployeeVO> employee_list() {
		return sql.selectList("hr.list");
	}

	@Override
	public EmployeeVO employee_info(int employee_id) {
		return sql.selectOne("hr.info", employee_id);
	}

	@Override
	public void employee_update(EmployeeVO vo) {
		sql.update("hr.update", vo);
	}

	@Override
	public void employee_delete(int employee_id) {
		sql.delete("hr.delete", employee_id);
	}

	@Override
	public List<DepartmentVO> hr_department_list() {
		return sql.selectList("hr.department_list");
	}

	@Override
	public List<JobVO> hr_job_list() {
		return sql.selectList("hr.job_list");
	}

}
