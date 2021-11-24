package fr.eni.encheres.dal;

import java.util.List;

public interface EnchereDAO<Enchere> {
	public Enchere insert(Enchere obj);

	public List<Enchere> selectAllByIDUser(int idUser);
	
	public List<Enchere> selectAllByIDArticle(int idArticle);
	
	public Enchere selectByIDArticle(int idArticle);
	
	public Enchere update(Enchere obj);


}
