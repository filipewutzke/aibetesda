package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Profissao;

public interface IProfissaoDAO extends DAO<Profissao>{
	
	public List<Profissao> getProfissaoQuery(String query);
	public Profissao getProfissaoNome(String nome);
	public List<Profissao> loadProfissaoOrdenada();
	
}
