package br.com.aibetesda.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import br.com.aibetesda.annotations.NaoVazio;

@SuppressWarnings("serial")
@Entity
public class Aluno implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	@NaoVazio(mensagem="Nome não pode ser vazio")
	private String nome;

	@Temporal(TemporalType.DATE)
	@Past(message="Data de nascimento não pode ser maior que a data atual!")
	private Date dataNascimento;
	
	@Column(length=14,nullable=false)
	private String rg;
	
	@Column(length=9, nullable=false)
	private String sexo;
	
	@Column
	private String cor;
	
	@Embedded
	private Endereco endereco = new Endereco();
	
	@Column(length = 14, nullable=true)
	private String telefoneCasa;
	
	@Column(length = 14, nullable=true)
	private String telefoneCelular;
	
	@Column(length = 14, nullable=true)
	private String telefoneRecado;
	
	@Column(length = 30, nullable=true)
	private String nomeRecado;
	
	@Column(nullable=false)
	private Double rendaFamiliar;
	
	@ManyToOne
	private Pais pai;
	
	@ManyToOne
	private Pais mae;
	
	@ManyToOne
	private Pais reponsavel;
	
	@Column
	private boolean outraEscola = false;
	
	@Column
	private String qualEscola;
	
	@Column
	private String motivoSaida;
	
	@Column
	private boolean opPlanoSaude = false;
	
	@Column
	private String planoSaude;
	
	@ManyToMany(targetEntity=DoencaAlergia.class,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name="aluno_doencaAlergia",
		joinColumns={@JoinColumn(referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
	private List<DoencaAlergia> doencaAlergias = new ArrayList<DoencaAlergia>();
	
	@Column
	private boolean irmaoMesmaEscola = false;
	
	@Column
	private String nomeIrmaos;
	
	@Column (nullable=false)
	private String quemTrazBusca;
	
	@Column
	private String observacao;
	
	@ManyToOne
	private Interessado interessado;
	
	@ManyToOne
	private Usuario usuarioAlteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAlteracao = new Date();
	
	@ManyToOne
	private Bairro bairro;
	
	@ManyToOne
	private Usuario usuarioRegistro; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtRegistro;
	
	@Column
	private boolean turmaDefinir = true;
	
	@Column
	private boolean ativo = true;
	
	@OneToMany(mappedBy="pk.aluno", cascade={CascadeType.ALL, CascadeType.PERSIST}, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Matricula> alunoTurma = new ArrayList<Matricula>();
	
	@ManyToOne(cascade={CascadeType.ALL, CascadeType.PERSIST})
	private Pedagogico pedagogico = new Pedagogico();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefoneCasa() {
		return telefoneCasa;
	}

	public void setTelefoneCasa(String telefoneCasa) {
		this.telefoneCasa = telefoneCasa;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneRecado() {
		return telefoneRecado;
	}

	public void setTelefoneRecado(String telefoneRecado) {
		this.telefoneRecado = telefoneRecado;
	}

	public boolean isOutraEscola() {
		return outraEscola;
	}

	public void setOutraEscola(boolean outraEscola) {
		this.outraEscola = outraEscola;
	}

	public String getQualEscola() {
		return qualEscola;
	}

	public void setQualEscola(String qualEscola) {
		this.qualEscola = qualEscola;
	}

	public String getMotivoSaida() {
		return motivoSaida;
	}

	public void setMotivoSaida(String motivoSaida) {
		this.motivoSaida = motivoSaida;
	}

	public boolean isOpPlanoSaude() {
		return opPlanoSaude;
	}

	public void setOpPlanoSaude(boolean opPlanoSaude) {
		this.opPlanoSaude = opPlanoSaude;
	}

	public String getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(String planoSaude) {
		this.planoSaude = planoSaude;
	}
	
	public List<DoencaAlergia> getDoencaAlergias() {
		return doencaAlergias;
	}

	public void setDoencaAlergias(List<DoencaAlergia> doencaAlergias) {
		this.doencaAlergias = doencaAlergias;
	}

	public boolean isIrmaoMesmaEscola() {
		return irmaoMesmaEscola;
	}

	public void setIrmaoMesmaEscola(boolean irmaoMesmaEscola) {
		this.irmaoMesmaEscola = irmaoMesmaEscola;
	}

	public String getNomeIrmaos() {
		return nomeIrmaos;
	}

	public void setNomeIrmaos(String nomeIrmaos) {
		this.nomeIrmaos = nomeIrmaos;
	}

	public String getQuemTrazBusca() {
		return quemTrazBusca;
	}

	public void setQuemTrazBusca(String quemTrazBusca) {
		this.quemTrazBusca = quemTrazBusca;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		
	public Double getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(Double rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}
	
	
	public Interessado getInteressado() {
		return interessado;
	}

	public void setInteressado(Interessado interessado) {
		this.interessado = interessado;
	}

	public Pais getPai() {
		return pai;
	}

	public void setPai(Pais pai) {
		this.pai = pai;
	}

	public Pais getMae() {
		return mae;
	}

	public void setMae(Pais mae) {
		this.mae = mae;
	}

	public Pais getReponsavel() {
		return reponsavel;
	}

	public void setReponsavel(Pais reponsavel) {
		this.reponsavel = reponsavel;
	}

	public boolean isTurmaDefinir() {
		return turmaDefinir;
	}

	public void setTurmaDefinir(boolean turmaDefinir) {
		this.turmaDefinir = turmaDefinir;
	}

	public String getNomeRecado() {
		return nomeRecado;
	}

	public void setNomeRecado(String nomeRecado) {
		this.nomeRecado = nomeRecado;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public List<Matricula> getAlunoTurma() {
		return alunoTurma;
	}

	public void setAlunoTurma(List<Matricula> alunoTurma) {
		this.alunoTurma = alunoTurma;
	}

	public Pedagogico getPedagogico() {
		return pedagogico;
	}

	public void setPedagogico(Pedagogico pedagogico) {
		this.pedagogico = pedagogico;
	}
		
	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	
	public boolean isSize() {
		if(id == null)
			return false;
		
		if(alunoTurma != null && alunoTurma.size() > 0)
			return true;
		return false;		
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	public void adicionarMatricula(Matricula mat) {
		this.alunoTurma.add(mat);
	}
	
	public boolean removerMatricula(Matricula mat) {
		return this.alunoTurma.remove(mat);
	}
	
	public void addContatoPedagogico(ContatoPedagogico contato) {
		getPedagogico().addContato(contato);
	}
	
	public void removerContatoPedagogico(ContatoPedagogico contato) {
		getPedagogico().removerContato(contato);
	}
	
	public void addLancamentoAtraso(LancamentoAtraso atraso) {
		getPedagogico().addAtraso(atraso);
	}
	
	public void removerLancamentoAtraso(LancamentoAtraso atraso) {
		getPedagogico().removerAtraso(atraso);
	}
	
	public void addDoencaAlergia(DoencaAlergia doencaAlergia) {
		doencaAlergias.add(doencaAlergia);
	}

	public void removerDoencaAlergia(DoencaAlergia doencaAlergiaSelecionado) {
		doencaAlergias.remove(doencaAlergiaSelecionado);
	}
	
}
