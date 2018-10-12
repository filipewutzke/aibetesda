package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Lembrete;

public interface ILembreteDAO extends DAO<Lembrete>{
	
	public Lembrete getReuniaoID(Long id);
	public List<Lembrete> getAvisoDatas();
}
