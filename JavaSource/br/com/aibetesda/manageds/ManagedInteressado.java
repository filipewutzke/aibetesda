package br.com.aibetesda.manageds;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import br.com.aibetesda.controladores.ControladorInteressado;
import br.com.aibetesda.controladores.ControladorUsuario;
import br.com.aibetesda.enums.Permissoes;
import br.com.aibetesda.modelos.BemDuravel;
import br.com.aibetesda.modelos.BeneficioSocial;
import br.com.aibetesda.modelos.Interessado;
import br.com.aibetesda.modelos.Usuario;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name = "managedInteressado")
@SessionScoped
public class ManagedInteressado extends ManagedCadastroConsulta{
	
	private static final Logger log = LoggerFactory.getLogger(ManagedInteressado.class);
	
	private Interessado interessado;
	private Interessado interessadoConsulta;
	private List<Interessado> todosInteressados;
	
	private BemDuravel bemDuravel;
	private BemDuravel bemDuravelSelecionado;
	
	private BeneficioSocial beneficioSocial;
	private BeneficioSocial beneficioSocialSelecionado;
	
	private String login, senha;
	private boolean statusInteressado;
	
	
	public ManagedInteressado() {
		novo();
		loadAll();
	}	
	
	@Override
	public void novo() {
		interessado = new Interessado();
	}

	@Override
	public void salvar() {
		try {
			if (!objetoValido(interessado))
				return;

			new ControladorInteressado().save(interessado);

			addMensagem("Interessado salvo com sucesso");
			log.info(UserUtils.getUsuarioLogado() +"salvou Interessado");
			interessado = new Interessado();
		} catch (DataAccessException e) {
			e.printStackTrace();
			addMensagemErro("Erro ao salvar interessado: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro("Erro ao salvar interessado: " + e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try {
			if (interessado.getId() == null) {
				addMensagemErro("Interessado vazio!");
				return;
			}
			ControladorInteressado controlador = new ControladorInteressado();
			if (controlador.delete(interessado)) {
				addMensagem("Interessado removido com sucesso");
				log.info(UserUtils.getUsuarioLogado() + " excluiu um interessado: "
						+ interessado);
				novo();
				loadAll();
			} else
				addMensagem("Erro ao excluir interessado");
		} catch (Exception e) {
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao excluir interessado!");
			log.error("Erro ao excluir interessado: " + e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if (interessadoConsulta != null) {
			interessado = new ControladorInteressado().getById(interessadoConsulta.getId());
		} else {
			addMensagemWarn("Selecione um interessado");
		}
	}

	@Override
	public void loadAll() {
		todosInteressados = new ControladorInteressado().loadAll();
	}
	
	public List<Interessado> completeInteressado(String query) {
		return new ControladorInteressado().getByNameLimit(query);
	}
	
	public List<Interessado> completeInteressadoForAluno(String query) {
		return new ControladorInteressado().getByInteresadoAlunoLimit(query);
	}
	
	public void adicionarBemDuravel() {
		this.interessado.addBemDuravel(bemDuravel);
		
		bemDuravelSelecionado = null;
		bemDuravel = new BemDuravel();
	}
	
	public void removerBemDuravel() {
		this.interessado.removerBemDuravel(bemDuravelSelecionado);
	}
	
	public void adicionarBeneficioSocial() {
		this.interessado.addBeneficioSocial(beneficioSocial);
		
		beneficioSocialSelecionado = null;
		beneficioSocial = new BeneficioSocial();
	}
	
	public void removerBeneficioSocial() {
		this.interessado.removerBeneficioSocial(beneficioSocialSelecionado);
	}
	
	public void liberarInteressadoMatricula() {
		try {
			System.out.println("NOME: "+interessadoConsulta);
			if(interessadoConsulta == null){
				addMensagemErro("Deve selecionar um Interessado!");
				return;
			}
			
				
			if (login == null || login.trim().isEmpty() || senha == null
					|| senha.trim().isEmpty()) {
				addMensagemErro("Deve informar o usuário e a senha!");
				return;
			}
	
			Usuario us = new ControladorUsuario().getByName(login);
	
			if (us == null) {
				addMensagemErro("Login não confere, verifique");
				return;
			}
				
			if(!us.getSenha().equals(encriptarString(senha))){
				addMensagemErro("Senha não confere com Login");
				return;
			}
	
			if (!us.getPapel().equals(Permissoes.ADMINISTRADOR.name())) {
				addMensagemErro("Usuário não é administrador para realizar a operação!");
				login = "";
				senha = "";
				return;
			}
				
			if(statusInteressado == true){
				System.out.println("statusInteressado: "+statusInteressado);
				if(interessadoConsulta.isAptoMatricula() == true){
					addMensagemErro("Interessado já está liberado para matrícula!");
					return;
				}
			} else {
				System.out.println("statusInteressado: "+statusInteressado);
				System.out.println("interessado.NOME: "+interessadoConsulta.getNome());
				if(interessadoConsulta.isAptoMatricula() == false){
					addMensagemErro("Interessado já está bloqueado!");
					return;
				}
			}
		
			interessadoConsulta.setAptoMatricula(statusInteressado);
			
			new ControladorInteressado().save(interessadoConsulta);
			if(statusInteressado == true)
				addMensagem("Interessado liberado para Matricula!");
			if(statusInteressado == false)
				addMensagem("Interessado bloqueado para Matricula!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		interessadoConsulta = null;
	}
	
	public Interessado getInteressado() {
		return interessado;
	}

	public void setInteressado(Interessado interessado) {
		this.interessado = interessado;
	}

	public Interessado getInteressadoConsulta() {
		return interessadoConsulta;
	}

	public void setInteressadoConsulta(Interessado interessadoConsulta) {
		this.interessadoConsulta = interessadoConsulta;
	}

	public List<Interessado> getTodosInteressados() {
		return todosInteressados;
	}

	public void setTodosInteressados(List<Interessado> todosInteressados) {
		this.todosInteressados = todosInteressados;
	}

	public BemDuravel getBemDuravel() {
		return bemDuravel;
	}

	public void setBemDuravel(BemDuravel bemDuravel) {
		this.bemDuravel = bemDuravel;
	}

	public BemDuravel getBemDuravelSelecionado() {
		return bemDuravelSelecionado;
	}

	public void setBemDuravelSelecionado(BemDuravel bemDuravelSelecionado) {
		this.bemDuravelSelecionado = bemDuravelSelecionado;
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

	public boolean isStatusInteressado() {
		return statusInteressado;
	}

	public void setStatusInteressado(boolean statusInteressado) {
		this.statusInteressado = statusInteressado;
	}

	public BeneficioSocial getBeneficioSocial() {
		return beneficioSocial;
	}

	public void setBeneficioSocial(BeneficioSocial beneficioSocial) {
		this.beneficioSocial = beneficioSocial;
	}

	public BeneficioSocial getBeneficioSocialSelecionado() {
		return beneficioSocialSelecionado;
	}

	public void setBeneficioSocialSelecionado(
			BeneficioSocial beneficioSocialSelecionado) {
		this.beneficioSocialSelecionado = beneficioSocialSelecionado;
	}
	

}
