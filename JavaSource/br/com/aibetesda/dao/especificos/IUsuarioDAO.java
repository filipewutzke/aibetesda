package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Usuario;

public interface IUsuarioDAO extends DAO<Usuario> {
	public Usuario getByName(String nome);
	public List<Usuario> getUsuariosLimit(String nome);
	public Usuario getByNome(String nome);
}
