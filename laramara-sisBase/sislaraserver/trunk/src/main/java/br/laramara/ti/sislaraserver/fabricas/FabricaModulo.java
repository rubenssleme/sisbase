package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;

public class FabricaModulo extends FabricaBase<ModuloDTO, Modulo> {

	public final ModuloDTO converterParaDTO(Modulo modulo) {
		return modulo != null ? new ModuloDTO(modulo.getId(), modulo.getNome())
				: null;
	}

	public final Modulo converterParaDominio(ModuloDTO moduloDto) {
		return moduloDto != null ? new Modulo(moduloDto.getId(),
				moduloDto.toString()) : null;
	}
}
