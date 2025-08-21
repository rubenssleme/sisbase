package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.PapelDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Papel;

public class FabricaPapel extends FabricaBase<PapelDTO, Papel> {
	public final PapelDTO converterParaDTO(Papel papel) {
		return papel != null ? new PapelDTO(papel.toString()) : null;
	}

	public final Papel converterParaDominio(PapelDTO papelDto) {
		return papelDto != null ? Papel.valueOf(papelDto.toString()) : null;
	}
}
