package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import br.com.aibetesda.controladores.ControladorAluno;
import br.com.aibetesda.controladores.ControladorTurma;
import br.com.aibetesda.enums.StatusAluno;
import br.com.aibetesda.modelos.Aluno;
import br.com.aibetesda.modelos.AlunoTurmaPk;
import br.com.aibetesda.modelos.ContatoPedagogico;
import br.com.aibetesda.modelos.Curso;
import br.com.aibetesda.modelos.DoencaAlergia;
import br.com.aibetesda.modelos.Interessado;
import br.com.aibetesda.modelos.LancamentoAtraso;
import br.com.aibetesda.modelos.Matricula;
import br.com.aibetesda.modelos.Turma;
import br.com.aibetesda.util.FacesUtils;
import br.com.aibetesda.util.RelatorioUtils;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name = "managedAluno")
@SessionScoped
public class ManagedAluno extends ManagedCadastroConsulta {
	
	private static final Logger log = LoggerFactory.getLogger(ManagedAluno.class);
	
	private Aluno aluno;
	private Aluno alunoConsulta;
	private List<Aluno> todosAlunos;
	
	private Interessado interessadoCarregar;
	private boolean liberarPainel = false;
	
	private Turma turmaCursoSelecionado;
	private Turma turmaAlunoSelecionado;
	private List<Turma> turmasAlunoSelecionado = new ArrayList<Turma>();
	private List<Turma> turmasCursoSelecionado = new ArrayList<Turma>();
	
	private DoencaAlergia doencaAlergia = new DoencaAlergia();
	private DoencaAlergia doencaAlergiaSelecionada = new DoencaAlergia();
	
	private Curso curso;
	private String matricula = null;
	private String statusMatricula;
	
	private Aluno alunoContato;
	private String contatoPedagogico;
	private String horarioEntrada;
	
	private Date dtInicial;
	private Date dtFinal;
	
	private Aluno alunoFicha;
	
	
	public ManagedAluno() {
		novo();
		loadAll();
	}
	
	@Override
	public void novo() {
		aluno = new Aluno();
		interessadoCarregar = new Interessado();
		mudaTurmasAluno();
		turmaAlunoSelecionado = null;
		zerarTurmasCurso();
	}

	@Override
	public void salvar() {
		try {
			if (!objetoValido(aluno))
				return;

			new ControladorAluno().save(aluno);

			addMensagem("Aluno salvo com sucesso");
			log.info(UserUtils.getUsuarioLogado()+" salvou um aluno");
			aluno = new Aluno();
		} catch (DataAccessException e) {
			e.printStackTrace();
			addMensagemErro("Erro ao salvar aluno: " + e.getMessage());
			log.error(UserUtils.getUsuarioLogado() + "erro ao salvar um aluno: "+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro("Erro ao salvar aluno: " + e.getMessage());
			log.error(UserUtils.getUsuarioLogado() + "erro ao salvar um aluno: "+e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try {
			if (aluno.getId() == null) {
				addMensagemErro("Aluno vazio!");
				return;
			}
			ControladorAluno controlador = new ControladorAluno();
			if (controlador.delete(aluno)) {
				addMensagem("Aluno removido com sucesso");
				log.info(UserUtils.getUsuarioLogado() + " excluiu um aluno: "+aluno);
				novo();
				loadAll();
			} else
				addMensagem("Erro ao excluir aluno");
		} catch (Exception e) {
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao excluir aluno!");
			log.error(UserUtils.getUsuarioLogado() + "erro ao salvar um aluno: "+e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if (alunoConsulta != null) {
			aluno = new ControladorAluno().getById(alunoConsulta.getId());
		} else {
			addMensagemWarn("Selecione um aluno");
		}
	}

	public void loadAll() {
		todosAlunos = new ControladorAluno().loadAll();
	}
	
	public List<Aluno> completeAluno(String query) {
		return new ControladorAluno().getByNameLimit(query);
	}
	
	public void adicionarDoencaAlergia() {
		this.aluno.addDoencaAlergia(doencaAlergia);
		
		doencaAlergiaSelecionada = null;
		doencaAlergia = new DoencaAlergia();
	}
	
	public void removerDoencaAlergia() {
		this.aluno.removerDoencaAlergia(doencaAlergiaSelecionada);
	}
	
	/**
	 * Carrega e seta os dados do interessado no aluno
	 * */
	public void carregarDadosInteressado(){
		
		if(interessadoCarregar == null){
			addMensagemErro("Deve informar um interessado");
			return;
		}
		
		System.out.println("Carregando dados do Interessado...");
		liberarPainel = true;
		
		aluno.setInteressado(interessadoCarregar);
		aluno.setNome(interessadoCarregar.getNome());
		aluno.setDataNascimento(interessadoCarregar.getDataNascimento());
		aluno.setSexo(interessadoCarregar.getSexo());
		aluno.setEndereco(interessadoCarregar.getEndereco());
		aluno.setBairro(interessadoCarregar.getBairro());
		aluno.setTelefoneCasa(interessadoCarregar.getTelefoneCasa());
		aluno.setTelefoneCelular(interessadoCarregar.getTelefoneCelular());
		aluno.setMae(interessadoCarregar.getMae());
		aluno.setPai(interessadoCarregar.getPai());
		aluno.setRendaFamiliar(interessadoCarregar.getRendaFamiliar());
		
		System.out.println("Carregado dados do interessado "+interessadoCarregar.getNome());
		log.info(UserUtils.getUsuarioLogado()+ ", Carregado dados do interessado "+interessadoCarregar.getNome());
	}
	
	/**
	 * Carrega os dados do aluno para matricula
	 * */
	public void carregarAluno(){
		try	{
			if(aluno == null || aluno.getId() == null){
				addMensagemWarn("Sem aluno selecionado", "Selecione um aluno (escrevendo seu nome no campo ao lado) antes de" +
						" carregar seus dados");
				return;
			}
			desativaBotoes();
			setAjaxStatus(false);
			aluno = new ControladorAluno().getById(aluno.getId());
			mudaTurmasAluno();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * Método que altera os horários da turma no change do curso, ao inserir
	 * turmas
	 * */
	public void mudaTurmasCurso() {
		turmasCursoSelecionado = new ControladorTurma()
				.getTurmasAbertas();
		System.out.println("turmasCursoSelecionado: "+turmasCursoSelecionado);
		if (turmasCursoSelecionado == null || turmasCursoSelecionado.size() < 1) {
			turmasCursoSelecionado = new ArrayList<Turma>();
		} else {
			turmaCursoSelecionado = turmasCursoSelecionado.get(0);
			mudaHorariosCurso();
		}
	}
	
	/**
	 * Muda os horários para inserção confome a turma selecionada
	 * */
	public void mudaHorariosCurso() {
		if (turmaCursoSelecionado == null) {
			turmaCursoSelecionado = new Turma();
		}
	}
	
	/**
	 * Muda as turmas do aluno selecionado
	 * */
	public void mudaTurmasAluno() {
		turmasAlunoSelecionado = new ArrayList<Turma>();
		for (Matricula m : aluno.getAlunoTurma()){
			turmasAlunoSelecionado.add(m.getPk().getTurma());
			System.out.println("tumaAlunoSelecionado "+turmaAlunoSelecionado);
		}
		if (turmasAlunoSelecionado.size() > 0){
			turmaAlunoSelecionado = turmasAlunoSelecionado.get(0);
			System.out.println("tumaAlunoSelecionado "+turmaAlunoSelecionado);
		}	
		mudaHorariosAluno();
	}
	
	/**
	 * Muda os horários da turma inserida e selecionada no aluno
	 * */
	public void mudaHorariosAluno() {
		matricula = null;
		if (turmaAlunoSelecionado == null) {
			turmasAlunoSelecionado = new ArrayList<Turma>();
		} else {
			for (Matricula m : aluno.getAlunoTurma()){
				if (m.getPk().getTurma().equals(turmaAlunoSelecionado)) {
					if (m.getMatricula() != null) {
						matricula = String.valueOf(m.getMatricula());
						StatusAluno status = StatusAluno.getByCodigo(m
								.getStatus());
						setStatusMatricula(status.getStatus());
					}
				}
				break;
			}
		}
	}
	
	/**
	 * Matricula o aluno selecionado na turma
	 * */
	public void matricularTurma() {
		try {
			Matricula mat = new Matricula();
			
			Turma turma = new ControladorTurma().getById(turmaCursoSelecionado.getId());
			//verifica se há vagas disponiveis
			if(turma.getNumeroVagasDisp().equals(0)){
				addMensagemErro("Não há vagas para esta turma!");
				addMensagemWarn("Turma está completa!");
				return;
			}		
			turma.setNumeroVagasDisp(turma.getNumeroVagasDisp() - 1);
			
			AlunoTurmaPk pk = new AlunoTurmaPk(aluno,turma);
			
			mat.setPk(pk);
			mat.setStatus(StatusAluno.ATIVO.getCodigo());
			mat.setDataMatricula(new Date());

			aluno.adicionarMatricula(mat);
			alunoFicha = aluno;
			
			addMensagem("Matricula adicionada com sucesso!");
			mudaTurmasAluno();
			salvar();
			
		} catch (Exception e) {
			log.error("Erro ao matricular aluno: "+e.getMessage());
			addMensagemErro("Erro ao adicionar matricula: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void alterarMatricula(){
		try{
			Matricula mat = new ControladorAluno().getMatricula(Long.parseLong(matricula));
			//Libera uma vaga na turma anterior
			mat.getPk().getTurma().setNumeroVagasDisp(mat.getPk().getTurma().getNumeroVagasDisp() + 1);
			
			Turma turma = new ControladorTurma().getById(turmaCursoSelecionado.getId());
			if(turma.getNumeroVagasDisp().equals(0)){
				addMensagemErro("Não há vagas para esta turma!");
				addMensagemWarn("Turma está completa");
				return;
			}
			//Ocupa uma nova vaga na turma selecionada
			turma.setNumeroVagasDisp(turma.getNumeroVagasDisp() - 1);
			
			new ControladorAluno().transferirAluno(turma, mat);
			addMensagem("Sucesso na Transferência de Aluno!");
			log.info("sucesso na transferencia de turma do aluno");
			
			novo();
			mudaTurmasAluno();			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Cancela matricula do aluno selecionado na turma
	 * */
	public void cancelarMatricula() {
		try {
			Matricula mat = new ControladorAluno().getMatricula(Long.parseLong(matricula));

			if (mat.getStatus().equals(StatusAluno.DESISTENTE.getCodigo())) {
				addMensagemErro("Esta matrícula ja foi cancelada");
				return;
			}
			mat.setStatus(StatusAluno.DESISTENTE.getCodigo());
			mat.setMotivoDesistencia(contatoPedagogico);
			
			mat.getPk().getTurma().setNumeroVagasDisp(mat.getPk().getTurma().getNumeroVagasDisp() + 1);

			new ControladorAluno().salvarMatricula(mat);

			aluno.removerMatricula(mat);
			if(aluno.getAlunoTurma().isEmpty()){
				aluno.setTurmaDefinir(true);
			}
			salvar();
			
			//mudaTurmasCurso();
			turmaCursoSelecionado = new Turma();
			turmaAlunoSelecionado = new Turma();
			contatoPedagogico = null;
			log.info("Matrícula cancelada com sucesso!");
		} catch (Exception e) {
			addMensagemErro(e);
			log.error("Erro ao cancelar matrícula de aluno: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void zerarTurmasCurso() {
		if (turmasCursoSelecionado != null)
			turmasCursoSelecionado.clear();
	}
	
	
	public void goMatricula() {
		setAjaxStatus(false);
		desativaBotoes();
		FacesUtils.redirect("/aibetesda/manutencao/matricula");
	}
	
	public void goDesistencia() {
		setAjaxStatus(false);
		desativaBotoes();
		FacesUtils.redirect("/aibetesda/aluno/desistencia");
	}
	
	public void goTransferenciaAluno() {
		setAjaxStatus(false);
		desativaBotoes();
		FacesUtils.redirect("/aibetesda/aluno/transferencia");
	}
	
	public void voltarCadastroAluno() {
		FacesUtils.redirect("/aibetesda/cadastro/aluno");
	}
	
	public void salvarContato() {
		try {
			if (alunoContato == null) {
				addMensagemErro("Selecione um aluno");
				return;
			}
			ControladorAluno ca = new ControladorAluno();
			Aluno addContato = ca.getById(alunoContato.getId());

			ContatoPedagogico cp = new ContatoPedagogico();
			cp.setConteudo(contatoPedagogico);
			cp.setPedagogico(addContato.getPedagogico());

			addContato.addContatoPedagogico(cp);

			ca.save(addContato);

			contatoPedagogico = new String();
			addContato = new Aluno();

			addMensagem("Contato adicionado com sucesso!");
		} catch (Exception e) {
			addMensagemErro("Erro ao salvar contato!");
			log.error("Erro ao salvar contato pedagogico: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void salvarAtraso() {
		try {
			if (alunoContato == null) {
				addMensagemErro("Selecione um aluno");
				return;
			}
			
			ControladorAluno ca = new ControladorAluno();
			Aluno addContato = ca.getById(alunoContato.getId());

			LancamentoAtraso la = new LancamentoAtraso();
			la.setConteudo(contatoPedagogico);
			la.setHorarioEntrada(horarioEntrada);
			la.setPedagogico(addContato.getPedagogico());

			addContato.addLancamentoAtraso(la);

			ca.save(addContato);

			contatoPedagogico = new String();
			horarioEntrada = new String();
			addContato = new Aluno();

			addMensagem("Atraso adicionado com sucesso!");
		} catch (Exception e) {
			addMensagemErro("Erro ao salvar Atraso de aluno");
			log.error("Erro ao salvar atraso de aluno: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
/*
 * RELATORIOS REFERENTE A ALUNOS
 */
	
	public void fichaMatriculaAluno() {
		
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("aluno", alunoFicha.getId());

		try {
			RelatorioUtils.gerarRelatorio("Matricula "+alunoFicha.getNome(),
					RelatorioUtils.FICHAALUNO, params);
			log.info("Sucesso na geração da Ficha de Matricula");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao gerar ficha de matricula de aluno: "+e.getMessage());
		}
		alunoFicha = null;
	}
	
	public void relatorioAlunosMatriculados() {
		
		if(dtInicial == null || dtFinal == null){
			addMensagemErro("Deve ser informada as datas de inicio e fim!");
			return;
		}
		
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("dtInicial", dtInicial);
		params.put("dtFinal", dtFinal);		
		
		try {
			RelatorioUtils.gerarRelatorio("Alunos Matriculados",
					RelatorioUtils.ALUNOSMATRICULADOS, params);
			log.info("Sucesso na geração do relatorio de alunos matriculados");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Erro ao gerar relatorio de alunos matriculados: "+e.getMessage());
		}
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Aluno getAlunoConsulta() {
		return alunoConsulta;
	}

	public void setAlunoConsulta(Aluno alunoConsulta) {
		this.alunoConsulta = alunoConsulta;
	}

	public List<Aluno> getTodosAlunos() {
		return todosAlunos;
	}

	public void setTodosAlunos(List<Aluno> todosAlunos) {
		this.todosAlunos = todosAlunos;
	}
	
	public Interessado getInteressadoCarregar() {
		return interessadoCarregar;
	}

	public void setInteressadoCarregar(Interessado interessadoCarregar) {
		this.interessadoCarregar = interessadoCarregar;
	}

	public boolean isLiberarPainel() {
		return liberarPainel;
	}

	public void setLiberarPainel(boolean liberarPainel) {
		this.liberarPainel = liberarPainel;
	}

	public Turma getTurmaCursoSelecionado() {
		return turmaCursoSelecionado;
	}

	public void setTurmaCursoSelecionado(Turma turmaCursoSelecionado) {
		this.turmaCursoSelecionado = turmaCursoSelecionado;
	}

	public Turma getTurmaAlunoSelecionado() {
		return turmaAlunoSelecionado;
	}

	public void setTurmaAlunoSelecionado(Turma turmaAlunoSelecionado) {
		this.turmaAlunoSelecionado = turmaAlunoSelecionado;
	}

	public List<Turma> getTurmasAlunoSelecionado() {
		return turmasAlunoSelecionado;
	}

	public void setTurmasAlunoSelecionado(List<Turma> turmasAlunoSelecionado) {
		this.turmasAlunoSelecionado = turmasAlunoSelecionado;
	}

	public List<Turma> getTurmasCursoSelecionado() {
		return turmasCursoSelecionado;
	}

	public void setTurmasCursoSelecionado(List<Turma> turmasCursoSelecionado) {
		this.turmasCursoSelecionado = turmasCursoSelecionado;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getStatusMatricula() {
		return statusMatricula;
	}

	public void setStatusMatricula(String statusMatricula) {
		this.statusMatricula = statusMatricula;
	}

	public Aluno getAlunoContato() {
		return alunoContato;
	}

	public void setAlunoContato(Aluno alunoContato) {
		this.alunoContato = alunoContato;
	}

	public String getContatoPedagogico() {
		return contatoPedagogico;
	}

	public void setContatoPedagogico(String contatoPedagogico) {
		this.contatoPedagogico = contatoPedagogico;
	}

	public String getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(String horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public Aluno getAlunoFicha() {
		return alunoFicha;
	}

	public void setAlunoFicha(Aluno alunoFicha) {
		this.alunoFicha = alunoFicha;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}
	
	public DoencaAlergia getDoencaAlergia() {
		return doencaAlergia;
	}

	public void setDoencaAlergia(DoencaAlergia doencaAlergia) {
		this.doencaAlergia = doencaAlergia;
	}

	public DoencaAlergia getDoencaAlergiaSelecionada() {
		return doencaAlergiaSelecionada;
	}

	public void setDoencaAlergiaSelecionada(DoencaAlergia doencaAlergiaSelecionada) {
		this.doencaAlergiaSelecionada = doencaAlergiaSelecionada;
	}
		
	
}
