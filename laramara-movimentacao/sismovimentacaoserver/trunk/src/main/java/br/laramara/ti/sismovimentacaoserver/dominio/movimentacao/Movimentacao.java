package br.laramara.ti.sismovimentacaoserver.dominio.movimentacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.persistence.Table;

import br.laramara.ti.sismovimentacaocommons.Identificavel;
import br.laramara.ti.sismovimentacaocommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sismovimentacaoserver.dominio.Historico;
import br.laramara.ti.sismovimentacaoserver.dominio.SimNao;
import br.laramara.ti.sismovimentacaoserver.dominio.Validavel;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;

@Entity
@Table(name = "movimentacao")
public class Movimentacao extends Validavel implements Serializable, Identificavel {

	private static final long serialVersionUID = -7260080897526811475L;

	public static final String SEQUENCIA = "movimentacao_id_seq";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gl", length = TamanhoMaximoMovimentacao.GL)
	private String gl;

	@Column(name = "cliente", length = TamanhoMaximoMovimentacao.CLIENTE)
	private String cliente;

	@Column(name = "codigo_produto", length = TamanhoMaximoMovimentacao.CODIGO_PRODUTO)
	private String codigoProduto;

	@Column(name = "descricao", length = TamanhoMaximoMovimentacao.DESCRICAO)
	private String descricao;

	@Column(name = "descricao_produto", length = TamanhoMaximoMovimentacao.DESCRICAO_PRODUTO)
	private String descricaoProduto;

	@Column(name = "quantidade_cor", length = TamanhoMaximoMovimentacao.QUANTIDADE_COR)
	private String quantidadeCor;

	@Column(name = "cor", length = TamanhoMaximoMovimentacao.COR)
	private String cor;

	@Enumerated(EnumType.STRING)
	@Column(name = "fibra", length = TamanhoMaximoMovimentacao.FIBRA)
	private Fibra fibra;

	@Column(name = "direcao_fibra", length = TamanhoMaximoMovimentacao.DIRECAO_FIBRA)
	private String direcaoFibra;

	@Column(name = "formato", length = TamanhoMaximoMovimentacao.FORMATO)
	private String formato;

	@Column(name = "codigo_anterior", length = TamanhoMaximoMovimentacao.CODIGO_ANTERIOR)
	private String codigoAnterior;

	@Column(name = "gramatura", length = TamanhoMaximoMovimentacao.GRAMATURA)
	private String gramatura;

	@Enumerated(EnumType.STRING)
	@Column(name = "papel", length = TamanhoMaximoMovimentacao.PAPEL)
	private Papel papel;

	@Column(name = "laetus", length = TamanhoMaximoMovimentacao.LAETUS)
	private String laetus;

	@Enumerated(EnumType.STRING)
	@Column(name = "sangria", length = TamanhoMaximoMovimentacao.SIMNAO)
	private SimNao sangria;

	@Enumerated(EnumType.STRING)
	@Column(name = "abdb", length = TamanhoMaximoMovimentacao.ABDB)
	private ABDB abdb;

	@Enumerated(EnumType.STRING)
	@Column(name = "especificacao", length = TamanhoMaximoMovimentacao.SIMNAO)
	private SimNao especificacao;

	@Column(name = "obs_especificacao", length = TamanhoMaximoMovimentacao.OBS)
	private String obsEspecificacao;

	@Column(name = "obs_arte", length = TamanhoMaximoMovimentacao.OBS)
	private String obsArte;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "movimentacao_historico_status", joinColumns = { @JoinColumn(name = "id_movimentacao", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_status", referencedColumnName = "id") })
	private List<HistoricoStatus> historicosStatus;
	
	@Column(name = "gr", length = TamanhoMaximoMovimentacao.GR)
	private String gr;

	@Column(name = "pasta", length = TamanhoMaximoMovimentacao.PASTA)
	private String pasta;

	@Column(name = "bobina", length = TamanhoMaximoMovimentacao.BOBINA)
	private String bobina;
	
	@Column(name = "plana_papel", length = TamanhoMaximoMovimentacao.PLANA_PAPEL)
	private String planaPapel;
	
	@Column(name = "tipo_prova", length = TamanhoMaximoMovimentacao.TIPO_PROVA)
	private String tipoProva;
	
	public Movimentacao() {
		historicosStatus = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGl() {
		return gl;
	}

	public void setGl(String gl) {
		this.gl = gl;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getQuantidadeCor() {
		return quantidadeCor;
	}

	public void setQuantidadeCor(String quantidadeCor) {
		this.quantidadeCor = quantidadeCor;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Fibra getFibra() {
		return fibra;
	}

	public void setFibra(Fibra fibra) {
		this.fibra = fibra;
	}

	public String getDirecaoFibra() {
		return direcaoFibra;
	}

	public void setDirecaoFibra(String direcaoFibra) {
		this.direcaoFibra = direcaoFibra;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getCodigoAnterior() {
		return codigoAnterior;
	}

	public void setCodigoAnterior(String codigoAnterior) {
		this.codigoAnterior = codigoAnterior;
	}

	public String getGramatura() {
		return gramatura;
	}

	public void setGramatura(String gramatura) {
		this.gramatura = gramatura;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public String getLaetus() {
		return laetus;
	}

	public void setLaetus(String laetus) {
		this.laetus = laetus;
	}

	public SimNao getSangria() {
		return sangria;
	}

	public void setSangria(SimNao sangria) {
		this.sangria = sangria;
	}

	public ABDB getAbdb() {
		return abdb;
	}

	public void setAbdb(ABDB abdb) {
		this.abdb = abdb;
	}

	public SimNao getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(SimNao especificacao) {
		this.especificacao = especificacao;
	}

	public String getObsEspecificacao() {
		return obsEspecificacao;
	}

	public void setObsEspecificacao(String obsEspecificacao) {
		this.obsEspecificacao = obsEspecificacao;
	}

	public String getObsArte() {
		return obsArte;
	}

	public void setObsArte(String obsArte) {
		this.obsArte = obsArte;
	}
	
	public String getGr() {
		return gr;
	}

	public void setGr(String gr) {
		this.gr = gr;
	}

	public String getPasta() {
		return pasta;
	}

	public void setPasta(String pasta) {
		this.pasta = pasta;
	}

	public String getBobina() {
		return bobina;
	}

	public void setBobina(String bobina) {
		this.bobina = bobina;
	}

	public String getPlanaPapel() {
		return planaPapel;
	}

	public void setPlanaPapel(String planaPapel) {
		this.planaPapel = planaPapel;
	}

	public String getTipoProva() {
		return tipoProva;
	}

	public void setTipoProva(String tipoProva) {
		this.tipoProva = tipoProva;
	}

	public Status obterStatusAtual() {
		return !historicosStatus.isEmpty() ? ((HistoricoStatus) Historico
				.obterHistoricoAtual(historicosStatus)).getStatus() : null;
	}
	
	public void adicionarStatus(Status status, String data,
			ContaAcesso contaAcesso) {
		HistoricoStatus historicoStatusAtual = (HistoricoStatus) Historico
				.obterHistoricoAtual(historicosStatus);
		if (historicoStatusAtual != null) {
			historicoStatusAtual.encerrarVigencia();
		}
		HistoricoStatus historicoStatusAAdicionar = new HistoricoStatus(status,
				contaAcesso);
		historicoStatusAAdicionar.setDataInicial(data);
		historicosStatus.add(historicoStatusAAdicionar);
	}
	
	private HistoricoStatus obterHistoricoStatusSalvoMaisRecentemente() {
		HistoricoStatus statusSalvoMaisRecente = null;

		for (HistoricoStatus statusAtual : historicosStatus) {
			if (statusSalvoMaisRecente == null
					|| (statusAtual.possuiIdentificacao() && statusAtual
							.getId() > statusSalvoMaisRecente.getId())) {
				statusSalvoMaisRecente = statusAtual;
			}
		}

		return statusSalvoMaisRecente;
	}
	
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		HistoricoStatus statusSalvoMaisRecente = obterHistoricoStatusSalvoMaisRecentemente();
		
		if (obterStatusAtual() == null){
			adicionarErro("Selecione um status.");
		}
		
		if (obterStatusAtual() != null && historicosStatus.size() > 1) {
			Status statusAtual = obterStatusAtual();
			if (statusAtual.possuiAnterior() && !statusAtual.obterAnterior().equals(statusSalvoMaisRecente.getStatus())) {
				adicionarErro("O status atual deveria ser   "
						+ Status.ARTE_EM_APROVACAO.toString());
			}
		}
		
		if (dataInvalida()) {
			adicionarErro("Adicione uma data/hora correta.");
		}
	}

	private boolean dataInvalida() {
		boolean retorno = false;
		for (HistoricoStatus historicoStatus : historicosStatus) {
			if (DataHoraUtils.dataInvalida(historicoStatus.getDataInicial())) {
				retorno = true;
			}
		}
		return retorno;
	}
	
	public void avancarHistoricoStatus(String dataHora, ContaAcesso contaAcesso){
		HistoricoStatus historicoStatusAtual = (HistoricoStatus) Historico
				.obterHistoricoAtual(historicosStatus);
		HistoricoStatus novoHistoricoStatus = new HistoricoStatus(obterProximoStatus(), contaAcesso);
		historicoStatusAtual.setDataFinal(dataHora);
		novoHistoricoStatus.setDataInicial(dataHora);
		historicosStatus.add(novoHistoricoStatus);
	}
	
	public boolean estaLiberadoParaTransicao() {
		Status status = obterStatusAtual();
		return status != null ? !Status.obterStatusFinais().contains(status)
				: false;
	}
	
	private Status obterProximoStatus() {
		return obterStatusAtual().obterProximo();
	}

	public void setDataCriacao(String data, ContaAcesso contaAcesso) {
		HistoricoStatus novoHistoricoStatus = new HistoricoStatus(
				Status.ENTRADA_DO_ARQUIVO, contaAcesso);
		novoHistoricoStatus.setDataInicial(data);
		historicosStatus.add(novoHistoricoStatus);
	}
	
	public String getHistoricoOperacoes() {
		Collections.sort(historicosStatus, Historico.obterComparador());
		return Historico.getHistoricoOperacoes(historicosStatus);
	}
	
	@Override
	public String toString() {
		return "Movimentacao [id=" + id + ", gl=" + gl + ", cliente=" + cliente
				+ ", codigoProduto=" + codigoProduto + ", descricao="
				+ descricao + ", descricaoProduto=" + descricaoProduto
				+ ", quantidadeCor=" + quantidadeCor + ", cor=" + cor
				+ ", fibra=" + fibra + ", direcaoFibra=" + direcaoFibra
				+ ", formato=" + formato + ", codigoAnterior=" + codigoAnterior
				+ ", gramatura=" + gramatura + ", papel=" + papel + ", laetus="
				+ laetus + ", sangria=" + sangria + ", abdb=" + abdb
				+ ", especificacao=" + especificacao + ", obsEspecificacao="
				+ obsEspecificacao + ", obsArte=" + obsArte + ", gr=" + gr
				+ ", pasta=" + pasta + ", bobina=" + bobina + ", planaPapel="
				+ planaPapel + ", tipoProva=" + tipoProva
				+ ", historicoStatus=" + historicosStatus + "]";
	}
}
