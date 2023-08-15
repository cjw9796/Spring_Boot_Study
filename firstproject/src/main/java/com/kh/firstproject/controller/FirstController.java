package com.kh.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//view Templates
// 화면을 담당하는 기술
// 웹 페이지를 하나의 틀로 만들고 여기에 변수를 삽입하게 한다. 
// 틀이 되는 페이지가 변수의 값에 따라서 수많은 페이지로 바뀔 수 있다. 
// 그 도구중 하나가 Mustache 


@Controller //이 클래스는 컨트롤러로 사용되는 클래스이다.
public class FirstController {
	
	// 브라우저에서 "/hi"라는 요청이 들어오면 niceToMeetYou메서드를 실행하게 된다. 
	@GetMapping("/hi")
	//view페이지로 데이터를 넘길 때 사용할 model 인터페이스 객체를 인수로 넣어준다. 
	public String niceToMeetYou1(Model model) {
		
		//model 인터페이스 객체에 addAttribute() 메서드로 
		//viewpage로 넘겨줄 데이터를 넣어서 
		model.addAttribute("username","홍길동");
		
		
		return "greetings"; //viewpage 이름 greetings.mustache 를 화면에 출력
	}
	
	@GetMapping("/bye")
	//view페이지로 데이터를 넘길 때 사용할 model 인터페이스 객체를 인수로 넣어준다. 
	public String niceToMeetYou(Model model) {
		
		//model 인터페이스 객체에 addAttribute() 메서드로 
		//viewpage로 넘겨줄 데이터를 넣어서 
		model.addAttribute("username","길동길동");
		
		
		return "goodbye"; //viewpage 이름 goodbye.mustache 를 화면에 출력 
	}
	
	@GetMapping("/test") //test 부트스트랩 로그인창 
	public String test(Model model) {
		
		model.addAttribute("username","새로운유저1");
		
		
		return"testtest"; //viewpage 이름 testtest.mustache 를 화면에 출력 
	}
	
	
	@GetMapping("/album") // album 부트스트랩 앨범창 
	public String album() {
		return "album"; //viewpage 이름 album.mustache 를 화면에 출력 
	}
	
	
	

}
