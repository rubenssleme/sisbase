package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoConstrucaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipo_construcao_dto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String texto = "TEXTO";

		TipoConstrucaoDTO tipoConstrucaoDto = new TipoConstrucaoDTO(id, texto);

		Assert.assertEquals(tipoConstrucaoDto.getId(), id);
		Assert.assertEquals(tipoConstrucaoDto.toString(), texto);
	}
}
