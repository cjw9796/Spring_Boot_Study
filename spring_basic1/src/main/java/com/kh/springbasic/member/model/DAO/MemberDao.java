package com.kh.springbasic.member.model.DAO;

import java.util.List;

import com.kh.springbasic.member.model.vo.Member;

public interface MemberDao {
	
	int insertMember(Member member);
	List<Member> selectAll();
	Member selectById(String id);
	
}
