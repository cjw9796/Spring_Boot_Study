package com.kh.springbasic.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springbasic.member.model.DAO.MemberDao;
import com.kh.springbasic.member.model.vo.Member;

@Service // 서비스임을 알리고, Bean을 통해서 자동으로 객체 생성되는 것 
public class MemberServiceImpl implements MemberService{
	
	//기존 서블릿에서 dao를 활용하던 방법
	// private Member dao = MemberDaoImpl();
	
	@Autowired // 사용자 별도로 객체를 만들 지 않고 BeanFactory로 부터 객체의 관리를 위임하는 어노테이션이다. 
	// type은 MemberDAO가 되고 실제 생성되는 객체는 MemberDAOImpl가 된다.
	private MemberDao dao; 
	
	@Override
	public int joinMember(Member member) {
		return dao.insertMember(member);
	}

	@Override
	public List<Member> getAllList() {
		return dao.selectAll();
	}

	@Override
	public Member login(String id) {
		return dao.selectById(id);
	}

}
