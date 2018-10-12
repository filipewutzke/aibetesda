package br.com.aibetesda.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import br.com.aibetesda.annotations.NaoVazio;

@SuppressWarnings("serial")
@Entity
public class Interessado implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	@NaoVazio(mensagem="Nome não pode ser vazio")
	private String nome;

	@Temporal(TemporalType.DATE)
	@Past(message="Data de nascimento não pode ser maior que a data atual!")
	private Date dataNascimento;
	
	@Column
	private String sexo;
	
	@Embedded
	private Endereco endereco = new Endereco();
	
	@Column
	private Integer numResidentesCasa;
	
	@Column(length = 14, nullable=true)
	private String telefoneCasa;
	
	@Column(length = 14, nullable=true)
	private String telefoneCelular;
	
	@Column(length = 14, nullable=true)
	private String telefoneRecado;
	
	@ManyToOne
	private Pais pai;
	
	@ManyToOne
	private Pais mae;
	
	@ManyToMany(targetEntity=BeneficioSocial.class,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name="interessado_beneficiosocial",
		joinColumns={@JoinColumn(referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
	private List<BeneficioSocial> beneficiosSociais = new ArrayList<BeneficioSocial>();
	
	@Column
	private Double rendaFamiliar;
	
	@Column(nullable=true)
	private String outrosMoradoresCasa;
	
	@Column
	private Double valorPercapita;
	
	@Column
	private String tipoCasa;
	
	@Column
	private Double valorCasa;
	
	@Column
	private String meioTransporte;
	
	@ManyToMany(targetEntity=BemDuravel.class,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name="interessado_bemduravel",
		joinColumns={@JoinColumn(referencedColumnName="id")},
		inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
	private List<BemDuravel> bensDuraveis = new ArrayList<BemDuravel>();
	
	@ManyToOne
	private Bairro bairro;
	
	@ManyToOne
	private Usuario usuarioAlteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAlteracao = new Date();
	
	@ManyToOne
	private Usuario usuarioRegistro; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtRegistro;
	
	@Column
	private boolean ativo = true;
	
	@Column
	private boolean aptoMatricula = false;
	
	
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getNumResidentesCasa() {
		return numResidentesCasa;
	}

	public void setNumResidentesCasa(Integer numResidentesCasa) {
		this.numResidentesCasa = numResidentesCasa;
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

	public Double getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(Double rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	public String getOutrosMoradoresCasa() {
		return outrosMoradoresCasa;
	}

	public void setOutrosMoradoresCasa(String outrosMoradoresCasa) {
		this.outrosMoradoresCasa = outrosMoradoresCasa;
	}

	public Double getValorPercapita() {
		return valorPercapita;
	}

	public void setValorPercapita(Double valorPercapita) {
		this.valorPercapita = valorPercapita;
	}

	public String getTipoCasa() {
		return tipoCasa;
	}

	public void setTipoCasa(String tipoCasa) {
		this.tipoCasa = tipoCasa;
	}

	public Double getValorCasa() {
		return valorCasa;
	}

	public void setValorCasa(Double valorCasa) {
		this.valorCasa = valorCasa;
	}

	public String getMeioTransporte() {
		return meioTransporte;
	}

	public void setMeioTransporte(String meioTransporte) {
		this.meioTransporte = meioTransporte;
	}

	public List<BemDuravel> getBensDuraveis() {
		return bensDuraveis;
	}

	public void setBensDuraveis(List<BemDuravel> bensDuraveis) {
		this.bensDuraveis = bensDuraveis;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public boolean isAptoMatricula() {
		return aptoMatricula;
	}

	public void setAptoMatricula(boolean aptoMatricula) {
		this.aptoMatricula = aptoMatricula;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}	
	
	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<BeneficioSocial> getBeneficiosSociais() {
		return beneficiosSociais;
	}

	public void setBeneficiosSociais(List<BeneficioSocial> beneficiosSociais) {
		this.beneficiosSociais = beneficiosSociais;
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
		Interessado other = (Interessado) obj;
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

	public void addBemDuravel(BemDuravel bemDuravel) {
		bensDuraveis.add(bemDuravel);
	}

	public void removerBemDuravel(BemDuravel bemDuravelSelecionado) {
		bensDuraveis.remove(bemDuravelSelecionado);
	}
	
	public void addBeneficioSocial(BeneficioSocial beneficioSocial) {
		beneficiosSociais.add(beneficioSocial);
	}

	public void removerBeneficioSocial(BeneficioSocial beneficioSocialSelecionado) {
		beneficiosSociais.remove(beneficioSocialSelecionado);
	}
}
