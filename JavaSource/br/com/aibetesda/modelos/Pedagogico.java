package br.com.aibetesda.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.com.aibetesda.controladores.ControladorAluno;

@SuppressWarnings("serial")
@Entity
public class Pedagogico implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="pedagogico",
			cascade={CascadeType.ALL, CascadeType.PERSIST},orphanRemoval=true)
	private List<ContatoPedagogico> contatos = new ArrayList<ContatoPedagogico>();

	@OneToMany(mappedBy="pedagogico",
			cascade={CascadeType.ALL, CascadeType.PERSIST},orphanRemoval=true)
	private List<LancamentoAtraso> atrasos = new ArrayList<LancamentoAtraso>();
	
	@Transient
	private Aluno aluno;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ContatoPedagogico> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoPedagogico> contatos) {
		this.contatos = contatos;
	}
	
	public void addContato(ContatoPedagogico contato) {
		this.contatos.add(contato);
	}
	
	public void removerContato(ContatoPedagogico contato) {
		this.contatos.remove(contato);
	}
	
	public void addAtraso(LancamentoAtraso atraso) {
		this.atrasos.add(atraso);
	}
	
	public void removerAtraso(LancamentoAtraso atraso) {
		this.atrasos.remove(atraso);
	}
	
	public List<LancamentoAtraso> getAtrasos() {
		return atrasos;
	}

	public void setAtrasos(List<LancamentoAtraso> atrasos) {
		this.atrasos = atrasos;
	}

	public Aluno getAluno() {
		this.aluno = new ControladorAluno().getAlunoPedagogico(this);
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
		Pedagogico other = (Pedagogico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
