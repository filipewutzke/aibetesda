package br.com.aibetesda.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.aibetesda.annotations.NaoVazio;

@Entity
public class PlanejamentoPedagogicoAnual implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false, length = 4)
	@NaoVazio(mensagem="Planejamento ano, não pode ser vazio!")
	private String ano;
	
	@Column(nullable = true)
	private String observacoes;
	
	@Temporal(TemporalType.DATE)
	private Date dataInicioAnoLetivo;
	
	@Temporal(TemporalType.DATE)
	private Date dataFimAnoLetivo;
	
	@Temporal(TemporalType.DATE)
	private Date dataInicioFerias;
	
	@Temporal(TemporalType.DATE)
	private Date dataFimFerias;
	
	@OneToMany (mappedBy="planejamento", cascade={CascadeType.ALL, CascadeType.REMOVE}, orphanRemoval=true)
	private List<PlanejamentoAnualDatasReuniao> datasReunioes = new ArrayList<PlanejamentoAnualDatasReuniao>();
	
	@ManyToOne
	private Usuario usuarioAlteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAlteracao = new Date();
	
	@ManyToOne
	private Usuario usuarioRegistro; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtRegistro;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Date getDataInicioAnoLetivo() {
		return dataInicioAnoLetivo;
	}

	public void setDataInicioAnoLetivo(Date dataInicioAnoLetivo) {
		this.dataInicioAnoLetivo = dataInicioAnoLetivo;
	}

	public Date getDataFimAnoLetivo() {
		return dataFimAnoLetivo;
	}

	public void setDataFimAnoLetivo(Date dataFimAnoLetivo) {
		this.dataFimAnoLetivo = dataFimAnoLetivo;
	}

	public Date getDataInicioFerias() {
		return dataInicioFerias;
	}

	public void setDataInicioFerias(Date dataInicioFerias) {
		this.dataInicioFerias = dataInicioFerias;
	}

	public Date getDataFimFerias() {
		return dataFimFerias;
	}

	public void setDataFimFerias(Date dataFimFerias) {
		this.dataFimFerias = dataFimFerias;
	}

	public List<PlanejamentoAnualDatasReuniao> getDatasReunioes() {
		return datasReunioes;
	}

	public void setDatasReunioes(List<PlanejamentoAnualDatasReuniao> datasReunioes) {
		this.datasReunioes = datasReunioes;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public Usuario getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(Usuario usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}
	
	public void addRetorno(PlanejamentoAnualDatasReuniao planejamentoAnualDatasReuniao) {
		this.datasReunioes.add(planejamentoAnualDatasReuniao);
	}
	
	public void removerDataReuniao (PlanejamentoAnualDatasReuniao planejamentoAnualDatasReuniao){
		this.datasReunioes.remove(planejamentoAnualDatasReuniao);
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
		PlanejamentoPedagogicoAnual other = (PlanejamentoPedagogicoAnual) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ano;
	}	
	
}
