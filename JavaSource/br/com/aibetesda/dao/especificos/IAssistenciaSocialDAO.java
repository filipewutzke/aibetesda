package br.com.aibetesda.dao.especificos;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.AssistenciaSocial;

public interface IAssistenciaSocialDAO extends DAO<AssistenciaSocial>{
	
	public AssistenciaSocial getAssistenciaPorInteressado(String nome);
}
