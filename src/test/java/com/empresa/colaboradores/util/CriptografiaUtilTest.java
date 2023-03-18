package com.empresa.colaboradores.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CriptografiaUtilTest {
	
	@Test
	public void criptografarSenha123456() {
		String senha123456criptografada = CriptografiaUtil.criptografar("123456");
		assertEquals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92", senha123456criptografada);
	}
	
	@Test
	public void criptografarSenhaNula() {
		String senha123456criptografada = CriptografiaUtil.criptografar(null);
		assertEquals(null, senha123456criptografada);
	}
	
	@Test
	public void criptografarSenhaA45andre45() {
		String senhaA45andre45criptografada = CriptografiaUtil.criptografar("A45andre45");
		assertEquals("145f51ecb6332671b9d1f04b380e2436d07ee89db1b6e2cc265fc70eb5366fba", senhaA45andre45criptografada);
	}
	
	@Test
	public void criptografarSenhaVazia() {
		String senhaCriptografadaVazia = CriptografiaUtil.criptografar("  ");
		assertEquals(null, senhaCriptografadaVazia);
	}

}
