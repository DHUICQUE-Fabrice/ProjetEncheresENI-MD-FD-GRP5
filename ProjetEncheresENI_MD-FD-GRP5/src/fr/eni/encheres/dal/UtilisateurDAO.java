package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public Utilisateur insert(Utilisateur utilisateur);

	public List<Utilisateur> selectAll();

	public Utilisateur selectByID(int id);

	public Utilisateur update(Utilisateur utilisateur);

	public Utilisateur delete(int id);

}
