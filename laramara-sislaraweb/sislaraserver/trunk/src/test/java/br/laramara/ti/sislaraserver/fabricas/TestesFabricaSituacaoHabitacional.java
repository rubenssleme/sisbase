package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.SituacaoHabitacionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoHabitacional;

public class TestesFabricaSituacaoHabitacional {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_situacao_habitacional_converte_objeto_de_dominio_para_dto() {
		SituacaoHabitacional situacaoHabitacional = new SituacaoHabitacional(new Long(1000), "TESTE");

		SituacaoHabitacionalDTO situacaoHabitacionalDTO = new FabricaSituacaoHabitacional()
				.converterParaDTO(situacaoHabitacional);

		Assert.assertEquals(situacaoHabitacionalDTO.getId(), situacaoHabitacional.getId());
		Assert.assertEquals(situacaoHabitacionalDTO.toString(), situacaoHabitacional.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_situacao_habitacional_converte_objeto_de_dto_para_dominio() {
		SituacaoHabitacionalDTO situacaoHabitacionalDto = new SituacaoHabitacionalDTO(new Long(1000),
				"TESTE");

		SituacaoHabitacional situacaoHabitacionalObtido = new FabricaSituacaoHabitacional()
				.converterParaDominio(situacaoHabitacionalDto);

		Assert.assertEquals(situacaoHabitacionalObtido.getId(), situacaoHabitacionalDto.getId());
		Assert.assertEquals(situacaoHabitacionalObtido.getDescricao(), situacaoHabitacionalDto.toString());
	}
}
