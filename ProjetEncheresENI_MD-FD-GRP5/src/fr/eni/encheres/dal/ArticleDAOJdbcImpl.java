package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	public static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie, url_image, rue, code_postal, ville) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_image, rue, code_postal, ville FROM ARTICLES_VENDUS";
	public static final String SELECT_ALL_ARTICLES_BY_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_image, rue, code_postal, ville FROM ARTICLES_VENDUS WHERE no_categorie = ?";
	public static final String SELECT_ALL_ARTICLES_BY_NAME = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_image, rue, code_postal, ville FROM ARTICLES_VENDUS WHERE nom_article LIKE ?%";
	public static final String SELECT_ARTICLE_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, url_image, rue, code_postal, ville FROM ARTICLES_VENDUS WHERE no_article = ?";
	public static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_categorie = ?, url_image = ?, rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
	public static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	public Article insert(Article article) {
		if (article != null) {
			try (Connection connection = ConnectionProvider.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICLE,
							PreparedStatement.RETURN_GENERATED_KEYS);) {
				// Je transmet les informations a la BDD de l'article ajouté.
				preparedStatement.setString(1, article.getNomArticle());
				preparedStatement.setString(2, article.getDescription());
				preparedStatement.setDate(3, java.sql.Date.valueOf(article.getDateDebut()));
				preparedStatement.setDate(4, java.sql.Date.valueOf(article.getDateFin()));
				preparedStatement.setInt(5, article.getPrixInitial());
				preparedStatement.setInt(6, article.getUtilisateur().getIdUtilisateur());
				preparedStatement.setInt(7, article.getCategorie().getIdCategorie());
				preparedStatement.setString(8, article.getUrlImage());
				preparedStatement.setString(9, article.getRue());
				preparedStatement.setInt(10, article.getCodePostal());
				preparedStatement.setString(11, article.getVille());
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
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICLES);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			while (resultSet.next()) {
				
				// Je récupère les informations de la BDD a partir d'un ID.
				int idArticle = resultSet.getInt("no_article");
				String nomArticle = resultSet.getString("nom_article");
				String description = resultSet.getString("description");
				LocalDate dateDebut = resultSet.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFin = resultSet.getDate("date_fin_encheres").toLocalDate();
				int prixInitial = resultSet.getInt("prix_initial");
				int prixFinal = resultSet.getInt("prix_vente");
				Utilisateur utilisateur = new Utilisateur();
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				utilisateur = utilisateurManager.getUserById(resultSet.getInt("no_utilisateur"));
				Categorie categorie = new Categorie();
				CategorieManager categorieManager = new CategorieManager();
				categorie = categorieManager.selectById(resultSet.getInt("no_categorie"));
				String urlImage = resultSet.getString("url_image");
				String rue = resultSet.getString("rue");
				int codePostal = resultSet.getInt("code_postal");
				String ville = resultSet.getString("code_postal");
				
				Article article = new Article(idArticle, nomArticle, description, dateDebut, dateFin, prixInitial,
						prixFinal, utilisateur, categorie, urlImage, rue, codePostal, ville);
				// J'ajoute l'article dans une liste pour récupérer tout les articles.
				listArticle.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listArticle;
	}
	
	@Override
	public List<Article> selectAllByCategorie(int idCategorie) {
		List<Article> listArticle = new ArrayList<>();
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICLES_BY_CATEGORIE);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			while (resultSet.next()) {
				
				// Je récupère les informations de la BDD a partir d'un ID.
				int idArticle = resultSet.getInt("no_article");
				String nomArticle = resultSet.getString("nom_article");
				String description = resultSet.getString("description");
				LocalDate dateDebut = resultSet.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFin = resultSet.getDate("date_fin_encheres").toLocalDate();
				int prixInitial = resultSet.getInt("prix_initial");
				int prixFinal = resultSet.getInt("prix_vente");
				Utilisateur utilisateur = new Utilisateur();
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				utilisateur = utilisateurManager.getUserById(resultSet.getInt("no_utilisateur"));
				Categorie categorie = new Categorie();
				CategorieManager categorieManager = new CategorieManager();
				categorie = categorieManager.selectById(idCategorie);
				String urlImage = resultSet.getString("url_image");
				String rue = resultSet.getString("rue");
				int codePostal = resultSet.getInt("code_postal");
				String ville = resultSet.getString("code_postal");
				
				// je créer un article qui contient les informations de la BDD
				Article article = new Article(idArticle, nomArticle, description, dateDebut, dateFin, prixInitial,
						prixFinal, utilisateur, categorie, urlImage, rue, codePostal, ville);
				// J'ajoute l'article dans une liste pour récupérer tout les articles de la catégorie.
				listArticle.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listArticle;
	}
	
	@Override
	public List<Article> selectAllByName(String nomArticle) {
		List<Article> listArticle = new ArrayList<>();
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICLES_BY_NAME);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			while (resultSet.next()) {
				
				// Je récupère les informations de la BDD a partir d'un ID.
				int idArticle = resultSet.getInt("no_article");
				String description = resultSet.getString("description");
				LocalDate dateDebut = resultSet.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFin = resultSet.getDate("date_fin_encheres").toLocalDate();
				int prixInitial = resultSet.getInt("prix_initial");
				int prixFinal = resultSet.getInt("prix_vente");
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt("no_utilisateur"));
				Categorie categorie = new Categorie();
				categorie.setIdCategorie(resultSet.getInt("no_categorie"));
				String urlImage = resultSet.getString("url_image");
				String rue = resultSet.getString("rue");
				int codePostal = resultSet.getInt("code_postal");
				String ville = resultSet.getString("code_postal");
				
				Article article = new Article(idArticle, nomArticle, description, dateDebut, dateFin, prixInitial,
						prixFinal, utilisateur, categorie, urlImage, rue, codePostal, ville);
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
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_ID);) {
			
			// Je récupère mon article ID depuis la BDD
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// Je récupère les informations a partir de la BDD En fonction de l'ID.
				String nomArticle = resultSet.getString("nom_article");
				String description = resultSet.getString("description");
				LocalDate dateDebut = resultSet.getDate("date_debut_encheres").toLocalDate();
				LocalDate dateFin = resultSet.getDate("date_fin_encheres").toLocalDate();
				int prixInitial = resultSet.getInt("prix_initial");
				int prixFinal = resultSet.getInt("prix_vente");
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt("no_utilisateur"));
				Categorie categorie = new Categorie();
				categorie.setIdCategorie(resultSet.getInt("no_categorie"));
				String urlImage = resultSet.getString("url_image");
				String rue = resultSet.getString("rue");
				int codePostal = resultSet.getInt("code_postal");
				String ville = resultSet.getString("ville");
				
				article = new Article(id, nomArticle, description, dateDebut, dateFin, prixInitial, prixFinal,
						utilisateur, categorie, urlImage, rue, codePostal, ville);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}
	
	@Override
	public Article updateArticle(Article article) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ARTICLE);) {
			// Je transmet les informations modifier a la BDD pour l'article modifier.
			preparedStatement.setInt(11, article.getIdArticle());
			preparedStatement.setString(1, article.getNomArticle());
			preparedStatement.setString(2, article.getDescription());
			preparedStatement.setDate(3, java.sql.Date.valueOf(article.getDateDebut()));
			preparedStatement.setDate(4, java.sql.Date.valueOf(article.getDateFin()));
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
		return article;
	}
	
	@Override
	public Article deleteArticle(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ARTICLE);) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
