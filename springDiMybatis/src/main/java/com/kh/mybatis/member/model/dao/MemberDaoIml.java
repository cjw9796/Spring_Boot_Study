package com.kh.mybatis.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.mybatis.member.model.vo.Member;

@Repository("memberDao")
public class MemberDaoIml implements MemberDao{

	@Override
	public int insertMember(SqlSessionTemplate session, Member member) {
		// TODO Auto-generated method stub
		return session.insert("memberMapper.insertMember", member);
	}

	@Override
	public List<Member> selectMemberAll(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("memberMapper.selectMemberAll");
	}

	@Override
	public Member selectMemberById(SqlSessionTemplate session, String id) {
		// TODO Auto-generated method stub
		return session.selectOne("memberMapper.selectMemberById",id);
	}

	@Override
	public int deleteMember(SqlSessionTemplate session, String id) {
		// TODO Auto-generated method stub
		return session.delete("memberMapper.deleteMember",id);
	}
	
	
	
	

}
