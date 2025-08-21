package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@DiscriminatorValue(value = TipoPendencia.DescricaoTipo.ATENDIMENTO_INDIVIDUAL_PRE_CADASTRO)
public class PendenciaAtendimentoIndividualPreCadastro extends PendenciaAtendimentoIndividual implements AtendimentoIndividualReferenciavel, Agendavel{
	
	public PendenciaAtendimentoIndividualPreCadastro() {
	}
	
	public PendenciaAtendimentoIndividualPreCadastro(Agendamento agendamento,
			List<Profissional> profissionaisAfetados) {
		super();
		this.agendamento = agendamento;
		this.profissionaisAfetados = profissionaisAfetados;
	}

	public AtendimentoIndividual obterAtendimentoIndividualReferenciaParaGeracao(){
		AtendimentoIndividual atendimentoIndividual = obterAtendimentoIndividualReferenciaParaGeracao(agendamento);
		atendimentoIndividual.setPreCadastro(agendamento.getPreCadastro());
		return atendimentoIndividual;
	}
	
	public boolean possuiUsuarioCriadoPeloPreCadastro() {
		return agendamento.getPreCadastro().possuiUsuarioAssociado();
	}
	
	public Usuario obterUsuarioCriadoPeloPreCadastro(){
		return agendamento.obterUsuarioCriadoPeloPreCadastro();
	}
	
	public PreCadastro obterPreCadastroDoAgendamento() {
		return agendamento.getPreCadastro();
	}
	
	@Override
	public boolean estaAgendado() {
		return agendamento.estaAgendado();
	}

	@Override
	public String obterDescricaoPendencia() {
		return "Atendimento individual do pré-cadastro "
				+ agendamento.getPreCadastro().getInformacaoEssencial().getNome() + obterTextoDescricao();
	}

	@Override
	public String toString() {
		return "PendenciaAtendimentoIndividualPreCadastro [" + super.toString() + "]";
	}
}
