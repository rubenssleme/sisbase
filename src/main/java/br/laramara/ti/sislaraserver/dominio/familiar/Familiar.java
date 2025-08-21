package br.laramara.ti.sislaraserver.dominio.familiar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.CpfUtils;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;
import br.laramara.ti.sislaraserver.dominio.usuario.EstadoCivil;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;

@Entity
@Table(name = "familiar")
public class Familiar extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
	@JoinColumn(name = "id_informacao_essencial")
	private InformacaoEssencial informacaoEssencial;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "id_parentesco")
	private Parentesco parentesco;

	@Column(name = "cpf", length = TamanhoMaximoGenerico.CPF)
	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Calendar dataNascimento;

	@OneToOne
	@JoinColumn(name = "id_estado_civil")
	private EstadoCivil estadoCivil;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, optional = false)
	@JoinColumn(name = "id_informacao_trabalho")
	private InformacaoTrabalho informacaoTrabalho;

	@Column(name = "nao_alfabetizado")
	private boolean naoAlfabetizado;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "familiar_informacao_educacional", joinColumns = { @JoinColumn(name = "id_familiar", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_informacao_educacional", referencedColumnName = "id") })
	private List<InformacaoEducacional> informacoesEducacionais;

	@Column(name = "renda")
	private BigDecimal renda;

	@Column(name = "principal_responsavel", nullable = false)
	private boolean principalResponsavel;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS)
	private Status status;

	public Familiar() {
		super();
		informacaoEssencial = new InformacaoEssencial();
		informacaoTrabalho = new InformacaoTrabalho();
		informacoesEducacionais = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InformacaoEssencial getInformacaoEssencial() {
		return informacaoEssencial;
	}

	public void setInformacaoEssencial(InformacaoEssencial informacaoEssencial) {
		this.informacaoEssencial = informacaoEssencial;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = TextoUtils.removerCaracteresInvalidosEAnularVazio(cpf);
	}

	public String getDataNascimento() {
		return DataHoraUtils.formatarData(dataNascimento);
	}

	public void setDataNascimento(String data) {
		this.dataNascimento = DataHoraUtils.obterDataValidaInvalidaOuNulo(data);
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public InformacaoTrabalho getInformacaoTrabalho() {
		return informacaoTrabalho;
	}

	public void setInformacaoTrabalho(InformacaoTrabalho informacaoTrabalho) {
		this.informacaoTrabalho = informacaoTrabalho;
	}

	public List<InformacaoEducacional> obterInformacoesEducacionais() {
		return InformacaoEducacional.obterInformacoesEducacionais(informacoesEducacionais);
	}

	public void adicionarInformacoesEducacionais(
			List<InformacaoEducacional> informacoesEducacionaisParaAdicionar) {
		InformacaoEducacional.adicionarInformacoesEducacionais(informacoesEducacionais,
				informacoesEducacionaisParaAdicionar);
	}
	
	public Boolean possuiValorRenda() {
		return renda != null;
	}

	public String getRenda() {
		return DinheiroUtils.obterDinheiro(renda);
	}
	
	public BigDecimal getValorRenda() {
		return renda;
	}

	public void setRenda(String renda) {
		this.renda = DinheiroUtils.obterDinheiroOuInvalido(renda);
	}

	public boolean isPrincipalResponsavel() {
		return principalResponsavel;
	}

	public void setPrincipalResponsavel(boolean principalResponsavel) {
		this.principalResponsavel = principalResponsavel;
	}

	public boolean isNaoAlfabetizado() {
		return naoAlfabetizado;
	}

	public void setNaoAlfabetizado(boolean naoAlfabetizado) {
		this.naoAlfabetizado = naoAlfabetizado;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedadeDadosMinimos();
		validarObrigatoriedadeDeNomeETamanhoMaximoDeDadosInformacaoEssencial();
		validarTamanhoMaximoDeDadosDeInformacaoTrabalho();
		if (renda != null && renda.equals(DinheiroUtils.obterDinheiroInvalido())) {
			adicionarErro("Insira uma Renda válida.");
		}
	}

	private void validarObrigatoriedadeDeNomeETamanhoMaximoDeDadosInformacaoEssencial(){
		informacaoEssencial.validarObrigatoriedadeDeNomeETamanhoMaximoDeDados();
		if (!informacaoEssencial.validado()){
			adicionarErro(informacaoEssencial.obterErros());
		}
	}	
	
	private void validarDataNascimento() {
		if (DataHoraUtils.dataInvalida(dataNascimento)) {
			adicionarErro("Insira uma Data de Nascimento Válida.");
		}
	}
	
	private void validarObrigatoriedadeDadosMinimos() {
		if (parentesco == null) {
			adicionarErro("Insira um Parentesco.");
		}
		validarDataNascimento();
		if (cpf != null && !TextoUtils.estaVazio(cpf)
				&& !CpfUtils.validarCPF(cpf)) {
			adicionarErro("Insira um CPF válido.");
		}
	}
		
	private void validarTamanhoMaximoDeDadosDeInformacaoTrabalho() {
		if (informacaoTrabalho != null) {
			informacaoTrabalho.validarTamanhoMaximoDeDados();
			if (!informacaoTrabalho.validado()) {
				adicionarErro(informacaoTrabalho.obterErros());
			}
		}
	}

	@Override
	public String toString() {
		return "Familiar [id=" + id + ", parentesco=" + parentesco
				+ ", informacaoEssencial=" + informacaoEssencial + ", cpf="
				+ cpf + ", dataNascimento="
				+ DataHoraUtils.formatarDataHora(dataNascimento) + ", estadoCivil="
				+ estadoCivil + ", informacaoTrabalho=" + informacaoTrabalho
				+ ", Não Alfabetizado=" + naoAlfabetizado + ", informacaoEscolar="
				+ obterInformacoesEducacionais() + ", renda=" + renda + ", principal="
				+ principalResponsavel +", status=" + status + "]";
	}
}
