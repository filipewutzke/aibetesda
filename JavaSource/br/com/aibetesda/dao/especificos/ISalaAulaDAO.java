package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.SalaAula;

public interface ISalaAulaDAO extends DAO<SalaAula> {
	public List<SalaAula> getSalaQuery(String query);
	public SalaAula getSalaNome(String nome);
}
