package com.sbs.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.demo.service.ArticleService;
import com.sbs.demo.vo.Article;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = articleService.writeArticle(title, body);
		Article article = articleService.getArticle(id);
		
		return  article;
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return  articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return id + "번 게시글이 존재하지 않습니다.";
		}
		
		articleService.deleteArticle(id);
		return  id + "번 게시글이 삭제 되었습니다.";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return id + "번 게시글이 존재하지 않습니다.";
		}
		
		articleService.modifyArticle(id, title, body);
		return  id + "번 게시글이 수정 되었습니다.";
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticleAction(int id) {
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return id + "번 게시글이 존재하지 않습니다.";
		}
		return  article;
	}
}
