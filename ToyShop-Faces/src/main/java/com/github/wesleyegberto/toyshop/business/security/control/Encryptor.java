package com.github.wesleyegberto.toyshop.business.security.control;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

	public Encryptor() {
	}

	public static String encrypt(String text) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			String finalText = text + "_\\?!|3!/%@";
			byte[] enc = digest.digest(finalText.getBytes());
			StringBuilder encrypted = new StringBuilder();
			for(int i = 0; i < enc.length; i++) {
				// thanks to https://github.com/fitorec/java-hashes
				encrypted.append(Integer.toHexString((enc[i] & 0xFF) | 0x200).substring(1,3));
			}
			return encrypted.toString();
		} catch(NoSuchAlgorithmException e) {
		}
		return null;
	}
	
}
