package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ExpectativaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Expectativa;

public class TestesFabricaExpectativa {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_expectativa_converte_objeto_de_dominio_para_dto() {
		Expectativa expectativa = new Expectativa(new Long(11111), "Expectativa A");

		ExpectativaDTO expectativaDTO = new FabricaExpectativa()
				.converterParaDTO(expectativa);

		Assert.assertEquals(expectativaDTO.getId(), expectativa.getId());
		Assert.assertEquals(expectativaDTO.toString(), expectativa.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_expectativa_converte_objeto_de_dto_para_dominio() {
		ExpectativaDTO expectativaDto = new ExpectativaDTO(new Long(11111),
				"Expectativa B");

		Expectativa expectativaObtido = new FabricaExpectativa()
				.converterParaDominio(expectativaDto);

		Assert.assertEquals(expectativaObtido.getId(), expectativaDto.getId());
		Assert.assertEquals(expectativaObtido.getDescricao(), expectativaDto.toString());
	}
}
