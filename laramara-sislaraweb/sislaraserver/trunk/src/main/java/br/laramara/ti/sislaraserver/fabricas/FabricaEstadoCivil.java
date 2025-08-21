package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.EstadoCivil;

public class FabricaEstadoCivil extends FabricaBase<EstadoCivilDTO, EstadoCivil> {
	public final EstadoCivilDTO converterParaDTO(
			EstadoCivil estadoCivil) {
		return estadoCivil != null ? new EstadoCivilDTO(estadoCivil.getId(), estadoCivil.getDescricao())
				: null;
	}

	public final EstadoCivil converterParaDominio(
			EstadoCivilDTO estadoCivilDto) {
		return estadoCivilDto != null ? new EstadoCivil(estadoCivilDto.getId(),
				estadoCivilDto.toString()) : null;
	}
}
