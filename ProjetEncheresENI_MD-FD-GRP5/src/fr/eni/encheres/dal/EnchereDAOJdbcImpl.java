package fr.eni.encheres.dal;

import java.sql.Connection;
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
				utilisateur.setIdUtilisateur(resultSet.getInt(4));
				Article article = new Article();
				article.setIdArticle(resultSet.getInt(1));
				LocalDate date_enchere = resultSet.getDate(2).toLocalDate();
				int montant_enchere = resultSet.getInt(3);

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
				article.setIdArticle(resultSet.getInt(4));
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt(1));
				LocalDate date_enchere = resultSet.getDate(2).toLocalDate();
				int montant_enchere = resultSet.getInt(3);

				enchere = new Enchere(utilisateur, article, date_enchere, montant_enchere);
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
				article.setIdArticle(resultSet.getInt(4));
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(resultSet.getInt(1));
				LocalDate date_enchere = resultSet.getDate(2).toLocalDate();
				int montant_enchere = resultSet.getInt(3);

				enchere = new Enchere(utilisateur, article, date_enchere, montant_enchere);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchere;
	}
}
