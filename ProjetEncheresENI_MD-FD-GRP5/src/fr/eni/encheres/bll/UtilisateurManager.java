package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.exceptions.BusinessException;

public class UtilisateurManager {
	private DAO<Utilisateur> utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur ajouter(Utilisateur utilisateur) throws BusinessException {

		BusinessException exception = new BusinessException();

		this.validerTelephone(utilisateur, exception);
		this.validerEmail(utilisateur, exception);
		this.validerCodePostal(utilisateur, exception);
		this.validerMotDePasse(utilisateur, exception);

		if (!exception.hasErreurs()) {
			this.utilisateurDAO.insert(utilisateur);
		} else {
			throw exception;
		}
		System.out.println(utilisateur.toString());
		return utilisateur;
	}

	public List<Utilisateur> allUsers() {
		return this.utilisateurDAO.selectAll();
	}

	private void validerEmail(Utilisateur utilisateur, BusinessException exception) {
		if (!utilisateur.getEmail().contains("@")) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_MAIL_AROBASE_ERREUR);
		}
	}

	private void validerTelephone(Utilisateur utilisateur, BusinessException exception) {
		if (utilisateur.getTelephone() != null) {
			String number = utilisateur.getTelephone();
			for (int i = 0; i < number.length(); i++) {
				if (!Character.isDigit(number.charAt(i))) {
					exception.ajouterErreur(CodesErreursBLL.REGLE_NUMERO_TELEPHONE_ERREUR);
					;
				}
			}
		}
	}

	private void validerCodePostal(Utilisateur utilisateur, BusinessException exception) {
		String number = utilisateur.getTelephone();
		Boolean isNumber = true;
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i))) {
				exception.ajouterErreur(CodesErreursBLL.REGLE_CODE_POSTAL_NOMBRE_ERREUR);
				isNumber = false;
			}
		}
		if (isNumber) {
			int cp = Integer.parseInt(number);
			if (cp < 1000 || cp > 99999) {
				exception.ajouterErreur(CodesErreursBLL.REGLE_CODE_POSTAL_VALEUR_ERREUR);
			}
		}
	}

	private void validerMotDePasse(Utilisateur utilisateur, BusinessException exception) {
		// TODO vérification de la force du mdp
	}
}
