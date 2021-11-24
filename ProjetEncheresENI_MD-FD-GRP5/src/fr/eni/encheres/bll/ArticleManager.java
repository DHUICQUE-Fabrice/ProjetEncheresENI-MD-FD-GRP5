package fr.eni.encheres.bll;

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
		BusinessException exception = new BusinessException();
		// TODO Gérer les exceptions de ArticleManager;

		this.articleDAO.insert(article);
		return article;
	}
	// TODO Méthodes supprimer, modifier, selectionner de ArticleManager

}
