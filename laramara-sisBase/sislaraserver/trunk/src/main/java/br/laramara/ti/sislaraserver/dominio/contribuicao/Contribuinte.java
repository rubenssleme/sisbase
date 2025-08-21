package br.laramara.ti.sislaraserver.dominio.contribuicao;

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
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.Contato;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "contribuinte")
public class Contribuinte extends Validavel implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;

	@Column(name = "nome_empresa", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nomeEmpresa;

	@Column(name = "prontuario")
	private Integer prontuario;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@Column(name = "indicado_por", length = TamanhoMaximoGenerico.NOME)
	private String indicadoPor;

	@Enumerated(EnumType.STRING)
	@Column(name = "frequencia_contribuicao", length = TamanhoMaximoGenerico.FREQUENCIA_CONTRIBUICAO)
	private FrequenciaContribuicao frequencia;

	@Column(name = "valor_contribuicao")
	private BigDecimal valorContribuicao;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
	@JoinColumn(name = "id_contato")
	private Contato contato;
	
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "contribuinte_historico_status_contribuinte", joinColumns = { @JoinColumn(name = "id_contribuinte", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_status_contribuinte", referencedColumnName = "id") })
	private List<HistoricoStatusContribuinte> historicoStatus;
	
	@OneToOne
	@JoinColumn(name = "id_motivo_desativacao")
	private MotivoDesativacao motivoDesativacao;
	
	@Column(name = "cpf", length = TamanhoMaximoGenerico.CPF)
	private String cpf;
		
	public Contribuinte() {
		dataCadastro = MaquinaTempo.obterInstancia().obterCalendarioAtual();
		contato = new Contato();
		historicoStatus = new ArrayList<>();
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataCadastro() {
		return DataHoraUtils.formatarDataHora(dataCadastro);
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	public MotivoDesativacao getMotivoDesativacao() {
		return motivoDesativacao;
	}

	public void setMotivoDesativacao(MotivoDesativacao motivoDesativacao) {
		this.motivoDesativacao = motivoDesativacao;
	}

	public String getValorContribuicao() {
		return DinheiroUtils.obterDinheiro(valorContribuicao);
	}

	public void setValorContribuicao(String valorContribuicao) {
		this.valorContribuicao = DinheiroUtils.obterDinheiroOuInvalido(valorContribuicao);
	}
	
	public StatusContribuinte obterStatusAtual() {
		return obterHistoricoStatusAtual().getStatus();
	}

	public boolean estaAtivado() {
		return obterHistoricoStatusAtual().getStatus().equals(StatusContribuinte.ATIVADO);
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	private void validarConsistenciaDoCpf() {
		if (cpf != null && !TextoUtils.estaVazio(cpf) && !CpfUtils.validarCPF(cpf)) {
			adicionarErro("Insira um CPF válido.");
		}
	}
	
	public void adicionarStatus(StatusContribuinte status) {
		if (status != null) {
			HistoricoStatusContribuinte historicoStatusAtual = obterHistoricoStatusAtual();
			if (historicoStatusAtual.possuiStatus() && !historicoStatusAtual.getStatus().equals(status)) {
				historicoStatusAtual.encerrarVigencia();
				historicoStatus.add(new HistoricoStatusContribuinte(status));
			} else {
				historicoStatus.add(new HistoricoStatusContribuinte(status));
			}
		}
	}
		
	private HistoricoStatusContribuinte obterHistoricoStatusAtual() {
		return Historico.obterHistoricoAtual(historicoStatus) != null
				? (HistoricoStatusContribuinte) Historico.obterHistoricoAtual(historicoStatus)
				: new HistoricoStatusContribuinte();
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarConsistenciaDoCpf();
		validarTamanhoMaximoDeDados();
	}
	
	private void validarObrigatoriedade() {
		if (historicoStatus.isEmpty()){
			adicionarErro("Insira um Status.");
		}
		if (nomeEmpresa == null
				|| (nomeEmpresa != null && nomeEmpresa.isEmpty())) {
			adicionarErro("Insira o Nome/Empresa.");
		}
		endereco.validarObrigatoriedadeETamanhoMaximoDeDadosSemZona();
		if (!endereco.validado()) {
			adicionarErro(endereco.obterErros());
		}
		if (valorContribuicao == null
				|| (valorContribuicao != null && valorContribuicao.equals(DinheiroUtils.obterDinheiroInvalido()))) {
			adicionarErro("Insira um valor de contribuição válido.");
		}
	}
	
	protected void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(nomeEmpresa, TamanhoMaximoGenerico.NOME)) {
			adicionarErro("Insira a Nome/Empresa contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
	}

	@Override
	public String toString() {
		return "Contribuinte [id=" + id + ", dataCadastro=" + DataHoraUtils.formatarData(dataCadastro)
				+ ", nomeEmpresa=" + nomeEmpresa + ", prontuario=" + prontuario + ", endereco=" + endereco
				+ ", indicadoPor=" + indicadoPor + ", frequencia=" + frequencia + ", valorContribuicao="
				+ valorContribuicao + ", contato=" + contato + ", historicoStatus=" + historicoStatus + ", cpf=" + cpf + "]";
	}
}
