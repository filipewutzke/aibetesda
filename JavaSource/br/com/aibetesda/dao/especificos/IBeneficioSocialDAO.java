package br.com.aibetesda.dao.especificos;

import java.util.List;

import br.com.aibetesda.dao.DAO;
import br.com.aibetesda.modelos.BeneficioSocial;

public interface IBeneficioSocialDAO extends DAO<BeneficioSocial> {
	
	public BeneficioSocial getBeneficioPorNome(String nome);
	public List<BeneficioSocial> getBeneficioQuery(String nome);
	
}
