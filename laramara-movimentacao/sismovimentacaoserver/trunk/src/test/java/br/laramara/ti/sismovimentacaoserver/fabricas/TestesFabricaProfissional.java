package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.Profissional;

public class TestesFabricaProfissional {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_profissional_converte_objeto_de_dto_para_dominio() {
		ProfissionalDTO profissionalDto = ContextoProfissional
				.construirProfissionalDTO();

		Profissional profissional = new FabricaProfissional()
				.converterParaDominio(profissionalDto);

		Assert.assertEquals(profissional.getId(), profissionalDto.getId());
		Assert.assertEquals(profissional.getNome(), profissionalDto.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_profissional_converte_objeto_de_dominio_para_dto() {
		Profissional profissional = ContextoProfissional
				.fabricarComTodosOsDados();

		ProfissionalDTO profissionalDto = new FabricaProfissional()
				.converterParaDTO(profissional);

		Assert.assertEquals(profissional.getId(), profissionalDto.getId());
		Assert.assertEquals(profissional.getNome(), profissionalDto.toString());
	}
}
