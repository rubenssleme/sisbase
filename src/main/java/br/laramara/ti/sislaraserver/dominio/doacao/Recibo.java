package br.laramara.ti.sislaraserver.dominio.doacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

import br.laramara.ti.sislaracommons.utilitarios.CpfCnpjUtils;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;

@Entity
@Table(name = "recibo")
public class Recibo extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;
	
	@Column(name = "cpf_cnpj", length = TamanhoMaximoGenerico.CNPJ, nullable = false)
	private String cpfCnpj;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal valorTotalRecibo;
	
	@ManyToOne
	@JoinColumn(name = "id_filial")
	private Filial filial;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_do_registro", nullable = false)
	private Calendar dataRegistro;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Calendar data;
	
	@Column(name = "descricao", length = TamanhoMaximoGenerico.OBS)
	private String descricao;
	
	@Column(name = "motivo_do_cancelamento", length = TamanhoMaximoGenerico.OBS)
	private String motivoDoCancelamento;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "recibo_historico_status_recibo", joinColumns = { @JoinColumn(name = "id_recibo", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_status_recibo", referencedColumnName = "id") })
	private List<HistoricoStatusRecibo> historicoStatus;
	
	transient ContaAcesso contaAcessoResponsavelPorOperacao;
	
	public Recibo() {
		this.dataRegistro = MaquinaTempo.obterInstancia().obterCalendarioAtual();
		this.historicoStatus = new ArrayList<>();
		inicializarStatus();
	}

	private void inicializarStatus() {
		if (motivoDoCancelamento == null){
			adicionarStatusRecibo(StatusRecibo.NORMAL);
		} else if (motivoDoCancelamento != null && !motivoDoCancelamento.isEmpty()){
			adicionarStatusRecibo(StatusRecibo.CANCELADO);
		}
	}

	public Long getId() {
		return id;
	}

	public boolean possuiId() {
		return id != null;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj.trim();
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
	public String obterDataRegistro() {
		return DataHoraUtils.formatarDataHora(dataRegistro);
	}
	
	public Calendar getDataRegistro() {
		return dataRegistro;
	}

	public String obterData() {
		return DataHoraUtils.formatarData(data);
	}

	public void setData(String data) {
		this.data = DataHoraUtils.criar(data);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMotivoDoCancelamento() {
		return motivoDoCancelamento;
	}

	public void setMotivoDoCancelamento(String motivoDoCancelamento) {
		this.motivoDoCancelamento = motivoDoCancelamento;
	}

	public void setValorTotalRecibo(String valorTotalRecibo) {
		this.valorTotalRecibo =  DinheiroUtils.obterDinheiroOuInvalido(valorTotalRecibo);
	}

	public BigDecimal getValorTotalRecibo() {
		return valorTotalRecibo;
	}
	
	public String obterValorTotalRecibo() {
		return DinheiroUtils.obterDinheiro(valorTotalRecibo);
	}
	
	public void setContaAcessoResponsavelOperacao(ContaAcesso contaAcesso) {
		this.contaAcessoResponsavelPorOperacao = contaAcesso;
		HistoricoStatusRecibo historicoStatusRecibo = obterHistoricoStatusReciboAtual();
		historicoStatusRecibo.setContaAcesso(contaAcesso);
	}
	
	public StatusRecibo getStatusAtual() {
		return obterHistoricoStatusReciboAtual().getStatus();
	}
	
	public boolean estaNormal(){
		return this.getStatusAtual().equals(StatusRecibo.NORMAL);
	}
	
	public boolean estaCancelado(){
		return this.getStatusAtual().equals(StatusRecibo.CANCELADO);
	}
	
	public void adicionarStatusRecibo(StatusRecibo status) {
		HistoricoStatusRecibo historicoStatusReciboAtual = obterHistoricoStatusReciboAtual();
		if (!historicoStatusReciboAtual.possuiStatus() || !historicoStatusReciboAtual
						.getStatus().equals(status)) {
			historicoStatusReciboAtual.encerrarVigencia();
			this.historicoStatus.add(new HistoricoStatusRecibo(status,
					contaAcessoResponsavelPorOperacao));
		}
		if (!historicoStatusReciboAtual.possuiContaAcesso()){
			historicoStatusReciboAtual.setContaAcesso(contaAcessoResponsavelPorOperacao);
		}
	}
	
	public String getHistoricoOperacoes() {
		return Historico.getHistoricoOperacoes(historicoStatus);
	}
	
	public HistoricoStatusRecibo obterHistoricoStatusReciboAtual() {
		return Historico.obterHistoricoAtual(historicoStatus) != null ? (HistoricoStatusRecibo) Historico
				.obterHistoricoAtual(historicoStatus)
				: new HistoricoStatusRecibo();
	}
	
	public void cancelarSePossivel(String motivoDoCancelamento, ContaAcesso contaAcesso) {
		if (estaNormal()) {
			this.motivoDoCancelamento = motivoDoCancelamento;
			validarObrigatoriedadeETamanhoMaximoDeDadosParaCancelamento();
			setContaAcessoResponsavelOperacao(contaAcesso);
			adicionarStatusRecibo(StatusRecibo.CANCELADO);
		} else {
			adicionarErro("Não é possível cancelar o recibo novamente.");
		}
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarAutorizacaoDoacao(); 
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}

	private void validarAutorizacaoDoacao() {
		if ((contaAcessoResponsavelPorOperacao != null
				&& contaAcessoResponsavelPorOperacao.possuiPermissao(Permissao.RECIBO_DOACAO))
				&& (filial != null && !filial.eFilial1())) {
			adicionarErro("Não é possível gerar o recibo para a filial informada.");
		}
	}

	private void validarObrigatoriedadeETamanhoMaximoDeDadosParaCancelamento() {
		if (motivoDoCancelamento == null || motivoDoCancelamento != null && motivoDoCancelamento.trim().isEmpty()) {
			adicionarErro("Insira um motivo de cancelamento.");
		}
		validarTamanhoMaximoDeDadosCancelamento();
	}

	public void validarObrigatoriedade() {
		if (!CpfCnpjUtils.validarCpfCnpj(cpfCnpj)){
			adicionarErro("Insira um CPF/CNPJ válido.");
		}
		if (nome == null || nome.isEmpty()){
			adicionarErro("Insira um nome.");
		}
		if (filial == null){
			adicionarErro("Insira uma filial.");
		}
		if (data == null || DataHoraUtils.dataInvalida(data)) {
			adicionarErro("Insira uma data válida.");
		}
		if (valorTotalRecibo == null
				|| (valorTotalRecibo != null && valorTotalRecibo.equals(DinheiroUtils.obterDinheiroInvalido()))) {
			adicionarErro("Insira um valor total de recibo válido.");
		}
		if (possuiId()) {
			adicionarErro("Não é possível alterar o recibo.");
		} 
	}
	
	protected void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(nome, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira um nome contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
		if (tamanhoMaximoViolado(descricao, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira uma descrição contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
		}
	}

	private void validarTamanhoMaximoDeDadosCancelamento() {
		if (tamanhoMaximoViolado(motivoDoCancelamento, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira um motivo de cancelamento contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
		}
	}

	@Override
	public String toString() {
		return "Recibo [id=" + id + ", nome=" + nome + ", cpfCnpj=" + cpfCnpj + ", valorTotalRecibo=" + DinheiroUtils.obterDinheiro(valorTotalRecibo)
				+ ", filial=" + filial + ", dataRegistro=" + DataHoraUtils.formatarDataHoraMinutosMile(dataRegistro) + ", data=" + DataHoraUtils.formatarData(data) + 
				", statusAtual = " + obterHistoricoStatusReciboAtual() + ", motivoDoCancelamento = " + motivoDoCancelamento + "]";
	}
}
