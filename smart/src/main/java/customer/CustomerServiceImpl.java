package customer;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("customer")
public class CustomerServiceImpl implements CustomerService {
	//@Autowired private CustomerDAO dao; 
	private CustomerDAO dao;
	public CustomerServiceImpl(CustomerDAO dao) {
		this.dao = dao;
	}
	
	
	@Override
	public void customer_insert(CustomerVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CustomerVO> customer_list() {
		return dao.customer_list();
	}

	@Override
	public CustomerVO customer_info(int id) {
		return null;
	}

	@Override
	public void customer_update(CustomerVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void customer_delete(int id) {
		// TODO Auto-generated method stub

	}

}
