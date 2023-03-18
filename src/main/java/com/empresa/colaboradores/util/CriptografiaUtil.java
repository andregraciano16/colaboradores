package com.empresa.colaboradores.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.StringUtils;

public class CriptografiaUtil {

	public static String criptografar(String senha) {
		if (!StringUtils.hasText(senha)) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(senha.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				hexString.append(String.format("%02x", b & 0xff));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Erro ao criptografar senha", e);
		}
	}

}
