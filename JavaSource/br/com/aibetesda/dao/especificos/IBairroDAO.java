package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Bairro;

public interface IBairroDAO extends DAO<Bairro>{
	
	public List<Bairro> getBairroQuery(String query);
	public Bairro getBairroNome(String nome);
	
}
