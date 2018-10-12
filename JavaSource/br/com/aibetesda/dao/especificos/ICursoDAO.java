package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Curso;

public interface ICursoDAO extends DAO<Curso>{
	public Curso getCursoPorNome(String nome);
	public List<Curso> getCursoQuery(String nome);
	
}
