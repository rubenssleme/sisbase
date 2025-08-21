package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.TipoLeituraDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoLeitura;

public class TestesFabricaTipoLeitura {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipoleitura_converte_objeto_de_dominio_para_dto() {
		TipoLeitura tipoLeitura = TipoLeitura.BRAILLE;

		TipoLeituraDTO tipoLeituraDTO = new FabricaTipoLeitura()
				.converterParaDTO(tipoLeitura);

		Assert.assertEquals(tipoLeituraDTO.toString(), tipoLeitura.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipoleitura_converte_objeto_de_dto_para_dominio() {
		TipoLeitura tipoLeituraEsperado = TipoLeitura.BRAILLE;
		TipoLeituraDTO tipoLeituraDto = new TipoLeituraDTO(
				tipoLeituraEsperado.toString());

		TipoLeitura tipoLeituraObtido = new FabricaTipoLeitura()
				.converterParaDominio(tipoLeituraDto);

		Assert.assertEquals(tipoLeituraObtido, tipoLeituraEsperado);
	}
}
