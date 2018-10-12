package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorFuncionario;
import br.com.aibetesda.modelos.Funcionario;

@ManagedBean(name="managedFuncionario")
@SessionScoped
public class ManagedFuncionario extends ManagedCadastroConsulta {
	
	private static final Logger log = LoggerFactory.getLogger(ManagedFuncionario.class);
	
	private Funcionario funcionario = new Funcionario();
	private Funcionario funcionarioConsulta = new Funcionario();
	private List<Funcionario> todosFuncionarios = new ArrayList<Funcionario>();
	
	public ManagedFuncionario() { 
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		funcionario = new Funcionario();
	}

	@Override
	public void salvar() {
		try {
			if(!objetoValido(funcionario))
				return;
			
			new ControladorFuncionario().save(funcionario);
			log.info("Funcionario salvo");
			addMensagem("Funcionário salvo com sucesso!");
			novo();
			loadAll();
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro(e);
			log.error("Erro ao salvar funcionario: " + e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try {
			if (funcionario.getId() == null) {
				addMensagemErro("Funcionário vazio!");
				return;
			}
			
			new ControladorFuncionario().delete(funcionario);
			log.info("Funcionario excluido");
			addMensagem("Funcionário excluído com sucesso!");
			novo();
			loadAll();
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro(e);
			log.error("Erro ao excluir funcionario: " + e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(funcionarioConsulta != null && funcionarioConsulta.getId() != null)
			setFuncionario(new ControladorFuncionario().getById(funcionarioConsulta.getId()));
	}

	@Override
	public void loadAll() {
		this.todosFuncionarios = new ControladorFuncionario().loadAll();
	}
	
	public List<Funcionario> getFuncionarioQuery(String query){
		return new ControladorFuncionario().getByNomeQuery(query);
	}
	
	public List<Funcionario> getProfessorQuery(String query){
		return new ControladorFuncionario().getByProfessorQuery(query);
	}
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioConsulta() {
		return funcionarioConsulta;
	}

	public void setFuncionarioConsulta(Funcionario funcionarioConsulta) {
		this.funcionarioConsulta = funcionarioConsulta;
	}

	public List<Funcionario> getTodosFuncionarios() {
		return todosFuncionarios;
	}

	public void setTodosFuncionarios(List<Funcionario> todosFuncionarios) {
		this.todosFuncionarios = todosFuncionarios;
	}

}
