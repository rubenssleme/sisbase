package br.laramara.ti.sislaraserver.dominio.retirada;

import java.util.ArrayList;
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

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;

@Entity
@Table(name = "retirada")
public class Retirada extends Validavel implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "prontuario")
	private Long prontuario;
	
	@ManyToOne
	@JoinColumn(name = "id_profissional")
	private Profissional profissional;

	@ManyToOne
	@JoinColumn(name = "id_voluntario")
	private Profissional voluntario;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "retirada_historico_status_retirada", joinColumns = { @JoinColumn(name = "id_retirada", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_historico_status_retirada", referencedColumnName = "id") })
	private List<HistoricoStatusRetirada> historicoStatus;

	public Retirada(){
		this.historicoStatus = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public Long getProntuario() {
		return prontuario;
	}

	public void setProntuario(Long prontuario) {
		this.prontuario = prontuario;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Profissional getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Profissional voluntario) {
		this.voluntario = voluntario;
	}

	private void setContaAcessoResponsavelPorOperacao(
			ContaAcesso contaAcessoOperacao, StatusRetirada statusRetirada) {
		HistoricoStatusRetirada historicoStatusRetirada = obterHistoricoStatusRetiradaAtual();
		historicoStatusRetirada.setContaAcesso(contaAcessoOperacao, statusRetirada);
		historicoStatus.add(historicoStatusRetirada);
	}
	
	public void setContaAcessoResponsavelPorOperacaoRetirada(
			ContaAcesso contaAcessoOperacao) {
		setContaAcessoResponsavelPorOperacao(contaAcessoOperacao, StatusRetirada.RETIRADO);
	}
	
	public void setContaAcessoResponsavelPorOperacaoBaixa(
			ContaAcesso contaAcesso) {
		HistoricoStatusRetirada historicoStatusRetiradaAtual = obterHistoricoStatusRetiradaAtual();
		historicoStatusRetiradaAtual.encerrarVigencia();
		setContaAcessoResponsavelPorOperacao(contaAcesso,
				StatusRetirada.DEVOLVIDO);
	}

	public StatusRetirada obterStatusRetiradaAtual() {
		return ((HistoricoStatusRetirada) obterHistoricoStatusRetiradaAtual())
				.getStatus();
	}
	
	public HistoricoStatusRetirada obterHistoricoStatusRetiradaAtual() {
		return Historico.obterHistoricoAtual(historicoStatus) != null ? (HistoricoStatusRetirada) Historico
				.obterHistoricoAtual(historicoStatus)
				: new HistoricoStatusRetirada();
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (prontuario == null){
			adicionarErro("Insira um número de Prontuário.");
		}
		if (profissional == null){
			adicionarErro("Selecione um Profissional.");
		}
	}

	@Override
	public String toString() {
		return "Retirada [id=" + id + ", prontuario=" + prontuario
				+ ", profissional=" + profissional + ", voluntario="
				+ voluntario + ", historicoStatus=" + historicoStatus + "]";
	}
}
