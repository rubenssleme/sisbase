package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.espera.TipoEsperaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.espera.TipoEspera;

public class TestesFabricaTipoEspera {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_tipoespera_converte_objeto_de_dto_para_dominio() {
		TipoEsperaDTO tipoEsperaDto = new TipoEsperaDTO("RET");

		TipoEspera tipoEspera = new FabricaTipoEspera()
				.converterParaDominio(tipoEsperaDto);

		Assert.assertEquals(tipoEspera.toString(), tipoEsperaDto.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_tipoespera_converte_objeto_de_dominio_para_dto() {
		TipoEspera tipoEspera = TipoEspera.RET;

		TipoEsperaDTO tipoEsperaDto = new FabricaTipoEspera()
				.converterParaDTO(tipoEspera);

		Assert.assertEquals(tipoEspera.toString(), tipoEsperaDto.toString());
	}
}
