package fr.eni.encheres.bo;


import java.util.Date;

public class Article {

	private int id_article;
	private String nom_article;
	private String description;
	private Date date_debut;
	private Date date_fin;
	private int prix_initial;
	private int prix_vente;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private String url_image;
	private String rue;
	private int code_postal;
	private String ville;
	
	
	public Article() {
	}

	// Constructeur sans Identifiant
	public Article(String nom_article, String description, Date date_debut, Date date_fin, int prix_initial,
			int prix_vente, Utilisateur utilisateur, Categorie categorie, String url_image, String rue, int code_postal,
			String ville) {
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.url_image = url_image;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	// Constructeur avec un Identidiant
	public Article(int id_article, String nom_article, String description, Date date_debut, Date date_fin,
			int prix_initial, int prix_vente, Utilisateur utilisateur, Categorie categorie, String url_image,
			String rue, int code_postal, String ville) {
		this.id_article = id_article;
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.url_image = url_image;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	// Getter de l'article
	public int getId_article() {
		return id_article;
	}

	public String getNom_article() {
		return nom_article;
	}

	public String getDescription() {
		return description;
	}
	
	public Date getDate_debut() {
		return date_debut;
	}
	
	public Date getDate_fin() {
		return date_fin;
	}

	public int getPrix_initial() {
		return prix_initial;
	}
	
	public int getPrix_vente() {
		return prix_vente;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public String getUrl_image() {
		return url_image;
	}

	public String getRue() {
		return rue;
	}
	
	public int getCode_postal() {
		return code_postal;
	}
	
	public String getVille() {
		return ville;
	}
	
	
	// Setter de l'article 
	public void setId_article(int id_article) {
		this.id_article = id_article;
	}
	
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public void setPrix_initial(int prix_initial) {
		this.prix_initial = prix_initial;
	}

	public void setPrix_vente(int prix_vente) {
		this.prix_vente = prix_vente;
	}
	
	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setCode_postal(int code_postal) {
		this.code_postal = code_postal;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	// Override de la métohde ToString.
	@Override
	public String toString() {
		return "Article [nom_article=" + nom_article + ", description=" + description + ", date_debut=" + date_debut
				+ ", date_fin=" + date_fin + ", prix_initial=" + prix_initial + ", prix_vente=" + prix_vente
				+ ", utilisateur=" + utilisateur + ", categorie=" + categorie + ", rue=" + rue + ", code_postal="
				+ code_postal + ", ville=" + ville + "]";
	}

	// Override de la méthode Equals. 
	// parce qu'il faut bien que je push bordel
	
}
