package br.com.aibetesda.controladores;

import java.util.List;
import br.com.aibetesda.dao.DAO;

public abstract class Controlador<T> {
	public abstract DAO<T> getDAO();
	
	public abstract T getById(Long id);
	public abstract T save(T obj) throws Exception;
	public abstract boolean delete(T obj) throws Exception;
	public abstract List<T> loadAll();
}
