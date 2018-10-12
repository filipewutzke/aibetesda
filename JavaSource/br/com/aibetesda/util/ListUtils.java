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
		DIAS_SEMANA.add("Terça-feira");
		DIAS_SEMANA.add("Quarta-feira");
		DIAS_SEMANA.add("Quinta-feira");
		DIAS_SEMANA.add("Sexta-feira");
		DIAS_SEMANA.add("Sábado");
		
		MESES.add("Janeiro");
		MESES.add("Fevereiro");
		MESES.add("Março");
		MESES.add("Abril");
		MESES.add("Maio");
		MESES.add("Junho");
		MESES.add("Julho");
		MESES.add("Agosto");
		MESES.add("Setembro");
		MESES.add("Outubro");
		MESES.add("Novembro");
		MESES.add("Dezembro");
		
		TURNOS.add("Manhã");
		TURNOS.add("Tarde");
		TURNOS.add("Noite");
		TURNOS.add("Sábado manhã");
		TURNOS.add("Sábado tarde");
	}
}
