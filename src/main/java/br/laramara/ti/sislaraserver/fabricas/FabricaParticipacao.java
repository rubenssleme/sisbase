package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.Participacao;

public class FabricaParticipacao extends
		FabricaBase<ParticipacaoDTO, Participacao> {
	public final ParticipacaoDTO converterParaDTO(Participacao participacao) {
		return participacao != null ? new ParticipacaoDTO(
				participacao.toString()) : null;
	}

	public final Participacao converterParaDominio(ParticipacaoDTO participacao) {
		return participacao != null ? Participacao.valueOf(Participacao.class,
				participacao.toString()) : null;
	}
}
