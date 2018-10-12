package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorTurma;
import br.com.aibetesda.modelos.PlanejamentoTurma;
import br.com.aibetesda.modelos.Turma;

@ManagedBean(name="managedTurma")
@SessionScoped
public class ManagedTurma extends ManagedCadastroConsulta {
	
	private static final Logger log = LoggerFactory.getLogger(ManagedTurma.class);
	
	private Turma turma = new Turma();
	private Turma turmaConsulta = new Turma();
	private List<Turma> todasTurmas = new ArrayList<Turma>();
	
	private PlanejamentoTurma planejamentoTurmaAluno = new PlanejamentoTurma();
	
	
	public ManagedTurma() {
		loadAll();
	}
	
	@Override
	public void novo() {
		turma = new Turma();
	}

	@Override
	public void salvar() {
		try {
			if(!objetoValido(turma))
				return;
			
			if(validarHoras() == false)
				return;
			
			new ControladorTurma().save(turma);
			log.info("Turma salva");
			addMensagem("Turma salva com sucesso");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro(e);
			log.error("Erro ao salvar turma: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void excluir() {
		try {
			new ControladorTurma().delete(turma);
			log.info("Turma excluida");
			addMensagem("Turma excluida com sucesso!");
			novo();
			loadAll();
		} catch (Exception e) {
			addMensagemErro(e);
			log.error("Erro ao excluir turma: " + e.getMessage());
		}
	}
	
	public void adicionarPlanejamento(){
		try{
			turma.setPlanejamentoTurma(planejamentoTurmaAluno);
			new ControladorTurma().save(turma);
			planejamentoTurmaAluno = new PlanejamentoTurma();
			
			addMensagem("Planejamento salvo com Sucesso!");
			System.out.println("Adicionou Planejamento!");  
		} catch(Exception e ){
			addMensagemErro(e);
		}
	}

	@Override
	public void selecionar() {
		if(turmaConsulta != null && turmaConsulta.getId() != null)
			setTurma(new ControladorTurma().getById(turmaConsulta.getId()));
	}

	@Override
	public void loadAll() {
		todasTurmas = new ControladorTurma().loadAll();
	}
	
	public boolean validarHoras() throws Exception{
		boolean valida;
		System.out.println("horarios: "+turma.getHorarioEntrada()+" e fim: "+turma.getHorarioSaida());
		int horaEntrada = Integer.valueOf(turma.getHorarioEntrada().substring(0, 2));
		int horaSaida = Integer.valueOf(turma.getHorarioSaida().substring(0, 2));
		int minEntrada = Integer.valueOf(turma.getHorarioEntrada().substring(3, 5));
		int minSaida = Integer.valueOf(turma.getHorarioSaida().substring(3, 5));
		
		if(horaEntrada > 23){
			System.out.println("horaEntrada: "+horaEntrada);
			addMensagemErro("hórario de entrada com formato incorreto");
			valida = false;	
		} else if(horaSaida > 23){
			System.out.println("horaSaida: "+horaSaida);
			addMensagemErro("hórario de saída com formato incorreto");
			valida = false;
		} else if(minEntrada > 59){
			System.out.println("minEntrada: "+minEntrada);
			addMensagemErro("minutos de entrada com formato incorreto");
			valida = false;
		} else if(minSaida > 59){
			System.out.println("minSaida: "+minSaida);
			addMensagemErro("minutos de saída com formato incorreto");
			valida = false;
		} else if(horaEntrada > horaSaida){
			System.out.println(horaEntrada+" > "+horaSaida);
			addMensagemErro("Hora entrada é maior que hora de saída");
			valida = false;
		} else {
			System.out.println("retornando TRUE...");
			valida = true;
		}
		return valida;
	}
	
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Turma getTurmaConsulta() {
		return turmaConsulta;
	}

	public void setTurmaConsulta(Turma turmaConsulta) {
		this.turmaConsulta = turmaConsulta;
	}

	public List<Turma> getTodasTurmas() {
		return todasTurmas;
	}

	public void setTodasTurmas(List<Turma> todasTurmas) {
		this.todasTurmas = todasTurmas;
	}

	public PlanejamentoTurma getPlanejamentoTurmaAluno() {
		return planejamentoTurmaAluno;
	}

	public void setPlanejamentoTurmaAluno(PlanejamentoTurma planejamentoTurmaAluno) {
		this.planejamentoTurmaAluno = planejamentoTurmaAluno;
	}
	
	
}
