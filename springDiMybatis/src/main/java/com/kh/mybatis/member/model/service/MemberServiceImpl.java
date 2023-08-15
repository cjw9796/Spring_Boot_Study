package com.kh.mybatis.member.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;



@Service("memberService") // 서비스임을 알리고, Bean을 통해서 자동으로 객체 생성되는 것 
public class MemberServiceImpl implements MemberService{
	
	
	
	@Autowired
	//@Qualifier("memberDao")
	private MemberDao dao; 
	
	// 마이바티스 연결하기 위해서 sqlSession 객체를 생성해야된다.
	// root-context.xml에 정의한 bean 
	
	@Autowired
	@Qualifier("sqlSession")
	private SqlSessionTemplate session;
	
	@Override
	public int joinMember(Member member) {
		return dao.insertMember(session, member);
	}

	@Override
	public Member searchMember(String id) {
		return dao.selectMemberById(session, id);
	}

	@Override
	public List<Member> getMemberList() {
		// TODO Auto-generated method stub
		return dao.selectMemberAll(session);
	}

	@Override
	public int deleteMember(String id) {
		return dao.deleteMember(session, id);
	}

}
