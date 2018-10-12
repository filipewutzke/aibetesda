package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorCurso;
import br.com.aibetesda.modelos.Curso;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name="managedCurso")
@SessionScoped
public class ManagedCurso extends ManagedCadastroConsulta{

	private Curso curso = new Curso();
	private Curso cursoConsulta = new Curso();
	private List<Curso> cursos = new ArrayList<Curso>();

	private static final Logger log = LoggerFactory.getLogger(ManagedCurso.class);

	public ManagedCurso() {
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		curso = new Curso();
	}

	@Override
	public void salvar() {
		try{
			if(!objetoValido(curso))
				return;
			
			ControladorCurso cc = new ControladorCurso();
			cc.save(curso);
			addMensagem("Curso salvo com sucesso!");
			novo();
			loadAll();
			log.info("Curso salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro("Erro ao salvar curso: "+e.getMessage());
			log.error("Curso não foi salvo: "+e.getMessage());
		}
	}

	
	@Override
	public void excluir() {
		try{
			if(curso.getId() == null){
				addMensagemErro("Curso vazio!");
				return;
			}
			ControladorCurso cc = new ControladorCurso();
			if(cc.delete(curso)){
				addMensagem("Curso excluido!");
				log.info(UserUtils.getUsuarioLogado()+" excluiu uma curso: "+curso);
				novo();
				loadAll();
			}else{
				addMensagem("Curso não foi excluido! Verifique os dados");
			}
		} catch(Exception e){
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao excluir curso!");
			log.error("Erro ao excluir curso: "+e.getMessage());
		}		
	}

	@Override
	public void selecionar() {
		if(cursoConsulta != null && cursoConsulta.getId() != null){
			this.curso = new ControladorCurso().getById(cursoConsulta.getId());
		}	
	}

	@Override
	public void loadAll() {
		this.cursos = new ControladorCurso().loadAll();
	}
	
	public List<Curso> getCursoQuery(String query){
		return new ControladorCurso().getCursoQuery(query);
	}
	

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Curso getCursoConsulta() {
		return cursoConsulta;
	}

	public void setCursoConsulta(Curso cursoConsulta) {
		this.cursoConsulta = cursoConsulta;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
