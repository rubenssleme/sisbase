package br.laramara.ti.sislaraserver.dominio.solicitacao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.TamanhoMaximoUsuario;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "solicitacao_relatorio")
public class SolicitacaoRelatorio extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "solicitacao_relatorio_historico_status_solicitacao_relatorio", joinColumns = { @JoinColumn(name = "id_solicitacao_relatorio", referencedColumnName = "id") }, 
	inverseJoinColumns = { @JoinColumn(name = "id_historico_status_solicitacao_relatorio", referencedColumnName = "id") })
	private List<HistoricoStatusSolicitacaoRelatorio> historicosStatusSolicitacaoRelatorio;

	@Column(name = "nome_solicitante", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nomeSolicitante;

	@Column(name = "rg_solicitante", length = TamanhoMaximoUsuario.RG, nullable = false)
	private String rgSolicitante;

	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "solicitacao_relatorio_finalidade_relatorio", joinColumns = @JoinColumn(name = "id_solicitacao_relatorio"))
	@Column(name = "finalidade_relatorio", length = TamanhoMaximoGenerico.FINALIDADE_RELATORIO)
	private List<FinalidadeRelatorio> finalidadesRelatorios;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@Column(name = "quantidade_relatorios_emitidos")
	private Integer quantidadeRelatoriosEmitidos;

	@Column(name = "quantidade_relatorios_entregues")
	private Integer quantidadeRelatoriosEntregues;
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String obs;

	@Enumerated(EnumType.STRING)
	@Column(name = "elaborador", length = TamanhoMaximoGenerico.DESCRICAO, nullable = false)
	private Elaborador elaborador;
	
	@Column(name = "enviar_pelo_correio")
	private boolean enviarPeloCorreio;
	
	@Transient
	private StatusSolicitacaoRelatorio statusDaEntrega;

	public SolicitacaoRelatorio() {
		historicosStatusSolicitacaoRelatorio = new ArrayList<>();
		finalidadesRelatorios = new ArrayList<>();
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getRgSolicitante() {
		return rgSolicitante;
	}

	public void setRgSolicitante(String rgSolicitante) {
		this.rgSolicitante = rgSolicitante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getQuantidadeRelatoriosEmitidos() {
		return NumeroUtils.obterTexto(quantidadeRelatoriosEmitidos);
	}

	public void setQuantidadeRelatoriosEmitidos(
			String quantidadeRelatoriosEmitidos) {
		this.quantidadeRelatoriosEmitidos = NumeroUtils
				.retornaInteiroOuInvalido(quantidadeRelatoriosEmitidos);
	}

	public String getQuantidadeRelatoriosEntregues() {
		return NumeroUtils.obterTexto(quantidadeRelatoriosEntregues);
	}

	public void setQuantidadeRelatoriosEntregues(
			String quantidadeRelatoriosEntregues) {
		this.quantidadeRelatoriosEntregues = NumeroUtils
				.retornaInteiroOuInvalido(quantidadeRelatoriosEntregues);
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Elaborador getElaborador() {
		return elaborador;
	}

	public void setElaborador(Elaborador elaborador) {
		this.elaborador = elaborador;
	}

	public List<FinalidadeRelatorio> getFinalidadeRelatorios() {
		return finalidadesRelatorios.stream().distinct().collect(Collectors.toList());
	}

	public void setFinalidadesRelatorios(List<FinalidadeRelatorio> finalidadesRelatorios) {
		this.finalidadesRelatorios = finalidadesRelatorios;
	}
	
	public String obterFinalidadesRelatorios(){
		return getFinalidadeRelatorios().toString();
	}

	public boolean estaSolicitado() {
		return estaStatus(StatusSolicitacaoRelatorio.SOLICITADO);
	}
	
	public boolean isEnviarPeloCorreio() {
		return enviarPeloCorreio;
	}

	public void setEnviarPeloCorreio(boolean enviarPeloCorreio) {
		this.enviarPeloCorreio = enviarPeloCorreio;
	}

	public boolean estaEncaminhadoPelaRecepcao() {
		return estaStatus(StatusSolicitacaoRelatorio.ENCAMINHADO_PELA_RECEPCAO);
	}
	
	public boolean estaEmitidoPeloProfissional() {
		return estaStatus(StatusSolicitacaoRelatorio.EMITIDO_PELO_PROFISSIONAL);
	}
	
	public boolean estaEntregue() {
		return estaStatus(StatusSolicitacaoRelatorio.ENTREGUE_PARA_CORREIOS)
				|| estaStatus(StatusSolicitacaoRelatorio.ENTREGUE_PARA_FAMILIA)
				|| estaStatus(StatusSolicitacaoRelatorio.ENTREGUE_PARA_RECEPCAO);
	}
	
	public boolean estaEntregueParaRecepcao() {
		return estaStatus(StatusSolicitacaoRelatorio.ENTREGUE_PARA_RECEPCAO);
	}
	
	public boolean estaCancelado() {
		return estaStatus(StatusSolicitacaoRelatorio.CANCELADO);
	}
	
	public StatusSolicitacaoRelatorio getStatusDaEntrega() {
		return statusDaEntrega;
	}

	public void setStatusDaEntrega(
			StatusSolicitacaoRelatorio statusDaEntrega) {
		this.statusDaEntrega = statusDaEntrega;
	}

	private boolean estaStatus(
			StatusSolicitacaoRelatorio statusSolicitacaoRelatorio) {
		HistoricoStatusSolicitacaoRelatorio historicoStatusSolicitacaoRelatorio = obterHistoricoStatusRetiradaAtual();
		return historicoStatusSolicitacaoRelatorio.getStatusSolicitacaoRelatorio() != null ? historicoStatusSolicitacaoRelatorio
				.getStatusSolicitacaoRelatorio().equals(
						statusSolicitacaoRelatorio) : false;
	}

	public void setContaAcessoResponsavelPorOperacaoSolicitacao(
			ContaAcesso contaAcessoOperacao) {
		setContaAcessoReponsavel(contaAcessoOperacao,
				StatusSolicitacaoRelatorio.SOLICITADO);
	}

	public void setContaAcessoResponsavelPorOperacaoEncaminhamentoRecepcao(
			ContaAcesso contaAcesso) {
		setContaAcessoReponsavel(contaAcesso,
				StatusSolicitacaoRelatorio.ENCAMINHADO_PELA_RECEPCAO);
	}
	
	public void setContaAcessoResponsavelPorOperacaoEmissaoPorProfissional(
			ContaAcesso contaAcesso) {
		setContaAcessoReponsavel(contaAcesso,
				StatusSolicitacaoRelatorio.EMITIDO_PELO_PROFISSIONAL);
	}
	
	public void setContaAcessoResponsavelPorOperacaoCancelamento(
			ContaAcesso contaAcesso) {
		setContaAcessoReponsavel(contaAcesso,
				StatusSolicitacaoRelatorio.CANCELADO);
	}

	public boolean INSS() {
		return finalidadesRelatorios.stream()
				.anyMatch(finalidadeRelatorio -> finalidadeRelatorio.equals(FinalidadeRelatorio.INSS));
	}
	
	public boolean possuiDataSolicitacaoMenorQueSeisMeses() {
		return DataHoraUtils.obterMesesTranscorridos(obterDataSolicitacao()) < 6;
	}

	public void setContaAcessoResponsavelPorOperacaoEntrega(ContaAcesso contaAcesso) {
		setContaAcessoReponsavel(contaAcesso, statusDaEntrega);
	}

	private void setContaAcessoReponsavel(ContaAcesso contaAcesso,
			StatusSolicitacaoRelatorio status) {
		HistoricoStatusSolicitacaoRelatorio historicoStatusSolicitacaoRelatorioAtual = obterHistoricoStatusRetiradaAtual();
		if (historicoStatusSolicitacaoRelatorioAtual != null
				&& historicoStatusSolicitacaoRelatorioAtual.possuiStatus()) {
			historicoStatusSolicitacaoRelatorioAtual.encerrarVigencia();
		}
		HistoricoStatusSolicitacaoRelatorio historico = new HistoricoStatusSolicitacaoRelatorio();
		historico.setContaAcesso(contaAcesso, status);
		historicosStatusSolicitacaoRelatorio.add(historico);
	}

	public HistoricoStatusSolicitacaoRelatorio obterHistoricoStatusRetiradaAtual() {
		return Historico
				.obterHistoricoAtual(historicosStatusSolicitacaoRelatorio) != null ? (HistoricoStatusSolicitacaoRelatorio) Historico
				.obterHistoricoAtual(historicosStatusSolicitacaoRelatorio)
				: new HistoricoStatusSolicitacaoRelatorio();
	}

	public Calendar obterDataSolicitacao() {
		HistoricoStatusSolicitacaoRelatorio historicoStatusSolicitacaoRelatorio = historicosStatusSolicitacaoRelatorio
				.stream()
				.filter(historicosStatusSolicitacaoRelatorio -> historicosStatusSolicitacaoRelatorio
						.getStatusSolicitacaoRelatorio().equals(StatusSolicitacaoRelatorio.SOLICITADO))
				.findFirst().orElse(null);
		return historicoStatusSolicitacaoRelatorio != null
				? historicoStatusSolicitacaoRelatorio.getDataInicialVigencia() : null;
	}
	
	public StatusSolicitacaoRelatorio obterStatusAtual() {
		return obterHistoricoStatusRetiradaAtual().getStatusSolicitacaoRelatorio();
	}
	
	public boolean elaboradoPelaOrtoptica() {
		return elaborador != null && elaborador.equals(Elaborador.ORTOPTICA);
	}

	public String getHistoricoOperacoes() {
		return Historico
				.getHistoricoOperacoes(historicosStatusSolicitacaoRelatorio);
	}

	private boolean estaStatusDuplicacao(StatusSolicitacaoRelatorio status) {
		Integer resultado = 0;
		for (HistoricoStatusSolicitacaoRelatorio historicoStatusSolicitacaoRelatorio : historicosStatusSolicitacaoRelatorio) {
			if (historicoStatusSolicitacaoRelatorio
					.getStatusSolicitacaoRelatorio() != null
					&& historicoStatusSolicitacaoRelatorio
							.getStatusSolicitacaoRelatorio().equals(status)) {
				resultado++;
			}
		}
		return resultado > 1;
	}
	
	private void validarEntregaDuplicadaRecepcao() {
		if (estaStatusDuplicacao(StatusSolicitacaoRelatorio.ENTREGUE_PARA_RECEPCAO)) {
			adicionarErro("Solicitação já foi entregue para Recepção.");
		}
	}
	
	private void validarEntregaObrigatorioQuantidadeRelatoriosNaEmissaoDoProfissional(){
		if (estaEmitidoPeloProfissional() && quantidadeRelatoriosEmitidos == null){
			adicionarErro("Inserir uma Quantidade de Relatórios.");
		}
	}
	
	private boolean validarStatusDeEntregaEmBranco(){
		HistoricoStatusSolicitacaoRelatorio historicoStatusSolicitacaoRelatorio = obterHistoricoStatusRetiradaAtual();
		return !historicoStatusSolicitacaoRelatorio.possuiStatus();
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarEntregaDuplicadaRecepcao();
		if (usuario == null) {
			adicionarErro("Insira um Usuário.");
		}
		if (nomeSolicitante == null || TextoUtils.estaVazio(nomeSolicitante)) {
			adicionarErro("Insira o Nome do Solicitante.");
		}
		if (rgSolicitante == null || TextoUtils.estaVazio(rgSolicitante)) {
			adicionarErro("Insira o RG do Solicitante.");
		}
		if (validarStatusDeEntregaEmBranco()) {
			adicionarErro("Insira um Status de Entrega.");
		}
		if (estaEntregueParaRecepcao() && quantidadeRelatoriosEntregues != null) {
			adicionarErro("Não insira a Quantidade de Relatórios. Deverá ser iserido apenas na entrega para Família ou Correiros.");
		}
		if ((estaEntregue() && !estaEntregueParaRecepcao())
				&& quantidadeRelatoriosEntregues == null) {
			adicionarErro("Insira a Quantidade de Relatórios.");
		}
		if (finalidadesRelatorios.isEmpty()){
			adicionarErro("Insira uma Finalidade de Relatório.");
		}
		if (elaborador == null){
			adicionarErro("Insira um Elaborador.");
		}
		validarTamanhoMaximoDeDados();
		validarEntregaObrigatorioQuantidadeRelatoriosNaEmissaoDoProfissional();
	}
	
	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(nomeSolicitante, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira o Nome do Solicitante contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}

		if (tamanhoMaximoViolado(rgSolicitante, TamanhoMaximoUsuario.RG)) {
			adicionarErro("Insira o RG do Solicitante contendo até "
					+ TamanhoMaximoUsuario.RG + " caracteres.");
		}

		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira uma Observação contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}

	@Override
	public String toString() {
		return "SolicitacaoRelatorio [id=" + id + ", nomeSolicitante="
				+ nomeSolicitante + ", rgSolicitante=" + rgSolicitante
				+ ", elaborador=" + elaborador + ", finalidadesRelatorio="
				+ finalidadesRelatorios + ", enviarViaCorreio="
				+ enviarPeloCorreio + ", usuario=" + usuario
				+ ", quantidadeRelatoriosEmitidos="
				+ quantidadeRelatoriosEmitidos
				+ ", quantidadeRelatoriosEntregues="
				+ quantidadeRelatoriosEntregues + ", obs=" + obs + "]";
	}
}
