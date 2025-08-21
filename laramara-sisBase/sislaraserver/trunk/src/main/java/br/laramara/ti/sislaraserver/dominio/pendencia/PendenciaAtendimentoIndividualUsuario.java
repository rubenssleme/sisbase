package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@Entity
@DiscriminatorValue(value = TipoPendencia.DescricaoTipo.ATENDIMENTO_INDIVIDUAL_USUARIO)
public class PendenciaAtendimentoIndividualUsuario extends PendenciaAtendimentoIndividual implements AtendimentoIndividualReferenciavel, Agendavel{

	@Column(name = "prontuario")
	private Long prontuario;
	
	public PendenciaAtendimentoIndividualUsuario() {
	}
	
	public PendenciaAtendimentoIndividualUsuario(Long prontuario, Agendamento agendamento,
			List<Profissional> profissionaisAfetados) {
		super();
		this.prontuario = prontuario;
		this.agendamento = agendamento;
		this.profissionaisAfetados = profissionaisAfetados;
	}

	public Long getProntuario() {
		return prontuario;
	}

	public AtendimentoIndividual obterAtendimentoIndividualReferenciaParaGeracao(){
		AtendimentoIndividual atendimentoIndividual = obterAtendimentoIndividualReferenciaParaGeracao(agendamento);
		atendimentoIndividual.setUsuario(agendamento.getUsuario());
		return atendimentoIndividual;
	}

	@Override
	public boolean estaAgendado() {
		return agendamento.estaAgendado();
	}

	@Override
	public String obterDescricaoPendencia() {
		return "Atendimento individual do Prontuário " + prontuario + obterTextoDescricao();
	}

	@Override
	public String toString() {
		return "PendenciaAtendimentoIndividualUsuario [" + super.toString() + ", prontuario=" + prontuario + "]";
	}
}
