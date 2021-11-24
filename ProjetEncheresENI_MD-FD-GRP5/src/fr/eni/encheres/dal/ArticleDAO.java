package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	public Article insert(Article article);

	public List<Article> selectAll();

	public Article selectByID(int id);

	public Article update(Article article);

	public Article delete(int id);

}
