package fr.eni.encheres.dal;

import java.util.List;

public interface EnchereDAO<T> {
	public T insert(T obj);

	public List<T> selectAllByIDUser(int idUser);
	
	public List<T> selectAllByIDArticle(int idArticle);
	
	public T selectByIDArticle(int idArticle);
	
	public T update(T obj);


}
