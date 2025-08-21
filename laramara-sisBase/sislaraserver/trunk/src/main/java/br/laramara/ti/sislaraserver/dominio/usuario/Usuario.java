package br.laramara.ti.sislaraserver.dominio.usuario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.SimNao;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.Versionavel;
import br.laramara.ti.sislaraserver.dominio.endereco.TamanhoMaximoEndereco;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;
import br.laramara.ti.sislaraserver.dominio.familiar.Familiar;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.dominio.trabalho.InformacaoTrabalhoCompleta;

@Entity
@Table(name = "usuario")
public class Usuario extends Validavel implements UsuarioComFoto, Identificavel, Versionavel, ValidavelObrigatoriedadeETamanhoMaximo{
	
	public static final String SEQUENCIA = "usuario_id_seq"; 
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="versao", length = TamanhoMaximoGenerico.TOKEN)
	protected String versao;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
	@JoinColumn(name = "id_informacao_essencial")
	private InformacaoEssencial informacaoEssencial;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;

	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_historico_classificacao_social", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_classificacao_social", referencedColumnName = "id") })
	private List<HistoricoClassificacaoSocial> historicoClassificacaoSocial;

	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_historico_status", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_status", referencedColumnName = "id") })
	private List<HistoricoStatus> historicoStatus;
	
	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_historico_status_usuario", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_status_usuario", referencedColumnName = "id") })
	private List<HistoricoStatusUsuario> historicoStatusUsuario;
		
	@Enumerated(EnumType.STRING)
	@Column(name = "genero", length = TamanhoMaximoUsuario.GENERO, nullable = false)
	private Genero genero;

	@Column(name = "naturalidade", length = TamanhoMaximoUsuario.NATURALIDADE, nullable = false)
	private String naturalidade;
	
	@Column(name = "nacionalidade", length = TamanhoMaximoUsuario.NACIONALIDADE)
	private String nacionalidade;

	@Enumerated(EnumType.STRING)
	@Column(name = "rg_uf", length = TamanhoMaximoEndereco.UF, nullable = false)
	private UF ufRg;

	@Temporal(TemporalType.DATE)
	@Column(name = "rg_data_expedicao", nullable = false)
	private Calendar dataExpedicaoRg;

	@Column(name = "rg_orgao_emissor", length = TamanhoMaximoUsuario.ORGAO_EMISSOR, nullable = false)
	private String orgaoEmissorRg;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "id_estado_civil")
	private EstadoCivil estadoCivil;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "grupo_etnico", length = TamanhoMaximoUsuario.GRUPO_ETNICO)
	private GrupoEtnico grupoEtnico;
	
	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_historico_setor", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_setor", referencedColumnName = "id") })
	private List<HistoricoSetor> historicoSetores;

	@Column(name = "nao_alfabetizado")
	private boolean naoAlfabetizado;

	@Column(name = "nao_esta_na_escola")
	private boolean naoEstaNaEscola;
	
	@Column(name = "responsavel_por_si_mesmo")
	private boolean responsavelPorSiMesmo;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_informacao_educacional", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_informacao_educacional", referencedColumnName = "id") })
	private List<InformacaoEducacional> informacoesEducacionais;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_historico_instituicao_srms", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_instituicao", referencedColumnName = "id") })
	private List<HistoricoInstituicao> historicoInstituicoesComSRMs;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_historico_instituicao_sala_recurso", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_instituicao", referencedColumnName = "id") })
	private List<HistoricoInstituicao> historicoInstituicoesComSalaRecurso;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_historico_instituicao_outros_aee", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_instituicao", referencedColumnName = "id") })
	private List<HistoricoInstituicao> historicoInstituicoesComOutrosAEE;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_familiar", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_familiar", referencedColumnName = "id") })
	private List<Familiar> familiares;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_situacao_guarda", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_situacao_guarda", referencedColumnName = "id") })
	private List<SituacaoGuarda> situacoesGuarda;
	
	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_periodo_beneficio", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_periodo_beneficio", referencedColumnName = "id") })
	private List<PeriodoBeneficio> periodoBeneficios;
	
	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_periodo_deficiencia", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_periodo_deficiencia", referencedColumnName = "id") })
	private List<PeriodoDeficiencia> periodoDeficiencias;
	
	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_periodo_comprometimento", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_periodo_comprometimento", referencedColumnName = "id") })
	private List<PeriodoComprometimento> periodoComprometimentos;
	
	@OneToMany(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_periodo_doenca", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_periodo_doenca", referencedColumnName = "id") })
	private List<PeriodoDoenca> periodoDoencas;
	
	@Column(name = "cirurgias", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String cirurgias;

	@Column(name = "medicamentos", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String medicamentos;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "possui_consanguinidade", length = TamanhoMaximoGenerico.SIM_NAO)
	private SimNao possuiConsanguinidade;
	
	@Column(name = "consanguinidade", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String consanguinidade;
		
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_convenio", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_convenio", referencedColumnName = "id") })
	private List<Convenio> convenios;
	
	@Column(name = "outros_apoios_servicos", length = TamanhoMaximoUsuario.OUTROS_APOIOS_SERVICOS)
	private String outrosApoiosServicos;
	
	@Column(name = "multipla_deficiencia")
	private boolean multiplaDeficiencia;
	
	@Column(name = "cadeira_de_rodas")
	private boolean cadeiraDeRodas;
	
	@Transient
	private byte[] foto;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_certidao", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_certidao", referencedColumnName = "id") })
	private List<Certidao> certidoes;
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String obs;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_leitura", length = TamanhoMaximoGenerico.TIPO_LEITURA)
	private TipoLeitura tipoLeitura;
	
	@Column(name = "tamanho_fonte", length = TamanhoMaximoGenerico.TAMANHO_FONTE)
	private String tamanhoFonte;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_recurso", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_recurso", referencedColumnName = "id") })
	private List<Recurso> recursos;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "atualmente_trabalhando", length = TamanhoMaximoGenerico.SIM_NAO)
	private SimNao atualmenteTrabalhando;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_informacao_trabalho_completa", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_informacao_trabalho_completa", referencedColumnName = "id") })
	private List<InformacaoTrabalhoCompleta> informacoesTrabalhoCompleta;
	
	@Column(name = "renda")
	private BigDecimal renda;
		
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_custo_doenca", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_custo", referencedColumnName = "id") })
	private List<Custo> custosDoenca;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_custo_deficiencia", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_custo", referencedColumnName = "id") })
	private List<Custo> custosDeficiencia;
	
	@Column(name = "falecido")
	private boolean falecido;
	
	@Transient
	private boolean desistentePorExpiracaoRetorno;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_encaminhamento", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_encaminhamento", referencedColumnName = "id") })
	private List<Encaminhamento> encaminhamentos;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_servico", joinColumns = { @JoinColumn(name = "id_usuario", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_servico", referencedColumnName = "id") })
	private List<Servico> servicos;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_recurso_moradia", joinColumns = {
			@JoinColumn(name = "id_usuario", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_recurso_moradia", referencedColumnName = "id") })
	private List<RecursoMoradia> recursosProximoAMoradia;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_necessidade", joinColumns = {
			@JoinColumn(name = "id_usuario", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_necessidade", referencedColumnName = "id") })
	private List<Necessidade> necessidades;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_expectativa", joinColumns = {
			@JoinColumn(name = "id_usuario", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_expectativa", referencedColumnName = "id") })
	private List<Expectativa> expectativas;
	
	@ManyToOne
	@JoinColumn(name = "id_condicao_moradia")
	private CondicaoMoradia condicaoMoradia;
	
	@ManyToOne
	@JoinColumn(name = "id_situacao_habitacional")
	private SituacaoHabitacional situacaoHabitacional;

	@ManyToOne
	@JoinColumn(name = "id_habitacao")
	private Habitacao habitacao;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_construcao")
	private TipoConstrucao tipoConstrucao;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_infraestrutura_basica", joinColumns = {
			@JoinColumn(name = "id_usuario", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_infraestrutura_basica", referencedColumnName = "id") })
	private List<InfraestruturaBasica> infraestruturaBasica;
			
	@Column(name = "historico", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String historico;

	@Column(name = "funcionalidade", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String funcionalidade;
	
	@Column(name = "reacao_frente_a_deficiencia", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String reacaoFrenteADeficiencia;

	@Column(name = "reacao_frente_a_deficiencia_familia", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String reacaoFrenteADeficienciaFamiliar;

	@Column(name = "rede_de_apoio", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String redeDeApoio;

	@Column(name = "rede_de_amigos", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String redeDeAmigos;
	
	@Column(name = "namoro_casamento_sexualidade", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String namoroCasamentoSexualidade;

	@Column(name = "necessidades_expectativas_queixas", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String necessidadesExpectativasQueixas;
	
	@Column(name = "educacional", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String educacional;
	
	@Column(name = "comunicacao", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String comunicacao;

	@Column(name = "religiao_lazer_cultura_rotina", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String religiaoLazerCulturaRotina;

	@Column(name = "parecer", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String parecer;
	
	@Transient
	private String resumoAtendimentosPsicossocial;

	public Usuario() {
		super();
		this.dataCadastro = MaquinaTempo.obterInstancia().obterCalendarioAtual();
		this.historicoClassificacaoSocial = new ArrayList<>();
		this.historicoSetores = new ArrayList<>();
		this.historicoStatus = new ArrayList<>();
		this.historicoStatusUsuario = new ArrayList<>();
		this.historicoInstituicoesComSRMs = new ArrayList<>();
		this.historicoInstituicoesComSalaRecurso = new ArrayList<>();
		this.historicoInstituicoesComOutrosAEE = new ArrayList<>();
		this.informacaoEssencial = new InformacaoEssencial();
		this.informacoesEducacionais = new ArrayList<>();
		this.familiares = new ArrayList<>();
		this.situacoesGuarda = new ArrayList<>();
		this.periodoBeneficios = new ArrayList<>();
		this.periodoDeficiencias = new ArrayList<>();
		this.periodoComprometimentos = new ArrayList<>();
		this.convenios = new ArrayList<>();
		this.certidoes = new ArrayList<>();
		this.informacoesTrabalhoCompleta = new ArrayList<>();
		this.custosDoenca = new ArrayList<>();
		this.custosDeficiencia = new ArrayList<>();
		this.encaminhamentos = new ArrayList<>();
		this.servicos = new ArrayList<>();
		this.recursosProximoAMoradia = new ArrayList<>();
		this.necessidades = new ArrayList<>();
		this.expectativas = new ArrayList<>();
		this.infraestruturaBasica = new ArrayList<>();
		this.versao = "";
	}

	public void inicializarStatusUsuario() {
		if (id == null && historicoStatusUsuario.isEmpty()) {
			HistoricoStatusUsuario historicoStatusUsuario = new HistoricoStatusUsuario(
					StatusRelacaoComModulo.CASO_NOVO);
			this.historicoStatusUsuario.add(historicoStatusUsuario);
		}
	}

	public Long getId(){
		return id;
	}
	
	public void setId(Long prontuario){
		this.id = prontuario;
	}

	public String getVersao(){
		return versao;
	}
	
	public void setVersao(String versao){
		this.versao = versao;
	}
	
	public void mudarVersao(){
		versao = UUID.randomUUID().toString();
	}

	public String getDataCadastro() {
		return DataHoraUtils.formatarDataHora(dataCadastro);
	}
	
	public InformacaoEssencial getInformacaoEssencial() {
		return informacaoEssencial;
	}

	public void setInformacaoEssencial(InformacaoEssencial informacaoEssencial) {
		this.informacaoEssencial = informacaoEssencial;
	}
	
	public List<RecursoMoradia> getRecursosProximoAMoradia() {
		return recursosProximoAMoradia;
	}

	public void setRecursosProximoAMoradia(List<RecursoMoradia> recursosProximoAMoradia) {
		this.recursosProximoAMoradia = recursosProximoAMoradia;
	}
	
	public List<Necessidade> getNecessidades() {
		return necessidades;
	}

	public void setNecessidades(List<Necessidade> necessidades) {
		this.necessidades = necessidades;
	}

	public List<Expectativa> getExpectativas() {
		return expectativas;
	}

	public void setExpectativas(List<Expectativa> expectativas) {
		this.expectativas = expectativas;
	}

	public Status getStatus(){
		return obterHistoricoStatusAtual().getStatus();
	}

	public StatusRelacaoComModulo getStatusUsuarioAtual() {
		return obterHistoricoStatusUsuarioAtual() != null ? obterHistoricoStatusUsuarioAtual()
				.getStatus() : null;
	}
	
	public ClassificacaoSocial getClassificacaoSocial() {
		return obterHistoricoClassificacaoSocialAtual()
				.getClassificacaoSocial();
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	public List<Encaminhamento> getEncaminhamentos() {
		return encaminhamentos;
	}

	public void setEncaminhamentos(List<Encaminhamento> encaminhamentos) {
		this.encaminhamentos = encaminhamentos;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getDataNascimento() {
		return informacaoEssencial.getDataNascimento();
	}

	public void setDataNascimento(String data) {
		informacaoEssencial.setDataNascimento(data);
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public UF getUfRg() {
		return ufRg;
	}

	public void setUfRg(UF ufRg) {
		this.ufRg = ufRg;
	}

	public String obterIdade(){
		return informacaoEssencial.getIdadeComoString();
	}
	
	public String getDataExpedicaoRg() {
		return DataHoraUtils.formatarData(dataExpedicaoRg);
	}

	public void setDataExpedicaoRg(String dataExpedicaoRg) {
		this.dataExpedicaoRg = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataExpedicaoRg);
	}

	public String getOrgaoEmissorRg() {
		return orgaoEmissorRg;
	}

	public void setOrgaoEmissorRg(String orgaoEmissorRg) {
		this.orgaoEmissorRg = orgaoEmissorRg;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public GrupoEtnico getGrupoEtnico() {
		return grupoEtnico;
	}

	public void setGrupoEtnico(GrupoEtnico grupoEtnico) {
		this.grupoEtnico = grupoEtnico;
	}

	public boolean isNaoAlfabetizado() {
		return naoAlfabetizado;
	}

	public void setNaoAlfabetizado(boolean naoAlfabetizado) {
		this.naoAlfabetizado = naoAlfabetizado;
	}

	public boolean isNaoEstaNaEscola() {
		return naoEstaNaEscola;
	}

	public void setNaoEstaNaEscola(boolean naoEstaNaEscola) {
		this.naoEstaNaEscola = naoEstaNaEscola;
	}
	
	public boolean isResponsavelPorSiMesmo() {
		return responsavelPorSiMesmo;
	}

	public void setResponsavelPorSiMesmo(boolean responsavelPorSiMesmo) {
		this.responsavelPorSiMesmo = responsavelPorSiMesmo;
	}
	
	public void associarAoSetorProceja(boolean valor) {
		ativarOuEncerrarVigencia(Setor.PROCEJA, valor);
	}
	
	public void associarAoSetorCTO(boolean valor) {
		ativarOuEncerrarVigencia(Setor.CTO, valor);
	}
		
	private void ativarOuEncerrarVigencia(Setor setor, boolean ativar){
		if (ativar) {
			ativarVigenciaSetor(setor);
		} else {
			encerrarVigenciaSetor(setor);
		}
	}
	
	private void ativarVigenciaSetor(Setor setor) {
		if (!possuiVigenciaAtiva(setor)) {
			historicoSetores.add(new HistoricoSetor(setor));
		}
	}
	
	public boolean possuiVigenciaAtivaProceja(){
		return possuiVigenciaAtiva(Setor.PROCEJA);
	}
	
	public boolean possuiVigenciaAtivaCTO(){
		return possuiVigenciaAtiva(Setor.CTO);
	}
	
	private boolean possuiVigenciaAtiva(Setor setor) {
		List<HistoricoSetor> historicoEmVigenciaSetorProceja = obterHistoricosVigentesSetor(setor);
		return (historicoEmVigenciaSetorProceja.size() > 0) ? true : false;
	}
	
	private void encerrarVigenciaSetor(Setor setor) {
		List<HistoricoSetor> historicoEmVigenciaSetor = obterHistoricosVigentesSetor(setor);
		for (HistoricoSetor historicoSetor : historicoEmVigenciaSetor) {
			historicoSetor.encerrarVigencia();
		}
	}
	
	public List<HistoricoSetor> obterHistoricosVigentesSetor(
			Setor setor) {
		List<HistoricoSetor> retorno = new ArrayList<>();
		for (HistoricoSetor historicoSetor : historicoSetores) {
			if (historicoSetor.getSetor().equals(setor)
					&& !historicoSetor.vigenciaEncerrada()) {
				retorno.add(historicoSetor);
			}
		}
		return retorno;
	}
	
	public List<Setor> obterHistoricosSetorVigentes() {
		Set<Setor> retorno = new HashSet<Setor>();
		for (HistoricoSetor historicoSetor : historicoSetores) {
			if (!historicoSetor.vigenciaEncerrada()) {
				retorno.add(historicoSetor.getSetor());
			}
		}
		return new ArrayList<Setor>(retorno);
	}
	
	public boolean possuiSetorVigente(Setor setor){
		return obterHistoricosSetorVigentes().contains(setor);
	}
	
	public void adicionarClassificacaoSocial(
			ClassificacaoSocial classificacaoSocialParaAdicionar) {
		HistoricoClassificacaoSocial historicoClassificaoSocialAtual = obterHistoricoClassificacaoSocialAtual();

		if (classificacaoSocialParaAdicionar != null) {
			if (historicoClassificaoSocialAtual.possuiClassificacaoSocialNula()){
				encerrarHistoricoClassificacaoSocialEAdicionarNovo(historicoClassificaoSocialAtual, classificacaoSocialParaAdicionar);
			}else if (!historicoClassificaoSocialAtual
							.getClassificacaoSocial().equals(
									classificacaoSocialParaAdicionar)){
				encerrarHistoricoClassificacaoSocialEAdicionarNovo(historicoClassificaoSocialAtual, classificacaoSocialParaAdicionar);
			}
		}else if (!historicoClassificaoSocialAtual.possuiClassificacaoSocialNula()){
			encerrarHistoricoClassificacaoSocialEAdicionarNovo(historicoClassificaoSocialAtual, classificacaoSocialParaAdicionar);
		}
	}
	
	private void encerrarHistoricoClassificacaoSocialEAdicionarNovo(
			HistoricoClassificacaoSocial historicoClassificaoSocialAtual,
			ClassificacaoSocial classificacaoSocialParaAdicionar) {
		historicoClassificaoSocialAtual.encerrarVigencia();
		this.historicoClassificacaoSocial.add(new HistoricoClassificacaoSocial(
				classificacaoSocialParaAdicionar));
	}

	public HistoricoClassificacaoSocial obterHistoricoClassificacaoSocialAtual() {
		return Historico.obterHistoricoAtual(historicoClassificacaoSocial) != null ? (HistoricoClassificacaoSocial) Historico
				.obterHistoricoAtual(historicoClassificacaoSocial)
				: new HistoricoClassificacaoSocial();
	}
	
	public void adicionarStatus(Status status) {
		HistoricoStatus historicoStatusAtual = obterHistoricoStatusAtual();
		if (!historicoStatusAtual.possuiStatus()) {
			this.historicoStatus.add(new HistoricoStatus(status));
		} else if (!historicoStatusAtual.getStatus().equals(status)) {
			historicoStatusAtual.encerrarVigencia();
			this.historicoStatus.add(new HistoricoStatus(status));
		}
	}
	
	public void adicionarStatusUsuario(StatusRelacaoComModulo status) {
		HistoricoStatusUsuario historicoStatusUsuarioAtual = obterHistoricoStatusUsuarioAtual();
		historicoStatusUsuarioAtual.encerrarVigencia();
		historicoStatusUsuario.add(new HistoricoStatusUsuario(status));
	}
	
	public HistoricoStatus obterHistoricoStatusAtual() {
		return Historico.obterHistoricoAtual(historicoStatus) != null ? (HistoricoStatus) Historico
				.obterHistoricoAtual(historicoStatus) : new HistoricoStatus();
	}
	
	public HistoricoStatusUsuario obterHistoricoStatusUsuarioAtual() {
		return Historico.obterHistoricoAtual(historicoStatusUsuario) != null ? (HistoricoStatusUsuario) Historico
				.obterHistoricoAtual(historicoStatusUsuario)
				: new HistoricoStatusUsuario();
	}
	
	public boolean possuiAlgumSetorComVigenciaAtiva() {
		boolean retorno = false;
		for(Setor setor : Setor.values()){
			if (possuiVigenciaAtiva(setor)){
				retorno = true;
			}
		}
		return retorno;
	}
	
	public boolean possuiHistoricoClassificacaoSocialNuloVigente() {
		int quantidadeClassificacaoSocialNuloVigente = 0;
		for (HistoricoClassificacaoSocial historicoClassificacaoSocial : this.historicoClassificacaoSocial) {
			if (historicoClassificacaoSocial.possuiClassificacaoSocialNula()
					&& !historicoClassificacaoSocial.vigenciaEncerrada()) {
				quantidadeClassificacaoSocialNuloVigente++;
			}
		}
		return quantidadeClassificacaoSocialNuloVigente >= 1;
	}
	
	public boolean possuiMaisDeUmHistoricoClassificacaoSocialVigente() {
		return this.historicoClassificacaoSocial.size() > 1;
	}
	
	public List<SituacaoGuarda> getSituacoesGuarda() {
		return situacoesGuarda;
	}

	public void setSituacoesGuarda(List<SituacaoGuarda> situacoesGuarda) {
		this.situacoesGuarda = situacoesGuarda;
	}

	public List<PeriodoBeneficio> getPeriodoBeneficios() {
		return periodoBeneficios;
	}

	public void setPeriodoBeneficios(List<PeriodoBeneficio> periodoBeneficios) {
		this.periodoBeneficios = periodoBeneficios;
	}

	public List<PeriodoDeficiencia> getPeriodoDeficiencias() {
		return PeriodoDeficiencia.obterPeriodoDeficiencia(periodoDeficiencias);
	}

	public void setPeriodoDeficiencias(List<PeriodoDeficiencia> periodoDeficiencias) {
		PeriodoDeficiencia.adicionarPeriodoDeficiencia(this.periodoDeficiencias, periodoDeficiencias);
	}
	
	public List<PeriodoComprometimento> getPeriodoComprometimentos() {
		return periodoComprometimentos;
	}

	public void setPeriodoComprometimentos(List<PeriodoComprometimento> periodoComprometimentos) {
		this.periodoComprometimentos = periodoComprometimentos;
	}

	public List<PeriodoDoenca> getPeriodoDoencas() {
		return periodoDoencas;
	}

	public void setPeriodoDoencas(List<PeriodoDoenca> periodoDoencas) {
		this.periodoDoencas = periodoDoencas;
	}
	
	public List<Convenio> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}

	public List<InformacaoEducacional> obterInformacoesEducacionais() {
		return InformacaoEducacional.obterInformacoesEducacionais(informacoesEducacionais);
	}

	public void adicionarInformacoesEducacionais(
			List<InformacaoEducacional> informacoesEducacionaisParaAdicionar) {
		InformacaoEducacional.adicionarInformacoesEducacionais(informacoesEducacionais,
				informacoesEducacionaisParaAdicionar);
	}
	
	private HistoricoInstituicao obterHistoricoInstituicao(List<HistoricoInstituicao> historicoInstituicoes) {
		return Historico.obterHistoricoAtual(historicoInstituicoes) != null ? (HistoricoInstituicao) Historico
				.obterHistoricoAtual(historicoInstituicoes)
				: new HistoricoInstituicao();
	}
	
	private HistoricoInstituicao obterHistoricoInstituicaoComSRMsAtual() {
		return obterHistoricoInstituicao(historicoInstituicoesComSRMs);
	}
	
	private HistoricoInstituicao obterHistoricoInstituicaoComSalaRecursoAtual() {
		return obterHistoricoInstituicao(historicoInstituicoesComSalaRecurso);
	}
	
	private HistoricoInstituicao obterHistoricoInstituicaoComOutrosAEE() {
		return obterHistoricoInstituicao(historicoInstituicoesComOutrosAEE);
	}

	public Instituicao getInstituicaoComSRMs() {
		return Historico.obterHistoricoAtual(historicoInstituicoesComSRMs) != null ? obterHistoricoInstituicaoComSRMsAtual()
				.getInstituicao() : null;
	}

	public void setInstituicaoComSRMs(Instituicao instituicaoComSRMs) {
		setInstituicao(instituicaoComSRMs,
				obterHistoricoInstituicaoComSRMsAtual(),
				historicoInstituicoesComSRMs);
	}
	
	private void setInstituicao(Instituicao instituicao,
			HistoricoInstituicao historicoInstituicaoAtual,
			List<HistoricoInstituicao> historicoInstituicoes) {
		if ((!historicoInstituicaoAtual.possuiInstituicao() && instituicao != null)
				|| (historicoInstituicaoAtual.possuiInstituicao() && !historicoInstituicaoAtual
						.getInstituicao().equals(instituicao))) {
			historicoInstituicaoAtual.encerrarVigencia();
			historicoInstituicoes.add(new HistoricoInstituicao(instituicao));
		}
	}
	
	public Instituicao getInstituicaoComSalaRecurso() {
 		return 	Historico.obterHistoricoAtual(historicoInstituicoesComSalaRecurso) != null ? obterHistoricoInstituicaoComSalaRecursoAtual()
				.getInstituicao() : null;
	}

	public void setInstituicaoComSalaRecurso(
			Instituicao instituicaoComSalaRecurso) {
		setInstituicao(instituicaoComSalaRecurso,
				obterHistoricoInstituicaoComSalaRecursoAtual(),
				historicoInstituicoesComSalaRecurso);
	}

	public Instituicao getInstituicaoComOutrosAEE() {
		return Historico.obterHistoricoAtual(historicoInstituicoesComOutrosAEE) != null ? obterHistoricoInstituicaoComOutrosAEE()
				.getInstituicao() : null;
	}
	
	public void setInstituicaoComOutrosAEE(Instituicao instituicaoComOutroAEE) {
		setInstituicao(instituicaoComOutroAEE,
				obterHistoricoInstituicaoComOutrosAEE(),
				historicoInstituicoesComOutrosAEE);
	}
	
	public List<Familiar> getFamiliares() {
		return familiares;
	}

	public void setFamiliares(List<Familiar> familiares) {
		this.familiares = familiares;
	}
	
	public boolean isMultiplaDeficiencia() {
		return multiplaDeficiencia;
	}

	public void setMultiplaDeficiencia(boolean multiplaDeficiencia) {
		this.multiplaDeficiencia = multiplaDeficiencia;
	}
	
	public boolean isCadeiraDeRodas() {
		return cadeiraDeRodas;
	}

	public void setCadeiraDeRodas(boolean cadeiraderodas) {
		this.cadeiraDeRodas = cadeiraderodas;
	}

	public List<Certidao> getCertidoes() {
		return certidoes;
	}

	public void setCertidoes(List<Certidao> certidoes) {
		this.certidoes = certidoes;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public String getOutrosApoiosServicos() {
		return outrosApoiosServicos;
	}

	public void setOutrosApoiosServicos(String outrosApoiosServicos) {
		this.outrosApoiosServicos = outrosApoiosServicos;
	}
	
	public TipoLeitura getTipoLeitura() {
		return tipoLeitura;
	}

	public void setTipoLeitura(TipoLeitura tipoLeitura) {
		this.tipoLeitura = tipoLeitura;
	}

	public String getTamanhoFonte() {
		return tamanhoFonte;
	}

	public void setTamanhoFonte(String tamanhoFonte) {
		this.tamanhoFonte = tamanhoFonte;
	}
	
	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	
	public SimNao getAtualmenteTrabalhando() {
		return atualmenteTrabalhando;
	}

	public void setAtualmenteTrabalhando(SimNao atualmenteTrabalhando) {
		this.atualmenteTrabalhando = atualmenteTrabalhando;
	}
	
	public SimNao possuiConsanguinidade() {
		return possuiConsanguinidade;
	}

	public void setPossuiConsanguinidade(SimNao possuiConsanguinidade) {
		this.possuiConsanguinidade = possuiConsanguinidade;
	}

	public List<InformacaoTrabalhoCompleta> getInformacoesTrabalhoCompleta() {
		return informacoesTrabalhoCompleta;
	}

	public void setInformacoesTrabalhoCompleta(
			List<InformacaoTrabalhoCompleta> informacoesTrabalhoCompleta) {
		this.informacoesTrabalhoCompleta = informacoesTrabalhoCompleta;
	}
	
	public String obterRendaTotalFamiliar() {
		BigDecimal rendaTotalFamiliar = familiares.stream().filter(familiar -> familiar.possuiValorRenda())
				.map(Familiar::getValorRenda).reduce(BigDecimal.ZERO, BigDecimal::add);
		return DinheiroUtils.obterDinheiro(rendaTotalFamiliar.add(renda != null ? renda : new BigDecimal(0)));
	}
	
	public String getRenda() {
		return DinheiroUtils.obterDinheiro(renda);
	}

	public void setRenda(String renda) {
		this.renda = DinheiroUtils.obterDinheiroOuInvalido(renda);
	}
	
	public String getCirurgias() {
		return cirurgias;
	}

	public void setCirurgias(String cirurgias) {
		this.cirurgias = cirurgias;
	}

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}

	public String getConsanguinidade() {
		return consanguinidade;
	}

	public void setConsanguinidade(String consanguinidade) {
		this.consanguinidade = consanguinidade;
	}
	
	public List<Custo> getCustosDoenca(){
		return this.custosDoenca;
	}
	
	public void setCustosDoenca(List<Custo> custosDoenca){
		this.custosDoenca = custosDoenca;
	}
	
	public List<Custo> getCustosDeficiencia(){
		return this.custosDeficiencia;
	}
	
	public void setCustosDeficiencia(List<Custo> custosDeficiencia){
		this.custosDeficiencia = custosDeficiencia;
	}
	
	public boolean isFalecido() {
		return falecido;
	}

	public void setFalecido(boolean falecido) {
		this.falecido = falecido;
	}
	
	public CondicaoMoradia getCondicaoMoradia() {
		return condicaoMoradia;
	}

	public void setCondicaoMoradia(CondicaoMoradia condicaoMoradia) {
		this.condicaoMoradia = condicaoMoradia;
	}

	public SituacaoHabitacional getSituacaoHabitacional() {
		return situacaoHabitacional;
	}

	public void setSituacaoHabitacional(SituacaoHabitacional situacaoHabitacional) {
		this.situacaoHabitacional = situacaoHabitacional;
	}

	public Habitacao getHabitacao() {
		return habitacao;
	}

	public void setHabitacao(Habitacao habitacao) {
		this.habitacao = habitacao;
	}

	public TipoConstrucao getTipoConstrucao() {
		return tipoConstrucao;
	}

	public void setTipoConstrucao(TipoConstrucao tipoConstrucao) {
		this.tipoConstrucao = tipoConstrucao;
	}

	public List<InfraestruturaBasica> getInfraestruturaBasica() {
		return infraestruturaBasica;
	}

	public void setInfraestruturaBasica(List<InfraestruturaBasica> infraestruturaBasica) {
		this.infraestruturaBasica = infraestruturaBasica;
	}
	
	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public String getReacaoFrenteADeficiencia() {
		return reacaoFrenteADeficiencia;
	}

	public void setReacaoFrenteADeficiencia(String reacaoFrenteADeficiencia) {
		this.reacaoFrenteADeficiencia = reacaoFrenteADeficiencia;
	}

	public String getReacaoFrenteADeficienciaFamiliar() {
		return reacaoFrenteADeficienciaFamiliar;
	}

	public void setReacaoFrenteADeficienciaFamiliar(String reacaoFrenteADeficienciaFamiliar) {
		this.reacaoFrenteADeficienciaFamiliar = reacaoFrenteADeficienciaFamiliar;
	}

	public String getRedeDeApoio() {
		return redeDeApoio;
	}

	public void setRedeDeApoio(String redeDeApoio) {
		this.redeDeApoio = redeDeApoio;
	}

	public String getRedeDeAmigos() {
		return redeDeAmigos;
	}

	public void setRedeDeAmigos(String redeDeAmigos) {
		this.redeDeAmigos = redeDeAmigos;
	}

	public void setResumoAtendimentosPsicossocial(
			String textoResumoAtendimentosPsicossocial) {
		this.resumoAtendimentosPsicossocial = textoResumoAtendimentosPsicossocial;
	}
	
	public String getResumoAtendimentosPsicossocial() {
		return resumoAtendimentosPsicossocial;
	}

	public String getNamoroCasamentoSexualidade() {
		return namoroCasamentoSexualidade;
	}

	public void setNamoroCasamentoSexualidade(String namoroCasamentoSexualidade) {
		this.namoroCasamentoSexualidade = namoroCasamentoSexualidade;
	}

	public String getNecessidadesExpectativasQueixas() {
		return necessidadesExpectativasQueixas;
	}

	public void setNecessidadesExpectativasQueixas(String necessidadesExpectativasQueixas) {
		this.necessidadesExpectativasQueixas = necessidadesExpectativasQueixas;
	}

	public String getEducacional() {
		return educacional;
	}

	public void setEducacional(String educacional) {
		this.educacional = educacional;
	}

	public String getComunicacao() {
		return comunicacao;
	}

	public void setComunicacao(String comunicacao) {
		this.comunicacao = comunicacao;
	}

	public String getReligiaoLazerCulturaRotina() {
		return religiaoLazerCulturaRotina;
	}

	public void setReligiaoLazerCulturaRotina(String religiaoLazerCulturaRotina) {
		this.religiaoLazerCulturaRotina = religiaoLazerCulturaRotina;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public boolean possuiStatusUsuario() {
		return getStatusUsuarioAtual() != null;
	}
	
	public boolean possuiMenosDe21Anos() {
		try {
			return informacaoEssencial.getIdadeComoInteiro() < 21;
		} catch (Exception e) {
			return false;
		}
	}

	public Calendar obterDataDesligamento() {
		return eDesligado() ? obterHistoricoStatusUsuarioAtual().getDataInicialVigencia() : null;
	}

	public static String obterNomesDosUsuarios(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> usuario.getId() + " - " + usuario.getInformacaoEssencial().getNome())
				.collect(Collectors.toList()).toString();
	}
	
	private boolean possuiAntePenultimoStatusComoRetorno() {
		Collections.sort(historicoStatusUsuario,
				HistoricoStatusUsuario.obterComparador());
		if (historicoStatusUsuario.size() > 1) {
			HistoricoStatusUsuario historicoStatusUsuarioAntePenultimo = historicoStatusUsuario
					.get(historicoStatusUsuario.size() - 2);
			return historicoStatusUsuarioAntePenultimo.getStatus().equals(
							StatusRelacaoComModulo.RETORNO);
		} else {
			return false;
		}
	}

	public String getRg() {
		return informacaoEssencial.getRg();
	}
	
	private boolean eDesistente() {
		return obterHistoricoStatusUsuarioAtual().getStatus() != null
				&& obterHistoricoStatusUsuarioAtual().getStatus().equals(
						StatusRelacaoComModulo.DESISTENTE);
	}
	
	private boolean eCasoNovo() {
		return obterHistoricoStatusUsuarioAtual().getStatus() != null
				&& obterHistoricoStatusUsuarioAtual().getStatus().equals(
						StatusRelacaoComModulo.CASO_NOVO);
	}

	public boolean naoPossuiStatus() {
		return historicoStatusUsuario.isEmpty();
	}

	private boolean eRetorno() {
		return obterHistoricoStatusUsuarioAtual().getStatus() != null
				&& obterHistoricoStatusUsuarioAtual().getStatus().equals(
						StatusRelacaoComModulo.RETORNO);
	}
	
	private boolean eDesligado() {
		return obterHistoricoStatusUsuarioAtual().getStatus() != null
				&& obterHistoricoStatusUsuarioAtual().getStatus().eDesligado();
	}
	
	private boolean statusRetornoAMaisDeDoisAnos() {
		Calendar dataInicioStatus = obterHistoricoStatusUsuarioAtual()
				.getDataInicialVigencia();
		return DataHoraUtils.obterAnosTranscorridos(dataInicioStatus) > 2;
	}
		
	private boolean statusAtualHaMaisDeUmAno() {
		return obterHistoricoStatusUsuarioAtual().getAnosTranscorridos() >= 1;
	}
	
	public boolean possuiStatusUsuarioNovoASalvar() {
		for (HistoricoStatusUsuario historicoStatusUsuario : historicoStatusUsuario) {
			if (!historicoStatusUsuario.possuiId())
				return true;
		}
		return false;
	}
	
	public boolean eRetornoAMaisDeDoisAnos() {
		return eRetorno() && statusRetornoAMaisDeDoisAnos();
	}
	
	public boolean ePossuiMenosDe21AnosEEstaDesligadoHaMaisDeUmAno() {
		return possuiMenosDe21Anos() && eDesligado() && statusAtualHaMaisDeUmAno();
	}

	public boolean eDesistentePorExpiracaoDeRetorno(){
		return desistentePorExpiracaoRetorno;
	}

	public boolean eCasoNovoOuNaoPossuiStatus() {
		return eCasoNovo() || naoPossuiStatus();
	}

	public void atualizarStatusUsuarioParaRetorno() {
		adicionarStatusUsuario(StatusRelacaoComModulo.RETORNO);
	}
	
	public void atualizarStatusUsuarioParaDesistenteOuRetorno() {
		if (possuiMenosDe21Anos() && eDesistente()
				&& !possuiAntePenultimoStatusComoRetorno()) {
			atualizarStatusUsuarioParaRetorno();
		}
		if (eRetornoAMaisDeDoisAnos()) {
			adicionarStatusUsuario(StatusRelacaoComModulo.DESISTENTE);
			desistentePorExpiracaoRetorno = true;
		}
	}

	protected void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(naturalidade, TamanhoMaximoUsuario.NATURALIDADE)) {
			adicionarErro("Insira a Naturalidade contendo até "
					+ TamanhoMaximoUsuario.NATURALIDADE + " caracteres.");
		}
		if (tamanhoMaximoViolado(nacionalidade, TamanhoMaximoUsuario.NACIONALIDADE)) {
			adicionarErro("Insira a Nacionalidade contendo até "
					+ TamanhoMaximoUsuario.NACIONALIDADE + " caracteres.");
		}
		if (tamanhoMaximoViolado(orgaoEmissorRg, TamanhoMaximoUsuario.ORGAO_EMISSOR)) {
			adicionarErro("Insira o Orgão Emissor contendo até "
					+ TamanhoMaximoUsuario.ORGAO_EMISSOR + " caracteres.");
		}
		if (tamanhoMaximoViolado(outrosApoiosServicos,
				TamanhoMaximoUsuario.OUTROS_APOIOS_SERVICOS)) {
			adicionarErro("Insira o Outros Apoios/Serviços contendo até "
					+ TamanhoMaximoUsuario.OUTROS_APOIOS_SERVICOS
					+ " caracteres.");
		}
		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira uma Obs contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(tamanhoFonte,
				TamanhoMaximoGenerico.TAMANHO_FONTE)) {
			adicionarErro("Insira um Tamanho de Fonte contendo até "
					+ TamanhoMaximoGenerico.TAMANHO_FONTE + " caracteres.");
		}
		if (tamanhoMaximoViolado(cirurgias, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira as Cirurgias contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(medicamentos, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira as Medicamentos contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(consanguinidade, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira as Consanguinidade contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(historico, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira o Historico contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(funcionalidade, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira a Funcionalidade contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(reacaoFrenteADeficiencia, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira a Reação frente à deficiência contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
			if (tamanhoMaximoViolado(reacaoFrenteADeficienciaFamiliar, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira a Reação do familiar frente à deficiência contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(redeDeApoio, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira a Rede de apoio contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(redeDeAmigos, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira a Rede de amigos contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(namoroCasamentoSexualidade, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira o Namamoro/Casamento/Sexualidade contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(necessidadesExpectativasQueixas, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira a Necessidade/Expectativa/Queixas contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(educacional, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira o Educacional contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(comunicacao, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira a Comunicação contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(religiaoLazerCulturaRotina, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira a Religião/Lazer/Cultura/Rotina contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(parecer, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira o Parecer contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarObrigatoriedadeETamanhoMaximoInformacaoEssencial();
		validarTamanhoMaximoDeDados();
		validarExistenciaFamiliarReponsavel();		
		verificarDuplicidadeBeneficio();
		inicializarStatusUsuario();
		validarEncaminhamento();
	}
	
	private void validarEncaminhamento() {
		encaminhamentos.forEach(encaminhamento -> encaminhamento.validarObrigatoriedadeETamanhoMaximoDeDados());
		if (encaminhamentos.stream().anyMatch(encaminhamento -> !encaminhamento.validado())){
			adicionarErro("Exite um encaminhamento inválido.");
		}
	}

	private void verificarDuplicidadeBeneficio(){
		if (possuiDuplicacaoDeBeneficio()){
			adicionarErro("Existe dois Benefícios simultaneamente ativos.");
		}
	}
	
	private void validarObrigatoriedade(){
		if (possuiHistoricoClassificacaoSocialNuloVigente()
				&& possuiMaisDeUmHistoricoClassificacaoSocialVigente()) {
			adicionarErro("Insira uma Classificação Social válida.");
		}
		if (!possuiAlgumSetorComVigenciaAtiva()) {
			adicionarErro("Marque pelo menos um Setor.");
		}
		if (genero == null){
			adicionarErro("Insira o Genero.");
		}
		informacaoEssencial.validatObrigatoriedadeDeDataNascimento();
		if (estadoCivil == null){
			adicionarErro("Insira o Estado Civil.");
		}
		if (nacionalidade == null || TextoUtils.estaVazio(nacionalidade)){
			adicionarErro("Insira a Nacionalidade.");
		}
		if (dataExpedicaoRg == null || DataHoraUtils.dataInvalida(dataExpedicaoRg)) {
			adicionarErro("Insira uma Data de Expedição do RG válida.");
		}
		if (naturalidade == null || TextoUtils.estaVazio(naturalidade)){
			adicionarErro("Insira a Naturalidade.");
		}
		if (orgaoEmissorRg == null || TextoUtils.estaVazio(orgaoEmissorRg)){
			adicionarErro("Insira o Órgão de Emissao do RG.");
		}
		if (ufRg == null){
			adicionarErro("Insira o UF do RG.");
		}
		if (renda != null && renda.equals(DinheiroUtils.obterDinheiroInvalido())) {
			adicionarErro("Insira uma Renda válida.");
		}
	}

	private void validarExistenciaFamiliarReponsavel() {
		if ((!responsavelPorSiMesmo && !existeExatamenteUmFamiliarResponsavel())) {
			adicionarErro("Insira exatamente um Familiar Responsável.");
		}
	}
	
	private boolean existeExatamenteUmFamiliarResponsavel(){
		int quantidadeFamiliarResponsavel = 0;
		for(Familiar familiar : familiares){
			if (familiar.isPrincipalResponsavel()){
				quantidadeFamiliarResponsavel++;
			}
		}
		return quantidadeFamiliarResponsavel == 1;
	}
	
	private void validarObrigatoriedadeETamanhoMaximoInformacaoEssencial(){
		informacaoEssencial.validarObrigatoriedadeDeNomeRgCpfContatoEnderecoCompletoETamanhoMaximo();
		if (!informacaoEssencial.validado()){
			adicionarErro(informacaoEssencial.obterErros());
		}
	}

	private boolean possuiDuplicacaoDeBeneficio() {
		boolean possui = false;
		for (PeriodoBeneficio periodoBeneficio : periodoBeneficios) {
			for (PeriodoBeneficio periodoBeneficioComparado : periodoBeneficios) {
				if (!periodoBeneficio.equals(periodoBeneficioComparado) 
						&& (periodoBeneficio.getBeneficio()
								.equals(periodoBeneficioComparado.getBeneficio()))
						&&(!periodoBeneficio.vigenciaEncerrada())
						&& (!periodoBeneficioComparado.vigenciaEncerrada())) {
					possui = true;
					break;
				}
			}
		}
		return possui;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Usuario [prontuário="
				+ id
				+ ", dataCadastro="
				+ DataHoraUtils.formatarDataHora(dataCadastro)
				+ ", classificacaoSocial="
				+ obterHistoricoClassificacaoSocialAtual()
						.getClassificacaoSocial() + ", status="
				+ obterHistoricoStatusAtual().getStatus()
				+ ", statusUsuario="
				+ obterHistoricoStatusUsuarioAtual()
				+ ", informacaoEssencial=" + informacaoEssencial
				+ ", genero=" + genero + ", nacionalidade=" + nacionalidade + ", naturalidade="
				+ naturalidade + ", ufRg = " + ufRg + ", dataExpedicaoRg="
				+ DataHoraUtils.formatarDataHora(dataExpedicaoRg)
				+ ", orgaoEmissorRg=" + orgaoEmissorRg  
				+ ", estadoCivil=" + estadoCivil + ", grupoEtnico="
				+ grupoEtnico + ", renda=" + DinheiroUtils.obterDinheiro(renda) + ", setoresVigentes="
				+ obterHistoricosSetorVigentes() + ", nãoAlfabetizado="
				+ naoAlfabetizado + ", nãoEstaNaEscola=" + naoEstaNaEscola
				+ ", informacaoEscolar=" + obterInformacoesEducacionais()
				+ ", instituicaoComSRMs=" + obterHistoricoInstituicaoComSRMsAtual().getInstituicao() 
				+ ", instituicaoComSalaDeRecurso=" + obterHistoricoInstituicaoComSalaRecursoAtual().getInstituicao() 
				+ ", instituicaoComOutrosAEE=" + obterHistoricoInstituicaoComOutrosAEE().getInstituicao() 
				+ ", familiares=" + familiares + ", situaçõesGuarda="
				+ situacoesGuarda + ", beneficios = " + periodoBeneficios
				+ ", deficiencias=" + periodoDeficiencias + ", periodoComportamentos=" + periodoComprometimentos + ", doencas=" + periodoDoencas + ", cirurgias=" + cirurgias
				+ ", medicamentos=" + medicamentos + ", possuiConsanguinidade=" + possuiConsanguinidade + ", consanguinidade="
				+ consanguinidade + ", custosDoenca=" + custosDoenca + ", custosDeficiencia=" + custosDeficiencia + ", convenios=" + convenios
				+ ", outrosApoiosServicos=" + outrosApoiosServicos + ", certidoes=" + certidoes
				+ ", obs=" + obs + ", tipoLeitura=" + tipoLeitura
				+ ", tamanhoFonte=" + tamanhoFonte + ", recursos="
				+ recursos + ", informacaoTrabalhoCompleta="
				+ informacoesTrabalhoCompleta + ", falecido=" + falecido + ", encaminhamentos=" + encaminhamentos 
				+ ", servicos=" + servicos + ", recursosProximosAMoradia=" + recursosProximoAMoradia +", necessidades=" + necessidades + ", expectativas=" + expectativas 
				+ ", condicaoMoradia=" + condicaoMoradia + ", situacaoHabitacional=" + situacaoHabitacional + ", habitacao=" + habitacao + ", tipoConstrucao=" + tipoConstrucao 
				+ ", infraestruturaBasica=" + infraestruturaBasica + ", historico=" + historico + ", funcionalidade="
				+ funcionalidade + ", reacaoFrenteADeficiencia=" + reacaoFrenteADeficiencia
				+ ", reacaoFrenteADeficienciaFamilia=" + reacaoFrenteADeficienciaFamiliar + ", redeDeApoio="
				+ redeDeApoio + ", redeDeAmigos=" + redeDeAmigos + ", namoroCasamentoSexualidade="
				+ namoroCasamentoSexualidade + ", necessidadeExpectativasQueixas=" + necessidadesExpectativasQueixas
				+ ", educacional=" + educacional + ", comunicacao=" + comunicacao
				+ ", religiaoLazerCulturaRotina=" + religiaoLazerCulturaRotina + ", parecer= " + parecer + "]";
	}
}
