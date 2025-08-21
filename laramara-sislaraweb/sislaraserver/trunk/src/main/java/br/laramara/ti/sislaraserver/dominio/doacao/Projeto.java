package br.laramara.ti.sislaraserver.dominio.doacao;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.SimNao;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.utilitarios.ArquivoUtils;

@Entity
@Table(name = "projeto")
public class Projeto extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;

	@Column(name = "edital_investimento", length = TamanhoMaximoProjeto.EDITAL_INVESTIMENTO)
	private String editalInvestimento;

	@Column(name = "orgao_parceiro_financiador", length = TamanhoMaximoProjeto.ORGAO_PARCEIRO_FINANCIADOR)
	private String orgaoParceiroFinanciador;

	@Column(name = "lei", length = TamanhoMaximoProjeto.LEI)
	private String lei;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "incentivado", length = TamanhoMaximoGenerico.SIM_NAO)
	private SimNao incentivado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "classificacao", length = TamanhoMaximoGenerico.CLASSIFICACAO)
	private Setor classificacao;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "projeto_responsavel_tecnico", joinColumns = { @JoinColumn(name = "id_projeto", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_responsavel_tecnico", referencedColumnName = "id") })
	private List<ResponsavelTecnico> responsaveisTecnicos;
	
	@Column(name = "objetivo_geral", length = TamanhoMaximoGenerico.OBS)
	private String objetivoGeral;

	@Column(name = "publico_alvo", length = TamanhoMaximoGenerico.OBS)
	private String publicoAlvo;

	@ManyToOne
	@JoinColumn(name = "id_profissional_administrativo_responsavel")
	private Profissional profissionalAdministrativoResponsavel;
	
	@Column(name = "idade_minima")
	private Integer idadeMinima;
	
	@Column(name = "idade_maxima")
	private Integer idadeMaxima;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "prestacao_contas", length = TamanhoMaximoProjeto.PRESTACAO_DE_CONTAS)
	private PrestacaoConta prestacaoConta;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "projeto_patrocinio", joinColumns = { @JoinColumn(name = "id_projeto", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_patrocinio", referencedColumnName = "id") })
	private List<Patrocinio> patrocinios;
	
	@Column(name = "numero_termo_fomento_parceria", length = TamanhoMaximoProjeto.NUMERO_TERMO_FOMENTO_PARCERIA)
	private String numeroTermoFomentoParceria;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "projeto_arquivo", joinColumns = { @JoinColumn(name = "id_projeto", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_arquivo", referencedColumnName = "id") })
	private List<Arquivo> arquivos;
		
	@Column(name = "duracao")
	private Integer duracao;
	
	@Column(name = "aditamento")
	private Integer aditamento;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "projeto_recurso_disponivel", joinColumns = { @JoinColumn(name = "id_projeto", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_recurso_disponivel", referencedColumnName = "id") })
	private List<RecursoDisponivel> recursosDisponiveis;
	
	@Column(name = "ativo")
	private boolean ativo;
	
	@Column(name = "valor_produtos", nullable = false)
	private BigDecimal valorProdutos;
		
	@Column(name = "valor_outros", nullable = false)
	private BigDecimal valorOutros;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "projeto_reserva", joinColumns = { @JoinColumn(name = "id_projeto", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_reserva", referencedColumnName = "id") })
	private List<Reserva> reserva;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicial_vigencia", nullable = false)
	private Calendar dataInicialVigencia;

	public Projeto() {
		recursosDisponiveis = new ArrayList<>();
		reserva = new ArrayList<>();
		arquivos = new ArrayList<>();
	}

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
	
	public String getDuracao() {
		return NumeroUtils.obterTexto(duracao);
	}

	public void setDuracao(String duracao) {
		this.duracao = NumeroUtils.retornaInteiroOuInvalido(duracao);
	}
	
	public String getDataFinalVigencia() {
		return DataHoraUtils.formatarData(DataHoraUtils.avancarMeses(dataInicialVigencia, duracao));
	}

	public String getDataInicialVigencia() {
		return DataHoraUtils.formatarData(dataInicialVigencia);
	}

	public void setDataInicialVigencia(String dataInicialVigencia) {
		this.dataInicialVigencia = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataInicialVigencia);
	}

	public String getAditamento() {
		return NumeroUtils.obterTexto(aditamento);
	}

	public void setAditamento(String aditamento) {
		this.aditamento = NumeroUtils.retornaInteiroOuInvalido(aditamento);
	}
	
	public String getEditalInvestimento() {
		return editalInvestimento;
	}

	public void setEditalInvestimento(String editalInvestimento) {
		this.editalInvestimento = editalInvestimento;
	}

	public String getOrgaoParceiroFinanciador() {
		return orgaoParceiroFinanciador;
	}

	public void setOrgaoParceiroFinanciador(String orgaoParceiroFinanciador) {
		this.orgaoParceiroFinanciador = orgaoParceiroFinanciador;
	}

	public String getLei() {
		return lei;
	}

	public void setLei(String lei) {
		this.lei = lei;
	}

	public String getObjetivoGeral() {
		return objetivoGeral;
	}

	public void setObjetivoGeral(String objetivoGeral) {
		this.objetivoGeral = objetivoGeral;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public Profissional getProfissionalAdministrativoResponsavel() {
		return profissionalAdministrativoResponsavel;
	}

	public void setProfissionalAdministrativoResponsavel(Profissional profissionalAdministrativoResponsavel) {
		this.profissionalAdministrativoResponsavel = profissionalAdministrativoResponsavel;
	}
	
	public String getIdadeMinima() {
		return NumeroUtils.obterTexto(idadeMinima);
	}

	public void setIdadeMinima(String idadeMinima) {
		this.idadeMinima = NumeroUtils.retornaInteiroOuInvalido(idadeMinima);
	}
	
	public String getIdadeMaxima() {
		return NumeroUtils.obterTexto(idadeMaxima);
	}

	public void setIdadeMaxima(String idadeMaxima) {
		this.idadeMaxima = NumeroUtils.retornaInteiroOuInvalido(idadeMaxima);
	}

	public PrestacaoConta getPrestacaoConta() {
		return prestacaoConta;
	}

	public void setPrestacaoConta(PrestacaoConta prestacaoConta) {
		this.prestacaoConta = prestacaoConta;
	}
	
	public List<Patrocinio> getPatrocinios() {
		return patrocinios;
	}

	public void setPatrocinios(List<Patrocinio> patrocinios) {
		this.patrocinios = patrocinios;
	}

	public String getNumeroTermoFomentoParceria() {
		return numeroTermoFomentoParceria;
	}

	public void setNumeroTermoFomentoParceria(String numeroTermoFomentoParceria) {
		this.numeroTermoFomentoParceria = numeroTermoFomentoParceria;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}
	
	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void reservarParaDemanda(Demanda demandaAguardando) {
		if (!reserva.stream().anyMatch(reserva -> reserva.getDemanda().equals(demandaAguardando))) {
			reserva.add(new Reserva(demandaAguardando));
		}
	}
	
	public String getValorTotal() {
		return DinheiroUtils.obterDinheiro(obterValorTotal());
	}

	private BigDecimal obterValorTotal() {
		return valorProdutos != null ? valorProdutos.add(valorOutros) : new BigDecimal("0.00");
	}
	
	//TODO: REMOVER
	/*
	public List<Recurso> getRecursosComSaldo() {
		List<Recurso> recursosComSaldo = new ArrayList<>();
		for (RecursoDisponivel recursoDisponivel : recursosDisponiveis) {
			BigDecimal valorTotalReservado = somaValorReservado(recursoDisponivel.getRecurso());
			if (DinheiroUtils.primeiroValorMaiorOuIgualQueSegundoValor(recursoDisponivel.obterValor(), valorTotalReservado)) {
				recursosComSaldo.add(loterecurso.getRecurso());
			}
		}
		return recursosComSaldo;
	}
	*/

	//TODO: REMOVER
	/*
	public BigDecimal somaValorDisponivelParaRecurso(Recurso recurso) {
		BigDecimal valorTotalReservadoDeRecurso = loteRecurso.stream()
				.filter(loteRecurso -> loteRecurso.getRecurso().equals(recurso)).map(LoteRecurso::obterValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return valorTotalReservadoDeRecurso;
	}
	
	public BigDecimal somaValorReservado(Recurso recurso) {
		BigDecimal valorTotalReservadoDeRecurso = reserva.stream()
				.filter(reserva -> reserva.getDemanda().getRecurso().equals(recurso)).map(Reserva::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return valorTotalReservadoDeRecurso;
	}
	
	public BigDecimal saldoDisponiveParaRecurso(Recurso recurso) {
		BigDecimal valorTotalDisponivelParaRecurso = somaValorDisponivelParaRecurso(recurso);
		BigDecimal valorTotalReservadoDeRecurso = somaValorReservado(recurso);
		return valorTotalDisponivelParaRecurso.subtract(valorTotalReservadoDeRecurso);
	}
	
	public String obterSomaTotalProdutos() {
		BigDecimal somaTotalProdutos = obterSomaValorTotalProdutos();
		return DinheiroUtils.obterDinheiro(somaTotalProdutos);
	}

	private BigDecimal obterSomaValorTotalProdutos() {
		BigDecimal somaTotalProdutos = loteRecurso.stream().map(LoteRecurso::obterValor).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		return somaTotalProdutos;
	}
	*/
	public List<RecursoDisponivel> getRecursosDisponiveis() {
		return recursosDisponiveis;
	}

	public void setRecursosDisponiveis(List<RecursoDisponivel> recursosDisponiveis) {
		this.recursosDisponiveis = recursosDisponiveis;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public SimNao getIncentivado() {
		return incentivado;
	}

	public void setIncentivado(SimNao incentivado) {
		this.incentivado = incentivado;
	}

	public Setor getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Setor classificacao) {
		this.classificacao = classificacao;
	}
	
	public List<ResponsavelTecnico> getResponsaveisTecnicos() {
		return responsaveisTecnicos;
	}

	public void setResponsaveisTecnicos(List<ResponsavelTecnico> responsaveisTecnicos) {
		this.responsaveisTecnicos = responsaveisTecnicos;
	}
	
	public boolean possuiRecursoDisponivel(Demanda demanda) {
		return obterRecursosDisponiveisParaDoacao().contains(demanda.getRecurso());
	}

	public List<Recurso> obterRecursosDisponiveisParaDoacao() {
		List<Recurso> recursosDisponiveisParaDoacao = new ArrayList<>();
		for (RecursoDisponivel recursoDisponivel : this.recursosDisponiveis) {
			long quantidade = reserva.stream()
					.filter(reserva -> reserva.getRecurso().equals(recursoDisponivel.getRecurso())).count();
			if (quantidade <= recursoDisponivel.obterQuantidadeDisponiveParaDoacao()) {
				recursosDisponiveisParaDoacao.add(recursoDisponivel.getRecurso());
			}
		}
		return recursosDisponiveisParaDoacao;
	}

	public String getValorProdutos() {
		return DinheiroUtils.obterDinheiro(valorProdutos);
	}

	public void setValorProdutos(String valorProdutos) {
		this.valorProdutos = DinheiroUtils.obterDinheiroOuInvalido(valorProdutos);
	}
	
	public String getValorOutros() {
		return DinheiroUtils.obterDinheiro(valorOutros);
	}

	public void setValorOutros(String valorOutros) {
		this.valorOutros = DinheiroUtils.obterDinheiroOuInvalido(valorOutros);
	}
	
	//TODO: REMOVER
		/*
	private void verificarSeValorDisponivelParaProdutosEMaiorQueValorResevado() {
		for (Recurso recursoReservado : obterRecursosReservados()) {
			if (DinheiroUtils.primeiroValorMaiorQueSegundoValor(new BigDecimal(0),
					saldoDisponiveParaRecurso(recursoReservado))) {
				adicionarErro(
						"Não é possível haver um valor total de recursos " + recursoReservado.getDescricao() + " reservados maior que o total disponível.");
			}
		}
	}

	private List<Recurso> obterRecursosReservados() {
		return reserva.stream().map(Reserva::getRecurso).distinct()
				.collect(Collectors.toList());
	}
	
	public String obterResumoReservas() {
		String resumoReservas = "";
		for (Recurso recursoReservado : obterRecursosReservados()) {
			resumoReservas += recursoReservado.getDescricao() + " - "
					+ DinheiroUtils.obterDinheiro(somaValorReservado(recursoReservado)) + "\n";
		}
		return resumoReservas;
	}
	*/
	
	private void validarRecursoDisponivel() {
		for(RecursoDisponivel recursoDisponivel : recursosDisponiveis){
			recursoDisponivel.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (!recursoDisponivel.validado()){
				adicionarErro(recursoDisponivel.obterErros());
			}
		}
	}
	
	
	private void validarExistenciaDeArquivosDuplicados() {
		if (ArquivoUtils.validarDuplicacaoArquivos(arquivos)){
			adicionarErro("Existem arquivos de publicação duplicados.");
		}
	}
	
	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(nome, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um Nome contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(editalInvestimento, TamanhoMaximoProjeto.EDITAL_INVESTIMENTO)) {
			adicionarErro("Insira um Edital de Investimento até " + TamanhoMaximoProjeto.EDITAL_INVESTIMENTO
					+ " caracteres.");
		}
		if (tamanhoMaximoViolado(orgaoParceiroFinanciador, TamanhoMaximoProjeto.ORGAO_PARCEIRO_FINANCIADOR)) {
			adicionarErro("Insira um Orgão Parceiro/Financiador contendo até "
					+ TamanhoMaximoProjeto.ORGAO_PARCEIRO_FINANCIADOR + " caracteres.");
		}
		if (tamanhoMaximoViolado(lei, TamanhoMaximoProjeto.LEI)) {
			adicionarErro("Insira uma Lei contendo até "
					+ TamanhoMaximoProjeto.LEI + " caracteres.");
		}
		if (tamanhoMaximoViolado(objetivoGeral, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira um Objetivo Geral contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
		}
		if (tamanhoMaximoViolado(publicoAlvo, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira um Público Alvo contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
		}
		if (tamanhoMaximoViolado(numeroTermoFomentoParceria, TamanhoMaximoProjeto.NUMERO_TERMO_FOMENTO_PARCERIA)) {
			adicionarErro("Insira um Número de Termo de Fomento/Parceria contendo até "
					+ TamanhoMaximoProjeto.NUMERO_TERMO_FOMENTO_PARCERIA + " caracteres.");
		}
	}
	
	private void validarObrigatoriedade(){
		if (nome == null || TextoUtils.estaVazio(nome)) {
			adicionarErro("Insira um Nome.");
		}
		
		if (dataInicialVigencia == null || DataHoraUtils.dataInvalida(dataInicialVigencia)) {
			adicionarErro("Insira uma Data de vigência válida.");
		}
		
/*		if (dataFinalVigencia != null && DataHoraUtils.dataInvalida(dataFinalVigencia)) {
			adicionarErro("Insira uma Data Final válida.");
		}
*/						
		if (duracao == null || NumeroUtils.numeroInteiroInvalido(duracao)) {
			adicionarErro("Insira uma Duração em meses válida.");
		}
		//TODO: REMOVER
				/*
		if (valorProdutos == null || DinheiroUtils.eDinheiroValido(valorProdutos)){
			adicionarErro("Adicionar o valor válido disponível para doação.");
		}
		
		if (valorOutros == null || DinheiroUtils.eDinheiroValido(valorOutros)){
			adicionarErro("Adicionar outros valores válidos(ex. mão de obra).");
		}
		
		if (valorProdutos == null){
			adicionarErro("Insira o Valor de produtos.");
		}
		
		if (valorOutros == null){
			adicionarErro("Insira Outros Valores.");
		}
		*/
		//TODO: REMOVER
		/*		
		if (valorProdutos != null && valorOutros != null && !DinheiroUtils
				.primeiroValorMaiorOuIgualQueSegundoValor(valorProdutos, obterSomaValorTotalProdutos())) {
			adicionarErro("Total de produtos não pode ser superior ao Valor dos produtos.");
		}
		*/
		if (NumeroUtils.numeroInteiroInvalido(duracao)){
			adicionarErro("Insira uma Duração válida.");
		}
		
		if (NumeroUtils.numeroInteiroInvalido(aditamento)){
			adicionarErro("Insira um Aditamento válido.");
		}
		
		if (NumeroUtils.numeroInteiroInvalido(idadeMinima)){
			adicionarErro("Insira uma idade mínima válida.");
		}

		if (NumeroUtils.numeroInteiroInvalido(idadeMaxima)){
			adicionarErro("Insira uma idade máxima válida.");
		}

		//TODO: REMOVER
		/*
		verificarSeValorDisponivelParaProdutosEMaiorQueValorResevado();
		*/
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarRecursoDisponivel();
		validarTamanhoMaximoDeDados();
		validarExistenciaDeArquivosDuplicados();
	}

    @Override
	public String toString() {
		return "Projeto [id=" + id + ", dataInicialVigencia=" + DataHoraUtils.formatarData(dataInicialVigencia) + ", nome=" + nome + ", editalInvestimento=" + editalInvestimento + ", orgaoParceiroFinanciador=" + orgaoParceiroFinanciador + 
				", lei=" + lei + ", incentivado=" + incentivado + ", classificacao=" + classificacao + ", responsaveisTecnicos=" + responsaveisTecnicos + ", objetivoGeral=" + objetivoGeral + ", publicoAlvo=" + publicoAlvo +
				", responsavelAdministrativoResponsavel=" + profissionalAdministrativoResponsavel + ", idadeMinima=" + idadeMinima + ", idadeMaxima= " + idadeMaxima + ", prestacaoConta=" + prestacaoConta + ", patrocinios=" + patrocinios + ", numeroTermoFomentoParceria=" + numeroTermoFomentoParceria +
				", duracao=" + duracao + ", aditamento=" + aditamento + ", valorTotal=" + getValorTotal()
				+ ", recursoDisponivel=" + recursosDisponiveis + ", ativo=" + ativo + ", valorProdutos=" + getValorProdutos() + ", valorOutros=" + getValorOutros() + ", reserva=" + reserva + "]";
	}
}
