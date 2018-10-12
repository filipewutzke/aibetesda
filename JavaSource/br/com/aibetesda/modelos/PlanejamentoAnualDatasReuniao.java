package br.com.aibetesda.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PlanejamentoAnualDatasReuniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.DATE) 
	private Date dataReuniao;
	
	@Column(nullable=true)
	private String observacoes;
	
	@Column
	private String nomeAtividade;
	
	@ManyToOne
	private PlanejamentoPedagogicoAnual planejamento;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataReuniao() {
		return dataReuniao;
	}

	public void setDataReuniao(Date dataReuniao) {
		this.dataReuniao = dataReuniao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public PlanejamentoPedagogicoAnual getPlanejamento() {
		return planejamento;
	}

	public void setPlanejamento(PlanejamentoPedagogicoAnual planejamento) {
		this.planejamento = planejamento;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanejamentoAnualDatasReuniao other = (PlanejamentoAnualDatasReuniao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
