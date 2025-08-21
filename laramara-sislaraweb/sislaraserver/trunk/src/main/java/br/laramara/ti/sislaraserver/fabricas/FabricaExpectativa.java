package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ExpectativaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Expectativa;

public class FabricaExpectativa extends FabricaBase<ExpectativaDTO, Expectativa> {

	public final ExpectativaDTO converterParaDTO(Expectativa expectativa) {
		return expectativa != null ? new ExpectativaDTO(expectativa.getId(), expectativa.getDescricao()) : null;
	}

	public final Expectativa converterParaDominio(ExpectativaDTO expectativaDto) {
		return expectativaDto != null ? new Expectativa(expectativaDto.getId(), expectativaDto.toString()) : null;
	}
}
