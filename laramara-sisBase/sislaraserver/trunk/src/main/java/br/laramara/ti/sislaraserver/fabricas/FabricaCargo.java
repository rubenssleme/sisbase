package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.trabalho.CargoDTO;
import br.laramara.ti.sislaraserver.dominio.trabalho.Cargo;

public class FabricaCargo extends FabricaBase<CargoDTO, Cargo> {
	public final CargoDTO converterParaDTO(Cargo cargo) {
		return cargo != null ? new CargoDTO(cargo.getId(), cargo.getNome())
				: null;
	}

	public final Cargo converterParaDominio(CargoDTO cargoDto) {
		return cargoDto != null ? new Cargo(cargoDto.getId(),
				cargoDto.toString()) : null;
	}
}
