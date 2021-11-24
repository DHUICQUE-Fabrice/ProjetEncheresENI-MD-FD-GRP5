package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {
	public Categorie insert(Categorie categorie);

	public List<Categorie> selectAll();

	public Categorie selectByID(int id);

	public Categorie update(Categorie categorie);

	public Categorie delete(int id);

}
