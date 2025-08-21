package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.Participacao;
import br.laramara.ti.sislaraserver.dominio.atendimento.ParticipacaoDetalhada;

public class ContextoParticipacaoDetalhada {

	public static ParticipacaoDetalhada contruirParticipacaoDetalhada() {
		ParticipacaoDetalhada participacaoDetalhada = new ParticipacaoDetalhada();
		participacaoDetalhada.setParticipacao(Participacao.COM_FAMILIA);
		participacaoDetalhada.setQuantidade("1");
		return participacaoDetalhada;
	}

	public static ParticipacaoDetalhadaDTO contruirParticipacaoDetalhadaDto() {
		ParticipacaoDetalhadaDTO participacaoDetalhadaDto = new ParticipacaoDetalhadaDTO();
		participacaoDetalhadaDto.setParticipacaoDto(new ParticipacaoDTO(
				Participacao.APENAS_FAMILIA.toString()));
		participacaoDetalhadaDto.setQuantidade("1");
		return participacaoDetalhadaDto;
	}
}
