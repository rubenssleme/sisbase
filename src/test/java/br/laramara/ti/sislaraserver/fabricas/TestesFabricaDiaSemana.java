package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;

public class TestesFabricaDiaSemana {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_diasemana_converte_objeto_de_dominio_para_dto() {
		DiaSemana diaSemana = DiaSemana.QUINTA;

		DiaSemanaDTO diaSemanaDTO = new FabricaDiaSemana()
				.converterParaDTO(diaSemana);

		Assert.assertEquals(diaSemanaDTO.toString(), diaSemana.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_diasemana_converte_objeto_de_dto_para_dominio() {
		DiaSemana diaSemanaEsperado = DiaSemana.QUINTA;
		DiaSemanaDTO diaSemanaDto = new DiaSemanaDTO(
				diaSemanaEsperado.toString());

		DiaSemana diaSemanaObtido = new FabricaDiaSemana()
				.converterParaDominio(diaSemanaDto);

		Assert.assertEquals(diaSemanaObtido, diaSemanaEsperado);
	}
}
