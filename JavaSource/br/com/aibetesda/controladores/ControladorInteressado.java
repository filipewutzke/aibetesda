package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IInteressadoDAO;
import br.com.aibetesda.modelos.Interessado;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorInteressado extends Controlador<Interessado>{
	
	public IInteressadoDAO getDAO() {
		return (IInteressadoDAO) ApplicationContextUtils.getDAOBean(DAOUtils.INTERESSADO);
	}
	
	public Interessado getById(Long id){		
		return getDAO().read(id);		
	}

	public Interessado save(Interessado interessado) throws Exception{
		if(interessado.getUsuarioRegistro() == null){
			interessado.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			interessado.setDtRegistro(new Date());
		}
		interessado.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		interessado.setDtAlteracao(new Date());
		
		getDAO().create(interessado);
		return interessado;
	}

	public boolean delete(Interessado interessado) throws Exception{
		getDAO().delete(interessado.getId());
		return true;
	}
	
	public List<Interessado> loadAll(){
		return getDAO().readAll();
	}
	
	public List<Interessado> getByNameLimit(String nome){
		return getDAO().getByNameLimit(nome);
	}
	
	public Interessado getByName(String nome){
		return getDAO().getByName(nome);
	}

	public List<Interessado> getByInteresadoAlunoLimit(String query) {
		return getDAO().getByInteressadoAlunoLimit(query);
	}
	

}
