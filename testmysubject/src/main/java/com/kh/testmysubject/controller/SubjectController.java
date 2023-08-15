package com.kh.testmysubject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubjectController {
	
	@GetMapping("/subject/login")
	public String loginForm() {
		
		return "/Subject/login";
	}
	

	@GetMapping("/subject/main")
	public String mainForm() {
		
		return "/Subject/main";
	}
	

}
