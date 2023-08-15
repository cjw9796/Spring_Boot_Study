package com.kh.rentcar.rentcar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.kh.rentcar.rentcar.service.RentcarService;

import lombok.extern.slf4j.Slf4j;

@RestController 
@Slf4j
public class RentcarApiController {
	
	@Autowired
	private RentcarService rentcarService;
	
	
	

	
	
	
}



