package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.CondicaoMoradiaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.CondicaoMoradia;

public class TestesFabricaCondicaoMoradia {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_condicao_moradia_converte_objeto_de_dominio_para_dto() {
		CondicaoMoradia condicaoMoradia = new CondicaoMoradia(new Long(1000), "TESTE");

		CondicaoMoradiaDTO condicaoMoradiaDTO = new FabricaCondicaoMoradia()
				.converterParaDTO(condicaoMoradia);

		Assert.assertEquals(condicaoMoradiaDTO.getId(), condicaoMoradia.getId());
		Assert.assertEquals(condicaoMoradiaDTO.toString(), condicaoMoradia.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_condicao_moradia_converte_objeto_de_dto_para_dominio() {
		CondicaoMoradiaDTO condicaoMoradiaDto = new CondicaoMoradiaDTO(new Long(1000),
				"TESTE");

		CondicaoMoradia condicaoMoradiaObtido = new FabricaCondicaoMoradia()
				.converterParaDominio(condicaoMoradiaDto);

		Assert.assertEquals(condicaoMoradiaObtido.getId(), condicaoMoradiaDto.getId());
		Assert.assertEquals(condicaoMoradiaObtido.getDescricao(), condicaoMoradiaDto.toString());
	}
}
