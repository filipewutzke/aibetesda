package br.com.aibetesda.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SimpleFormatterUtils {

	public static SimpleDateFormat getFormatterHoras()
	{
		return getFormatterHoras("HH:mm");
	}
	
	public static SimpleDateFormat getFormatterHoras(String pattern)
	{	
		return new SimpleDateFormat(pattern, new Locale("pt_BR"));
	}
	
	public static DecimalFormat formatterNumber = new DecimalFormat("#,##0.00");
}
