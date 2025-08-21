package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPreCadastro;

@Entity
@Table(name = "atendimento_individual")
public class AtendimentoIndividual extends AtendimentoBase implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_pre_cadastro")
	private PreCadastro preCadastro;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
	@JoinColumn(name = "id_informacao_atendimento", nullable = false)
	protected InformacaoAtendimento informacaoAtendimento;

	@ManyToOne
	@JoinColumn(name = "id_descricao_tipo_atendimento", nullable = false)
	private DescricaoTipoAtendimento descricaoTipoAtendimento;

	@ManyToOne
	@JoinColumn(name = "id_modulo")
	private Modulo modulo;

	@Enumerated(EnumType.STRING)
	@Column(name = "id_setor", length = TamanhoMaximoGenerico.SETOR, nullable = false)
	private Setor setor;

	@Column(name = "interdisciplinar", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String interdisciplinar;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_individual_atendimento_profissional", joinColumns = { @JoinColumn(name = "id_atendimento_individual", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_atendimento_profissional", referencedColumnName = "id") })
	private List<AtendimentoProfissional> atendimentosProfissionais;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_individual_atendimento_comunidade", joinColumns = { @JoinColumn(name = "id_atendimento_individual", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_atendimento_comunidade", referencedColumnName = "id") })
	private List<AtendimentoComunidade> atendimentosComunidade;

	@ManyToOne
	@JoinColumn(name = "id_local_atendimento")
	private LocalAtendimento localAtendimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "participacao", length = TamanhoMaximoGenerico.DESCRICAO)
	private Participacao participacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "primeira_vez_ou_retorno", length = TamanhoMaximoGenerico.STATUS)
	protected Status primeiraVezOuRetorno;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_individual_arquivo", joinColumns = { @JoinColumn(name = "id_atendimento_individual", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_arquivo", referencedColumnName = "id") })
	private List<Arquivo> arquivos;
	
	public AtendimentoIndividual() {
		super(CategoriaAtendimento.INDIVIDUAL);
		inicializarStatusAtendimento();
		informacaoAtendimento = new InformacaoAtendimento();
		atendimentosProfissionais = new ArrayList<>();
		atendimentosComunidade = new ArrayList<>();
		arquivos = new ArrayList<>();
	}
	public AtendimentoIndividual(Long id) {
		this.id = id;
	}
	
	public boolean possuiUsuario() {
		return usuario != null;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public void prepararParaCopia(
			EspecificacaoCopiaAtendimentoIndividual especificacaoCopiaAtendimentoIndividual) {
		this.id = null;
		getInformacaoAtendimento().setId(null);
		setHorario(especificacaoCopiaAtendimentoIndividual.getHorario());
		removerIdentificacaoDoAtendimentoEInformacoes();
	}

	private void removerIdentificacaoDoAtendimentoEInformacoes() {
		for (AtendimentoProfissional atendimentoProfissional : atendimentosProfissionais) {
			atendimentoProfissional.setId(null);
			atendimentoProfissional.getInformacaoAtendimento().setId(null);
		}
		for (AtendimentoComunidade atendimentoComunidade : atendimentosComunidade) {
			atendimentoComunidade.setId(null);
			atendimentoComunidade.getInformacaoAtendimento().setId(null);
		}
	}

	public boolean possuiId() {
		return id != null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PreCadastro getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(PreCadastro preCadastro) {
		this.preCadastro = preCadastro;
	}
	
	public void atualizarUsuarioCadastroPosteriormente(RepositorioPreCadastro repositorioPreCadastro) {
		if (possuiPreCadastro()) {
			PreCadastro preCadastoRecente = repositorioPreCadastro.obterPorId(preCadastro.getId());
			if (preCadastoRecente.possuiUsuarioAssociado()) {
				atualizarUsuarioCadastroPosteriormente(preCadastoRecente);
			}
		}
	}
	
	private void atualizarUsuarioCadastroPosteriormente(PreCadastro preCadastroRecente){
		if (preCadastroRecente != null && preCadastroRecente.possuiUsuarioAssociado()){
			this.usuario = preCadastroRecente.obterUsuarioAssociado();
			this.preCadastro = null;
		}
	}

	public InformacaoAtendimento getInformacaoAtendimento() {
		return informacaoAtendimento;
	}

	public void setInformacaoAtendimento(
			InformacaoAtendimento informacaoAtendimento) {
		this.informacaoAtendimento = informacaoAtendimento;
	}

	public DescricaoTipoAtendimento getDescricaoTipoAtendimento() {
		return descricaoTipoAtendimento;
	}

	public void setDescricaoTipoAtendimento(
			DescricaoTipoAtendimento descricaoTipoAtendimento) {
		this.descricaoTipoAtendimento = descricaoTipoAtendimento;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getInterdisciplinar() {
		return interdisciplinar;
	}

	public void setInterdisciplinar(String interdisciplinar) {
		this.interdisciplinar = interdisciplinar;
	}

	public List<AtendimentoProfissional> getAtendimentosProfissionais() {
		Collections.sort(atendimentosProfissionais,
				AtendimentoProfissional.obterComparador());
		return atendimentosProfissionais;
	}

	public void setAtendimentosProfissionais(
			List<AtendimentoProfissional> atendimentosProfissionais) {
		this.atendimentosProfissionais = atendimentosProfissionais;
	}

	public List<AtendimentoComunidade> getAtendimentosComunidade() {
		return atendimentosComunidade;
	}
	
	public boolean possuiProfissionalComFrequenciaATouFP(Profissional profissional) {
		return obterProfissionaisComFrequenciaATouFP().contains(profissional);
	}

	public boolean atendimentoRealizadoUmAnoAposDesligamento(Usuario usuario) {
		return DataHoraUtils.obterAnosTranscorridos(usuario.obterDataDesligamento(), data) >= 1;
	}

	public void setAtendimentosComunidade(
			List<AtendimentoComunidade> atendimentosComunidade) {
		this.atendimentosComunidade = atendimentosComunidade;
	}

	public LocalAtendimento getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(LocalAtendimento localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}
	
	public boolean eDescricaoAvaliacaoFuncionalModuloAtendimentoEspecificoEspecializado() {
		return descricaoTipoAtendimento.eAvaliacaoFuncional() && modulo.eAtendimentoEspecificoEspecializado();
	}
	
	public boolean eDescricaoServicoSocialModuloAvaliacaoETriagem() {
		return descricaoTipoAtendimento.eServicoSocial() && modulo.eAvaliacaoETriagem();
	}

	public boolean eProceja() {
		return setor.equals(Setor.PROCEJA);
	}
	
	public boolean estaComFrequenciaFUouFJ() {
		return informacaoAtendimento.estaComFrenquenciaFUouFJ();
	}

	public boolean estaComFrequenciaAT() {
		return informacaoAtendimento.estaComFrenquenciaAT();
	}
	
	public List<Profissional> obterProfissionaisComFrequenciaATouFP() {
		return obterProfissionaisComFrequenciaATouFP(atendimentosProfissionais);
	}

	public boolean possuiPreCadastro() {
		return preCadastro != null;
	}
	
	public Status getPrimeiraVezOuRetorno() {
		return primeiraVezOuRetorno;
	}

	public void setPrimeiraVezOuRetorno(Status primeiraVezOuRetorno) {
		this.primeiraVezOuRetorno = primeiraVezOuRetorno;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}
	
	public boolean eRetorno() {
		return primeiraVezOuRetorno != null && primeiraVezOuRetorno.equals(Status.RETORNO);
	}

	public boolean ePrimeiraVez() {
		return primeiraVezOuRetorno != null && primeiraVezOuRetorno.equals(Status.PRIMEIRA_VEZ);
	}
	
	private void validarExistenciaDeProfissionaisDuplicados() {
		 if (possuiOcorrenciaDuplicada(atendimentosProfissionais.stream()
					.map(arquivo -> arquivo.getProfissional()).collect(Collectors.toList()))){
			 adicionarErro("Existem profissionais duplicados.");
		 }
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if ((usuario == null && preCadastro == null)
				|| (usuario != null && preCadastro != null)) {
			adicionarErro("Insira um Usuário ou Pré-Cadastro.");
		}
		if (descricaoTipoAtendimento == null) {
			adicionarErro("Insira uma Descrição de Tipo de Atendimento.");
		}
		if (modulo == null) {
			adicionarErro("Insira um Módulo.");
		}
		if (localAtendimento == null) {
			adicionarErro("Insira um Local de Atendimento.");
		}
		if (setor == null) {
			adicionarErro("Insira um Setor.");
		}
		validarObrigatoriedadeDeProfissional();
		validarObrigatoriedadeETamanhoMaximoInformacaoAtendimento();
		validarObrigatoriedadeDeData();
		validarObrigatoriedadeDeCategoriaAtendimento();
		validarObrigatoriedadeDePrimeiraVezOuRetorno();
		validarObrigatoriedadeETamanhoMaximoDeDadosHorario();
		validarExistenciaDeArquivosDuplicados();
		validarRestricaoDeAlteracao(atendimentosProfissionais);
		validarExistenciaDeProfissionaisDuplicados();
		validarFaltaDeProfissionais(atendimentosProfissionais, todosOsUsuariosPossuemFrequenciaFP());
		validarTamanhoMaximoDeDados();
	}

	private boolean todosOsUsuariosPossuemFrequenciaFP() {
		return informacaoAtendimento.estaComFrenquenciaFP();
	}
	
	private void validarExistenciaDeArquivosDuplicados() {
		if (validarDuplicacaoArquivos(arquivos)){
			adicionarErro("Existem arquivos duplicados.");
		}
	}

	private void validarObrigatoriedadeDePrimeiraVezOuRetorno() {
		if (primeiraVezOuRetorno == null && descricaoTipoAtendimento != null && modulo != null) {
			if ((descricaoTipoAtendimento.eAvaliacaoFuncional() && modulo.eAtendimentoEspecificoEspecializado())
					|| (descricaoTipoAtendimento.eOrientacaoEMobilidade()
							&& modulo.eAtendimentoEspecificoEspecializado())
					|| (descricaoTipoAtendimento.eServicoSocial()
							&& (modulo.eAvaliacaoETriagem() || modulo.eAcompanhamento()))
					|| (descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOftalmologia()
							&& (modulo.eAtendimentoEspecificoEspecializado()))
					|| (descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOrtoptica()
							&& (modulo.eAtendimentoEspecificoEspecializado()))
					|| (descricaoTipoAtendimento.ePsicologia()
							&& (modulo.eAvaliacaoETriagem() || modulo.eAcompanhamento()))) {
				adicionarErro("Selecione o campo Primeira Vez ou Retorno.");
			}
		}
	}

	private void validarObrigatoriedadeDeProfissional() {
		if (atendimentosProfissionais.isEmpty()) {
			adicionarErro("Insira um Profissional.");
		}
	}

	public void validarObrigatoriedadeETamanhoMaximoInformacaoAtendimento() {
		informacaoAtendimento.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (!informacaoAtendimento.validado()) {
			adicionarErro(informacaoAtendimento.obterErros());
		}
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(interdisciplinar,
				TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira um Interdisciplinar contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}

	@Override
	public String toString() {
		return "AtendimentoIndividual [id=" + id + ", categoria="
				+ categoriaAtendimento + ", data="
				+ DataHoraUtils.formatarData(data) + ", status="
				+ statusAtendimento + ", horario=" + horario + ", totalHoras="
				+ totalHoras + ", usuario=" + usuario + ", preCadastro="
				+ preCadastro + ", atendimentoProfissional="
				+ atendimentosProfissionais + ", atendimentoComunidade"
				+ atendimentosComunidade + ", localAtendimento="
				+ localAtendimento + ", participação=" + participacao
				+ ", informacaoAtendimento=" + informacaoAtendimento
				+ ", interdisciplinar=" + interdisciplinar
				+ ", descricaoTipoAtendimento=" + descricaoTipoAtendimento
				+ ", modulo=" + modulo + ", setor=" + setor + ", primeiraVezOuRetorno=" + primeiraVezOuRetorno + "]";
	}
}
