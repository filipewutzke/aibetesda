package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IAlunoDAO;
import br.com.aibetesda.modelos.Aluno;
import br.com.aibetesda.modelos.AlunoTurmaPk;
import br.com.aibetesda.modelos.Matricula;
import br.com.aibetesda.modelos.Pedagogico;
import br.com.aibetesda.modelos.Turma;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorAluno extends Controlador<Aluno>{

	public IAlunoDAO getDAO() {
		return (IAlunoDAO) ApplicationContextUtils.getDAOBean(DAOUtils.ALUNO);
	}

	public Aluno getById(Long id){		
		return getDAO().read(id);		
	}

	public Aluno save(Aluno aluno) throws Exception{
		if(aluno.getUsuarioRegistro() == null){
			aluno.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			aluno.setDtRegistro(new Date());
		}
		aluno.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		aluno.setDtAlteracao(new Date());
		
		getDAO().create(aluno);
		return aluno;
	}

	public boolean delete(Aluno aluno) throws Exception{
		getDAO().delete(aluno.getId());
		return true;
	}
	
	public List<Aluno> loadAll(){
		return getDAO().readAll();
	}
	
	public List<Aluno> getByNameLimit(String nome){
		return getDAO().getByNameLimit(nome);
	}
	
	public Aluno getByName(String nome){
		return getDAO().getByName(nome);
	}
	
	public Aluno getAlunoPedagogico(Pedagogico pedagogico){
		return getDAO().getByPedagogico(pedagogico);
	}
	
	public Matricula getMatricula(Long id){
		return getDAO().getMatricula(id);
	}
	
	public Matricula salvarMatricula(Matricula m) throws Exception{
		return getDAO().salvarMatricula(m);
	}
	
	public AlunoTurmaPk transferirAluno(Turma turma, Matricula mat){
		return getDAO().transferirAluno(turma, mat);
	}
	
}
