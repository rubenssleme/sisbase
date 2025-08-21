package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.SimNao;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPreCadastro;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuario;
import br.laramara.ti.sislaraserver.utilitarios.ArquivoUtils;
import br.laramara.ti.sislaraserver.utilitarios.EntidadeUtils;

@Entity
@Table(name = "atendimento_individual")
public class AtendimentoIndividual extends AtendimentoBase implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {

	private static final String INSIRA_O_RELATORIO_DE_AVFUN_NOS_ARQUIVOS = "Insira o relatório de AVFUN nos arquivos.";

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
	@Column(name = "primeira_vez_ou_retorno", length = TamanhoMaximoGenerico.STATUS)
	protected Status primeiraVezOuRetorno;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_individual_arquivo", joinColumns = { @JoinColumn(name = "id_atendimento_individual", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_arquivo", referencedColumnName = "id") })
	private List<Arquivo> arquivos;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_individual_acao_conduta", joinColumns = {
			@JoinColumn(name = "id_atendimento_individual", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_acao_conduta", referencedColumnName = "id") })
	private List<AcaoConduta> acoesDeCondutas;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "integracao", length = TamanhoMaximoGenerico.INTEGRACAO)
	private OpcaoIntegracao integracao;
	
	@Column(name = "motivo_nao_integracao", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String motivoNaoIntegracao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "discussao_caso", length = TamanhoMaximoGenerico.SIM_NAO)
	private SimNao discussaoCaso;
	
	@Column(name = "relatorio_avfun_nao_confeccionado")
	private boolean relatorioAvfunNaoConfeccionado;
	
	@Column(name = "motivo_nao_confeccao_relatorio_avfun", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String motivoNaoConfeccaoRelatorioAvfun;
	
	@Transient
	private String resumoIntegracao;
	
	public AtendimentoIndividual() {
		super(CategoriaAtendimento.INDIVIDUAL);
		inicializarStatusAtendimento();
		informacaoAtendimento = new InformacaoAtendimento();
		atendimentosProfissionais = new ArrayList<>();
		atendimentosComunidade = new ArrayList<>();
		arquivos = new ArrayList<>();
		acoesDeCondutas = new ArrayList<>();
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
		getInformacaoAtendimento().limparIndentificacaoDaParticipacaoDetalhada();
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
	
	public String obterIdadeDoUsuarioOuPreCadastro() {
		return EntidadeUtils.obterIdadeDeUsuarioOuPreCadastro(usuario, preCadastro);
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

	private void aplicarFrequenciaFPeRemoverParticipacaoDosUsuariosSeTodosProfissionaisEstiveremComFrequenciaFP(
			List<AtendimentoProfissional> atendimentosProfissionais) {
		if (atendimentoNormalContendoTodosProfissionaisComFrequenciaFP(atendimentosProfissionais)) {
			informacaoAtendimento.setFrequencia(Frequencia.FP);
			informacaoAtendimento.limparParticipacaoDetalhada();
		}
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
	
	public boolean eDescricaoAvaliacaoFuncionalModuloAtendimentoEspecificoEspecializado() {
		return descricaoTipoAtendimento != null && descricaoTipoAtendimento.eAvaliacaoFuncional() && modulo != null
				&& modulo.eAtendimentoEspecificoEspecializado();
	}
	
	public boolean eDescricaoServicoSocialModuloAvaliacaoETriagem() {
		return descricaoTipoAtendimento.eServicoSocial() && modulo.eAvaliacaoETriagem();
	}
	
	public boolean eDescricaoAvaliacaoServicoAtencaoEspecializadaOftalmologiaModuloAtendimentoEspecificoEspecializado() {
		return descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOftalmologia()
				&& modulo.eAtendimentoEspecificoEspecializado();
	}
	
	public boolean eDescricaoAcompanhamentoServicoAtencaoEspecializadaOftalmologiaModuloAtendimentoEspecificoEspecializado() {
		return descricaoTipoAtendimento.eAcompanhamentoServicoAtencaoEspecializadaOftalmologia()
				&& modulo.eAtendimentoEspecificoEspecializado();
	}
	
	public boolean eDescricaoPsicologiaModuloAvaliacaoETriagem() {
		return descricaoTipoAtendimento.ePsicologia() && modulo.eAvaliacaoETriagem();
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

	public OpcaoIntegracao getOpcaoIntegracao() {
		return integracao;
	}

	public void setOpcaoIntegracao(OpcaoIntegracao integracao) {
		this.integracao = integracao;
	}

	public String getMotivoNaoIntegracao() {
		return motivoNaoIntegracao;
	}

	public void setMotivoNaoIntegracao(String motivoNaoIntegracao) {
		this.motivoNaoIntegracao = motivoNaoIntegracao;
	}

	public SimNao getDiscussaoCasoSimNao() {
		return discussaoCaso;
	}

	public void setDiscussaoCasoSimNao(SimNao discussaoCaso) {
		this.discussaoCaso = discussaoCaso;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}
	
	public List<AcaoConduta> getAcoesDeCondutas() {
		return acoesDeCondutas;
	}
	
	public void setAcoesDeCondutas(List<AcaoConduta> acoesDeCondutas) {
		this.acoesDeCondutas = acoesDeCondutas;
	}
	
	public void setResumoIntegracao(String resumoIntegracao) {
		this.resumoIntegracao = resumoIntegracao;
	}
	
	public String getResumoIntegracao() {
		return resumoIntegracao;
	}
	
	public List<String> obterProfissionais() {
		return atendimentosProfissionais.stream()
				.map(atendimentoProfissinal -> atendimentoProfissinal.getProfissional().getNome())
				.collect(Collectors.toList());
	}

	public void atualizarAcoesCondutasNovas() {
		List<AcaoConduta> acoesCondutasNovas = acoesDeCondutas.stream().filter(acaoConduta -> !acaoConduta.possuiId())
				.collect(Collectors.toList());
		acoesCondutasNovas.forEach(acaoCondutaNova -> acaoCondutaNova.setUsuario(usuario));
	}

	public boolean eRetorno() {
		return primeiraVezOuRetorno != null && primeiraVezOuRetorno.equals(Status.RETORNO);
	}

	public boolean ePrimeiraVez() {
		return primeiraVezOuRetorno != null && primeiraVezOuRetorno.equals(Status.PRIMEIRA_VEZ);
	}
	
	public boolean naoECasoParaLM() {
		return integracao != null && integracao.equals(OpcaoIntegracao.NAO_E_CASO_PARA_LM);
	}
	
	public boolean isRelatorioAvfunNaoConfeccionado() {
		return relatorioAvfunNaoConfeccionado;
	}

	public void setRelatorioAvfunNaoConfeccionado(boolean relatorioAvfunNaoConfeccionado) {
		this.relatorioAvfunNaoConfeccionado = relatorioAvfunNaoConfeccionado;
	}

	public String getMotivoNaoConfeccaoRelatorioAvfun() {
		return motivoNaoConfeccaoRelatorioAvfun;
	}

	public void setMotivoNaoConfeccaoRelatorioAvfun(String motivoNaoConfeccaoRelatorioAvfun) {
		this.motivoNaoConfeccaoRelatorioAvfun = motivoNaoConfeccaoRelatorioAvfun;
	}
	
	public void validarRetornoDeAvaliacaoFuncionalComFrequenciaATSemAtendimentoDeAvaliacaoFuncionalNosUltimos6Meses(
			RepositorioAtendimentoIndividual repositorioAtendimentoIndividual) {
		if (possuiUsuario() && eDescricaoAvaliacaoFuncionalModuloAtendimentoEspecificoEspecializado() && eRetorno()
				&& estaComFrequenciaAT() && !possuiRelatorioAVFUN() && !repositorioAtendimentoIndividual
						.existeAtendimentoAvaliacaoFuncionalComFrequenciaATUltimos6Meses(this) && !relatorioAvfunNaoConfeccionado) {
			adicionarErro(INSIRA_O_RELATORIO_DE_AVFUN_NOS_ARQUIVOS);
		}
	}

	private void validarExistenciaDeProfissionaisDuplicados() {
		 if (EntidadeUtils.possuiOcorrenciaDuplicada(atendimentosProfissionais.stream()
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
		if ((integracao != null && !integracao.equals(OpcaoIntegracao.INTEGRACAO)
				&& !integracao.equals(OpcaoIntegracao.JA_INTEGRADO)) && motivoNaoIntegracao.isEmpty()) {
			adicionarErro("Insira o motivo de não integração.");
		}
		aplicarFrequenciaFPeRemoverParticipacaoDosUsuariosSeTodosProfissionaisEstiveremComFrequenciaFP(atendimentosProfissionais);
		removerParticipacaoDetalhadaComFrequenciaFPFUouFJ();
		validarObrigatoriedadeDeProfissional();
		validarObrigatoriedadeETamanhoMaximoInformacaoAtendimento();
		validarObrigatoriedadeDeData();
		validarObrigatoriedadeDeCategoriaAtendimento();
		validarObrigatoriedadeDePrimeiraVezOuRetorno();
		validarObrigatoriedadeETamanhoMaximoDeDadosHorario();
		validarObrigatoriedadeAnexamentoRelatorioAVFUNNaPrimeiraVezDeAtendimentoDeAvaliacaoFuncional();
		validarRestricaoDePosicionamentoDeIntegracaoOuDiscussaoComFrequenciaDiferenteDeAT();
		velidarExistenciaMotivoNaoConfeccaoRelatorioAVFUN();
		validarExistenciaDeMaisDeUmaAcaoCondutaPorTipoDeGrupo();
		validarExistenciaDeAcaoDeCondutaSomenteParaAvaliacaoFuncional();
		validarExclusaoDeCondutaJaProcessada();
		validarExistenciaDeArquivosDuplicados();
		validarRestricaoDeAlteracao(atendimentosProfissionais);
		validarExistenciaDeProfissionaisDuplicados();
		validarExistenciaParticipacaoDetalhada();
		validarAcaoConduta();
		validarTamanhoMaximoDeDados();
	}

	private void removerParticipacaoDetalhadaComFrequenciaFPFUouFJ() {
		if (informacaoAtendimento != null) {
			informacaoAtendimento.limparParticipacaoDetalhadaComFrequenciaFPFUouFJ();
		}
	}
	
	private void velidarExistenciaMotivoNaoConfeccaoRelatorioAVFUN() {
		if (relatorioAvfunNaoConfeccionado
				&& (motivoNaoConfeccaoRelatorioAvfun == null || motivoNaoConfeccaoRelatorioAvfun.isEmpty())) {
			adicionarErro("Insira o motivo da não confecção do relatório de AVFUN.");
		}
	}
	private void validarObrigatoriedadeAnexamentoRelatorioAVFUNNaPrimeiraVezDeAtendimentoDeAvaliacaoFuncional() {
		if (eDescricaoAvaliacaoFuncionalModuloAtendimentoEspecificoEspecializado() && ePrimeiraVez()
				&& estaComFrequenciaAT() && !possuiRelatorioAVFUN() && !relatorioAvfunNaoConfeccionado) {
			adicionarErro(INSIRA_O_RELATORIO_DE_AVFUN_NOS_ARQUIVOS);
		}
	}
	
	private boolean possuiRelatorioAVFUN(){
		return arquivos.stream().anyMatch(arquivo -> arquivo.possuiRelatorioDeAVFUN());
	}
	
	private void validarAcaoConduta() {
		acoesDeCondutas.forEach(acaoConduta -> acaoConduta.validarObrigatoriedadeETamanhoMaximoDeDados());
		if (acoesDeCondutas.stream().anyMatch(acaoConduta -> !acaoConduta.validado())) {
			adicionarErro("Existe uma conduta com dados inválidos.");
		}
	}
	
	private void validarRestricaoDePosicionamentoDeIntegracaoOuDiscussaoComFrequenciaDiferenteDeAT() {
		if (necessitaIntegracaoEDiscussaoDeCaso() && !estaComFrequenciaAT()
				&& (integracao != null || discussaoCaso != null)) {
			adicionarErro(
					"Não é possível incluir um posicionamento de integração ou triagem em atendimentos com frequência diferente de AT.");
		}
	}

	private boolean possuiDescricaoAtendimento() {
		return descricaoTipoAtendimento != null;
	}
	
	private boolean possuiCondutasNaoCanceladasAProcessar() {
		return acoesDeCondutas.stream().anyMatch(acaoConduta -> acaoConduta.naoCanceladaEAProcessar());
	}
	
	public void validarObrigatoriedadeDeIntegracaoEDiscussaoDeCaso(RepositorioUsuario repositorioUsuario) {
		if (possuiUsuario()) {
			Usuario usuarioAVerificar = repositorioUsuario.obterPorId(usuario.getId());
			if (((ePrimeiraVez()) || (eRetorno() && !usuarioAVerificar.eIntegrado()))
					&& estaComFrequenciaAT() && possuiUsuarioCTO()) {
				if (necessitaIntegracaoEDiscussaoDeCaso()) {
					if (integracao == null) {
						adicionarErro("Insira um posicionamento sobre a integração na triagem.");
					}
					if (discussaoCaso == null) {
						adicionarErro("Insira um posicionamento sobre a discussão de caso na triagem.");
					}
				}
			}
		}
	}
	
	private boolean possuiUsuarioCTO() {
		return usuario != null && usuario.possuiVigenciaAtivaCTO();
	}
	
	private boolean necessitaIntegracaoEDiscussaoDeCaso() {
		return possuiDescricaoAtendimento() && (eDescricaoServicoSocialModuloAvaliacaoETriagem()
				|| eDescricaoAvaliacaoServicoAtencaoEspecializadaOftalmologiaModuloAtendimentoEspecificoEspecializado()
				|| eDescricaoAvaliacaoFuncionalModuloAtendimentoEspecificoEspecializado());
	}
	
	private void validarExclusaoDeCondutaJaProcessada() {
		if (this.acoesDeCondutas.stream().anyMatch(acaoConduta -> acaoConduta.canceladaEProcessada())) {
			adicionarErro("Não é possível cancelar uma acao de conduta já processada.");
		}
	}
	
	private void validarExistenciaDeAcaoDeCondutaSomenteParaAvaliacaoFuncional() {
		if (possuiCondutasNaoCanceladasAProcessar() && !descricaoTipoAtendimento.eAvaliacaoFuncional()){
			adicionarErro("Não é possível incluir condutas em atendimento diferente de Avaliação Funcional.");
		}
	}
	
	private void validarExistenciaDeMaisDeUmaAcaoCondutaPorTipoDeGrupo() {
		boolean existeMaisDeUmPorDescricaoDeGrupo = acoesDeCondutas.stream()
				.filter(acaoConduta -> !acaoConduta.cancelada() && acaoConduta.eParaIntegrar())
				.collect(Collectors.groupingBy(AcaoConduta::obterDescricaoTipoAtendimento, Collectors.counting()))
				.values().stream().anyMatch(total -> total > 1);
			if (existeMaisDeUmPorDescricaoDeGrupo) {
				adicionarErro("Não é possível existir mais de uma ação de conduta por tipo de grupo.");
			}
	}
	
	private void validarExistenciaParticipacaoDetalhada() {
		informacaoAtendimento.validarExistenciaParticipacaDetalhada();
	}
	
	private void validarExistenciaDeArquivosDuplicados() {
		if (ArquivoUtils.validarDuplicacaoArquivos(arquivos)){
			adicionarErro("Existem arquivos duplicados.");
		}
	}

	private void validarObrigatoriedadeDePrimeiraVezOuRetorno() {
		if (primeiraVezOuRetorno == null && descricaoTipoAtendimento != null && modulo != null) {
			if ((eDescricaoAvaliacaoFuncionalModuloAtendimentoEspecificoEspecializado())
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
	
	public static final Comparator<AtendimentoIndividual> obterComparador() {
		return new Comparator<AtendimentoIndividual>() {
			public int compare(AtendimentoIndividual o1, AtendimentoIndividual o2) {
				return (o1.obterData().compareTo(o2.obterData()));
			}
		};
	}

	private void validarObrigatoriedadeDeProfissional() {
		if (atendimentosProfissionais.isEmpty()) {
			adicionarErro("Insira um Profissional.");
		}
	}

	public void validarObrigatoriedadeETamanhoMaximoInformacaoAtendimento() {
		informacaoAtendimento.validarObrigatoriedadeETamanhoMaximoDeDados();
		informacaoAtendimento.validarExistenciaParticipacaDetalhada();
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
				+ localAtendimento 
				+ ", informacaoAtendimento=" + informacaoAtendimento
				+ ", interdisciplinar=" + interdisciplinar
				+ ", descricaoTipoAtendimento=" + descricaoTipoAtendimento
				+ ", modulo=" + modulo + ", setor=" + setor + ", primeiraVezOuRetorno=" + primeiraVezOuRetorno 
				+ ", acoesDeCondutas=" + acoesDeCondutas + ", integracao=" + integracao + ", motivoNaoIntegracao=" + motivoNaoIntegracao 
				+ ", discussaoCaso=" + discussaoCaso + "]";
	}
}
