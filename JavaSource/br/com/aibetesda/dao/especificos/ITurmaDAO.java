package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Curso;
import br.com.aibetesda.modelos.SalaAula;
import br.com.aibetesda.modelos.Turma;

public interface ITurmaDAO extends DAO<Turma> {
	public List<Turma> getTurmasAbertas();
	public List<Turma> getTurmasAbertasCurso(Curso curso);
	public List<Turma> getTurmasSala(SalaAula sala);
}
