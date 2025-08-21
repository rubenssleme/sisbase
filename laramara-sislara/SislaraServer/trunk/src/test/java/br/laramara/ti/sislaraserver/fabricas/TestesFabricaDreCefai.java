package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;

public class TestesFabricaDreCefai {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_drecefai_converte_objeto_de_dominio_para_dto() {
		DreCefai dreCefai = new DreCefai(new Long(1), "CEFAI BUTANTAN");

		DreCefaiDTO dreCefaiDTO = new FabricaDreCefai()
				.converterParaDTO(dreCefai);

		Assert.assertEquals(dreCefai.getId(), dreCefaiDTO.getId());
		Assert.assertEquals(dreCefai.getNome(), dreCefaiDTO.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_drecefai_converte_objeto_de_dto_para_dominio() {
		DreCefai dreCefaiEsperada = new DreCefai(new Long(1), "CEFAI BUTANTAN");
		DreCefaiDTO dreCefaiDto = new DreCefaiDTO(new Long(1),
				dreCefaiEsperada.getNome());

		DreCefai dreCefaiObtido = new FabricaDreCefai()
				.converterParaDominio(dreCefaiDto);

		Assert.assertEquals(dreCefaiObtido.getId(), dreCefaiEsperada.getId());
		Assert.assertEquals(dreCefaiObtido.getNome(),
				dreCefaiEsperada.getNome());
	}
}
