package fr.eni.encheres.bll;

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
	}

	// TODO Méthodes supprimer, modifier, selectionner de EnchereManager

}
