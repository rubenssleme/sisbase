package br.laramara.ti.sislaracommons.dtos.escola;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.SituacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSituacaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void situacaodto_foi_construida_com_sucesso() {
		String situacao = "COMPLETO";

		SituacaoEducacionalDTO situacaoDto = new SituacaoEducacionalDTO(situacao);

		Assert.assertEquals(situacaoDto.toString(), situacao);
	}
}
