package br.laramara.ti.sislaraserver.dominio.doacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

@Entity
@Table(name = "projeto")
public class Projeto extends Historico implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "projeto_lote_recurso", joinColumns = { @JoinColumn(name = "id_projeto", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_lote_recurso", referencedColumnName = "id") })
	private List<LoteRecurso> loteRecurso;
	
	@Column(name = "ativo")
	private boolean ativo;
	
	@Column(name = "valor_produtos", nullable = false)
	private BigDecimal valorProdutos;
		
	@Column(name = "valor_outros", nullable = false)
	private BigDecimal valorOutros;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "projeto_reserva", joinColumns = { @JoinColumn(name = "id_projeto", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_reserva", referencedColumnName = "id") })
	private List<Reserva> reserva;

	public Projeto() {
		loteRecurso = new ArrayList<>();
		reserva = new ArrayList<>();
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
	
	public List<Recurso> getRecursosComSaldo() {
		List<Recurso> recursosComSaldo = new ArrayList<>();
		for (LoteRecurso loterecurso : loteRecurso) {
			BigDecimal valorTotalReservado = somaValorReservado(loterecurso.getRecurso());
			if (DinheiroUtils.primeiroValorMaiorOuIgualQueSegundoValor(loterecurso.obterValor(), valorTotalReservado)) {
				recursosComSaldo.add(loterecurso.getRecurso());
			}
		}
		return recursosComSaldo;
	}

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
	
	public List<LoteRecurso> getLoteRecurso() {
		return loteRecurso;
	}

	public void setLoteRecurso(List<LoteRecurso> loteRecurso) {
		this.loteRecurso = loteRecurso;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
	
	private void validarLoteRecursos() {
		for(LoteRecurso loteRecurso : loteRecurso){
			loteRecurso.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (!loteRecurso.validado()){
				adicionarErro(loteRecurso.obterErros());
			}
		}
	}
	
	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(nome, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um Nome contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
	}
	
	private void validarObrigatoriedade(){
		if (nome == null || TextoUtils.estaVazio(nome)) {
			adicionarErro("Insira um Nome.");
		}
		
		if (dataInicialVigencia == null || DataHoraUtils.dataInvalida(dataInicialVigencia)) {
			adicionarErro("Insira uma Data Inicial válida.");
		}
		
		if (dataFinalVigencia != null && DataHoraUtils.dataInvalida(dataFinalVigencia)) {
			adicionarErro("Insira uma Data Final válida.");
		}
						
		if (DataHoraUtils.dataTerminoAnteriorDataInicio(dataInicialVigencia, dataFinalVigencia)){
			adicionarErro("Insira uma Data Final posterior a Data Inicial.");
		}
		
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
		
		if (valorProdutos != null && valorOutros != null && !DinheiroUtils
				.primeiroValorMaiorOuIgualQueSegundoValor(valorProdutos, obterSomaValorTotalProdutos())) {
			adicionarErro("Total de produtos não pode ser superior ao Valor dos produtos.");
		}
		verificarSeValorDisponivelParaProdutosEMaiorQueValorResevado();
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarLoteRecursos();
		validarTamanhoMaximoDeDados();
	}

    @Override
	public String toString() {
		return "Projeto [id=" + id + ", nome=" + nome + ", valorTotal=" + getValorTotal()
				+ ", loteRecurso=" + loteRecurso + ", ativo=" + ativo + ", valorProdutos=" + getValorProdutos() + ", valorOutros=" + getValorOutros() + ", reserva=" + reserva + "]";
	}
}
