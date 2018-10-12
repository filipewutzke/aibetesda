package br.com.aibetesda.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
	
	public static final List<String> DIAS_SEMANA = new ArrayList<String>();
	public static final List<String> MESES = new ArrayList<String>();
	public static final List<String> TURNOS = new ArrayList<String>();
	
	static{
		DIAS_SEMANA.add("Domingo");
		DIAS_SEMANA.add("Segunda-feira");
		DIAS_SEMANA.add("Ter�a-feira");
		DIAS_SEMANA.add("Quarta-feira");
		DIAS_SEMANA.add("Quinta-feira");
		DIAS_SEMANA.add("Sexta-feira");
		DIAS_SEMANA.add("S�bado");
		
		MESES.add("Janeiro");
		MESES.add("Fevereiro");
		MESES.add("Mar�o");
		MESES.add("Abril");
		MESES.add("Maio");
		MESES.add("Junho");
		MESES.add("Julho");
		MESES.add("Agosto");
		MESES.add("Setembro");
		MESES.add("Outubro");
		MESES.add("Novembro");
		MESES.add("Dezembro");
		
		TURNOS.add("Manh�");
		TURNOS.add("Tarde");
		TURNOS.add("Noite");
		TURNOS.add("S�bado manh�");
		TURNOS.add("S�bado tarde");
	}
}
