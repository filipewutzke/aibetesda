package br.com.aibetesda.manageds;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.aibetesda.controladores.ControladorPlanejamentoPedagogicoAnual;
import br.com.aibetesda.modelos.PlanejamentoAnualDatasReuniao;
import br.com.aibetesda.modelos.PlanejamentoPedagogicoAnual;
import br.com.aibetesda.util.UserUtils;

@ManagedBean(name="managedPlanejamentoPedagogicoAnual")
@SessionScoped
public class ManagedPlanejamentoPedagogicoAnual extends ManagedCadastroConsulta {
	
	private static final Logger log = LoggerFactory.getLogger(ManagedPlanejamentoPedagogicoAnual.class);
	
	private PlanejamentoPedagogicoAnual planejamentoAnual = new PlanejamentoPedagogicoAnual();
	private List<PlanejamentoPedagogicoAnual> planejamentosAnual = new ArrayList<PlanejamentoPedagogicoAnual>();
	private PlanejamentoPedagogicoAnual planejamentoAnualSelecionado;
	
	private PlanejamentoAnualDatasReuniao planejamentoAnualDatasReuniao = new PlanejamentoAnualDatasReuniao();
	private PlanejamentoAnualDatasReuniao planejamentoAnualDatasSelecionada = new PlanejamentoAnualDatasReuniao();
	
	private String idPage;
	
	private List<PlanejamentoAnualDatasReuniao> avisoDatasReunioes = new ArrayList<PlanejamentoAnualDatasReuniao>();
	
	
	public ManagedPlanejamentoPedagogicoAnual(){
		novo();
		loadAvisoDatasReunioes();
	}
	
	@Override
	public void novo() {
		planejamentoAnual = new PlanejamentoPedagogicoAnual();
		this.planejamentoAnualDatasReuniao = new PlanejamentoAnualDatasReuniao();
	}

	@Override
	public void salvar() {
		try{	
			if(!objetoValido(planejamentoAnual))
				return;
		/*	if(new ControladorPlanejamentoPedagogicoAnual().getPlanejamentoPorAno(planejamentoAnual.getAno())!=null){
				addMensagemErro("Este Ano já foi lançado!");
				return;
			}  */
			
			if(validarDatas() == false){
				System.out.println("Entro nesa bosta aki kra!!!");
				return;
			}
			
			ControladorPlanejamentoPedagogicoAnual controlador = new ControladorPlanejamentoPedagogicoAnual();
			controlador.save(planejamentoAnual);
			addMensagem("Planejamento Anual salvo com sucesso!");
			novo();
			loadAll();
			log.info("Planejamento Pedagogico Anual foi salvo!");     
		} catch (Exception e){
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao salvar Planejamento Anual: "+e.getMessage());
			log.info("Erro ao salvar o planejamento anual: "+e.getMessage());
		}
	}

	@Override
	public void excluir() {
		try{
			if(planejamentoAnual.getId() == null){
				addMensagemErro("Planejamento Anual vazio");
				return;
			}
			ControladorPlanejamentoPedagogicoAnual controlador = new ControladorPlanejamentoPedagogicoAnual();
			controlador.delete(planejamentoAnual);
			addMensagem("Planejamento removido com sucesso");
			log.info(UserUtils.getUsuarioLogado()+" excluiu um planejamento anual: "+planejamentoAnual);
			novo();
			loadAll();
			
		} catch (Exception e){
			e.printStackTrace();
			addMensagem(e.getMessage(), "Erro ao excluir planejamento!");
			log.error("Erro ao excluir planejamento anual: "+e.getMessage());
		}
	}

	@Override
	public void selecionar() {
		if(planejamentoAnualSelecionado != null && planejamentoAnualSelecionado.getId() != null)
			setPlanejamentoAnual(new ControladorPlanejamentoPedagogicoAnual().
					getById(planejamentoAnualSelecionado.getId()));
	}

	@Override
	public void loadAll() {
		novo();
		ControladorPlanejamentoPedagogicoAnual controlador = new ControladorPlanejamentoPedagogicoAnual();
		planejamentosAnual = controlador.loadAll();
		avisoDatasReunioes = controlador.getAvisoDatasReunioes();
	}
	
	public void adicionarDataReuniao(){
		try{
			if(planejamentoAnualDatasReuniao.getDataReuniao() == null || 
					planejamentoAnualDatasReuniao.getObservacoes().equals("") || 
					planejamentoAnualDatasReuniao.getNomeAtividade().equals("")){
				addMensagemErro("Deve informar uma data de reunião, observação e o nome da Atividade!");
				return;
			}
			
			planejamentoAnualDatasReuniao.setPlanejamento(planejamentoAnual);
			planejamentoAnual.addRetorno(planejamentoAnualDatasReuniao);
			
			new ControladorPlanejamentoPedagogicoAnual().save(planejamentoAnual);
			planejamentoAnualDatasReuniao = new PlanejamentoAnualDatasReuniao();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void removerDataReuniao(){
		if(planejamentoAnualDatasSelecionada == null){
			addMensagemErro("Deve selecionar uma data de reunião!");
			return;
		}
		
		planejamentoAnual.removerDataReuniao(planejamentoAnualDatasSelecionada);
		planejamentoAnualDatasSelecionada = new PlanejamentoAnualDatasReuniao();
	}
	
	public boolean validarDatas() throws Exception{
		System.out.println("public void validarDatas()");
		boolean valida;
		int ano = Integer.valueOf(planejamentoAnual.getAno());
		int anoData;
		Calendar calendar = Calendar.getInstance();  
        
		calendar.setTime(planejamentoAnual.getDataInicioAnoLetivo());  
        	anoData = calendar.get(Calendar.YEAR);
        	System.out.println("ano data = "+anoData);
        	System.out.println("ano: "+ano);
		
		if(anoData != ano){
			System.out.println("if(anoData != ano)");
			addMensagemErro("Erro: a data de Início do ano letivo não confere com a data do planejamento");
			valida = false;
		} else {
			if(planejamentoAnual.getDataFimAnoLetivo().before(planejamentoAnual.getDataInicioAnoLetivo())
				|| planejamentoAnual.getDataFimAnoLetivo().equals(planejamentoAnual.getDataInicioAnoLetivo())){
				System.out.println("dataInicio > dataFim");
				addMensagemErro("Data fim do ano letivo é anterior que data de início!");
				valida = false;
			} else {
				if(planejamentoAnual.getDataInicioFerias().before(planejamentoAnual.getDataInicioAnoLetivo())){
					System.out.println("dataInicio > dataInicioFerias");
					addMensagemErro("Data data de início das férias é anterior a data de início do ano letivo!");
					valida = false;
				} else {
					if(planejamentoAnual.getDataFimAnoLetivo().before(planejamentoAnual.getDataInicioFerias())){
						System.out.println("dataInicio > dataFim");
						addMensagemErro("Data inicio de férias é posterior a data de fim do ano letivo!");
						valida = false;
					} else { 
						if(planejamentoAnual.getDataInicioFerias().after(planejamentoAnual.getDataFimFerias())){
							System.out.println("dataInicioFerias > dataFimFerias");
							addMensagemErro("Data fim das férias é anterior que data de início!");
							valida = false;
						} else {
							if(planejamentoAnual.getDataFimFerias().after(planejamentoAnual.getDataFimAnoLetivo())){
								System.out.println("dataFimFerias > dataFim");
								addMensagemErro("Data fim de férias é posterior a data de fim do ano letivo!");
								valida = false;
							} else { 
								if(planejamentoAnual.getDataFimFerias().before(planejamentoAnual.getDataInicioFerias())){
									System.out.println("dataFimFerias < dataInicioFerias");
									addMensagemErro("Data fim de férias é anterior a data de início de férias!");
									valida = false;
								} else {
									System.out.println("return maroto!");
									valida = true;
								}
							}
						}
					}	
				}
			}
		}
		return valida;
	}
	
	
	public String loadAvisoDatasReunioesId(){
		if(idPage != null && !("".equalsIgnoreCase(idPage))) {
			planejamentoAnual = new ControladorPlanejamentoPedagogicoAnual().getReuniaoPorAno(Long.parseLong(idPage));
		}
		return null;
	}

	private void loadAvisoDatasReunioes(){
		avisoDatasReunioes = new ControladorPlanejamentoPedagogicoAnual().getAvisoDatasReunioes();
	}

	public PlanejamentoPedagogicoAnual getPlanejamentoAnual() {
		return planejamentoAnual;
	}

	public void setPlanejamentoAnual(PlanejamentoPedagogicoAnual planejamentoAnual) {
		this.planejamentoAnual = planejamentoAnual;
	}

	public List<PlanejamentoPedagogicoAnual> getPlanejamentosAnual() {
		return planejamentosAnual;
	}

	public void setPlanejamentosAnual(
			List<PlanejamentoPedagogicoAnual> planejamentosAnual) {
		this.planejamentosAnual = planejamentosAnual;
	}

	public PlanejamentoPedagogicoAnual getPlanejamentoAnualSelecionado() {
		return planejamentoAnualSelecionado;
	}

	public void setPlanejamentoAnualSelecionado(
			PlanejamentoPedagogicoAnual planejamentoAnualSelecionado) {
		this.planejamentoAnualSelecionado = planejamentoAnualSelecionado;
	}

	public PlanejamentoAnualDatasReuniao getPlanejamentoAnualDatasReuniao() {
		return planejamentoAnualDatasReuniao;
	}

	public void setPlanejamentoAnualDatasReuniao(
			PlanejamentoAnualDatasReuniao planejamentoAnualDatasReuniao) {
		this.planejamentoAnualDatasReuniao = planejamentoAnualDatasReuniao;
	}

	public PlanejamentoAnualDatasReuniao getPlanejamentoAnualDatasSelecionada() {
		return planejamentoAnualDatasSelecionada;
	}

	public void setPlanejamentoAnualDatasSelecionada(
			PlanejamentoAnualDatasReuniao planejamentoAnualDatasSelecionada) {
		this.planejamentoAnualDatasSelecionada = planejamentoAnualDatasSelecionada;
	}

	public String getIdPage() {
		return idPage;
	}

	public void setIdPage(String idPage) {
		this.idPage = idPage;
	}

	public List<PlanejamentoAnualDatasReuniao> getAvisoDatasReunioes() {
		return avisoDatasReunioes;
	}

	public void setAvisoDatasReunioes(
			List<PlanejamentoAnualDatasReuniao> avisoDatasReunioes) {
		this.avisoDatasReunioes = avisoDatasReunioes;
	}
	
	
}
