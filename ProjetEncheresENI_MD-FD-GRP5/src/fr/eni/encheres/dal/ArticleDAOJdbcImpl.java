package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements DAO<Article> {
	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_image, rue, code_postal, ville) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_ALL_ARTICLES = "SELECT (no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_image, rue, code_postal, ville) FROM ARTICLES_VENDUS";
	public static final String SELECT_ARTICLE_BY_ID = "SELECT (no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_image, rue, code_postal, ville) FROM ARTICLES_VENDUS WHERE no_article = ?";
	public static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_categorie = ?, url_image = ?, rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	public Article insert(Article article) {
		if (article != null) {
			try (Connection connection = ConnectionProvider.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICLE,
					PreparedStatement.RETURN_GENERATED_KEYS);) {
				// Je transmet les informations a la BDD de l'article ajouté. 
				preparedStatement.setString(1, article.getNomArticle());
				preparedStatement.setString(2, article.getDescription());
				preparedStatement.setDate(3, java.sql.Date.valueOf(article.getDateDebut()));
				preparedStatement.setDate(4, java.sql.Date.valueOf( article.getDateFin()));
				preparedStatement.setInt(5, article.getPrixInitial());
				preparedStatement.setInt(6, article.getPrixVente());
				preparedStatement.setInt(7, article.getUtilisateur().getIdUtilisateur());
				preparedStatement.setInt(8, article.getCategorie().getIdCategorie());
				preparedStatement.setString(9, article.getUrlImage());
				preparedStatement.setString(10, article.getRue());
				preparedStatement.setInt(11, article.getCodePostal());
				preparedStatement.setString(12, article.getVille());
				preparedStatement.execute();
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					article.setIdArticle(resultSet.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return article;
	}

	@Override
	public List<Article> selectAll() {
		List<Article> listArticle = new ArrayList<>();
		try (Connection connection = ConnectionProvider.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICLES);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			while (resultSet.next()) {
				// Je récupère les informations de la BDD a partir d'un ID. 
				int idArticle = resultSet.getInt(1);
				String nomArticle = resultSet.getString(2);
				String description = resultSet.getString(3);
				LocalDate dateDebut = resultSet.getDate(4).toLocalDate();
				LocalDate dateFin = resultSet.getDate(5).toLocalDate();
				int prixInitial = resultSet.getInt(6);
				int prixFinal = resultSet.getInt(7);
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt(8));
				Categorie categorie = new Categorie(); 
				categorie.setIdCategorie(resultSet.getInt(9));
				String urlImage = resultSet.getString(10);
				String rue = resultSet.getString(11);
				int codePostal = resultSet.getInt(12);
				String ville = resultSet.getString(13);

				Article article = new Article(idArticle, nomArticle, description, dateDebut, dateFin, prixInitial, prixFinal, utilisateur, categorie, urlImage, rue, codePostal, ville);
				// J'ajoute l'article dans une liste pour récupérer tout les articles.
				listArticle.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listArticle;
	}

	@Override
	public Article selectByID(int id) {
		Article article = null;
		try (Connection connection = ConnectionProvider.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_ID);)
		{
			// Je récupère mon article ID depuis la BDD 
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// Je récupère les informations a partir de la BDD En fonction de l'ID. 
				int idArticle = resultSet.getInt(13);
				String nomArticle = resultSet.getString(1);
				String description = resultSet.getString(2);
				LocalDate dateDebut = resultSet.getDate(3).toLocalDate();
				LocalDate dateFin = resultSet.getDate(4).toLocalDate();
				int prixInitial = resultSet.getInt(5);
				int prixFinal = resultSet.getInt(6);
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt(7));
				Categorie categorie = new Categorie(); 
				categorie.setIdCategorie(resultSet.getInt(8));
				String urlImage = resultSet.getString(9);
				String rue = resultSet.getString(10);
				int codePostal = resultSet.getInt(11);
				String ville = resultSet.getString(12);

				article = new Article(idArticle, nomArticle, description, dateDebut, dateFin, prixInitial, prixFinal, utilisateur, categorie, urlImage, rue, codePostal, ville);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	@Override
	public Article update(Article article){
		try (Connection connection = ConnectionProvider.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ARTICLE);)
		{
			// Je transmet les informations modifier a la BDD pour l'article modifier.
			preparedStatement.setInt(11, article.getIdArticle());
			preparedStatement.setString(1, article.getNomArticle());
			preparedStatement.setString(2, article.getDescription());
			preparedStatement.setDate(3, java.sql.Date.valueOf(article.getDateDebut()));
			preparedStatement.setDate(4, java.sql.Date.valueOf( article.getDateFin()));
			preparedStatement.setInt(5, article.getPrixInitial());
			preparedStatement.setInt(6, article.getCategorie().getIdCategorie());
			preparedStatement.setString(7, article.getUrlImage());
			preparedStatement.setString(8, article.getRue());
			preparedStatement.setInt(9, article.getCodePostal());
			preparedStatement.setString(10, article.getVille());
					
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Article delete(int id) {
		try (Connection connection = ConnectionProvider.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ARTICLE);)
		{
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}


}
