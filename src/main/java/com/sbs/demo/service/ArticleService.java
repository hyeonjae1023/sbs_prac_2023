package com.sbs.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.demo.repository.ArticleRepository;
import com.sbs.demo.util.Ut;
import com.sbs.demo.vo.Article;
import com.sbs.demo.vo.ResultData;

@Service
public class ArticleService {
	ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public ResultData<Integer> writeArticle(int memberId, String title, String body) {
		articleRepository.writeArticle(memberId, title, body);
		
		int id = articleRepository.getLastArticleId();
		
		return ResultData.from("S-1",Ut.f("%d번 게시글이 작성 되었습니다.",id),id);
	}
	
	public Article getForPrintArticle(int actorId, int id) {
		Article article = articleRepository.getForPrintArticle(id);
		
		updateForPrintData(actorId, article);
		
		return article;
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);	
	}
	
	public ResultData<Article> modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
		Article article = getForPrintArticle(0, id);
		
		return ResultData.from("S-1",Ut.f("%d번 게시글이 수정 되었습니다.",id),article);
	}

	public List<Article> getForPrintArticles(int actorId) {
		List<Article> articles = articleRepository.getForPrintArticles();
		
		for(Article article : articles) {
			updateForPrintData(actorId, article);
		}
		return articles;
	}

	
	private void updateForPrintData(int actorId, Article article) {
		if( article == null) {
			return;
		}
		
		ResultData actorCanDeleteRd = actorCanDelete(actorId, article);
		article.setExta__actorCanDelete(actorCanDeleteRd.isSuccess());
		
	}

	private ResultData actorCanDelete(int actorId, Article article) {
		if( article == null) {
			return ResultData.from("F-1","게시물이 존재하지 않습니다.");
		}
		if(article.getMemberId() != actorId) {
			return ResultData.from("F-2","권한이 없습니다.");
		}
		
		return ResultData.from("S-1","삭제 가능합니다.");
	}
	
	public ResultData<Article> actorCanModify(int loginedMemberId, Article article) {
		if(article == null) {
			return ResultData.from("F-2","게시글이 존재하지 않습니다.");
		}
		if(article.memberId != loginedMemberId) {
			return ResultData.from("F-3","권한이 없습니다.");
		}
		
		return ResultData.from("S-1","수정 가능합니다.");
	}
}
