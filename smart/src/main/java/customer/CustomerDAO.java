package customer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO implements CustomerService {
	//@Autowired private SqlSession sql;
	private SqlSession sql; //SqlSessionTemplate
	public CustomerDAO(SqlSession sql) {
		this.sql = sql;
	}
	
	@Override
	public void customer_insert(CustomerVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CustomerVO> customer_list() {
		return sql.selectList("customer.list");
	}

	@Override
	public CustomerVO customer_info(int id) {
		return sql.selectOne("customer.info", id);
	}

	@Override
	public void customer_update(CustomerVO vo) {
	}

	@Override
	public void customer_delete(int id) {
		// TODO Auto-generated method stub

	}

}
