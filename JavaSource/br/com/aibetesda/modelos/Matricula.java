package br.com.aibetesda.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Matricula implements Serializable{

	@EmbeddedId
	private AlunoTurmaPk pk = new AlunoTurmaPk();
	
	@Column(updatable=false)
	private Long matricula;
	
	@Column
	private Integer status; 
	
	@Column(nullable=true)
	private String motivoDesistencia;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataMatricula = new Date();
	
	@Transient
	private String nomeAluno;

	public Matricula(){}
	
	public Matricula(AlunoTurmaPk pk){
		this.pk = pk;
	}
	
	public AlunoTurmaPk getPk() {
		return pk;
	}

	public void setPk(AlunoTurmaPk pk) {
		this.pk = pk;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
		System.out.println("Matricula: "+matricula);
	}

	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMotivoDesistencia() {
		return motivoDesistencia;
	}
	
	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public void setMotivoDesistencia(String motivoDesistencia) {
		this.motivoDesistencia = motivoDesistencia;
	}

	public String getNomeAluno() {
		nomeAluno = pk.getAluno().getNome();
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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
		Matricula other = (Matricula) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	
	
}
