package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.exceptions.BusinessException;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur ajouter(Utilisateur utilisateur) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		this.validerPseudo(utilisateur, exception);
		this.validerNom(utilisateur, exception);
		this.validerPrenom(utilisateur, exception);
		this.validerEmail(utilisateur, exception);
		this.validerTelephone(utilisateur, exception);
		this.validerRue(utilisateur, exception);
		this.validerCodePostal(utilisateur, exception);
		this.validerVille(utilisateur, exception);
		this.validerMotDePasse(utilisateur, exception);
		
		if (!exception.hasErreurs()) {
			this.utilisateurDAO.insert(utilisateur);
		} else {
			throw exception;
		}
		return utilisateur;
	}
	
	public List<Utilisateur> allUsers() {
		return this.utilisateurDAO.selectAll();
	}
	
	public Utilisateur modifierUtilisateur(Utilisateur utilisateur, String champ, String nouvelleValeur)
			throws BusinessException {
		BusinessException exception = new BusinessException();
		Utilisateur utilisateurModifie = utilisateur;
		switch (champ) {
			case "pseudo":
				utilisateurModifie.setPseudo(nouvelleValeur);
				this.validerPseudo(utilisateurModifie, exception);
				break;
			case "nom":
				utilisateurModifie.setNom(nouvelleValeur);
				this.validerNom(utilisateurModifie, exception);
				break;
			case "prenom":
				utilisateurModifie.setPrenom(nouvelleValeur);
				this.validerPrenom(utilisateurModifie, exception);
				break;
			case "email":
				utilisateurModifie.setEmail(nouvelleValeur);
				this.validerEmail(utilisateurModifie, exception);
				break;
			case "telephone":
				utilisateurModifie.setTelephone(nouvelleValeur);
				validerTelephone(utilisateurModifie, exception);
				break;
			case "rue":
				utilisateurModifie.setRue(nouvelleValeur);
				validerRue(utilisateurModifie, exception);
				break;
			case "codePostal":
				utilisateurModifie.setCodePostal(nouvelleValeur);
				validerCodePostal(utilisateurModifie, exception);
				break;
			case "ville":
				utilisateurModifie.setVille(nouvelleValeur);
				validerVille(utilisateurModifie, exception);
				break;
			case "password":
				utilisateurModifie.setMotDePasse(nouvelleValeur);
				validerMotDePasse(utilisateurModifie, exception);
			default:
				break;
		}
		if (!exception.hasErreurs()) {
			this.utilisateurDAO.update(utilisateurModifie);
			return utilisateurModifie;
		} else {
			throw exception;
		}
	}
	
	public Utilisateur getUserById(int id) {
		return this.utilisateurDAO.selectByID(id);
	}
	
	public Utilisateur supprimerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		int userId = utilisateur.getIdUtilisateur();
		EnchereManager enchereManager = new EnchereManager();
		enchereManager.deleteAllEncheresOfUser(userId);
		ArticleManager articleManager = new ArticleManager();
		List<Article> articlesToDelete = articleManager.allArticlesByUser(userId);
		for (Article article : articlesToDelete) {
			List<Enchere> encheres = enchereManager.allEnchereByArticle(article.getIdArticle());
			for (Enchere enchere : encheres) {
				enchereManager.supprimerEnchere(enchere);
			}
			articleManager.deleteArticle(article.getIdArticle());
		}
		this.utilisateurDAO.delete(utilisateur.getIdUtilisateur());
		return utilisateur;
	}
	
	private void validerVille(Utilisateur utilisateur, BusinessException exception) {
		if (utilisateur.getVille().equals("")) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_VILLE_NON_VIDE);
		}
		
	}
	
	private void validerRue(Utilisateur utilisateur, BusinessException exception) {
		if (utilisateur.getRue().equals(""))
			exception.ajouterErreur(CodesErreursBLL.REGLE_RUE_NON_VIDE);
	}
	
	private void validerPrenom(Utilisateur utilisateur, BusinessException exception) {
		if (utilisateur.getPrenom().equals("")) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_PRENOM_NON_VIDE);
		}
	}
	
	private void validerNom(Utilisateur utilisateur, BusinessException exception) {
		if (utilisateur.getNom().equals("")) {
			exception.ajouterErreur(CodesErreursBLL.REGLE_NOM_NON_VIDE);
		}
	}
	
	private void validerEmail(Utilisateur utilisateur, BusinessException exception) {
		String email = utilisateur.getEmail();
		if (!email.equals("")) {
			if (!email.contains("@")) {
				exception.ajouterErreur(CodesErreursBLL.REGLE_MAIL_AROBASE_ERREUR);
			}
			List<Utilisateur> utilisateurs = this.allUsers();
			for (Utilisateur user : utilisateurs) {
				if (email.equals(user.getEmail())) {
					exception.ajouterErreur(CodesErreursBLL.REGLE_EMAIL_EN_DOUBLE_ERREUR);
				}
			}
		} else {
			exception.ajouterErreur(CodesErreursBLL.REGLE_EMAIL_NON_VIDE);
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
		String number = utilisateur.getCodePostal();
		if (!number.equals("")) {
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
			} else {
				exception.ajouterErreur(CodesErreursBLL.REGLE_CODE_POSTAL_NOMBRE_ERREUR);
			}
		} else {
			exception.ajouterErreur(CodesErreursBLL.REGLE_CODE_POSTAL_NON_VIDE);
		}
	}
	
	private void validerMotDePasse(Utilisateur utilisateur, BusinessException exception) {
		String mdp = utilisateur.getMotDePasse();
		if (!mdp.equals("")) {
		} else {
			exception.ajouterErreur(CodesErreursBLL.REGLE_MOT_DE_PASSE_NON_VIDE);
		}
	}
	
	private void validerPseudo(Utilisateur utilisateur, BusinessException exception) {
		String pseudo = utilisateur.getPseudo();
		if (!pseudo.equals("")) {
			if (!pseudo.matches("^[a-zA-Z0-9]*$")) {
				exception.ajouterErreur(CodesErreursBLL.REGLE_PSEUDO_ALPHANUMERIQUE_ERREUR);
				return;
			}
			List<Utilisateur> utilisateurs = this.allUsers();
			for (Utilisateur user : utilisateurs) {
				if (pseudo.equals(user.getPseudo())) {
					exception.ajouterErreur(CodesErreursBLL.REGLE_PSEUDO_EN_DOUBLE_ERREUR);
				}
			}
		} else {
			exception.ajouterErreur(CodesErreursBLL.REGLE_PSEUDO_NON_VIDE);
		}
	}
}
