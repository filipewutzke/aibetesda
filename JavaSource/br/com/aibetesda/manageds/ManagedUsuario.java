package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.aibetesda.controladores.ControladorUsuario;
import br.com.aibetesda.enums.Permissoes;
import br.com.aibetesda.modelos.Usuario;
import br.com.aibetesda.util.UserUtils;

import com.rollingwithcode.collect.Collect;

@ManagedBean(name="managedUsuario")
@SessionScoped
public class ManagedUsuario extends ManagedCadastroConsulta {
	private static Logger log = LoggerFactory.getLogger(ManagedUsuario.class);
	
	private Usuario usuario;
	private Usuario usuarioConsulta;
	private List<Usuario> todosUsuarios;
	
	private List<Permissoes> permissoes = new ArrayList<Permissoes>();
	private Permissoes permissao;
	
	private String senha, confirmarSenha, senhaAtual = "";
	private String mask = "9.999.999-9";
	private boolean maxRg = false;

	public ManagedUsuario() {
		novo();
		loadPermissoes();
		log.info("ManagedUsuario instanciado");
	}
	
	@Override
	public void novo() {
		setUsuario(new Usuario());
	}

	@Override
	public void salvar() {
		try {
			if(!objetoValido(usuario))
				return;
			
			ControladorUsuario c = new ControladorUsuario();
			if(!verificaUnico(usuario.getLogin()))
			{
				addMensagemErro("Usuário ja existe com esse login!");
				return;
			}
			usuario.setPapel(permissao.getPapel());
			
			if(usuario.getId() == null){
				usuario.setSenha(encriptarString(usuario.getSenha()));
			}
			
			c.save(usuario);
			novo();
			loadAll();
			addMensagem("Usuário salvo!");
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro("Ocorreu um erro: "+e.getMessage());
			log.info("Erro ao cadastrar usuário: "+e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try {
			ControladorUsuario c = new ControladorUsuario();
			c.delete(usuario);
			log.info(UserUtils.getUsuarioLogado()+" excluiu um Usuário: "+usuario);
			novo();
			loadAll();
			addMensagem("Usuário removido com sucesso!");
		} catch (Exception e) {
			addMensagemErro("Erro: "+e.getMessage());
			log.info("Erro ao exlcuir usuário: "+e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(!(usuarioConsulta == null) && usuarioConsulta.getId() != null){
			setUsuario(usuarioConsulta);
			setPermissao(Permissoes.valueOf(usuario.getPapel()) );
		}
	}
	
	private void loadPermissoes() {
		for(Permissoes p : Permissoes.values()){
			permissoes.add(p);
		}
		
		if(!UserUtils.isAdmin()){
			Collection<Permissoes> permissoes = new Collect().in(this.permissoes).when().eql(Permissoes.ADMINISTRADOR);
			this.permissoes.removeAll(permissoes);
		}
	}
		
	public void alterarSenha() {
		try {
			Usuario logado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String atual = DigestUtils.sha256Hex(senhaAtual);
			
			if (!atual.equals(logado.getSenha())) {
				addMensagemErro("Senha atual não confere!");
				return;
			}
			
			if(!senha.equals(confirmarSenha)){
				addMensagemErro("Senhas não conferem!");
				return;
			}
			
			ControladorUsuario controladorUsuario = new ControladorUsuario();
			
			Usuario usuario;
			usuario = controladorUsuario.getById(logado.getId());
			usuario.setSenha(DigestUtils.sha256Hex(senha));
			controladorUsuario.save(usuario);
			addMensagem("Senha atualizada");
			limparSenhas();
		} catch (Exception e) {
			addMensagemErro("Não foi possivel completar: "+e.getMessage());
			limparSenhas();
		}
	}
	
	public List<Usuario> getUsuarioQuery(String query){
		return new ControladorUsuario().getUsuariosQuery(query);
	}
	
	private void limparSenhas() {
		senha = new String();
		confirmarSenha = new String();
	}
	
	@Override
	public void loadAll() {
		ControladorUsuario c = new ControladorUsuario();
		setTodosUsuarios(c.loadAll());
		if(!UserUtils.isAdmin()){
			Collection<Usuario> admin = new Collect().in(todosUsuarios).when("papel").eql(Permissoes.ADMINISTRADOR.getPapel());
			todosUsuarios.removeAll(admin);
		}
		Collection<Usuario> superuser = new Collect().in(todosUsuarios).when("id").eql(0L);
		todosUsuarios.removeAll(superuser);
	}
	
	public boolean verificaUnico(String login){
		ControladorUsuario c = new ControladorUsuario();
		if(usuario.getId() != null){
			return true;
		}
		return c.getByName(login) == null;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getTodosUsuarios() {
		return todosUsuarios;
	}
	
	public void setTodosUsuarios(List<Usuario> todosUsuarios) {
		this.todosUsuarios = todosUsuarios;
	}
	
	public List<Permissoes> getPermissoes() {
		return permissoes;
	}
	
	public void setPermissoes(List<Permissoes> permissoes) {
		this.permissoes = permissoes;
	}
	
	public void setPermissao(Permissoes permissao) {
		this.permissao = permissao;
	}
	
	public Permissoes getPermissao() {
		return permissao;
	}
	public Usuario getUsuarioConsulta() {
		return usuarioConsulta;
	}

	public void setUsuarioConsulta(Usuario usuarioConsulta) {
		this.usuarioConsulta = usuarioConsulta;
	}	
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmarSenha() {
		return confirmarSenha;
	}
	
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getMask() {
		return mask;
	}
	
	public void setMask(String mask) {
		this.mask = mask;
	}
	
	public boolean isMaxRg() {
		return maxRg;
	}
	
	public void setMaxRg(boolean maxRg) {
		this.maxRg = maxRg;
		if(this.maxRg)
			mask = "99.999.999-9";
		else
			mask = "9.999.999-9";
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
}
