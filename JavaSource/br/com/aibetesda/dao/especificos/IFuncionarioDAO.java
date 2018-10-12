package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.Funcionario;

public interface IFuncionarioDAO extends DAO<Funcionario> {
	public Funcionario getPorNome(String nome);
	public List<Funcionario> getFuncionarioLimit(String nome);
	public List<Funcionario> getProfessorLimit(String nome);
}
