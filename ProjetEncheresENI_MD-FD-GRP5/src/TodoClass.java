
public class TodoClass {
	// TODO importer liste encheres en cours dans page accueil mode déconnecté
	// TODO importer liste encheres en cours dans page accueil mode connecté
	// TODO Lister ses ventes
	// TODO Annuler une vente
	// TODO Pages d'erreur custom : 404, 500
	// TODO Session timeout 5mn : utiliser un listener (https://mail.codejava.net/java-ee/servlet/how-to-set-session-timeout-for-java-web-application)
	
	/*
	 * 
	 * Liste des pages devant exister :
	 * 
	 *  - Page d'accueil (créée, non complète):
	 *  	******DECONNECTE******								******CONNECTE******
	 * 		- S'inscrire / Se connecter (ok)					- Se déconnecter (ok)
	 * 		- Affichage des enchères en cours					- Possibilité d'intéragir avec les enchères en cours
	 * 		- Filtrer par catégorie								- Vendre un article (ok)
	 * 		- Filtrer par mot clé (recherche)					- Mon profil
	 * 		- Gestion des erreurs								- Filtrer achats/ventes (radio) avec sous filtres (en cours, terminé...) en checkBox
	 * 		
	 * 
	 * 	- Page de connexion (créée, non complète)
	 * 		- Rentrer son login et son mot de passe (ok)
	 * 		- "Se souvenir de moi"
	 * 		- Mot de passe oublié
	 * 
	 * 	- Page d'inscription (créée, complète)
	 * 		- Rentrer ses informations (ok)
	 * 		- Gestion des erreurs (ok, reste pseudo alphanumérique à vérifier)
	 * 
	 * 	- Page article (créée, non complète)
	 * 		- Rentrer les informations de l'article (ok)
	 * 		- Upload image
	 * 
	 * 	- Page Mon profil en mode consultation
	 * 		- Affichage des infos du profil en mode consultation
	 * 		- Bouton modifier pour passer en mode modifictaions
	 * 
	 * 	- Page Mon profil en mode édition (non créée)
	 * 		- Affichage des champs en mode éditable
	 * 		- Boutons enregistrer, supprimer compte et retour à l'accueil
	 * 
	 * 	- Page détail articles
	 * 		- Affichage image article
	 * 		- Affichage détails
	 * 		- Pseudo du vendeur
	 * 		- Possibilité d'enchérir (input type number avec valeur par défaut = meilleure offre + 10?)
	 * 		- Bouton retour
	 * 
	 * 	- Page fin de l'enchère
	 * 		- Dispo quand date_fin_encheres dépassée
	 * 		- "Vous avez gagné" ou "Jeff Bezos a gagné" selon les cas
	 * 		- Affichage mise à prix, prix final, vendeur, tel du vendeur, adresse du vendeur
	 * 		- Confirmer retrait effectué
	 * 
	 * 	- Page articles en vente
	 * 		- Possibilité annuler vente
	 * 		- Affichage meilleure offre et infos de l'article
	 * 
	 * 
	 * 	- Page article vendu
	 * 		- Dispo quand date_fin_encheres dépassée
	 * 		- Nom de l'acheteur
	 * 		- Affichage mise à prix, prix final, infos vente
	 * 		- Bouton "contacter acheteur"
	 * 		- Bouton confirmation retrait
	 * 
	 * 
	 * 
	 */
}
