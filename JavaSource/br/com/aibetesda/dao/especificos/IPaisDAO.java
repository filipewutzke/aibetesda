package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Pais;

public interface IPaisDAO extends DAO<Pais>{
	
	public List<Pais> getPaisQuery(String query);
	public Pais getPaisNome(String nome);
	
}
