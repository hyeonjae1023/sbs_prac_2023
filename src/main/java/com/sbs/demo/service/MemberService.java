package com.sbs.demo.service;

import org.springframework.stereotype.Service;

import com.sbs.demo.repository.MemberRepository;
import com.sbs.demo.util.Ut;
import com.sbs.demo.vo.Member;
import com.sbs.demo.vo.ResultData;

@Service
public class MemberService {
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public ResultData<Integer> doJoin(String loginId, String loginPw, String name, String nickName, String cellphoneNo, String email) {
		Member oldMember = getMemberByLoginId(loginId);
		
		if(oldMember != null) {
			return ResultData.from("F-7","이미 사용중인 아이디 입니다.");
		}
		
		oldMember = getMemberByNameAndEmail(name, email);
		
		if(oldMember != null) {
			return ResultData.from("F-8","이미 사용중인 이름과 이메일 입니다.");
		}
		
		memberRepository.doJoin(loginId, loginPw, name, nickName, cellphoneNo, email);
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1",Ut.f("%d번 회원가입이 완료 되었습니다.",id),id);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}
