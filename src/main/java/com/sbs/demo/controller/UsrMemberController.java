package com.sbs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.demo.service.MemberService;
import com.sbs.demo.vo.Member;

@Controller
public class UsrMemberController {
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Member doJoin(String loginId, String loginPw, String name, String nickName, String cellphoneNo, String email) {
		int id = memberService.doJoin(loginId, loginPw, name, nickName, cellphoneNo, email);
		Member member = memberService.getMemberById(id);
		return member;
	}

}
