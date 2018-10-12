package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.aibetesda.controladores.ControladorAluno;
import br.com.aibetesda.controladores.ControladorUsuario;
import br.com.aibetesda.enums.Permissoes;
import br.com.aibetesda.enums.StatusAluno;
import br.com.aibetesda.modelos.Aluno;
import br.com.aibetesda.modelos.Matricula;
import br.com.aibetesda.modelos.Turma;
import br.com.aibetesda.modelos.Usuario;

@ManagedBean(name="managedAlterarMatricula")
@SessionScoped
public class ManagedAlterarMatricula extends ManagedCadastroConsulta {
	
	private Aluno aluno;
	private String login, senha;
	private String matricula = null;
	private List<String> statuses = new ArrayList<String>();
	private String statusToChange;
	private Turma turmaSelecionada;
	private List<Turma> turmasAlunoSelecionado = new ArrayList<Turma>();
	private String statusMatricula;
	
	private static final Logger log = LoggerFactory.getLogger(ManagedAlterarMatricula.class);
	
	public ManagedAlterarMatricula() {
		setShowSalvar(false);
		setShowProcurar(false);
		setShowExcluir(false);
		setAjaxStatus(false);
		
		for(StatusAluno sa : StatusAluno.values()) {
			statuses.add(sa.getStatus());
		}
		zerarDados();
	}
	
	private void zerarDados() {
		turmasAlunoSelecionado.clear();
		turmaSelecionada = null;
		statusMatricula = null;
	}

	public void alterarStatusManual() {
		try {
			if(login == null || login.trim().isEmpty() || senha == null || senha.trim().isEmpty()){
				addMensagemErro("Deve informar o usuário e a senha!");
				return;
			}
			
			Usuario us = new ControladorUsuario().getByName(login);	
			
			if(us == null){
				addMensagemErro("Login não confere, verifique");
				return;
			}
			
			Usuario logado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String atual = DigestUtils.sha256Hex(senha);
			
			if (!atual.equals(logado.getSenha())) {
				addMensagemErro("Senha não confere!");
				return;
			}
			
			if(!us.getPapel().equals(Permissoes.ADMINISTRADOR.name())){
				addMensagemErro("Usuário não é administrador para realizar a operação!");
				login = "";
				senha = "";		
				return;
			}
			
			StatusAluno status = StatusAluno.getByString(statusToChange);
			Matricula alterar = new ControladorAluno().getMatricula(Long.parseLong(matricula));
			alterar.setStatus(status.getCodigo());
			new ControladorAluno().salvarMatricula(alterar);
			
			setAluno(alterar.getPk().getAluno());
			addMensagem("Matricula alterada!");
			log.info("Status de Matricula alterado com sucesso");
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error("Erro ao alterar status manualmente: "+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao alterar status manualmente: "+e.getMessage());
		}
	}
	
	@Override
	public void novo() {
		this.aluno = new Aluno();
		zerarDados();
	}

	@Override
	public void salvar() {}

	@Override
	public void excluir() {}

	@Override
	public void selecionar() {}

	@Override
	public void loadAll() {}
	
	public void handleSelect(SelectEvent evento){
		log.info("handling select event");
		aluno = (Aluno) evento.getObject();
		zerarDados();
		for(Matricula m : aluno.getAlunoTurma()){
			Turma turma = m.getPk().getTurma();
			turmasAlunoSelecionado.add(turma);
			log.info("turma added");
			turmaSelecionada = turma;
			log.info("turmaSelecionada added");
		}
		
		if(turmaSelecionada != null)
			handleTurma();
	}
	
	public void handleTurma(){
		log.info("handling turma");
		for(Matricula m : aluno.getAlunoTurma()){
			log.info("entering if...");
			if(m.getMatricula() != null) {
				matricula = String.valueOf(m.getMatricula());
				StatusAluno status = StatusAluno.getByCodigo(m.getStatus()); 
				setStatusMatricula(status.getStatus());
			}
			
			if(m.getPk().getTurma().equals(turmaSelecionada)){
				log.info("turma equals turma!");
			}
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<String> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}

	public String getStatusToChange() {
		return statusToChange;
	}

	public void setStatusToChange(String statusToChange) {
		this.statusToChange = statusToChange;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public List<Turma> getTurmasAlunoSelecionado() {
		return turmasAlunoSelecionado;
	}

	public void setTurmasAlunoSelecionado(List<Turma> turmasAlunoSelecionado) {
		this.turmasAlunoSelecionado = turmasAlunoSelecionado;
	}

	public String getStatusMatricula() {
		return statusMatricula;
	}

	public void setStatusMatricula(String statusMatricula) {
		this.statusMatricula = statusMatricula;
	}
}
