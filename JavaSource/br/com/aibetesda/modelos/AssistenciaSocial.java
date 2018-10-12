package br.com.aibetesda.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class AssistenciaSocial implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Interessado interessado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAssistencia;
	
	@Column
	private boolean violencia = false;
	
	@Lob
	private String violenciaComplemento;
	
	@Column
	private boolean fragilidadeVinculo = false;
	
	@Lob
	private String fragilidadeVinculoComplemento;
	
	@Column
	private boolean vulnerabilidade = false;
	
	@Lob
	private String vulnerabilidadeComplemento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Interessado getInteressado() {
		return interessado;
	}

	public void setInteressado(Interessado interessado) {
		this.interessado = interessado;
	}

	public Date getDataAssistencia() {
		return dataAssistencia;
	}

	public void setDataAssistencia(Date dataAssistencia) {
		this.dataAssistencia = dataAssistencia;
	}

	public boolean isViolencia() {
		return violencia;
	}

	public void setViolencia(boolean violencia) {
		this.violencia = violencia;
	}

	public String getViolenciaComplemento() {
		return violenciaComplemento;
	}

	public void setViolenciaComplemento(String violenciaComplemento) {
		this.violenciaComplemento = violenciaComplemento;
	}

	public boolean isFragilidadeVinculo() {
		return fragilidadeVinculo;
	}

	public void setFragilidadeVinculo(boolean fragilidadeVinculo) {
		this.fragilidadeVinculo = fragilidadeVinculo;
	}

	public String getFragilidadeVinculoComplemento() {
		return fragilidadeVinculoComplemento;
	}

	public void setFragilidadeVinculoComplemento(
			String fragilidadeVinculoComplemento) {
		this.fragilidadeVinculoComplemento = fragilidadeVinculoComplemento;
	}

	public boolean isVulnerabilidade() {
		return vulnerabilidade;
	}

	public void setVulnerabilidade(boolean vulnerabilidade) {
		this.vulnerabilidade = vulnerabilidade;
	}

	public String getVulnerabilidadeComplemento() {
		return vulnerabilidadeComplemento;
	}

	public void setVulnerabilidadeComplemento(String vulnerabilidadeComplemento) {
		this.vulnerabilidadeComplemento = vulnerabilidadeComplemento;
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
		AssistenciaSocial other = (AssistenciaSocial) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
