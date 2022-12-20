package com.and.middle;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return "요기까지옴";
	}
}
