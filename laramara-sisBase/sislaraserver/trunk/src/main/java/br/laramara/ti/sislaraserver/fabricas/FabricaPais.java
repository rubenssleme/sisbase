package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaraserver.dominio.endereco.Pais;

public class FabricaPais extends FabricaBase<PaisDTO, Pais> {
	public final PaisDTO converterParaDTO(Pais pais) {
		return pais != null ? new PaisDTO(pais.getId(), pais.getNome()) : null;
	}

	public final Pais converterParaDominio(PaisDTO paisDto) {
		return paisDto != null ? new Pais(paisDto.getId(), paisDto.toString())
				: null;
	}
}
