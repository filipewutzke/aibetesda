package br.com.aibetesda.controladores;

import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.ITurmaDAO;
import br.com.aibetesda.modelos.Curso;
import br.com.aibetesda.modelos.SalaAula;
import br.com.aibetesda.modelos.Turma;
import br.com.aibetesda.util.ApplicationContextUtils;

public class ControladorTurma extends Controlador<Turma> {
	
	
	@Override
	public ITurmaDAO getDAO() {
		return (ITurmaDAO) ApplicationContextUtils.getDAOBean(DAOUtils.TURMA);
	}

	@Override
	public Turma getById(Long id) {
		return getDAO().read(id);
	}

	@Override
	public Turma save(Turma obj) throws Exception {
		return getDAO().create(obj);
	}

	@Override
	public boolean delete(Turma obj) throws Exception {
		getDAO().delete(obj.getId());
		return true;
	}

	@Override
	public List<Turma> loadAll() {
		return getDAO().readAll();
	}
	
	public List<Turma> getTurmasAbertas(){
		return getDAO().getTurmasAbertas();
	}
	
	public List<Turma> getTurmasAbertasCurso(Curso curso){
		return getDAO().getTurmasAbertasCurso(curso);
	}
	
	public List<Turma> getTurmasAbertasSala(SalaAula sala){
		return getDAO().getTurmasSala(sala);
	}

}
