package fr.eni.encheres.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<Integer> listeCodeErreur;

	public BusinessException() {
		super();
		this.listeCodeErreur = new ArrayList<Integer>();
	}

	public void ajouterErreur(int code) {
		if (!this.listeCodeErreur.contains(code)) {
			listeCodeErreur.add(code);
		}
	}

	public boolean hasErreurs() {
		return (this.listeCodeErreur.size() > 0);
	}

	public List<Integer> getListeCodeErreur() {
		return this.listeCodeErreur;
	}

}
