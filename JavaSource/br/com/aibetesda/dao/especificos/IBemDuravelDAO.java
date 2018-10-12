package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.BemDuravel;

public interface IBemDuravelDAO extends DAO<BemDuravel>{
	
	public List<BemDuravel> getBemDuravelQuery(String query);
	public BemDuravel getBemDuravelNome(String nome);
	
}
