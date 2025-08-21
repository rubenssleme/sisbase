package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSituacaoGuardaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void situacaoguardadto_foi_construido_com_sucesso() {
		Long id = new Long(12222);
		String descricao = "TUTELA";

		SituacaoGuardaDTO situacaoGuardaDto = new SituacaoGuardaDTO(id,
				descricao);

		Assert.assertEquals(situacaoGuardaDto.getId(), id);
		Assert.assertEquals(situacaoGuardaDto.toString(), descricao);
	}
}
