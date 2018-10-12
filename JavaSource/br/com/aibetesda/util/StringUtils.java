package br.com.aibetesda.util;

import java.text.Normalizer;

public class StringUtils {
	
	public static String formatString(String s) {
		String temp = Normalizer.normalize(s, java.text.Normalizer.Form.NFD);
		return temp.replaceAll("[^\\p{ASCII}]","");
	} 
}
