package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.endereco.Zona;

public class TestesFabricaZona {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_zona_converte_objeto_de_dominio_para_dto() {
		Zona zona = Zona.NORTE;

		ZonaDTO zonaDTO = new FabricaZona().converterParaDTO(zona);

		Assert.assertEquals(zonaDTO.toString(), zona.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_zona_converte_objeto_de_dto_para_dominio() {
		Zona zonaEsperada = Zona.LESTE;
		ZonaDTO zonaDto = new ZonaDTO(zonaEsperada.toString());

		Zona zonaObtido = new FabricaZona().converterParaDominio(zonaDto);

		Assert.assertEquals(zonaObtido, zonaEsperada);
	}
}
