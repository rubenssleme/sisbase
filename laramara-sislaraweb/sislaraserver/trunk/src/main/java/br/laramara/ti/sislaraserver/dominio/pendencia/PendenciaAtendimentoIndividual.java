package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoProfissional;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@MappedSuperclass
public abstract class PendenciaAtendimentoIndividual extends Pendencia implements Comparavel {
	
	@ManyToOne
	@JoinColumn(name = "id_agendamento")
	protected Agendamento agendamento;
	
	public boolean eDescricaoAvaliacaoServicoAtencaoEspecializadaOrtopticaModuloAtendimentoEspecificoEspecializado() {
		return agendamento != null && agendamento.eDescricaoAvaliacaoServicoAtencaoEspecializadaOrtoptica()
				&& agendamento.eModuloAtendimentoEspecificoEspecializado();
	}
	
	public boolean eDescricaoServicoSocialModuloAvaliacaoETriagem() {
		return agendamento != null && agendamento.eDescricaoServicoSocial() && agendamento.eModuloAvaliacaoETriagem();
	}

	public boolean eDescricaoPsicologiaModuloAvaliacaoETriagem() {
		return agendamento != null && agendamento.eDescricaoPsicologia() && agendamento.eModuloAvaliacaoETriagem();
	}
	
	public Usuario obterUsuarioDoAgendamento(){
		return agendamento.getUsuario();
	}
	
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public boolean igualOuAnteriorADataAtual(){
		return igualOuAnteriorADataAtual(agendamento.getDataCalendario());
	}
	
	public Calendar getDataParaComparacao(){
		return agendamento.getDataCalendario();
	}

	protected AtendimentoIndividual obterAtendimentoIndividualReferenciaParaGeracao(Agendamento agendamento){
		AtendimentoProfissional atendimentoProfissional = new AtendimentoProfissional();
		atendimentoProfissional.setProfissional(agendamento.getProfissional());
		InformacaoAtendimento informacaoAtendimento = new InformacaoAtendimento();
		informacaoAtendimento.setFrequencia(Frequencia.AT);
		atendimentoProfissional.setInformacaoAtendimento(informacaoAtendimento);

		AtendimentoIndividual atendimentoIndividual = new AtendimentoIndividual();
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
	
	protected String obterTextoDescricao() {
		return ", Data: " + agendamento.getData() + ", Tipo: "
				+ agendamento.getDescricaoTipoAtendimento().getTipoAtendimento().getNome()
				+ ", Descrição: " + agendamento.getDescricaoTipoAtendimento().getNome() + ", Módulo: "
				+ agendamento.getModulo().getNome();
	}

	@Override
	public String toString() {
		return super.toString() + ", agendamento=" + agendamento;
	}
}
