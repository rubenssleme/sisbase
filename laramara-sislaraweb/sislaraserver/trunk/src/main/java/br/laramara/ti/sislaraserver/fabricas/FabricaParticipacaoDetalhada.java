package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.ParticipacaoDetalhada;

public class FabricaParticipacaoDetalhada extends FabricaBase<ParticipacaoDetalhadaDTO, ParticipacaoDetalhada> {

	@Override
	public ParticipacaoDetalhada converterParaDominio(ParticipacaoDetalhadaDTO participacaoDetalhadaDto) {
		ParticipacaoDetalhada participacaoDetalhada = null;
		if (participacaoDetalhadaDto != null) {
			participacaoDetalhada = new ParticipacaoDetalhada();
			if (participacaoDetalhadaDto.getId() != null) {
				participacaoDetalhada.setId(participacaoDetalhadaDto.getId());
			}
			participacaoDetalhada.setParticipacao(
					new FabricaParticipacao().converterParaDominio(participacaoDetalhadaDto.getParticipacaoDto()));
			participacaoDetalhada.setQuantidade(participacaoDetalhadaDto.getQuantidade());
		}
		return participacaoDetalhada;
	}

	@Override
	public ParticipacaoDetalhadaDTO converterParaDTO(ParticipacaoDetalhada participacaoDetalhada) {
		ParticipacaoDetalhadaDTO participacaoDetalhadaDto = null;
		if (participacaoDetalhada != null) {
			participacaoDetalhadaDto = new ParticipacaoDetalhadaDTO();
			participacaoDetalhadaDto.setId(participacaoDetalhada.getId());
			participacaoDetalhadaDto.setParticipacaoDto(
					new FabricaParticipacao().converterParaDTO(participacaoDetalhada.getParticipacao()));
			participacaoDetalhadaDto.setQuantidade(participacaoDetalhada.getQuantidade());
		}
		return participacaoDetalhadaDto;
	}
}
