package com.and.middle;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import customer.CustomerVO;

@RestController
public class AndController {
	@Autowired @Qualifier("hanul") SqlSession sql;
	//@ResponseBody <= RestAPI방식은 데이터를 return하기 위한 방식이기때문에 @ResponseBody를 생략해도 똑같은 처리가됨
	@RequestMapping(value = "/and" , produces = "text/html;charset=utf-8")
	public String and() {
		
		System.out.println(sql.selectOne("test.test")+"");
		// customer테이블에 있는 정보를 조회해서 Spring콘솔에 찍어보기.
		List<CustomerVO> list = sql.selectList("test.cus_list");
		System.out.println(list.size());
		
		for (CustomerVO vo : list) {
			System.out.println(vo.getName());
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName());
		}
		
		return new Gson().toJson(list);
	}
	
	@RequestMapping(value = "/andVo" , produces = "text/html;charset=utf-8")
	public String andVo(String data) {
		System.out.println(data);
		List<CustomerVO> list = sql.selectList("test.cus_list");
		return new Gson().toJson(list.get(0));
	}
	
}
