package br.laramara.ti.sislaraserver.dominio.doacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.agenda.MotivoCancelamento;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "demanda")
public class Demanda extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sequencia_criacao", length = TamanhoMaximoGenerico.SEQUENCIA_CRIACAO, nullable = false)
	private String sequenciaCriacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_prazo_captacao")
	private Calendar dataPrazoDeCaptacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_expectativa")
	private Calendar dataExpectativa;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_pre_cadastro")
	private PreCadastro preCadastro;

	@ManyToOne
	@JoinColumn(name = "id_grupo")
	private Grupo grupo;

	@ManyToOne
	@JoinColumn(name = "id_recurso")
	private Recurso recurso;
	
	@ManyToOne
	@JoinColumn(name = "id_descricao_recurso")
	private DescricaoRecurso descricaoRecurso;
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	private String obs;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "demanda_historico_status_demanda", joinColumns = { @JoinColumn(name = "id_demanda", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_historico_status_demanda", referencedColumnName = "id") })
	private List<HistoricoStatusDemanda> historicoStatus;

	@OneToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "demanda_documento_solicitacao_doacao", joinColumns = { @JoinColumn(name = "id_demanda", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_documento_solicitacao_doacao", referencedColumnName = "id") })
	private List<DocumentoSolicitacaoDoacao> documentosSolicitacaoDoacao;
	
	@Column(name = "cartela_de_selos", nullable = false)
	private boolean cartelaDeSelos;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "demanda_captacao", joinColumns = { @JoinColumn(name = "id_demanda", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_captacao", referencedColumnName = "id") })
	private List<Captacao> captacoes;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "id_motivo_cancelamento")
	private MotivoCancelamento motivoCancelamento;
	
	private static final Integer DIAS_DE_PRORROGACAO = 120;
	
	public Demanda() {
		historicoStatus = new ArrayList<>();
		documentosSolicitacaoDoacao = new ArrayList<>();
		captacoes = new ArrayList<>();
		inicializarHistoricoStatusDemanda();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSequenciaCricao(String sequenciaCriacao) {
		this.sequenciaCriacao = sequenciaCriacao;
	}

	public String getDataPrazoDeCaptacao() {
		return DataHoraUtils.formatarData(dataPrazoDeCaptacao);
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public DescricaoRecurso getDescricaoRecurso() {
		return descricaoRecurso;
	}

	public void setDescricaoRecurso(DescricaoRecurso descricaoRecurso) {
		this.descricaoRecurso = descricaoRecurso;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
		if (cartelaDeSelos){
			setValorApartirDeRecurso(recurso);
		}
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public String getDataExpectativa() {
		return DataHoraUtils.formatarData(dataExpectativa);
	}

	public void setDataExpectativa(String dataExpectativa) {
		this.dataExpectativa = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataExpectativa);
	}
	
	public boolean isCarteloDeSelos() {
		return cartelaDeSelos;
	}

	public void setCarteloDeSelosEInicializarDataPrazoCaptacao(boolean carteloDeSelos) {
		this.cartelaDeSelos = carteloDeSelos;
		if (this.cartelaDeSelos && dataPrazoDeCaptacao == null) {
			dataPrazoDeCaptacao = DataHoraUtils.obterDataAposDias(MaquinaTempo.obterInstancia().obterCalendarioAtual(), DIAS_DE_PRORROGACAO);
		}
	}
	
	public void setValorApartirDeRecurso(Recurso recurso) {
		if (recurso != null) {
			this.valor = recurso.getValor();
		}
	}

	public String obterValorCartela(){
		return DinheiroUtils.obterDinheiro(valor);
	}
	
	public String obterValorSaldo() {
		if (valor != null && cartelaDeSelos) {
			return DinheiroUtils.obterDinheiro(getValorSaldo());
		} else {
			return "0";
		}
	}

	public boolean saldoZerado() {
		return DinheiroUtils.primeiroValorMaiorOuIgualQueSegundoValor(BigDecimal.ZERO, getValorSaldo());
	}
	
	private BigDecimal getValorSaldo() {
		return valor.subtract(obterTotalCaptado());
	}
	
	public String obterValorTotalCaptado() {
		return DinheiroUtils
				.obterDinheiro(obterTotalCaptado());
	}

	public BigDecimal obterValorTotalCaptadoRecibo(Long numeroRecibo) {
		return obterCaptacoesNaoCanceladas().filter(captacao -> captacao.eReciboNumero(numeroRecibo))
				.map(captacao -> captacao.obterValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	private BigDecimal obterTotalCaptado() {
		return obterTodosOsValoresCaptados().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	private boolean valorTotalCaptadoMaiorQueValorDaCartela(){
		return valor != null && DinheiroUtils.valorNegativo(getValorSaldo());
	}

	private List<BigDecimal> obterTodosOsValoresCaptados() {
		return obterCaptacoesNaoCanceladas().map(captacao -> captacao.obterValor())
				.collect(Collectors.toList());
	}

	private Stream<Captacao> obterCaptacoesNaoCanceladas() {
		return captacoes.stream().filter(captacao -> !captacao.isCancelada());
	}

	public List<DocumentoSolicitacaoDoacao> getDocumentosSolicitacaoDoacao() {
		return documentosSolicitacaoDoacao;
	}

	public void setDocumentosSolicitacaoDoacao(List<DocumentoSolicitacaoDoacao> documentosSolicitacaoDoacao) {
		this.documentosSolicitacaoDoacao = documentosSolicitacaoDoacao;
	}

	public List<Arquivo> getArquivos() {
		return documentosSolicitacaoDoacao.stream().map(documento -> documento.getArquivo())
				.collect(Collectors.toList());
	}
	
	public MotivoCancelamento getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(MotivoCancelamento motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public StatusDemanda obterStatusDemantaAtual() {
		return this.obterHistoricoStatusDemandaAtual().getStatus();
	}

	private void inicializarHistoricoStatusDemanda() {
		if (historicoStatus.isEmpty()) {
			historicoStatus.add(new HistoricoStatusDemanda(StatusDemanda.AGUARDANDO));
		}
	}
	
	public void marcarComStatusReservadoEAtualizarValor() {
		marcarComStatusReservado();
		setValorApartirDeRecurso(recurso);
	}

	public void marcarComStatusReservado() {
		obterHistoricoStatusDemandaAtual().encerrarVigencia();
		historicoStatus.add(new HistoricoStatusDemanda(StatusDemanda.RESERVADO));
	}
	
	public void adicionarCaptacaoEmDemandasComStatusAguardandoOuProrrogada(EspecificacaoCaptacaoDemanda especificacaoCaptacaoDemanda,
			ContaAcesso contaAcessoResponsavelOperacao) {
		if (estaAguardando() || estaProrrogada()) {
			captacoes.add(new Captacao(especificacaoCaptacaoDemanda.getValor(),
					especificacaoCaptacaoDemanda.getRecibo(), contaAcessoResponsavelOperacao));
		} else {
			adicionarErro(
					"Não é possível efetuar uma captação em demandas com status diferente de AGUARDANDO ou PRORROGADA.");
		}
	}
	
	private boolean estaProrrogada() {
		return this.obterHistoricoStatusDemandaAtual().getStatus()
				.equals(StatusDemanda.PRORROGADA);
	}

	public boolean estaAguardando() {
		return this.obterHistoricoStatusDemandaAtual().getStatus()
				.equals(StatusDemanda.AGUARDANDO);
	}
	
	public boolean possuiUsuairo() {
		return usuario != null;
	}
	
	public boolean estaReservado() {
		return this.obterHistoricoStatusDemandaAtual().getStatus()
				.equals(StatusDemanda.RESERVADO);
	}
	
	public boolean estaDisponivel() {
		return this.obterHistoricoStatusDemandaAtual().getStatus()
				.equals(StatusDemanda.DISPONIVEL);
	}
	
	public boolean estaCancelada() {
		return this.obterHistoricoStatusDemandaAtual().getStatus()
				.equals(StatusDemanda.CANCELADA);
	}

	public boolean possuiUsuarioComIdadeIgualOuSuperiorA4Anos() {
		return possuiUsuairo() && usuario.possuiIdadeIgualOuSuperiorA4Anos();
	}
	
	public boolean possuiUsuarioComIdadeIgualOuSuperiorA16Anos() {
		return possuiUsuairo() && usuario.possuiIdadeIgualOuSuperiorA16Anos();
	}
	
	public boolean eBengala() {
		return recurso.eBengala();
	}

	public void prorrogar(ContaAcesso contaAcesso) {
		if (cartelaDeSelos) {
			dataPrazoDeCaptacao = DataHoraUtils.obterDataAposDias(dataPrazoDeCaptacao, DIAS_DE_PRORROGACAO);
			adicionarStatusDemanda(StatusDemanda.PRORROGADA);
			setContaAcessoResponsavelPorOperacao(contaAcesso);
		} else {
			adicionarErro("Somente projeto cartela de selos pode ser prorrogado.");
		}
	}

	public void adicionarStatusDemanda(StatusDemanda status) {
		HistoricoStatusDemanda historicoStatusDemandaAtual = obterHistoricoStatusDemandaAtual();
		if (historicoStatusDemandaAtual.possuiStatus() || !historicoStatusDemandaAtual
						.getStatus().equals(status)) {
			historicoStatusDemandaAtual.encerrarVigencia();
			this.historicoStatus.add(new HistoricoStatusDemanda(status));
		}
	}

	public HistoricoStatusDemanda obterHistoricoStatusDemandaAtual() {
		return Historico.obterHistoricoAtual(historicoStatus) != null ? (HistoricoStatusDemanda) Historico
				.obterHistoricoAtual(historicoStatus)
				: new HistoricoStatusDemanda();
	}
	
	public void setContaAcessoResponsavelPorOperacao(ContaAcesso contaAcessoOperacao) {
		HistoricoStatusDemanda historicoStatusDemanda = obterHistoricoStatusDemandaAtual();
		historicoStatusDemanda.setContaAcesso(contaAcessoOperacao);
	}

	public String getHistoricoOperacoes() {
		return Historico.getHistoricoOperacoes(historicoStatus);
	}

	public static final Comparator<Demanda> obterComparadorId() {
		return new Comparator<Demanda>() {
			public int compare(Demanda o1, Demanda o2) {
				return Long.compare(o1.id, o2.id);
			}
		};
	}
	
	protected Integer obterIdadeUsuario() {
		return usuario.obterIdadeComoInteiro();
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarObrigatoriedadeDeCancelamento();
	}

	private void validarObrigatoriedadeDeCancelamento() {
		if (estaCancelada() && motivoCancelamento == null) {
			adicionarErro("Insira um motivo de cancelamento.");
		}
		if (motivoCancelamento != null && !estaCancelada()) {
			adicionarErro("O motivo de cancelamento só é permitido em status CANCELADA.");
		}
	}

	private void validarObrigatoriedade() {
		if(!obterHistoricoStatusDemandaAtual().possuiStatus()){
			adicionarErro("Insira um Status.");
		}
		if (cartelaDeSelos && (dataPrazoDeCaptacao == null || DataHoraUtils.dataInvalida(dataPrazoDeCaptacao))) {
			adicionarErro("Insira uma data de prorrogação válida.");
		}
		if (cartelaDeSelos && !recurso.eCartelaDeSelos()){
			adicionarErro("Não é possível usar um recurso não disponível para cartela de selos.");
		}
		if (valorTotalCaptadoMaiorQueValorDaCartela()){
			adicionarErro("O valor total captado não pode ser maior que o valor da cartela.");
		}
		if (usuario == null && preCadastro == null){
			adicionarErro("É necessário inserir um Usuário ou Pre-Cadastro.");
		}
		if (dataExpectativa != null && DataHoraUtils.dataInvalida(dataExpectativa)) {
			adicionarErro("Insira uma data de expectativa válida.");
		}
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
		Demanda other = (Demanda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Demanda [id=" + id + ", sequenciaCriacao=" + sequenciaCriacao + ", dataPrazoDeCaptacao="
				+ DataHoraUtils.formatarData(dataPrazoDeCaptacao) + ", dataExpectativa=" + DataHoraUtils.formatarData(dataExpectativa) + ", usuario=" + usuario + ", preCadastro="
				+ preCadastro + ", recurso=" + recurso + ", obs=" + obs + ", historicoStatus=" + getHistoricoOperacoes()
				+ ", documentosSolicitacaoDoacao=" + documentosSolicitacaoDoacao + ", carteloDeSelos=" + cartelaDeSelos
				+ ", captacoes=" + captacoes + ", valorCartela=" + obterValorCartela() + ", valorSaldo=" + obterValorSaldo() + ", motivoCancelamento=" + motivoCancelamento + "]";
	}
}
