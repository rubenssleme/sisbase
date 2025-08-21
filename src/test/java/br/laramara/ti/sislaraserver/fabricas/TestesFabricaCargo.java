package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.trabalho.CargoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.trabalho.Cargo;

public class TestesFabricaCargo {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_cargo_converte_objeto_de_dominio_para_dto() {
		Cargo cargo = new Cargo(new Long(1), "Administrador");

		CargoDTO cargoDTO = new FabricaCargo().converterParaDTO(cargo);

		Assert.assertEquals(cargo.getId(), cargoDTO.getId());
		Assert.assertEquals(cargo.getNome(), cargoDTO.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_cargo_converte_objeto_de_dto_para_dominio() {
		CargoDTO cargoDto = new CargoDTO(new Long(1), "Administrador");

		Cargo cargo = new FabricaCargo().converterParaDominio(cargoDto);

		Assert.assertEquals(cargo.getId(), cargoDto.getId());
		Assert.assertEquals(cargo.getNome(), cargoDto.toString());
	}
}
