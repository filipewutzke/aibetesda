package br.com.aibetesda.dao.especificos;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.AgendaTelefone;

public interface IAgendaTelefoneDAO extends DAO<AgendaTelefone>{
	public AgendaTelefone getByName(String nome);
}
