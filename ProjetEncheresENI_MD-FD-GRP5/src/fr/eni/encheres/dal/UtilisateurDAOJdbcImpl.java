package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements DAO<Utilisateur> {
	public static final String INSERT_USER = "INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SELECT_ALL_USERS = "SELECT * FROM UTILISATEURS";

	public Utilisateur insert(Utilisateur utilisateur) {
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
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					utilisateur.setIdUtilisateur(resultSet.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int noUtilisateur = resultSet.getInt(1);
				String pseudo = resultSet.getString(2);
				String nom = resultSet.getString(3);
				String prenom = resultSet.getString(4);
				String email = resultSet.getString(5);
				String telephone = resultSet.getString(6);
				String rue = resultSet.getString(7);
				String codePostal = resultSet.getString(8);
				String ville = resultSet.getString(9);
				String motDePasse = resultSet.getString(10);
				int credit = resultSet.getInt(11);
				boolean administrateur = (resultSet.getInt(12) != 0);
				Utilisateur utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue,
						codePostal, ville, motDePasse, credit, administrateur);
				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateurs;
	}

	@Override
	public Utilisateur selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur update(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
