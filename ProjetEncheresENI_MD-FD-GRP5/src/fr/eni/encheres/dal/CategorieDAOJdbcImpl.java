package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	public static final String INSERT_CATEGORIE = "INSERT INTO CATEGORIES (libelle) VALUES (?)";
	public static final String SELECT_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM CATEGORIES";
	public static final String SELECT_CATEGORIE_BY_ID = "SELECT libelle FROM CATEGORIES WHERE no_categorie = ?";
	public static final String UPDATE_CATEGORIE = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
	public static final String DELETE_CATEGORIE = "DELETE FROM CATEGORIES WHERE no_categorie = ?";
	
	@Override
	public Categorie insert(Categorie categorie) {
		if (categorie != null) {
			try (Connection connection = ConnectionProvider.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORIE,
							PreparedStatement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, categorie.getLibelle());
				preparedStatement.execute();
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					categorie.setIdCategorie(resultSet.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return categorie;
	}
	
	@Override
	public List<Categorie> selectAll() {
		List<Categorie> categories = new ArrayList<Categorie>();
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idCategorie = resultSet.getInt("no_categorie");
				String libelle = resultSet.getString("libelle");
				categories.add(new Categorie(idCategorie, libelle));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	@Override
	public Categorie selectByID(int id) {
		Categorie categorie = new Categorie();
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIE_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				categorie.setIdCategorie(id);
				categorie.setLibelle(resultSet.getString("libelle"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}
	
	@Override
	public Categorie update(Categorie categorie) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORIE)) {
			preparedStatement.setString(1, categorie.getLibelle());
			preparedStatement.setInt(2, categorie.getIdCategorie());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}
	
	@Override
	public Categorie delete(int id) {
		try (Connection connection = ConnectionProvider.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORIE)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
