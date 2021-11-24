package fr.eni.encheres.messages;

import java.util.ResourceBundle;

public class LecteurMessages {
	private static ResourceBundle resourceBundle;

	static {
		try {
			resourceBundle = ResourceBundle.getBundle("fr.eni.encheres.messages.messages_erreur");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMessageErreur(int code) {
		String message = "";
		try {
			if (resourceBundle != null) {
				message = resourceBundle.getString(String.valueOf(code));
			} else {
				message = "Erreur à la lecture des erreurs (inception error)";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Une erreur inconnue est survenue";
		}
		return message;
	}
}
