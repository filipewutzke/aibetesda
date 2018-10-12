package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.DoencaAlergia;

public interface IDoencaAlergiaDAO extends DAO<DoencaAlergia>{
	public DoencaAlergia getDoencaAlergiaPorNome(String nome);
	public List<DoencaAlergia> getDoencaAlergiaQuery(String nome);
	
}
