package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Aluno;
import br.com.aibetesda.modelos.AlunoTurmaPk;
import br.com.aibetesda.modelos.Matricula;
import br.com.aibetesda.modelos.Pedagogico;
import br.com.aibetesda.modelos.Turma;

public interface IAlunoDAO extends DAO<Aluno>{
	public Aluno getByName(String nome);
	public List<Aluno> getByNameLimit(String nome);
	public Aluno getByPedagogico(Pedagogico pedagogico);
	public Matricula getMatricula(Long id);
	public Matricula salvarMatricula(Matricula id) throws Exception;
	public AlunoTurmaPk transferirAluno(Turma turma, Matricula mat);
}
