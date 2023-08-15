package com.kh.rentcar.rentcar.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FirstApiController {
	
	@GetMapping("/api/hello")
	public String hello() {
		log.info("FirstApiController의 hello() 메서드 실행");
		return "hello world22222222222222";
	}
	

}
