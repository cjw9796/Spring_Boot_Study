package com.kh.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.mvc.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;
	
	@RequestMapping(value="/login",method =RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value ="code",required=false)String code) {
		// 1. 인가코드 
		System.out.println("####"+code);

		// 2. 토큰 받기 
		String access_Token = ms.getAccessToken(code);
		System.out.println(access_Token);
		
		// 3. 정보를 출력하는 메서드를 호출 
		System.out.println("getUserInfo(): " + ms.getUserInfo(access_Token));
		
		
		
		return "login";
	}
}
