package br.laramara.ti.sislaraserver.dominio.agenda;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.utilitarios.EntidadeUtils;

@Entity
@Table(name = "agendamento")
public class Agendamento extends Validavel implements Identificavel, ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_pre_cadastro")
	private PreCadastro preCadastro;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "agendamento_grupo", joinColumns = { @JoinColumn(name = "id_agendamento", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_grupo", referencedColumnName = "id") })
	private List<Grupo> grupos;

	@ManyToOne
	@JoinColumn(name = "id_profissional")
	private Profissional profissional;

	@ManyToOne
	@JoinColumn(name = "id_descricao_tipo_atendimento", nullable = false)
	private DescricaoTipoAtendimento descricaoTipoAtendimento;
	
	@ManyToOne
	@JoinColumn(name = "id_modulo", nullable = false)
	private Modulo modulo;

	@Enumerated(EnumType.STRING)
	@Column(name = "id_setor", length = TamanhoMaximoGenerico.SETOR, nullable = false)
	private Setor setor;

	@OneToOne
	@JoinColumn(name = "id_local_atendimento")
	private LocalAtendimento localAtendimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Calendar data;

	private Horario horario;

	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	private String obs;

	@Enumerated(EnumType.STRING)
	@Column(name = "reserva_para", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	private Status reservaPara;

	@ManyToOne
	@JoinColumn(name = "id_motivo_cancelamento")
	private MotivoCancelamento motivoCancelamento;
	
	@Column(name = "justificativa_cancelamento", length = TamanhoMaximoGenerico.OBS)
	private String justificativaCancelamento;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "agendamento_historico_status_agendamento", joinColumns = { @JoinColumn(name = "id_agendamento", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_historico_status_agendamento", referencedColumnName = "id") })
	private List<HistoricoStatusAgendamento> historicoStatus;
	
	@Column(name = "originado_por_excesso_de_faltas")
	private boolean originadoPorExcessoDeFaltas;
	
	@Transient
	private ContaAcesso contaAcessoOperacao;

	public Agendamento() {
		this(null);
	}
	
	public Agendamento(ContaAcesso contaAcesso) {
		horario = new Horario();
		grupos = new ArrayList<>();
		historicoStatus = new ArrayList<>();
		contaAcessoOperacao = contaAcesso;
		inicializarStatus();
	}
	
	public void preparaParaLiberar(ContaAcesso contaAcesso){
		this.usuario = null;
		this.preCadastro = null;
		this.obs = null;
		limparDadosBase(contaAcesso);
		inicializarStatus();
	}
	
	public void prepararParaCopia(Calendar data, Horario horario, ContaAcesso contaAcesso){
		this.data = data;
		this.horario = horario;
		limparDadosBase(contaAcesso);
		inicializarStatus();
	}
	
	private void limparDadosBase(ContaAcesso contaAcesso){
		this.id = null;
		this.motivoCancelamento = null;
		this.justificativaCancelamento = null;
		this.historicoStatus = new ArrayList<>();
		this.contaAcessoOperacao = contaAcesso;
	}

	private void inicializarStatus() {
		StatusAgendamento status = null;
		if (usuario == null && preCadastro == null && grupos.isEmpty()
				&& motivoCancelamento == null) {
			status = StatusAgendamento.DISPONIVEL;
		} else if (motivoCancelamento != null) {
			status = StatusAgendamento.CANCELADO;
		} else {
			status = StatusAgendamento.AGENDADO;
		}
		adicionarStatusAgendamento(status);
	}

	public void setContaAcessoResponsavelPorOperacao(ContaAcesso contaAcessoOperacao) {
		HistoricoStatusAgendamento historicoStatusAgendamento = obterHistoricoStatusAgendamentoAtual();
		historicoStatusAgendamento.setContaAcesso(contaAcessoOperacao);
		this.contaAcessoOperacao = contaAcessoOperacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		inicializarStatus();
	}

	public PreCadastro getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(PreCadastro preCadastro) {
		this.preCadastro = preCadastro;
		inicializarStatus();
	}
	
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
		inicializarStatus();
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
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

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public LocalAtendimento getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(LocalAtendimento localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public StatusAgendamento getStatusAtual() {
		return obterHistoricoStatusAgendamentoAtual().getStatus();
	}

	public String getData() {
		return DataHoraUtils.formatarData(data);
	}

	public Calendar getDataCalendario() {
		return data;
	}

	public void setData(String data) {
		this.data = DataHoraUtils.obterDataValidaInvalidaOuNulo(data);
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	public DiaSemana getDiaSemana() {
		return DiaSemana.obterDiaSemana(data);
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public MotivoCancelamento getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(MotivoCancelamento motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
		inicializarStatus();
	}

	public String getJustificativaCancelamento() {
		return justificativaCancelamento;
	}

	public void setJustificativaCancelamento(String justificativaCancelamento) {
		this.justificativaCancelamento = justificativaCancelamento;
	}

	public Status getReservaPara() {
		return reservaPara;
	}

	public void setReservaPara(Status reservaPara) {
		this.reservaPara = reservaPara;
	}

	public void aplicarStatus(StatusAgendamento statusAgendamento,
			ContaAcesso contaAcessoResponsavelOperacao) {
		this.contaAcessoOperacao = contaAcessoResponsavelOperacao;
		adicionarStatusAgendamento(statusAgendamento);
	}
	
	public void cancelar(ContaAcesso contaAcessoResponsavelOperacao) {
		this.contaAcessoOperacao = contaAcessoResponsavelOperacao;
		adicionarStatusAgendamento(StatusAgendamento.CANCELADO);
	}
	
	public boolean possuiDataAnteriorDataAtual() {
		return DataHoraUtils.dataAnteriorDataAtual(data);
	}
	
	public void adicionarStatusAgendamento(StatusAgendamento status) {
		HistoricoStatusAgendamento historicoStatusAgendamentoAtual = obterHistoricoStatusAgendamentoAtual();
		if (!historicoStatusAgendamentoAtual.possuiStatus() || !historicoStatusAgendamentoAtual
						.getStatus().equals(status)) {
			historicoStatusAgendamentoAtual.encerrarVigencia();
			this.historicoStatus.add(new HistoricoStatusAgendamento(status,
					contaAcessoOperacao));
		}
		if (!historicoStatusAgendamentoAtual.possuiContaAcesso()){
			historicoStatusAgendamentoAtual.setContaAcesso(contaAcessoOperacao);
		}
	}

	public boolean isOriginadoPorExcessoDeFaltas() {
		return originadoPorExcessoDeFaltas;
	}

	public void setOriginadoPorExcessoDeFaltas(boolean originadoPorExcessoDeFaltas) {
		this.originadoPorExcessoDeFaltas = originadoPorExcessoDeFaltas;
	}

	public boolean possuiUsuario() {
		return usuario != null;
	}
	
	public HistoricoStatusAgendamento obterHistoricoStatusAgendamentoAtual() {
		return Historico.obterHistoricoAtual(historicoStatus) != null ? (HistoricoStatusAgendamento) Historico
				.obterHistoricoAtual(historicoStatus)
				: new HistoricoStatusAgendamento();
	}

	public String getHistoricoOperacoes() {
		return Historico.getHistoricoOperacoes(historicoStatus);
	}
	
	private boolean conflitoHora(Agendamento agendamento) {
		return conflitoHora(agendamento.getHorario(), this.getHorario());
	}
	
	private boolean possuiMesmaDataEProfissional(Agendamento agendamento) {
		return this.getProfissional().possuiIdentificacaoIgual(
				agendamento.getProfissional())
				&& this.data.equals(agendamento.getDataCalendario());
	}
	
	public boolean possuiPreCadastro() {
		return preCadastro != null;
	}
	
	public boolean eDescricaoServicoSocial() {
		return descricaoTipoAtendimento.eServicoSocial();
	}
	
	public boolean eDescricaoPsicologia() {
		return descricaoTipoAtendimento.ePsicologia();
	}

	public boolean eDescricaoAvaliacaoServicoAtencaoEspecializadaOrtoptica() {
		return descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOrtoptica();
	}
	
	public boolean eModuloAvaliacaoETriagem() {
		return modulo.eAvaliacaoETriagem();
	}
	
	public boolean eModuloAtendimentoEspecificoEspecializado() {
		return modulo.eAtendimentoEspecificoEspecializado();
	}

	public boolean estaAgendadoOuReagendado() {
		return estaAgendado() || estaReagendado();
	}

	public boolean estaCancelado(){
		return this.getStatusAtual().equals(StatusAgendamento.CANCELADO);
	}
	
	public boolean estaDisponivel(){
		return this.getStatusAtual().equals(StatusAgendamento.DISPONIVEL);
	}
	
	public boolean estaAgendado() {
		return this.getStatusAtual().equals(StatusAgendamento.AGENDADO);
	}
	
	public boolean estaReagendado() {
		return this.getStatusAtual().equals(StatusAgendamento.REAGENDADO);
	}

	public boolean expirouLimiteDe24Horas() {
		return DataHoraUtils.expirouLimite24Horas(data, horario.getHoraInicioTime());
	}
	
	public String obterIdadeDoUsuarioOuPreCadastro() {
		return EntidadeUtils.obterIdadeDeUsuarioOuPreCadastro(usuario, preCadastro);
	}

	public boolean conflito(Agendamento agendamento) {
		return (possuiMesmaDataEProfissional(agendamento)
				&& conflitoHora(agendamento) && !estaCancelado() && !estaReagendado());
	}

	public boolean possuiDescricaoEModuloIdentico(Agendamento agendamentoNovo) {
		return descricaoTipoAtendimento.equals(agendamentoNovo.getDescricaoTipoAtendimento())
				&& modulo.equals(agendamentoNovo.getModulo());
	}

	public Usuario obterUsuarioCriadoPeloPreCadastro() {
		if (preCadastro != null) {
			return preCadastro.obterUsuarioCriadoPeloPreCadastro();
		} else {
			return null;
		}
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (usuario != null && preCadastro != null) {
			adicionarErro("Inserir somente um Usuário ou um Pré-cadastro.");
		}
		if (obterHistoricoStatusAgendamentoAtual().getStatus().equals(
				StatusAgendamento.CANCELADO)
				&& motivoCancelamento == null) {
			adicionarErro("Insira um motivo de cancelamento.");
		}
		validarRestricaoSetor();
	}

	public void validarRestricaoSetor() {
		if (usuario != null && !usuario.possuiSetorVigente(getSetor())
				&& ((descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOftalmologia()
						|| descricaoTipoAtendimento.eAcompanhamentoServicoAtencaoEspecializadaOftalmologia())
						|| descricaoTipoAtendimento.eAvaliacaoFuncional())) {
			adicionarErro("Não é possível agendar um usuário de setor incompatível com o solicitado pelo agendamento.");
		}
	}

	public void validarRestricaoAssociacaoPreCadastro(
			RestricaoAssociacaoPreCadastro restricaoAssociacaoPreCadastro) {
		if (preCadastro != null && restricaoAssociacaoPreCadastro.equals(RestricaoAssociacaoPreCadastro.SIM)
				&& !(descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOftalmologia() && modulo.eAtendimentoEspecificoEspecializado() 
					|| (descricaoTipoAtendimento.eServicoSocial() && modulo.eAvaliacaoETriagem()))) {
			adicionarErro("É possível associar somente Oftalmologia ou Servico Social.");
		}
	}
	
	@Override
	public String toString() {
		return "Agendamento [id=" + id + ", usuario=" + usuario
				+ ", preCadastro=" + preCadastro + ", grupos=" + grupos
				+ ", profissional=" + profissional
				+ ", descricaoTipoAtendimento=" + descricaoTipoAtendimento
				+ ", modulo=" + modulo
				+ ", setor=" + setor + ", localAtendimento=" + localAtendimento
				+ ", data=" + DataHoraUtils.formatarData(data) + ", horario="
				+ horario + ", obs=" + obs + ", status=" + getStatusAtual()
				+ ", motivoCancelamento=" + motivoCancelamento
				+ ", reservaPara=" + reservaPara + ", originadoPorExcessoDeFaltas=" + originadoPorExcessoDeFaltas + "]";
	}
}
