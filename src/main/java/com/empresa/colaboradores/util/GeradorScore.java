package com.empresa.colaboradores.util;

import java.util.HashMap;
import java.util.Map;

public class GeradorScore {

    public static int gerar(String password) {
        int score = 0;

        //Número de caracteres
        score += password.length() * 4;
        
        //Letras Maiusculas
        int countMinusculas = password.replaceAll("[^A-Z]", "").length();
        if (countMinusculas > 0) {
        	score += (password.length() - countMinusculas) * 2;        	
        }

        //Letras Minusculas
        int countMaiuscula = password.replaceAll("[^a-z]", "").length();
        if (countMaiuscula > 0) {
        	score += (password.length() - countMaiuscula) * 2;
        }
        
        //Verifica se tem numeros
        int countNumeros = password.replaceAll("\\D", "").length(); 
        if (password.length() != countNumeros) {
        	score += countNumeros * 4;
        }
        //Simbolos
        score += (password.replaceAll("[a-zA-Z0-9]", "").length() * 6);
        
        //Números e simbolos do meio
        String substring = password.substring(1, password.length() - 1);
        score += substring.replaceAll("[a-zA-Z]", "").length() * 2;

        //Requisitos
        if (password.length() >= 8) {
        	int countRegra = password.replaceAll("[^a-z]", "").length() + 
        			password.replaceAll("[^A-Z]", "").length() + 
        			password.replaceAll("\\D", "").length() +
        			password.replaceAll("[a-zA-Z0-9 ]", "").length();
        	if (countRegra > 2) {
        		score += countRegra * 2;
        	}
        			
        }
        
        //Regras negativas
        // Somente letras 
        if ((countMaiuscula + countMinusculas) == password.length()) {
        	score -= password.length();
        }
        
        //Somente numeros
        if (password.length() == countNumeros) {
        	score -= password.length();
        }

        //Caracteres reptidos
        Map<Character, Integer> ocorrencias = new HashMap<>();

        for (char c : password.toCharArray()) {
            if (ocorrencias.containsKey(c)) {
                ocorrencias.put(c, ocorrencias.get(c) + 1);
            } else {
                ocorrencias.put(c, 1);
            }
        }
        int countRepetidos = 0;
        for (int ocorrencia : ocorrencias.values()) {
            countRepetidos += ocorrencia > 1 ? ocorrencia : 0;
        }
        
        //TODO: Pendente gerar o restante da regra de score
        
        return score;
    }
}
