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
		BusinessException exception = new BusinessException();

		// TODO Gérer les exceptions de EnchereManager;

		this.enchereDAO.insert(enchere);
		return enchere;
	}
	
	public List<Enchere> allEnchereByArticle(int idArticle) {
		return this.enchereDAO.selectAllByIDArticle(idArticle);
	}

	// TODO Méthodes supprimer, modifier, selectionner de EnchereManager

}
