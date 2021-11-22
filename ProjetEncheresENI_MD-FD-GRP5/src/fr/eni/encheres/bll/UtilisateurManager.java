package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;

public class UtilisateurManager {
	private DAO<Utilisateur> utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur ajouter(Utilisateur utilisateur) {

		// validerUtilisateur;

		this.utilisateurDAO.insert(utilisateur);
		System.out.println(utilisateur.toString());
		return utilisateur;
	}
}
