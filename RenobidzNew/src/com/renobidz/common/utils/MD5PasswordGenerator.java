package com.renobidz.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5PasswordGenerator {
	private static final Logger LOGGER = Logger.getLogger(MD5PasswordGenerator.class.getName());
	
	public static String getEncodedPassword(String plainTextPassword) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			// Add password bytes to digest
			md.update(plainTextPassword.getBytes());
			
			// Get the hash's bytes
			byte[] bytes = md.digest();
			
			// This bytes[] has bytes in decimal format; Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return generatedPassword;
	}
}
