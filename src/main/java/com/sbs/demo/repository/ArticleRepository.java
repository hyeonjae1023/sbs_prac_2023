package com.sbs.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sbs.demo.vo.Article;

@Mapper
public interface ArticleRepository {
//	@Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` = #{body}")
	public void writeArticle(@Param("memberId") int memberId, @Param("title") String title,@Param("body") String body);
	
//	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(@Param("id") int id);
	
//	@Update("UPDATE article SET title = #{title}, `body` = #{body} WHERE id = #{id};")
	public void modifyArticle(@Param("id") int id,@Param("title") String title,@Param("body") String body);
	
	@Select("""
			SELECT A.*,
			M.nickName AS writerName
			FROM article AS A
			LEFT JOIN `member` AS M
			ON A.memberId = M.id
			WHERE A.id = #{id} 
			""")
	public Article getForPrintArticle(@Param("id") int id);
	
	@Select("""
			SELECT A.*,
			M.nickName AS writerName
			FROM article AS A
			LEFT JOIN `member` AS M
			ON A.memberId = M.id
			ORDER BY A.id DESC
			""")
	public List<Article> getForPrintArticles();
	
//	@Select("SELECT LAST_INSERT_ID()")
	public int getLastArticleId();
}
