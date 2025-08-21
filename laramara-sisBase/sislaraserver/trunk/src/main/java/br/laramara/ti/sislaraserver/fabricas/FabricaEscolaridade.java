package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.EscolaridadeDTO;
import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;

public class FabricaEscolaridade extends FabricaBase<EscolaridadeDTO, Escolaridade> {

	public final EscolaridadeDTO converterParaDTO(
			Escolaridade escolaridade) {
		return escolaridade != null ? new EscolaridadeDTO(escolaridade.getId(), escolaridade.getDescricao(),
				new FabricaSerie().converterParaDTO(escolaridade.getSeries())) : null;
	}

	public final Escolaridade converterParaDominio(
			EscolaridadeDTO escolaridadeDto) {
		return escolaridadeDto != null ? new Escolaridade(
				escolaridadeDto.getId(), escolaridadeDto.toString(),
				new FabricaSerie().converterParaDominio(escolaridadeDto
						.getSeries())) : null;
	}
}
