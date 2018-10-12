package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Interessado;

public interface IInteressadoDAO extends DAO<Interessado>{
	public Interessado getByName(String nome);
	public List<Interessado> getByNameLimit(String nome);
	public List<Interessado> getByInteressadoAlunoLimit(String query);
}
