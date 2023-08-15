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
		Member oldMember = getMemberByLoginId(loginId);
		
		if(oldMember != null) {
			return -1;
		}
		
		memberRepository.doJoin(loginId, loginPw, name, nickName, cellphoneNo, email);
		return memberRepository.getLastInsertId();
	}

	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
