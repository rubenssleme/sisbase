package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoProfissional;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "pendencia")
public class Pendencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "prontuario")
	private Long prontuario;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", length = TamanhoMaximoGenerico.TIPO_PENDENCIA, nullable = false)
	private TipoPendencia tipoPendencia;

	@Column(name = "resolvida")
	private boolean resolvida;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pendencia_profissional", joinColumns = {
			@JoinColumn(name = "id_pendencia", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_profissional", referencedColumnName = "id") })
	private List<Profissional> profissionaisAfetados;

	@ManyToOne
	@JoinColumn(name = "id_agendamento")
	private Agendamento agendamento;
	
	@ManyToOne()
	@JoinColumn(name = "id_grupo")
	private Grupo grupo;
	
	@ManyToOne
	@JoinColumn(name = "id_modulo_periodo")
	private ModuloPeriodo moduloPeriodo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	private Calendar data;
	
	private Horario horario;
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	private String obs;
	
	public Pendencia(){
		horario = new Horario();
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

	public void setTipoPendencia(TipoPendencia tipoPendencia) {
		this.tipoPendencia = tipoPendencia;
	}
	
	public TipoPendencia getTipoPendencia() {
		return tipoPendencia;
	}

	public void setModuloPeriodo(ModuloPeriodo moduloPeriodo) {
		this.moduloPeriodo = moduloPeriodo;
	}

	public String obterDescricaoPendencia() {
		return tipoPendencia.obterDescricao(this);
	}
	
	public boolean posterior() {
		return tipoPendencia.posterior(this);
	}
	
	public boolean possuiUsuarioCriadoPeloPreCadastro() {
		return agendamento.getPreCadastro().possuiUsuarioAssociado();
	}
	
	public Usuario obterUsuarioCriadoPeloPreCadastro(){
		return agendamento.obterUsuarioCriadoPeloPreCadastro();
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void marcarComoResolvida() {
		resolvida = true;
		obs = "Removida automaticamente pelo sistema.";
	}

	public void marcarComoResolvida(String mensagem) {
		resolvida = true;
		obs = mensagem;
	}

	public void setProfissionaisAfetados(List<Profissional> profissionaisAfetados) {
		this.profissionaisAfetados = profissionaisAfetados;
	}
	
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	
	public boolean eExcessoDeFaltas() {
		return tipoPendencia.equals(TipoPendencia.ENVIO_LISTA_ESPERA_EXCESSO_DE_FALTAS);
	}
	
	public boolean eReuniaoDeIntegracao() {
		return tipoPendencia.equals(TipoPendencia.TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO)
				|| tipoPendencia.equals(TipoPendencia.INCLUSAO_DE_REUNIAO_DE_INTEGRACAO);
	}

	public boolean eAtendimentoIndividualUsuarioOuPreCadastro() {
		return eAtendimentoIndividualUsuario() || eAtendimentoIndividualPreCadastro();
	}
	
	public boolean eAtendimentoIndividualUsuario() {
		return tipoPendencia.equals(TipoPendencia.ATENDIMENTO_INDIVIDUAL_USUARIO);
	}
	
	public boolean eAtendimentoIndividualPreCadastro() {
		return tipoPendencia.equals(TipoPendencia.ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO);
	}

	public boolean eAtendimentoDeGrupo() {
		return tipoPendencia.equals(TipoPendencia.ATENDIMENTO_GRUPO);
	}
	
	public boolean eDescricaoAvaliacaoServicoAtencaoEspecializadaOrtopticaModuloAtendimentoEspecificoEspecializado() {
		return agendamento != null && agendamento.eDescricaoAvaliacaoServicoAtencaoEspecializadaOrtoptica()
				&& agendamento.eModuloAtendimentoEspecificoEspecializado();
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}

	public Calendar getData() {
		return data;
	}

	public ModuloPeriodo getModuloPeriodo() {
		return moduloPeriodo;
	}
	
	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
	public boolean estaResolvida() {
		return resolvida;
	}

	public boolean eDescricaoServicoSocialModuloAvaliacaoETriagem() {
		return agendamento != null && agendamento.eDescricaoServicoSocial() && agendamento.eModuloAvaliacaoETriagem();
	}

	public boolean eDescricaoPsicologiaModuloAvaliacaoETriagem() {
		return agendamento != null && agendamento.eDescricaoPsicologia() && agendamento.eModuloAvaliacaoETriagem();
	}

	public AtendimentoIndividual obterAtendimentoIndividualReferenciaParaGeracao(){
		AtendimentoProfissional atendimentoProfissional = new AtendimentoProfissional();
		atendimentoProfissional.setProfissional(agendamento.getProfissional());
		InformacaoAtendimento informacaoAtendimento = new InformacaoAtendimento();
		informacaoAtendimento.setFrequencia(Frequencia.AT);
		atendimentoProfissional.setInformacaoAtendimento(informacaoAtendimento);

		AtendimentoIndividual atendimentoIndividual = new AtendimentoIndividual();
		if (agendamento.possuiUsuario()) {
			atendimentoIndividual.setUsuario(agendamento.getUsuario());
		} else if (agendamento.possuiPreCadastro()) {
			atendimentoIndividual.setPreCadastro(agendamento.getPreCadastro());
		}
		atendimentoIndividual.setData(agendamento.getData());
		atendimentoIndividual.setHorario(agendamento.getHorario());
		atendimentoIndividual.setDescricaoTipoAtendimento(agendamento.getDescricaoTipoAtendimento());
		atendimentoIndividual.setModulo(agendamento.getModulo());
		atendimentoIndividual.setSetor(agendamento.getSetor());
		atendimentoIndividual.setAtendimentosProfissionais(Arrays.asList(atendimentoProfissional));
		atendimentoIndividual.getInformacaoAtendimento().setFrequencia(Frequencia.AT);
		atendimentoIndividual.setLocalAtendimento(agendamento.getLocalAtendimento());
		return atendimentoIndividual;
	}

	@Override
	public String toString() {
		return "Pendencia [id=" + id + ", prontuario=" + prontuario + ", tipoPendencia=" + tipoPendencia
				+ ", resolvida=" + resolvida 
				+ ", profissionaisAfetados=" + profissionaisAfetados 
				+ ", agendamento=" + agendamento 
				+ (grupo != null ? ", grupo=" + grupo.obterNomeGrupoETurma() : "") 
				+ (moduloPeriodo != null ? ", moduloPeriodo=" +  moduloPeriodo.getModulo().getNome() : "") 
				+ ", data=" + DataHoraUtils.formatarData(data) 
				+ "]";
	}
}
