package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.PlanejamentoAnualDatasReuniao;
import br.com.aibetesda.modelos.PlanejamentoPedagogicoAnual;

public interface IPlanejamentoPedagogicoAnualDAO extends DAO<PlanejamentoPedagogicoAnual>{
	
	public PlanejamentoPedagogicoAnual getPlanejamentoPorAno(String ano);
	public List<PlanejamentoAnualDatasReuniao> getAvisoDatasReunioes();
	public PlanejamentoPedagogicoAnual getReuniaoPorAno(Long id);
	
}
