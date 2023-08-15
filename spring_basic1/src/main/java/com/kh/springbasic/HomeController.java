package com.kh.springbasic;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Spring mvc 개념 정리
 * Spring은 서블릿 대비 어노테이션(@Annotation)의 활용도가 높아졌다.
 * Spring 프레임워크는 상속 설계를 지양하고 interface 기반 설계를 권장한다.
 * Dependency injection(의존성 주입)을 통해서 객체를 직접 생성하지 않고 spring에서 생성 주입
 * 시키기 편하기 위해 
 * new 지양하고 프레임워크에서 대신 생성하는데 장점 : 의존성을 줄이고 메모리 관리가 우수하다. 
 * spring mvc에서 철저하게 mvc2 패턴을 사용하도록 설계 되어있다.
 * -> request(url + 파라메터) -> Controller(java 객체) + Model -> View(jsp or Thymleaf)
 * 
 * 서블릿 class 단위로 url을 매핑했던 기능이 @Controller에 핸들러 메서드로 이관 
 * web-inf구조(보안 기능)으로 인해 url로 jsp 접근 x
 * html에 직접적인 접근이 불가하고 반드시 controller -> view로 접근이 필요하다. 
 * 
 * 
 * 
 */

/*
root-context.xml
servlet-context.xml
pom.xml
web.xml
MemberController.java
Member.java

MemberDao.java
MemberDaoImpl.java

MemberService.java
MemberServiceImpl.java 
 */

@Controller  //컨트롤러. servlet class의 기능을 대체함 
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//핸들러 메서드(Handler Method)
	// - URL 매핑이 되어있는 메서드를 지칭, 특정단위 하나의 기능 담당하는 것이 일반적이다. 
	
	// return 값이 문자열일 경우 기본적으로 forward 되는 jsp 페이지를 지칭한다. 
	// 매핑되는 url 은 주로 .do 를 붙여서 특정 행위에 대한 처리를 담당시킨다. 
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// URL을 매핑시키는 가장 간단한 방법
	@RequestMapping("home2.do")
	public String home2() {
		return "home2";  // /WEB-INF/views/home2.jsp로 forward로 전달됨
	}
	
	/*
	// home2의 메서드 이름과 가장 유사한 jsp로 연결한다.
	@RequestMapping("home2.do")
	public void home2() {
	}
	*/
	
	// home3 메서드에서 home2.do 호출하고 싶을 경우에는
	@RequestMapping("home3.do")
	public String home3() {
	//	return "home2.do";  // /WEB-INF/views/home2.do.jsp로 forward로 전달됨 = 에러발생 
		
		return "redirect:home2.do"; //redirect로 전달되는 방법 
	}
	
	
}
