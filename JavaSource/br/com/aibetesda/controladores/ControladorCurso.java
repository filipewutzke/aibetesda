package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.ICursoDAO;
import br.com.aibetesda.modelos.Curso;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorCurso extends Controlador<Curso> {
	
	public ControladorCurso() {}
	
	@Override
	public ICursoDAO getDAO() {
		return (ICursoDAO) ApplicationContextUtils.getDAOBean(DAOUtils.CURSO);
	}
	
	public Curso save(Curso curso) throws Exception{
		if(curso.getUsuarioRegistro() == null){
			curso.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			curso.setDtRegistro(new Date());
		}
		curso.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		curso.setDtAlteracao(new Date());
		
		getDAO().create(curso);
		return curso;
	}
	
	public boolean delete(Curso curso) throws Exception{
		getDAO().delete(curso.getId());
		return true;
	}

	@Override
	public List<Curso> loadAll() {
		return getDAO().readAll();
	}

	@Override
	public Curso getById(Long id) {
		return getDAO().read(id);
	}
	
	public Curso getCursoPorNome(String nome) {
		return getDAO().getCursoPorNome(nome);
	}
	
	public List<Curso> getCursoQuery(String nome)
	{
		return getDAO().getCursoQuery(nome);
	}

}
