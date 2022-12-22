package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import member.MemberVO;


@RestController
public class AndMemberController {
	@Autowired @Qualifier("hanul") SqlSession sql ;
	@RequestMapping(value = "/login.me" , produces = "text/html;charset=utf-8")
	public String andVo(MemberVO vo) {
		MemberVO temp_vo = sql.selectOne("me.login" , vo);
		if(temp_vo != null ) {
			System.out.println(temp_vo.getEmail());			
		}else {
			System.out.println("로그인 실패");
		}
		return new Gson().toJson(temp_vo);
	}
	//social.me
	@RequestMapping(value = "/social.me" , produces = "text/html;charset=utf-8")
	public String andVo(String email) {
		System.out.println(email);
		return new Gson().toJson("");
	}
	
	
	
}
