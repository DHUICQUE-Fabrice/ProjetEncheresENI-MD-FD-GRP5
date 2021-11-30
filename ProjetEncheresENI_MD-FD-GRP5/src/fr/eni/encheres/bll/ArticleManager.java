package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.exceptions.BusinessException;

public class ArticleManager {
	private ArticleDAO articleDAO;
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	public Article ajouter(Article article) throws BusinessException {
		this.articleDAO.insert(article);
		return article;
	}
	
	public List<Article> allArticle() {
		return this.articleDAO.selectAll();
	}
	
	public List<Article> allArticleByCategorie(int idCategorie) {
		return this.articleDAO.selectAllByCategorie(idCategorie);
	}
	
	public List<Article> allArticleByName(String nomArticle) {
		return this.articleDAO.selectAllByName(nomArticle);
	}
	
	public List<Article> allArticlesByUser(int userId) {
		return this.articleDAO.selectAllArticlesOfUser(userId);
	}
	
	public Article selectByIdArticle(int idArticle) {
		return this.articleDAO.selectByID(idArticle);
	}
	
	public Article updateArticle(Article article) throws BusinessException {
		this.articleDAO.updateArticle(article);
		return null;
	}
	
	public Article deleteArticle(int idArticle) throws BusinessException {
		this.articleDAO.deleteArticle(idArticle);
		return null;
	}
	
	public void deleteAllArticlesOfUser(int userId) {
		this.articleDAO.deleteArticleByUserId(userId);
	}
	
}
