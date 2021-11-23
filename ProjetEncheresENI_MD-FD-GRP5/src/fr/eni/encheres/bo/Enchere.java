package fr.eni.encheres.bo;

import java.util.Date;

public class Enchere {
	private Utilisateur utilisateur;
	private Article article;
	private Date dateEnchere;
	private int montantenchere;

	public Enchere() {

	}

	public Enchere(Utilisateur utilisateur, Article article, Date dateEnchere, int montantenchere) {
		this.utilisateur = utilisateur;
		this.article = article;
		this.dateEnchere = dateEnchere;
		this.montantenchere = montantenchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantenchere() {
		return montantenchere;
	}

	public void setMontantenchere(int montantenchere) {
		this.montantenchere = montantenchere;
	}

	@Override
	public String toString() {
		return "Enchere [utilisateur=" + utilisateur + ", article=" + article + ", dateEnchere=" + dateEnchere
				+ ", montantenchere=" + montantenchere + "]";
	}

}
