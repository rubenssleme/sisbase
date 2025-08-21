package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoCertidaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipocertidaodto_foi_construido_com_sucesso() {
		String tipoCertidao = "NASCIMENTO";

		TipoCertidaoDTO tipoCertidaoDto = new TipoCertidaoDTO(tipoCertidao);

		Assert.assertEquals(tipoCertidao, tipoCertidaoDto.toString());
	}
}
