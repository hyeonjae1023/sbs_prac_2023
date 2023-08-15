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
	public Object doJoin(String loginId, String loginPw, String name, String nickName, String cellphoneNo, String email) {
		if ( loginId == null || loginId.trim().length() == 0 ) {
			return "loginId(을)를 입력해주세요.";
		}
		if ( loginPw == null || loginPw.trim().length() == 0 ) {
			return "loginPw(을)를 입력해주세요.";
		}
		if ( name == null || name.trim().length() == 0 ) {
			return "name(을)를 입력해주세요.";
		}
		if ( nickName == null || nickName.trim().length() == 0 ) {
			return "nickName(을)를 입력해주세요.";
		}
		if ( cellphoneNo == null || cellphoneNo.trim().length() == 0 ) {
			return "cellphoneNo(을)를 입력해주세요.";
		}
		if ( email == null || email.trim().length() == 0 ) {
			return "email(을)를 입력해주세요.";
		}
		
		int id = memberService.doJoin(loginId, loginPw, name, nickName, cellphoneNo, email);
		
		if(id == -1) {
			return "이미 사용중인 아이디 입니다.";
		}
		Member member = memberService.getMemberById(id);
		return member;
	}

}
