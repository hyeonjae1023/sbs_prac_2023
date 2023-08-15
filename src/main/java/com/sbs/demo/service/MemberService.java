package com.sbs.demo.service;

import org.springframework.stereotype.Service;

import com.sbs.demo.repository.MemberRepository;
import com.sbs.demo.vo.Member;

@Service
public class MemberService {
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public int doJoin(String loginId, String loginPw, String name, String nickName, String cellphoneNo, String email) {
		memberRepository.doJoin(loginId, loginPw, name, nickName, cellphoneNo, email);
		return memberRepository.getLastInsertId();
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
