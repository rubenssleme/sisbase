package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoCopiaAtendimentoIndividualDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoCopiaAtendimentoIndividual;

public class FabricaEspecificacaoCopiaAtendimentoIndividual
		extends
		FabricaBase<EspecificacaoCopiaAtendimentoIndividualDTO, EspecificacaoCopiaAtendimentoIndividual> {

	@Override
	public EspecificacaoCopiaAtendimentoIndividualDTO converterParaDTO(
			EspecificacaoCopiaAtendimentoIndividual especificacaoCopiaAtendimentoIndividual) {
		return null;
	}

	@Override
	public EspecificacaoCopiaAtendimentoIndividual converterParaDominio(
			EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDto) {
		EspecificacaoCopiaAtendimentoIndividual especificacaoGeracaoCopiaAgendamento = new EspecificacaoCopiaAtendimentoIndividual();
		especificacaoGeracaoCopiaAgendamento
				.setHorario(new FabricaHorario()
						.converterParaDominio(especificacaoCopiaAtendimentoIndividualDto
								.getHorarioDto()));
		return especificacaoGeracaoCopiaAgendamento;
	}
}
