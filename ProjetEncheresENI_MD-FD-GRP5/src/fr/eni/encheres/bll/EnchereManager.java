package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class EnchereManager {
	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public Enchere ajouter(Enchere enchere) throws BusinessException {
		this.enchereDAO.insert(enchere);
		return enchere;
	}
	
	public List<Enchere> allEnchereByArticle(int idArticle) {
		return this.enchereDAO.selectAllByIDArticle(idArticle);
	}
	
	public void supprimerEnchere(Enchere enchere) {
		this.enchereDAO.delete(enchere);
	}
	
	public void deleteAllEncheresOfArticle(int articleId) {
		this.enchereDAO.deleteEncheresByArticleId(articleId);
	}
	
	public void deleteAllEncheresOfUser(int userId) {
		this.enchereDAO.deleteEncheresByUserId(userId);
	}
	
	public Enchere selectMaxMontantByIdArticle(int idArticle) {
		return enchereDAO.selectMaxMontantByIdArticle(idArticle);
	}
	
	
	private void validerEnchereMontant(Enchere enchere, BusinessException exception) {
		if (enchere.getMontantEnchere() <= 0) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_PRIX_INITIAL_NOMBRE_ERREUR);
		}
	}
	// TODO Méthodes supprimer, modifier, selectionner de EnchereManager
	
}
