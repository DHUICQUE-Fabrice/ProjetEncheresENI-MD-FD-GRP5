package fr.eni.encheres.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<Integer> listeCodesErreurs;

	public BusinessException() {
		super();
		this.listeCodesErreurs = new ArrayList<Integer>();
	}

	public void ajouterErreur(int code) {
		if (!this.listeCodesErreurs.contains(code)) {
			listeCodesErreurs.add(code);
		}
	}

	public boolean hasErreurs() {
		return (this.listeCodesErreurs.size() > 0);
	}

	public List<Integer> getListeCodesErreurs() {
		return this.listeCodesErreurs;
	}

}
