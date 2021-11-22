package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Utilisateur;

public class DAOFactory {
	public static DAO<Utilisateur> getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

}
