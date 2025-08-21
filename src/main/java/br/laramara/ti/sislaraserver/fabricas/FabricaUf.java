package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;

public class FabricaUf extends FabricaBase<UfDTO, UF> {

	public final UfDTO converterParaDTO(UF uf) {
		return uf != null ? new UfDTO(uf.toString()) : null;
	}

	public final UF converterParaDominio(UfDTO ufDto) {
		return ufDto != null ? UF.valueOf(UF.class, ufDto.toString()) : null;
	}
}
