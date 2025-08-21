package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.TipoApoioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.escola.TipoApoio;

public class TestesFabricaTipoApoio {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipoapoio_converte_objeto_de_dominio_para_dto() {
		TipoApoio tipoApoio = TipoApoio.SALA_DE_RECURSO_DO_ESTADO;

		TipoApoioDTO tipoApoioDTO = new FabricaTipoApoio()
				.converterParaDTO(tipoApoio);

		Assert.assertEquals(tipoApoioDTO.toString(), tipoApoio.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipoapoio_converte_objeto_de_dto_para_dominio() {
		TipoApoio tipoApoioEsperado = TipoApoio.SRMs;
		TipoApoioDTO tipoApoioDto = new TipoApoioDTO(
				tipoApoioEsperado.toString());

		TipoApoio tipoApoioObtido = new FabricaTipoApoio()
				.converterParaDominio(tipoApoioDto);

		Assert.assertEquals(tipoApoioObtido, tipoApoioEsperado);
	}
}
