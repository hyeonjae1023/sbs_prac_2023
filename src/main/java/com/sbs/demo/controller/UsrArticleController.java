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
import com.sbs.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpServletRequest req, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(Ut.isEmpty(title)) {
			return ResultData.from("F-1","제목을 입력해 주세요");
		}
		
		if(Ut.isEmpty(body)) {
			return ResultData.from("F-2","내용을 입력해 주세요");
		}
		
		ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		int id = writeArticleRd.getData1();
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		return  ResultData.newData(writeArticleRd, article);
	}
	
	@RequestMapping("usr/article/write")
	public String showWrite() {
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId());
		
		model.addAttribute("articles",articles);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(rq.isLogined() == false) {
			return Ut.jsHistoryBack("로그인 후 이용해 주세요.");
		}
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if(article.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack("권한이 없습니다.");
		}
		
		if(article == null) {
			ResultData.from("F-1",Ut.f("%d번 게시글이 존재하지 않습니다.",id));
		}
		
		articleService.deleteArticle(id);
		
		return Ut.jsReplace(Ut.f("%d번 게시글이 삭제되었습니다.",id),"../article/list");
	}
	@RequestMapping("usr/article/modify")
	public String showModify(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if(article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물이 존재하지 않습니다.",id));
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);
		
		model.addAttribute("article",article);
		
		if(actorCanModifyRd.isFail()) {
			return rq.historyBackJsOnView(actorCanModifyRd.getMsg());
		}
		
		return "usr/article/modify";
		
	}
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(rq.isLogined() == false) {
			return Ut.jsHistoryBack("로그인 후 이용해 주세요.");
		}
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if(article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시글이 존재하지 않습니다.",id));
		}
		
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMemberId(), article);
		
		if(actorCanModifyRd.isFail()) {
			return Ut.jsHistoryBack(actorCanModifyRd.getMsg());
		}
		
		articleService.modifyArticle(id, title, body);
		return Ut.jsReplace(Ut.f("%d번 글이 수정되었습니다.", id), Ut.f("../article/detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		model.addAttribute("article",article);
		
		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticleAction(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if(article == null) {
			return ResultData.from("F-1",Ut.f("%d번 게시물은 존재하지 않습니다.",id));
		}
		
		return  ResultData.from("S-1",Ut.f("%d번 게시물 입니다.",id),article);
	}
}
