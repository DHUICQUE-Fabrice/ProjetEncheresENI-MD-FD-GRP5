package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	public Article insert(Article article);
	
	public List<Article> selectAll();
	
	public Article selectByID(int id);
	
	public List<Article> selectAllByCategorie(int id);
	
	public List<Article> selectAllByName(String name);
	
	public List<Article> selectAllArticlesOfUser(int userId);
	
	public Article updateArticle(Article article);
	
	public Article deleteArticle(int id);
	
	public List<Article> deleteArticleByUserId(int userId);
	
}
