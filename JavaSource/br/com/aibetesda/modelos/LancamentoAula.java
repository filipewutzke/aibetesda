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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.aibetesda.annotations.NaoNulo;

@SuppressWarnings("serial")
@Entity
public class LancamentoAula implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NaoNulo(mensagem="Selecione uma turma")
	private Turma turma;
	
	@Temporal(TemporalType.DATE)
	private Date data = new Date();
	
	@ManyToOne
	@NaoNulo(mensagem="Selecione um professor")
	private Funcionario professor;
	
	@Column(nullable=false, length=20)
	private String tipoAula;
	
	
	@OneToMany(mappedBy="lancamento", cascade={CascadeType.ALL, CascadeType.PERSIST})
	private List<AlunoPresenca> alunos = new ArrayList<AlunoPresenca>();
	
	@Lob
	private String conteudo = new String();
	
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Funcionario getProfessor() {
		return professor;
	}

	public void setProfessor(Funcionario professor) {
		this.professor = professor;
	}

	public String getTipoAula() {
		return tipoAula;
	}

	public void setTipoAula(String tipoAula) {
		this.tipoAula = tipoAula;
	}

	public List<AlunoPresenca> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<AlunoPresenca> alunos) {
		this.alunos = alunos;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
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
		LancamentoAula other = (LancamentoAula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return turma +": "+alunos;
	}
}
