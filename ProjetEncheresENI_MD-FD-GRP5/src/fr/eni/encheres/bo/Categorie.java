package fr.eni.encheres.bo;

public class Categorie {
	
	private int idCategorie;
	private String libelle;
	
	// -------------------------------------------------------------------------------
	// 							Constructeur vide
	// -------------------------------------------------------------------------------
	public Categorie() {
	}

	// -------------------------------------------------------------------------------
	//					 Constructeur sans Identifiant
	// -------------------------------------------------------------------------------
	public Categorie(String libelle) {
		this.libelle = libelle;
	}
	
	// -------------------------------------------------------------------------------
	//					 Constructeur avec Identifiant
	// -------------------------------------------------------------------------------
	public Categorie(int idCategorie, String libelle) {
		this.idCategorie = idCategorie;
		this.libelle = libelle;
	}

	// -------------------------------------------------------------------------------
	// 							Getter de cat�gorie
	// -------------------------------------------------------------------------------
	public String getLibelle() {
		return libelle;
	}

	public int getId_categorie() {
		return idCategorie;
	}
	
	// -------------------------------------------------------------------------------
	// 							Getter de cat�gorie
	// -------------------------------------------------------------------------------
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public void setId_categorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	// -------------------------------------------------------------------------------
	// 						Override de la m�tohde ToString.
	// -------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Categorie [id_categorie=" + idCategorie + ", libelle=" + libelle + "]";
	}
	
	// -------------------------------------------------------------------------------
	//						Override de la m�thode Equals. 
	// -------------------------------------------------------------------------------
	
}
