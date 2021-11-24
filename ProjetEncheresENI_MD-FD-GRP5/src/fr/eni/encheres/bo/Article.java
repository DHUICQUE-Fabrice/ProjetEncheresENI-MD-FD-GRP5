package fr.eni.encheres.bo;


import java.time.LocalDate;


public class Article {

	private int idArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private int prixInitial;
	private int prixVente;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private String urlImage;
	private String rue;
	private int codePostal;
	private String ville;
	
	// -------------------------------------------------------------------------------
	// 									Constructeur vide
	// -------------------------------------------------------------------------------
	public Article() {
	}

	// -------------------------------------------------------------------------------
	//							 Constructeur sans Identifiant
	// -------------------------------------------------------------------------------
	public Article(String nomArticle, String description, LocalDate dateDebut, LocalDate dateFin, int prixInitial, int prixVente,
			Utilisateur utilisateur, Categorie categorie, String urlImage, String rue, int codePostal, String ville) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.urlImage = urlImage;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	// -------------------------------------------------------------------------------
	//							 Constructeur avec un Identidiant
	// -------------------------------------------------------------------------------
	public Article(int idArticle, String nomArticle, String description, LocalDate dateDebut, LocalDate dateFin, int prixInitial,
			int prixVente, Utilisateur utilisateur, Categorie categorie, String urlImage, String rue, int codePostal,
			String ville) {
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.urlImage = urlImage;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	// -------------------------------------------------------------------------------
	// 								Getter de l'article
	// -------------------------------------------------------------------------------
	public int getIdArticle() {
		return idArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public String getRue() {
		return rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public String getVille() {
		return ville;
	}
	
	// -------------------------------------------------------------------------------
	// 								Setter de l'article 
	// -------------------------------------------------------------------------------
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	// -------------------------------------------------------------------------------
	// 						Override de la métohde ToString.
	// -------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Article [nomArticle=" + nomArticle + ", description=" + description + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", prixInitial=" + prixInitial + ", prixVente=" + prixVente
				+ ", utilisateur=" + utilisateur + ", categorie=" + categorie + ", urlImage=" + urlImage + ", rue="
				+ rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
	
	// -------------------------------------------------------------------------------
	//						Override de la méthode Equals. 
	// -------------------------------------------------------------------------------
}
