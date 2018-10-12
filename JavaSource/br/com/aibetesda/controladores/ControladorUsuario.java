package br.com.aibetesda.controladores;

import java.util.Date;
import java.util.List;

import br.com.aibetesda.dao.DAOUtils;
import br.com.aibetesda.dao.especificos.IUsuarioDAO;
import br.com.aibetesda.modelos.Usuario;
import br.com.aibetesda.util.ApplicationContextUtils;
import br.com.aibetesda.util.UserUtils;

public class ControladorUsuario extends Controlador<Usuario> {
	
	public Usuario getById(Long id) {
		try {
			Usuario loaded = getDAO().read(id);
			return loaded;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario save(Usuario obj) throws Exception {
		if(obj.getUsuarioRegistro() == null){
			obj.setUsuarioRegistro(UserUtils.getUsuarioLogado());
			obj.setDtRegistro(new Date());
		}
		obj.setUsuarioAlteracao(UserUtils.getUsuarioLogado());
		obj.setDtAlteracao(new Date());
		getDAO().create(obj);
		return obj;
	}

	public boolean delete(Usuario obj) throws Exception {
		getDAO().delete(obj.getId());
		return true;
	}

	public List<Usuario> loadAll() {
		return getDAO().readAll();
	}
	
	public Usuario getByName(String login)
	{
		return getDAO().getByName(login);
	}
	
	public IUsuarioDAO getDAO() {
		return (IUsuarioDAO) ApplicationContextUtils.getDAOBean(DAOUtils.USUARIO);
	}
	
	public List<Usuario> getUsuariosQuery(String query)
	{
		return getDAO().getUsuariosLimit(query);
	}
	
	public Usuario getByNome(String nome)
	{
		return getDAO().getByNome(nome);
	}
}
