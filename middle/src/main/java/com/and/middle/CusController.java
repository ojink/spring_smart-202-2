package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import customer.CustomerVO;

@RestController 
public class CusController {
	// 규칙 : Customer 모듈에 관련 된 맵핑은 모두 .cu로 끝낼것. 
	@Autowired @Qualifier("hanul") SqlSession sql;
	
	@RequestMapping(value = "/select.cu", produces = "text/html;charset=utf-8")
	public String select() {
		List<CustomerVO> list = sql.selectList("cu.select");
		return new Gson().toJson(list);
	}
	// delete.cu 요청시 customer테이블에서 사용하고있는 id값을 파라메터로 보내고
	// 해당하는 id에 row를 지우는 처리를 하기.(Spring<->Chrome)
	@RequestMapping(value = "/delete.cu", produces = "text/html;charset=utf-8")
	public String delete(int id) {
		int result = sql.delete("cu.delete" , id);//delete , insert , update => int
		System.out.println(result);
		return result +"";
	}
}
