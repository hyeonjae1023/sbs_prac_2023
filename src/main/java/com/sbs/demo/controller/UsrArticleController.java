package com.sbs.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.demo.service.ArticleService;
import com.sbs.demo.util.Ut;
import com.sbs.demo.vo.Article;
import com.sbs.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpSession httpSession, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		if(Ut.isEmpty(title)) {
			return ResultData.from("S-1","제목을 입력해 주세요");
		}
		
		if(Ut.isEmpty(body)) {
			return ResultData.from("S-2","내용을 입력해 주세요");
		}
		
		ResultData<Integer> writeArticleRd = articleService.writeArticle(loginedMemberId, title, body);
		int id = writeArticleRd.getData1();
		
		Article article = articleService.getArticle(id);
		
		return  ResultData.newData(writeArticleRd, article);
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getArticles();
		model.addAttribute("articles",articles);
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(HttpSession httpSession, int id) {
		boolean isLogined = false;

		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return ResultData.from("F-1",Ut.f("%d번 게시글이 존재하지 않습니다.",id));
		}
		
		articleService.deleteArticle(id);
		return ResultData.from("S-1",Ut.f("%d번 게시글이 삭제되었습니다.",id));
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Integer> doModify(HttpSession httpSession, int id, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if(httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
		if(isLogined == false) {
			return ResultData.from("F-A","로그인 후 이용해 주세요.");
		}
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return ResultData.from("F-1",Ut.f("%d번 게시글이 존재하지 않습니다.",id));
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(loginedMemberId, article);
		
		if(actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}
		
		articleService.modifyArticle(id, title, body);
		return ResultData.from("S-1",Ut.f("%d번 게시글이 수정되었습니다.",id));
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticleAction(int id) {
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.",id));
		}
		return  ResultData.from("S-1",Ut.f("%d번 게시물 입니다.",id),article);
	}
}
