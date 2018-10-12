package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IFuncionarioDAO;
import br.com.aibetesda.modelos.Funcionario;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorFuncionario extends Controlador<Funcionario>{
	
	@Override
	public IFuncionarioDAO getDAO() {
		return (IFuncionarioDAO) ApplicationContextUtils.getDAOBean(DAOUtils.FUNCIONARIO);
	}

	@Override
	public Funcionario getById(Long id) {
		return getDAO().read(id);
	}

	
	public Funcionario save(Funcionario f) throws Exception {
		if(f.getUsuarioRegistro() == null){
			f.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			f.setDtRegistro(new Date());
		}
		f.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		f.setDtAlteracao(new Date());
		
		getDAO().create(f);
		return f;
	}

	
	public boolean delete(Funcionario f) throws Exception {
		getDAO().delete(f.getId());
		return true;
	}

	@Override
	public List<Funcionario> loadAll() {
		List<Funcionario> todos = getDAO().readAll();
		return todos;
	}
	
	public Funcionario getPorNome(String nome){
		return getDAO().getPorNome(nome);
	}
	
	public List<Funcionario> getByNomeQuery(String query){
		return getDAO().getFuncionarioLimit(query);
	}
	
	public List<Funcionario> getByProfessorQuery(String query){
		return getDAO().getProfessorLimit(query);
	}

}
