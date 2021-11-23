package fr.eni.encheres.bo;

public class Categorie {
	
	private int id_categorie;
	private String libelle;
	
	public Categorie() {
		// TODO Auto-generated constructor stub
	}

	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Categorie(int id_categorie, String libelle) {
		super();
		this.id_categorie = id_categorie;
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getId_categorie() {
		return id_categorie;
	}
	
	@Override
	public String toString() {
		return "Categorie [id_categorie=" + id_categorie + ", libelle=" + libelle + "]";
	}

	
}
