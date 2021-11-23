package fr.eni.encheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChiffrementPwd {

	public static String SHAcrypted(String password) {
		StringBuffer crypted = new StringBuffer();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			byte byteData[] = messageDigest.digest();
			for (int i = 0; i < byteData.length; i++) {
				crypted.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return crypted.toString();
	}
}
