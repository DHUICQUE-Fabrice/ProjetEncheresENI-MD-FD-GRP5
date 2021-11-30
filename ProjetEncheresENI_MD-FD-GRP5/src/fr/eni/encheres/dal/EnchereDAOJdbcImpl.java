package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	public static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?)";
	public static final String SELECT_ALL_ENCHERE_BY_ID_USER = "SELECT no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_utilisateur = ?";
	public static final String SELECT_ALL_ENCHERE_BY_ID_ARTICLE = "SELECT no_utilisateur, date_enchere, montant_enchere FROM ENCHERES WHERE no_article = ?";
	public static final String SELECT_ENCHERE_BY_ID_ARTICLE = "SELECT no_utilisateur, date_enchere, montant_enchere FROM ENCHERES WHERE no_article = ?";
	public static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_enchere = ?, montant_enchere = ? WHERE no_article = ? AND no_Utilisateur = ?";
	public static final String DELETE_ENCHERES_BY_USER_ID = "DELETE FROM ENCHERES WHERE no_utilisateur=?";
	public static final String DELETE_ENCHERES_BY_ARTICLE_ID = "DELETE FROM ENCHERES WHERE no_article=?";
	public static final String DELETE_ENCHERE = "DELETE FROM ENCHERES WHERE no_utilisateur=? AND no_article=? AND date_enchere=? AND montant_enchere=?";
	
	@Override
	public Enchere insert(Enchere enchere) {
		if (enchere != null) {
			try (Connection connection = ConnectionProvider.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENCHERE);) {
				// Je transmet les informations a la BDD de l'article ajouté.
				preparedStatement.setInt(1, enchere.getUtilisateur().getIdUtilisateur());
				preparedStatement.setInt(2, enchere.getArticle().getIdArticle());
				preparedStatement.setDate(3, java.sql.Date.valueOf(enchere.getDateEnchere()));
				preparedStatement.setInt(4, enchere.getMontantenchere());
				
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return enchere;
	}
	
	@Override
	public Enchere update(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENCHERE);) {
			// Je transmet les informations a la BDD de l'article ajouté.
			preparedStatement.setInt(1, enchere.getUtilisateur().getIdUtilisateur());
			preparedStatement.setInt(2, enchere.getArticle().getIdArticle());
			preparedStatement.setDate(3, java.sql.Date.valueOf(enchere.getDateEnchere()));
			preparedStatement.setInt(4, enchere.getMontantenchere());
			
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Enchere> selectAllByIDUser(int idUser) {
		List<Enchere> enchereUser = new ArrayList<>();
		Enchere enchere = null;
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ENCHERE_BY_ID_USER);) {
			
			// Je récupère mon Utilisateur ID depuis la BDD
			preparedStatement.setInt(1, idUser);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// Je récupère les informations a partir de la BDD En fonction de l'ID.
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt("no_utilisateur"));
				Article article = new Article();
				article.setIdArticle(resultSet.getInt("no_article"));
				LocalDate date_enchere = resultSet.getDate("date-enchere").toLocalDate();
				int montant_enchere = resultSet.getInt("montant_enchere");
				
				enchere = new Enchere(utilisateur, article, date_enchere, montant_enchere);
				enchereUser.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchereUser;
	}
	
	@Override
	public List<Enchere> selectAllByIDArticle(int idArticle) {
		List<Enchere> enchereArticle = new ArrayList<>();
		Enchere enchere = null;
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ENCHERE_BY_ID_ARTICLE);) {
			
			// Je récupère mon Utilisateur ID depuis la BDD
			preparedStatement.setInt(1, idArticle);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// Je récupère les informations a partir de la BDD En fonction de l'ID.
				Article article = new Article();
				article.setIdArticle(idArticle);
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt("no_utilisateur"));
				LocalDate dateEnchere = resultSet.getDate("date_enchere").toLocalDate();
				int montantEnchere = resultSet.getInt("montant_enchere");
				
				enchere = new Enchere(utilisateur, article, dateEnchere, montantEnchere);
				enchereArticle.add(enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchereArticle;
	}
	
	@Override
	public Enchere selectByIDArticle(int idArticle) {
		Enchere enchere = null;
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ENCHERE_BY_ID_ARTICLE);) {
			
			// Je récupère mon Utilisateur ID depuis la BDD
			preparedStatement.setInt(1, idArticle);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// Je récupère les informations a partir de la BDD En fonction de l'ID.
				Article article = new Article();
				article.setIdArticle(resultSet.getInt("no_article"));
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt("no_utilisateur"));
				LocalDate dateEnchere = resultSet.getDate("date_enchere").toLocalDate();
				int montantEnchere = resultSet.getInt("montant_enchere");
				
				enchere = new Enchere(utilisateur, article, dateEnchere, montantEnchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchere;
	}
	
	@Override
	public List<Enchere> deleteEncheresByUserId(int userId) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ENCHERES_BY_USER_ID);) {
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<Enchere> deleteEncheresByArticleId(int articleId) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ENCHERES_BY_ARTICLE_ID);) {
			preparedStatement.setInt(1, articleId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Enchere delete(Enchere enchere) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ENCHERE)) {
			preparedStatement.setInt(1, enchere.getUtilisateur().getIdUtilisateur());
			preparedStatement.setInt(2, enchere.getArticle().getIdArticle());
			preparedStatement.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			preparedStatement.setInt(4, enchere.getMontantenchere());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enchere;
	}
}
