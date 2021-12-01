package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.exceptions.BusinessException;

public class ArticleManager {
	private ArticleDAO articleDAO;
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	// Methode de ArticleDAO
	public Article ajouter(Article article) throws BusinessException {
		BusinessException exception = new BusinessException();
		
		this.validerNomArticle(article, exception);
		this.validerDescription(article, exception);
		this.validerDateDebut(article, exception);
		System.out.println(article.getDateDebut());
		this.validerDateFin(article, exception);
		System.out.println(article.getDateFin());
		this.validerPrixInitial(article, exception);
		this.validerRue(article, exception);
		this.validerCodePostal(article, exception);
		this.validerVille(article, exception);

		
		if (!exception.hasErreurs()) {
			this.articleDAO.insert(article);
		} else {
			throw exception;
		}
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
	
	// Methode de validation de l'article. 
	private void validerNomArticle(Article article, BusinessException exception) {
		if (article.getNomArticle().equals("")) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_NOM_ARTICLE_VIDE);
		}
	}
	
	private void validerDescription(Article article, BusinessException exception) {
		if (article.getDescription().equals("")) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_DESCRITPTION_VIDE);
		}
	}
	
	private void validerDateDebut(Article article, BusinessException exception) {
		if (article.getDateDebut().toString().equals("")) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_DATE_DEBUT_ENCHERE_VIDE);
		}else if (article.getDateDebut().isBefore(LocalDate.now())) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_DATE_DEBUT_VALEUR_ERREUR);
		}
	}
	
	private void validerDateFin(Article article, BusinessException exception) {
		if (article.getDateFin().toString().equals("")) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_DATE_FIN_ENCHERE_VIDE);
		}else if (article.getDateFin().isBefore(article.getDateDebut())) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_DATE_FIN_VALEUR_ERREUR);
		}
	}
	
	private void validerPrixInitial(Article article, BusinessException exception) {
		if (article.getPrixInitial() <= 0) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_PRIX_INITIAL_NOMBRE_ERREUR);
		}
	}
	
	private void validerVille(Article article, BusinessException exception) {
		if (article.getVille().equals("")) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_VILLE_NON_VIDE);
		}
		
	}
	
	private void validerRue(Article article, BusinessException exception) {
		if (article.getRue().equals(""))
			exception.ajouterErreur(CodesErreursBLL.REGLE_RUE_NON_VIDE);
	}
	
	private void validerCodePostal(Article article, BusinessException exception) {
		int cp = article.getCodePostal();
		if (cp <= 0) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_CODE_POSTAL_NOMBRE_ERREUR);
		}else if (cp < 1000 || cp > 99999) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_CODE_POSTAL_VALEUR_ERREUR);
		}
	}
}
