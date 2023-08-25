package com.sbs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.demo.service.MemberService;
import com.sbs.demo.util.Ut;
import com.sbs.demo.vo.Member;
import com.sbs.demo.vo.ResultData;
import com.sbs.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickName, String cellphoneNo, String email) {
		if ( Ut.isEmpty(loginId) ) {
			return ResultData.from("F-1","아이디를 입력해 주세요.");
		}
		if ( Ut.isEmpty(loginPw) ) {
			return ResultData.from("F-2","비번을 입력해 주세요.");
		}
		if (Ut.isEmpty(name)) {
			return ResultData.from("F-3","이름을 입력해 주세요.");
		}
		if (Ut.isEmpty(nickName)) {
			return ResultData.from("F-4","닉네임을 입력해 주세요.");
		}
		if (Ut.isEmpty(cellphoneNo)) {
			return ResultData.from("F-5","전화번호를 입력해 주세요.");
		}
		if ( Ut.isEmpty(email) ) {
			return ResultData.from("F-6","이메일을 입력해 주세요.");
		}
		
		ResultData<Integer> joinRd = memberService.doJoin(loginId, loginPw, name, nickName, cellphoneNo, email);
		
		if(joinRd.isFail()) {
			return (ResultData) joinRd;
		}
		
		Member member = memberService.getMemberById(joinRd.getData1());
		return ResultData.newData(joinRd, member);
	}
	@RequestMapping("/usr/member/login")
	public String showLogin(HttpSession httpSession) {
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw) {
		
		
		Rq rq = (Rq)req.getAttribute("rq");
		
		
		if(rq.isLogined()) {
			return Ut.jsHistoryBack("이미 로그인 상태입니다.");
		}
		
		if(Ut.isEmpty(loginId)) {
			return Ut.jsHistoryBack("아이디를 입력하세요.");
		}
		if(Ut.isEmpty(loginPw)) {
			return Ut.jsHistoryBack("비밀번호를 입력하세요.");
		}
		
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return Ut.jsHistoryBack("존재하지 않는 아이디 입니다.");
		}
		if ( member.getLoginPw().equals(loginPw) == false ) {
			return Ut.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}
		
		rq.login(member);
		
		return Ut.jsReplace(Ut.f("%s님 환영합니다.",member.getNickname()),"/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		Rq rq = (Rq)req.getAttribute("rq");

		if(!rq.isLogined()) {
			return Ut.jsHistoryBack("이미 로그아웃 상태입니다.");
		}

		rq.logout();
		
		return Ut.jsReplace("로그아웃 되었습니다.","/");
	}
}
