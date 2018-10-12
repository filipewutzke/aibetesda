package br.com.aibetesda.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DiretorioPostgresUtil {

	private static final String DEFAULT_PATH = 
			"reg query HKLM\\Software\\PostgreSQL\\Installations\\postgresql-8.4";
	
	private static String getDiretorioWindows() throws IOException, InterruptedException
	{
		Runtime run = Runtime.getRuntime();
		Process regQuery = run.exec(DEFAULT_PATH);
		regQuery.waitFor();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(regQuery.getInputStream()));
		
		String data;
		
		//Impede que o while continue mesmo após o caminho ja ter sido lido
		boolean lido = false;
		StringBuffer inputStream = new StringBuffer();

		while ((data = reader.readLine()) != null) {
			if(data.contains("Base")){
				System.out.println(data);
				String dir = data.substring(data.indexOf("C:\\"), data.length());
				inputStream.append("\""+dir+ "\\bin\\pg_dump.exe\"");
				lido = true;
				break;
			}
			if(lido)
				break;
		}
		return inputStream.toString();
	}
	
	private static String getDiretorioLinux()
	{
		return "pg_dump";
	}
	
	public static String getDiretorioPgDump() throws Exception
	{
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("OS NAME: "+os);
		if(os.indexOf("windows") > -1) {
			return getDiretorioWindows();
		} else if(os.indexOf("linux") > -1){
			return getDiretorioLinux();
		}
		throw new Exception("OS não suportado!");
	}
}

