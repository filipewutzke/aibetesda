package br.com.aibetesda.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.com.aibetesda.annotations.NaoNulo;

@SuppressWarnings("serial")
@Entity(name="turma")
public class Turma implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private Integer numeroVagas;
	
	@Column
	private Integer numeroVagasDisp;
	
	@ManyToOne
	@NaoNulo(mensagem="Toda turma deve ter um professor")
	private Funcionario funcionario;
	
	@ManyToOne
	@NaoNulo(mensagem="Toda turma deve ter um curso")
	private Curso curso;
	
	@ManyToOne
	@NaoNulo(mensagem="Selecione alguma sala")
	private SalaAula sala;
	
	@Column
	private String periodo;
	
	@Column(nullable=false)
	private String horarioEntrada;
	
	@Column(nullable=false)
	private String horarioSaida;
	
	@OneToMany(mappedBy="pk.turma")
	private List<Matricula> alunoTurma = new ArrayList<Matricula>();
	
	@ManyToOne(cascade={CascadeType.ALL, CascadeType.PERSIST})
	private PlanejamentoTurma planejamentoTurma = new PlanejamentoTurma();
	
	@Transient
	private String desc;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroVagas() {
		return numeroVagas;
	}

	public void setNumeroVagas(Integer numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

	public Integer getNumeroVagasDisp() {
		return numeroVagasDisp;
	}

	public void setNumeroVagasDisp(Integer numeroVagasDisp) {
		this.numeroVagasDisp = numeroVagasDisp;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(String horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public String getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(String horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	public SalaAula getSala() {
		return sala;
	}

	public void setSala(SalaAula sala) {
		this.sala = sala;
	}

	public List<Matricula> getAlunoTurma() {
		return alunoTurma;
	}

	public void setAlunoTurma(List<Matricula> alunoTurma) {
		this.alunoTurma = alunoTurma;
	}

	public PlanejamentoTurma getPlanejamentoTurma() {
		return planejamentoTurma;
	}

	public void setPlanejamentoTurma(PlanejamentoTurma planejamentoTurma) {
		this.planejamentoTurma = planejamentoTurma;
	}
	
	public String getDesc() {
		desc = id + " - " + curso + " - " + sala;
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
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
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return id+ " - "+curso+" - "+sala;
	}

}
