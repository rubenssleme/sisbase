package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.TipoAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.TipoAtendimento;

public class FabricaTipoAtendimento extends
		FabricaBase<TipoAtendimentoDTO, TipoAtendimento> {

	public final TipoAtendimento converterParaDominio(
			TipoAtendimentoDTO tipoAtendimentoDto) {
		TipoAtendimento tipoAtendimento = null;
		if (tipoAtendimentoDto != null) {
			tipoAtendimento = new TipoAtendimento();
			tipoAtendimento.setId(tipoAtendimentoDto.getId());
			tipoAtendimento.setNome(tipoAtendimentoDto.toString());
			tipoAtendimento
					.setDescricoesTipoAtendimento(new FabricaDescricaoTipoAtendimento()
							.converterParaDominio(tipoAtendimentoDto
									.getDescricoesTipoAtendimentoDto()));
		}
		return tipoAtendimento;
	}

	public final TipoAtendimentoDTO converterParaDTO(
			TipoAtendimento tipoAtendimento) {
		TipoAtendimentoDTO tipoAtendimentoDto = converterSemListaParaDTO(tipoAtendimento);
		tipoAtendimentoDto
				.setDescricoesTipoAtendimentoDto(new FabricaDescricaoTipoAtendimento()
						.converterParaDTO(tipoAtendimento
								.getDescricoesTipoAtendimentoAtivos()));
		return tipoAtendimentoDto;
	}

	public final TipoAtendimentoDTO converterSemListaParaDTO(
			TipoAtendimento tipoAtendimento) {
		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(tipoAtendimento.getId());
		tipoAtendimentoDto.setNome(tipoAtendimento.getNome());
		return tipoAtendimentoDto;
	}
}
