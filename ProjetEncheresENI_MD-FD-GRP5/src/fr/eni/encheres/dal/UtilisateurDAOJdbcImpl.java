package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements DAO<Utilisateur> {
	public static final String INSERT_USER = "INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	public void insert(Utilisateur utilisateur) {
		if (utilisateur != null) {
			try (Connection connection = ConnectionProvider.getConnection()) {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER,
						PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, utilisateur.getPseudo());
				preparedStatement.setString(2, utilisateur.getNom());
				preparedStatement.setString(3, utilisateur.getPrenom());
				preparedStatement.setString(4, utilisateur.getEmail());
				preparedStatement.setString(5, utilisateur.getTelephone());
				preparedStatement.setString(6, utilisateur.getRue());
				preparedStatement.setString(7, utilisateur.getCodePostal());
				preparedStatement.setString(8, utilisateur.getVille());
				preparedStatement.setString(9, utilisateur.getMotDePasse());
				preparedStatement.setInt(10, utilisateur.getCredit());
				preparedStatement.setBoolean(11, utilisateur.isAdministrateur());
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
