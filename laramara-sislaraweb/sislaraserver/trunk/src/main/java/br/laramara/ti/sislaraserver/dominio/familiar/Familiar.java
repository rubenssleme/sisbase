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
import br.laramara.ti.sislaraserver.dominio.usuario.Genero;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoBeneficio;
import br.laramara.ti.sislaraserver.dominio.usuario.TamanhoMaximoUsuario;
import br.laramara.ti.sislaraserver.dominio.usuario.Vulnerabilidade;

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
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String obs;

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
	
	@Column(name = "mora_na_casa")
	private boolean moraNaCasa;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero", length = TamanhoMaximoUsuario.GENERO)
	private Genero genero;

	@Column(name = "responsavel_pela_avaliacao_social")
	private boolean responsavelPelaAvaliacaoSocial;

	@Column(name = "acompanhante")
	private boolean acompanhante;
	
	@Column(name = "responsavel_pelo_usuario")
	private boolean responsavelPeloUsuario;

	@Column(name = "paradeiro_ignorado")
	private boolean paradeiroIgnorado;
	
	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "familiar_periodo_beneficio", joinColumns = { @JoinColumn(name = "id_familiar", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_periodo_beneficio", referencedColumnName = "id") })
	private List<PeriodoBeneficio> periodoBeneficios;
	
	@Column(name = "falecido")
	private boolean falecido;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "familiar_vulnerabilidade", joinColumns = { @JoinColumn(name = "id_familiar", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_vulnerabilidade", referencedColumnName = "id") })
	private List<Vulnerabilidade> vulnerabilidades;

	public Familiar() {
		super();
		informacaoEssencial = new InformacaoEssencial();
		informacaoTrabalho = new InformacaoTrabalho();
		informacoesEducacionais = new ArrayList<>();
		periodoBeneficios = new ArrayList<>();
		vulnerabilidades = new ArrayList<>();
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
	
	public List<PeriodoBeneficio> getPeriodoBeneficios() {
		return periodoBeneficios;
	}

	public void setPeriodoBeneficios(List<PeriodoBeneficio> periodoBeneficios) {
		this.periodoBeneficios = periodoBeneficios;
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
	
	public boolean isMoraNaCasa() {
		return moraNaCasa;
	}

	public void setMoraNaCasa(boolean moraNaCasa) {
		this.moraNaCasa = moraNaCasa;
	}

	public boolean isNaoAlfabetizado() {
		return naoAlfabetizado;
	}

	public void setNaoAlfabetizado(boolean naoAlfabetizado) {
		this.naoAlfabetizado = naoAlfabetizado;
	}
	
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public boolean naoPossuiCpfRgEEmail() {
		return informacaoEssencial.naoPossuiRg() && !informacaoEssencial.possuiCpf()
				&& !informacaoEssencial.possuiEmail();
	}
	
	public boolean isResponsavelPelaAvaliacaoSocial() {
		return responsavelPelaAvaliacaoSocial;
	}

	public void setResponsavelPelaAvaliacaoSocial(boolean responsavelPelaAvaliacaoSocial) {
		this.responsavelPelaAvaliacaoSocial = responsavelPelaAvaliacaoSocial;
	}

	public boolean isAcompanhante() {
		return acompanhante;
	}

	public void setAcompanhante(boolean acompanhante) {
		this.acompanhante = acompanhante;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public boolean isResponsavelPeloUsuario() {
		return responsavelPeloUsuario;
	}

	public void setResposavelPeloUsuario(boolean responsavelPeloUsuario) {
		this.responsavelPeloUsuario = responsavelPeloUsuario;
	}
	
	public boolean isFalecido() {
		return falecido;
	}

	public void setFalecido(boolean falecido) {
		this.falecido = falecido;
	}

	public boolean isParadeiroIgnorado() {
		return paradeiroIgnorado;
	}

	public void setParadeiroIgnorado(boolean paradeiroIgnorado) {
		this.paradeiroIgnorado = paradeiroIgnorado;
	}
	
	public List<Vulnerabilidade> getVulnerabilidades() {
		return vulnerabilidades;
	}

	public void setVulnerabilidades(List<Vulnerabilidade> vulnerabilidades) {
		this.vulnerabilidades = vulnerabilidades;
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedadeDadosMinimos();
		validarObrigatoriedadeDeNomeETamanhoMaximoDeDadosInformacaoEssencial();
		validarTamanhoMaximoDeDadosDeInformacaoTrabalho();
		if (renda != null && renda.equals(DinheiroUtils.obterDinheiroInvalido())) {
			adicionarErro("Insira uma Renda v�lida.");
		}
		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira as Obs contendo at� "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
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
			adicionarErro("Insira uma Data de Nascimento V�lida.");
		}
	}
	
	private void validarObrigatoriedadeDadosMinimos() {
		if (parentesco == null) {
			adicionarErro("Insira um Parentesco.");
		}
		validarDataNascimento();
		if (cpf != null && !TextoUtils.estaVazio(cpf)
				&& !CpfUtils.validarCPF(cpf)) {
			adicionarErro("Insira um CPF v�lido.");
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
				+ estadoCivil + ", vulnerabilidades=" + vulnerabilidades + ", falecido=" + falecido + ", informacaoTrabalho=" + informacaoTrabalho
				+", obs=" + obs + ", N�o Alfabetizado=" + naoAlfabetizado + ", informacaoEscolar="
				+ obterInformacoesEducacionais() + ", renda=" + renda + ", principal="
				+ principalResponsavel + ", moraNaCasa=" + moraNaCasa + ", genero=" + genero 
				+ ", responsavelPelaAvaliacaoSocial=" + responsavelPelaAvaliacaoSocial + ", acompanhante=" + acompanhante 
				+ ", responsavelPeloUsuario=" + responsavelPeloUsuario + ", paradeiroIgnorado=" + paradeiroIgnorado 
				+ ", periodosBeneficios=" + periodoBeneficios + "]";
	}
}
