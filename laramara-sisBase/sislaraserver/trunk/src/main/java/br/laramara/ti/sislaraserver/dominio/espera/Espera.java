package br.laramara.ti.sislaraserver.dominio.espera;

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
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.utilitarios.EntidadeUtils;

@Entity
@Table(name = "espera")
public class Espera extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_pre_cadastro")
	private PreCadastro preCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Calendar data;

	@ManyToOne
	@JoinColumn(name = "id_descricao_tipo_atendimento", nullable = false)
	private DescricaoTipoAtendimento descricaoTipoAtendimento;

	@ManyToOne
	@JoinColumn(name = "id_modulo")
	private Modulo modulo;

	@Enumerated(EnumType.STRING)
	@Column(name = "id_setor", length = TamanhoMaximoGenerico.SETOR, nullable = false)
	private Setor setor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_nome_grupo")
	private NomeGrupo nomeGrupo;
	
	@ManyToOne
	@JoinColumn(name = "id_profissional_solicitado_por")
	private Profissional profissionalSolicitou;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_espera", length = TamanhoMaximoEspera.TIPO_ESPERA)
	private TipoEspera tipoEspera;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "espera_historico_status_espera", joinColumns = { @JoinColumn(name = "id_espera", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_historico_status_espera", referencedColumnName = "id") })
	private List<HistoricoStatusEspera> historicoStatus;

	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	private String obs;
	
	@Column(name = "justificativa_cancelamento", length = TamanhoMaximoGenerico.OBS)
	private String justificativaCancelamento;

	@Column(name = "chave_atendimento_acarretou_inclusao", length = TamanhoMaximoGenerico.CHAVE)
	private String chaveAtendimentoAcarretouInclusao;

	@Column(name = "interesse")
	private boolean interesse;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_ultima_ligacao_interesse")
	private Calendar dataUltimaLigacaoInteresse;
	
	@Column(name = "lm_ligou")
	private boolean lmLigou;
	
	@Column(name = "pendencias")
	private boolean pendencias;
	
	@Transient
	private ContaAcesso contaAcessoOperacao;

	public Espera() {
		historicoStatus = new ArrayList<>();
	}

	private void inicializarHistoricoStatusEspera() {
		if (historicoStatus.isEmpty()) {
			if (verificarSeEDescricaoAvaliacaoOftalmologiaModuloTriagemOftalmologica()) {
				historicoStatus.add(new HistoricoStatusEspera(
						StatusEspera.TRIANDO, contaAcessoOperacao));
			} else {
				historicoStatus.add(new HistoricoStatusEspera(
						StatusEspera.AGUARDANDO, contaAcessoOperacao));
			}
		}
	}
	
	private boolean verificarSeEDescricaoAvaliacaoOftalmologiaModuloTriagemOftalmologica(){
		return (descricaoTipoAtendimento != null
				&& descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOftalmologia()						
				&& (modulo != null && modulo.eTriagemOftalmologica()));
	}
	
	public String obterDataCadastro() {
		String retorno = "";
		for (HistoricoStatusEspera historico : historicoStatus) {
			if (historico.getStatus().equals(StatusEspera.AGUARDANDO)
					|| historico.getStatus().equals(StatusEspera.TRIANDO)) {
				retorno = DataHoraUtils.formatarDataHora(historico
						.getDataInicialVigencia());
			}
		}
		return retorno;
	}
	
	public boolean possuiMaisDe2AnosTranscorridosNaDataDeExpectativa() {
		return data != null ? DataHoraUtils.obterAnosTranscorridos(data) >= 2 : false;
	}
	
	public String obterIdadeDoUsuarioOuPreCadastro() {
		return EntidadeUtils.obterIdadeDeUsuarioOuPreCadastro(usuario, preCadastro);
	}

	public boolean possuiUsuarioComMenos21Anos() {
		return possuiUsuario() && usuario.possuiMenosDe21Anos();
	}
	
	public StatusEspera obterStatus() {
		return obterHistoricoStatusEsperaAtual().getStatus();
	}
	
	private HistoricoStatusEspera obterHistoricoStatusEsperaAtual(){
		return Historico.obterHistoricoAtual(historicoStatus) != null ? ((HistoricoStatusEspera) Historico
					.obterHistoricoAtual(historicoStatus)) : null;
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
	}
	
	public String obterRg() {
		return preCadastro != null ? preCadastro.getRg() : usuario.getRg();
	}

	public PreCadastro getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(PreCadastro preCadastro) {
		this.preCadastro = preCadastro;
	}

	public String getDataUltimaLigacaoInteresse() {
		return DataHoraUtils.formatarData(dataUltimaLigacaoInteresse);
	}

	public String getDataExpectativa() {
		return DataHoraUtils.formatarData(data);
	}
	
	public Calendar getDataExpectativaCalendar() {
		return data;
	}
	
	public String getChaveAtendimentoAcarretouInclusao() {
		return chaveAtendimentoAcarretouInclusao;
	}

	public void setChaveAtendimentoAcarretouInclusao(String chaveAtendimentoAcarretouInclusao) {
		this.chaveAtendimentoAcarretouInclusao = chaveAtendimentoAcarretouInclusao;
	}

	public void setDataExpectativa(String data) {
		this.data = DataHoraUtils.obterDataValidaInvalidaOuNulo(data);
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

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public NomeGrupo getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(NomeGrupo nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}
	
	public Profissional getProfissionalSolicitou() {
		return profissionalSolicitou;
	}

	public void setProfissionalSolicitou(Profissional profissionalSolicitou) {
		this.profissionalSolicitou = profissionalSolicitou;
	}

	public TipoEspera getTipoEspera() {
		return tipoEspera;
	}

	public void setTipoEspera(TipoEspera tipoEspera) {
		this.tipoEspera = tipoEspera;
	}
	
	public boolean isInteresse() {
		return interesse;
	}

	public void setInteresse(boolean interesse) {
		this.interesse = interesse;
		if (this.interesse && dataUltimaLigacaoInteresse == null) {
			this.dataUltimaLigacaoInteresse = MaquinaTempo.obterInstancia().obterCalendarioAtual();
		}
		if (!this.interesse && dataUltimaLigacaoInteresse != null) {
			this.dataUltimaLigacaoInteresse = null;
		}
	}
	
	public boolean isLmLigou() {
		return lmLigou;
	}

	public void setLmLigou(boolean lmLigou) {
		this.lmLigou = lmLigou;
	}

	public boolean isPendencias() {
		return pendencias;
	}

	public void setPendencias(boolean pendencias) {
		this.pendencias = pendencias;
	}

	public String getObs() {
		return obs;
	}

	public void adicionarObs(String obs) {
		if (this.obs == null) {
			this.obs = obs;
		} else {
			this.obs += "\n" + obs;
		}
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public Usuario obterUsuarioCriadoPeloPreCadastro() {
		if (preCadastro != null)
			return preCadastro.obterUsuarioCriadoPeloPreCadastro();
		else {
			return null;
		}
	}

	public List<Setor> obterSetoresUsuario() {
		List<Setor> setores = new ArrayList<>();
		if (usuario != null) {
			setores = usuario.obterHistoricosSetorVigentes();
		}
		return setores;
	}
	
	public String getJustificativaCancelamento() {
		return justificativaCancelamento;
	}

	public void setJustificativaCancelamento(String justificativaCancelamento) {
		this.justificativaCancelamento = justificativaCancelamento;
	}

	public void setContaAcessoOperacao(ContaAcesso contaAcessoOperacao) {
		this.contaAcessoOperacao = contaAcessoOperacao;
		inicializarHistoricoStatusEspera();
	}

	public boolean dentroDoPeriodo(Calendar dataInicio, Calendar dataTermino) {
		if (dataInicio != null && dataTermino == null) {
			dataTermino = dataInicio;
		}
		if (dataInicio == null && dataTermino != null) {
			dataInicio = dataTermino;
		}
		if (dataInicio == null && dataTermino == null){
			return true;
		}
		if (getDataExpectativaCalendar() != null
				&& (DataHoraUtils.dataIgualOuPosterior(getDataExpectativaCalendar(), dataInicio)
						&& DataHoraUtils.dataIgualOuAnterior(getDataExpectativaCalendar(), dataTermino))) {
			return true;
		} else {
			return false;
		}
	}

	public void atribuirDataAtualNaDataExpectativa() {
		this.data = MaquinaTempo.obterInstancia().obterCalendarioAtual();
	}

	public boolean eDescricaoServicoSocialEModuloExcessoDeFaltas() {
		return descricaoTipoAtendimento.eServicoSocial() && modulo.eExcessoDeFaltas();
	}
	
	private boolean estaAgendado() {
		return obterStatus().equals(StatusEspera.AGENDADO);
	}

	public boolean estaCancelado() {
		return obterStatus().equals(StatusEspera.CANCELADO);
	}
	
	public boolean estaTriando() {
		return obterStatus().equals(StatusEspera.TRIANDO);
	}
	
	public boolean naoEstaTriandoEPossuiPendencias(){
		return !historicoStatus.isEmpty() && !estaTriando() && pendencias;
	}
	
	public boolean estaAguardando() {
		return obterStatus().equals(StatusEspera.AGUARDANDO);
	}
	
	public void cancelar(ContaAcesso contaAcesso) {
		adicionarStatus(StatusEspera.CANCELADO, contaAcesso);
	}
	
	public boolean possuiUsuario() {
		return usuario != null;
	}
	
	public boolean possuiAlgumaDescricaoOuModuloDeAvaliacaoCTO() {
		return (descricaoTipoAtendimento.eAvaliacaoFuncional() && modulo.eAtendimentoEspecificoEspecializado())
				|| (descricaoTipoAtendimento.eAvaliacaoServicoAtencaoEspecializadaOftalmologia() && modulo.eAtendimentoEspecificoEspecializado())
				|| (descricaoTipoAtendimento.eServicoSocial() && modulo.eAvaliacaoETriagem());
	}

	public boolean possuiMesmaChaveAtendimentoAcarretouInclusao(Espera esperaExistente) {
		return esperaExistente.getChaveAtendimentoAcarretouInclusao() != null
				? getChaveAtendimentoAcarretouInclusao().equals(esperaExistente.getChaveAtendimentoAcarretouInclusao())
				: false;
	}
	
	public void agendado(ContaAcesso contaAcesso) {
		if (verificarSeEDescricaoAvaliacaoOftalmologiaModuloTriagemOftalmologica()
				&& (obterHistoricoStatusEsperaAtual() != null && obterHistoricoStatusEsperaAtual()
						.getStatus().equals(StatusEspera.TRIANDO))) {
			adicionarStatus(StatusEspera.AGUARDANDO, contaAcesso);
		} else {
			adicionarStatus(StatusEspera.AGENDADO, contaAcesso);
		}
	}
	
	private void adicionarStatus(StatusEspera statusEspera, ContaAcesso contaAcesso){
		this.contaAcessoOperacao = contaAcesso;
		inicializarHistoricoStatusEspera();
		HistoricoStatusEspera historioStatusEsperaAtual = obterHistoricoStatusEsperaAtual();
		historioStatusEsperaAtual.encerrarVigencia();
		historicoStatus.add(new HistoricoStatusEspera(statusEspera,
				contaAcessoOperacao));
	}

	private boolean ultimoResponsavelPorMudancaDeStatusEDaOftalmologia() {
		return obterHistoricoStatusEsperaAtual().getContaAcesso().oftalmologia();
	}
	
	public String getHistoricoOperacoes() {
		return Historico.getHistoricoOperacoes(historicoStatus);
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if ((usuario == null && preCadastro == null)
				|| (usuario != null && preCadastro != null)) {
			adicionarErro("Insira um Usuário ou Pré-Cadastro.");
		}
		if (data == null || DataHoraUtils.dataInvalida(data)) {
			adicionarErro("Insira a Data de Expectativa válida.");
		}
		if (descricaoTipoAtendimento == null) {
			adicionarErro("Insira uma Descrição de Tipo de Atendimento.");
		}
		if (modulo == null){
			adicionarErro("Insira um Módulo/Atividade.");
		}
		if (setor == null) {
			adicionarErro("Insira o Setor.");
		}
		if (estaCancelado()  && (justificativaCancelamento == null || justificativaCancelamento.trim().isEmpty())) {
			adicionarErro("Adicione uma justificativa.");
		}
		if (estaAgendado()  && (obs == null || obs.trim().isEmpty())) {
			adicionarErro("Adicione uma observação.");
		}
		if (contaAcessoOperacao == null){
			adicionarErro("Insira um Conta Acesso reponsável pela Operação");
		}
		if ((verificarSeEDescricaoAvaliacaoOftalmologiaModuloTriagemOftalmologica()
				&& (estaAguardando() || estaCancelado()) && !ultimoResponsavelPorMudancaDeStatusEDaOftalmologia())) {
			adicionarErro("Somente a oftalmologia tem autorização para concluir ou cancelar a triagem.");
		}
		if (naoEstaTriandoEPossuiPendencias()){
			adicionarErro("Não é possível existirem pendencias em espera com status diferente de TRIANDO.");
		}
		validarTamanhoMaximoDeDados();
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira uma Observação contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
		}
		
		if (tamanhoMaximoViolado(justificativaCancelamento, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira uma Justificativa de Cancelamento contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
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
		Espera other = (Espera) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Espera [id=" + id + ", usuario=" + usuario + ", preCadastro="
				+ preCadastro + ", data=" + DataHoraUtils.formatarData(data)
				+ ", descricaoTipoAtendimento=" + descricaoTipoAtendimento
				+ ", modulo=" + modulo + ", nomeGrupo=" + nomeGrupo
				+ ", setor=" + setor + ", tipoEspera=" + tipoEspera
				+ ", historicoStatus=" + historicoStatus + ", obs=" + obs 
				+ ", interesse=" + interesse  + ", lmLigou=" + lmLigou + ", pendencias=" + pendencias 
				+ ", chaveAtendimentoAcarretouInclusao=" + chaveAtendimentoAcarretouInclusao + ", dataUltimaLigacaoInteresse= " + DataHoraUtils.formatarData(dataUltimaLigacaoInteresse) + "]";
	}
}
