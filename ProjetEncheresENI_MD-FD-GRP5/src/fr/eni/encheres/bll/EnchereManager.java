package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class EnchereManager {
	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public Enchere ajouter(Enchere enchere) throws BusinessException {
		BusinessException exception = new BusinessException();
		
		this.validerEnchereMontant(enchere, exception);
		this.validerCreditSuffisant(enchere, exception);
		if (!exception.hasErreurs()) {
			debiteCredite(enchere);
			this.enchereDAO.insert(enchere);
		} else {
			System.out.println(exception.getListeCodesErreurs().get(0));
			throw exception;
		}
		
		return enchere;
	}
	
	private void validerCreditSuffisant(Enchere enchere, BusinessException exception) {
		if (enchere.getMontantEnchere() > enchere.getUtilisateur().getCredit()) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_ENCHERE_CREDIT_INSUFFISANT);
		}
	}
	
	private void debiteCredite(Enchere enchere) {
		try {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateurDebite = enchere.getUtilisateur();
			
			Enchere enchereMaxPrecedente = selectMaxMontantByIdArticle(enchere.getArticle().getIdArticle());
			Utilisateur utilisateurRecredite = enchereMaxPrecedente.getUtilisateur();
			
			utilisateurDebite.setCredit(utilisateurDebite.getCredit() - enchere.getMontantEnchere());
			utilisateurRecredite.setCredit(utilisateurRecredite.getCredit() + enchereMaxPrecedente.getMontantEnchere());
			
			utilisateurManager.modifierUtilisateur(utilisateurDebite, "credit",
					String.valueOf(utilisateurDebite.getCredit()));
			utilisateurManager.modifierUtilisateur(utilisateurRecredite, "credit",
					String.valueOf(utilisateurRecredite.getCredit()));
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
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
		if (enchere.getMontantEnchere() <= enchere.getArticle().getPrixInitial()) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_ENCHERE_PRIX_INITIAL_ERREUR);
		} else if (enchere.getMontantEnchere() <= enchere.getArticle().getPrixVente()) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_ENCHERE_PRIX_ENCHERE_ERREUR);
		}
	}
	// TODO Méthodes supprimer, modifier, selectionner de EnchereManager
	
}
