package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	public Enchere insert(Enchere obj);
	
	public List<Enchere> selectAllByIDUser(int idUser);
	
	public List<Enchere> selectAllByIDArticle(int idArticle);
	
	public Enchere selectByIDArticle(int idArticle);
	
	public Enchere update(Enchere obj);
	
	public Enchere delete(Enchere enchere);
	
	public List<Enchere> deleteEncheresByUserId(int userId);
	
	public List<Enchere> deleteEncheresByArticleId(int articleId);
	
}
