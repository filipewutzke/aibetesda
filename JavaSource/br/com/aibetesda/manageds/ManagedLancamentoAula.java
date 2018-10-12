package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.aibetesda.controladores.ControladorLancamentoAula;
import br.com.aibetesda.controladores.ControladorTurma;
import br.com.aibetesda.modelos.AlunoPresenca;
import br.com.aibetesda.modelos.Funcionario;
import br.com.aibetesda.modelos.LancamentoAula;
import br.com.aibetesda.modelos.Matricula;
import br.com.aibetesda.modelos.Turma;

@ManagedBean(name="managedLancamentoAula")
@SessionScoped
public class ManagedLancamentoAula extends ManagedCadastroConsulta {

	private LancamentoAula lancamentoAula = new LancamentoAula();
	private LancamentoAula lancamentoAulaConsulta;
	
	private List<LancamentoAula> lancamentosData = new ArrayList<LancamentoAula>();
	private Date filtroData = new Date();
	private Date dataLancamentosFalta = new Date();
	private Date dataLancamentosFalta2 = new Date();
	
	private Date dtInicial;
	private Date dtFinal;
	private Funcionario profHoras;
	
	@Override
	public void novo() {
		lancamentoAula = new LancamentoAula();
		filtroData = new Date();
		lancamentosData = new ArrayList<LancamentoAula>();
	}

	@Override
	public void salvar() {
		try {
			if(!objetoValido(lancamentoAula)) return;
			
			if(existeLancamentoData() && lancamentoAula.getId() == null){
				addMensagemErro("Já existe um lançamento com essa data!");
				return;
			}
			
			new ControladorLancamentoAula().save(lancamentoAula);
			
			addMensagem("Diário de aula cadastrado!");
			novo();
		} catch (Exception e) {
			e.printStackTrace();
			addMensagemErro(e);
		}
	}

	@Override
	public void excluir() {
		addMensagemErro("Não pode excluir!");
	}

	@Override
	public void selecionar() {
		if(lancamentoAulaConsulta != null && lancamentoAulaConsulta.getId() != null)
			setLancamentoAula(new ControladorLancamentoAula().getById(lancamentoAulaConsulta.getId()));
	}

	@Override
	public void loadAll() {
		
	}
	
	
	//Metodos especificos
	
	public void carregarDadosTurma() {
		if(existeLancamentoData()){
			addMensagemErro("Já existe um lançamento com essa data!");
			return;
		}
		
		Turma t = new ControladorTurma().getById(lancamentoAula.getTurma().getId());
		lancamentoAula.setProfessor(t.getFuncionario());
		
		List<AlunoPresenca> alunos = new ArrayList<AlunoPresenca>();
		for(Matricula m : t.getAlunoTurma()){
			AlunoPresenca ap = new AlunoPresenca();
			ap.setAluno(m.getPk().getAluno());
			ap.setLancamento(lancamentoAula);
			
			alunos.add(ap);
		}
		lancamentoAula.setAlunos(alunos);
	}
	
	public boolean existeLancamentoData()
	{
		List<LancamentoAula> lancamento = 
			new ControladorLancamentoAula().getLancamentoDataTurma(lancamentoAula.getData(), lancamentoAula.getTurma());
		
		return lancamento.size() > 0;
	}
	
	
	public void loadLancamentoFiltroData(){
		if(filtroData == null){
			addMensagemErro("Selecione uma data!");
			return;
		}
		this.lancamentosData = new ControladorLancamentoAula().getLancamentosData(filtroData);
	}
	
	
	public void loadAlunosFaltaramData(){
		List<AlunoPresenca> lancamentos = new ControladorLancamentoAula().
			getAlunosFaltaData(dataLancamentosFalta, dataLancamentosFalta2);
		System.err.println("============= Alunos que faltaram ===============");
		System.out.println("Quantidade: "+lancamentos.size());
		for(AlunoPresenca la : lancamentos){
			System.out.println("=======================");
			System.out.println("Aluno: "+la.getAluno());
			System.out.println("Data: "+la.getLancamento().getData());
			System.out.println("Turma: "+la.getLancamento().getTurma());
		}
		System.out.println("=============");
	}
	
	
	public LancamentoAula getLancamentoAula() {
		return lancamentoAula;
	}
	
	public void setLancamentoAula(LancamentoAula lancamentoAula) {
		this.lancamentoAula = lancamentoAula;
	}
	
	public LancamentoAula getLancamentoAulaConsulta() {
		return lancamentoAulaConsulta;
	}
	
	public void setLancamentoAulaConsulta(LancamentoAula lancamentoAulaConsulta) {
		this.lancamentoAulaConsulta = lancamentoAulaConsulta;
	}
	
	public List<LancamentoAula> getLancamentosData() {
		return lancamentosData;
	}
	
	public void setLancamentosData(List<LancamentoAula> lancamentosData) {
		this.lancamentosData = lancamentosData;
	}
	
	public Date getFiltroData() {
		return filtroData;
	}
	
	public void setFiltroData(Date filtroData) {
		this.filtroData = filtroData;
	}
	
	public Date getDataLancamentosFalta() {
		return dataLancamentosFalta;
	}
	
	public void setDataLancamentosFalta(Date dataLancamentosFalta) {
		this.dataLancamentosFalta = dataLancamentosFalta;
	}

	public Date getDataLancamentosFalta2() {
		return dataLancamentosFalta2;
	}

	public void setDataLancamentosFalta2(Date dataLancamentosFalta2) {
		this.dataLancamentosFalta2 = dataLancamentosFalta2;
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

	public Funcionario getProfHoras() {
		return profHoras;
	}

	public void setProfHoras(Funcionario profHoras) {
		this.profHoras = profHoras;
	}
}
