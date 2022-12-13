package com.and.middle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AndController {

	//@ResponseBody <= RestAPI방식은 데이터를 return하기 위한 방식이기때문에 @ResponseBody를 생략해도 똑같은 처리가됨
	@RequestMapping("/and")
	public String and() {
		
		return "test";
	}
}
