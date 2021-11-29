package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.exceptions.BusinessException;

public class CategorieManager {
	private CategorieDAO categorieDAO;
	
	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public Categorie ajouter(Categorie categorie) throws BusinessException {
		BusinessException exception = new BusinessException();
		// TODO Gérer les exceptions de CategorieManager;
		
		this.categorieDAO.insert(categorie);
		return categorie;
	}
	
	public List<Categorie> selectAll() {
		return this.categorieDAO.selectAll();
	}
	// TODO Méthodes supprimer, modifier, selectionner de CategorieManager
	
	public Categorie selectById(int id) {
		return this.categorieDAO.selectByID(id);
	}
	
}
