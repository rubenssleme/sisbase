package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.TipoEspecialidadeDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.escola.TipoEspecialidade;

public class TestesFabricaTipoEspecialidade {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipoespecialidade_converte_objeto_de_dominio_para_dto() {
		TipoEspecialidade tipoEspecialidade = TipoEspecialidade.DV;

		TipoEspecialidadeDTO tipoEspecialidadeDTO = new FabricaTipoEspecialidade()
				.converterParaDTO(tipoEspecialidade);

		Assert.assertEquals(tipoEspecialidadeDTO.toString(),
				tipoEspecialidade.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipoespecialidade_converte_objeto_de_dto_para_dominio() {
		TipoEspecialidade tipoEspecialidadeEsperado = TipoEspecialidade.DI;
		TipoEspecialidadeDTO tipoEspecialidadeDto = new TipoEspecialidadeDTO(
				tipoEspecialidadeEsperado.toString());

		TipoEspecialidade tipoEspecialidadeObtido = new FabricaTipoEspecialidade()
				.converterParaDominio(tipoEspecialidadeDto);

		Assert.assertEquals(tipoEspecialidadeObtido, tipoEspecialidadeEsperado);
	}
}
