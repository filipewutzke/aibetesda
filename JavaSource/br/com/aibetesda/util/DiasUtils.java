package br.com.aibetesda.util;

import java.util.HashMap;
import java.util.Map;

public class DiasUtils {
	
	public static Map<String, Integer> dias = new HashMap<String, Integer>();
	
	static{
		int i = 1;
		for(String d : ListUtils.DIAS_SEMANA)
		{
			dias.put(d, i);
			i++;
		}
	}
	
	public static int getDayOfWeek(String dia)
	{
		return dias.get(dia);
	}
}
