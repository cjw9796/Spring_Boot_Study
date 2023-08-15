package com.kh.mybatis.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mybatis.member.model.service.MemberService;
import com.kh.mybatis.member.model.vo.Member;

@Controller
public class MemberController {

	// 컨트롤러에서 서비스로 연결을 해야된다.
	// 연결 도와주는 것이 bean
	@Autowired
	private MemberService service;

	@RequestMapping("/member/index.do") // http://localhost:8084/mybatis/member/index.do
	public String index() {
		return "member/index";
	}

	// 회원가입
	// 실제 데이터를 저장하는 공간 데이터베이스 필요.
	// mybatis 이용해서 데이터를 저장
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll(Model model, Member m) {
		System.out.println("가입 정보:" + m);
		int result = service.joinMember(m);

		// 만약 1이 온다면 모델 객체에 "msg" "회원가입에 성공하였습니다."
		// 0미다. redirect: error.do

		if (result > 0) {
			model.addAttribute("msg", "회원 가입에 성공하였습니다.");
			return "/member/index";
		} else {
			return "redirect:error.do";
		}
	}

	// 회원목록 조회
	@RequestMapping("/member/memberList.do")
	String memberList(Model model) {
		List<Member> list = service.getMemberList();
		model.addAttribute("list", list);
		return "/member/memberList";
	}

	// 멤버 검색
	@RequestMapping("member/memberSearch.do")
	String memberSearch(Model model, String id) {

		// member의 객체가 없다면 즉 null 바로 에러페이지로 리다이렉트
		// 찾았으면 /member/memberVidw
		Member member = service.searchMember(id);
		if (member != null) {
			model.addAttribute("member", member);
			return "/member/memberView";
		} else {
			return "redirect:error.do";
		}
	}

	// 멤버 탈퇴
	@RequestMapping("member/memberDel.do")
	String memberdelete(Model model, String id) {
		// member의 객체가 없다면 즉 null 바로 에러페이지로 리다이렉트
		// 찾았으면 /member/memberVidw
		int result = service.deleteMember(id);

		if (result > 0) {
			model.addAttribute("msg", "회원 탈퇴에 성공하였습니다.");
			return "/member/index";
		} else {
			return "redirect:error.do";
		}

	}

	@RequestMapping("/member/error.do")
	public String errorPage(String msg) {
		System.out.println("에러 발생 로그 출력");
		return "common/error";
	}

}
