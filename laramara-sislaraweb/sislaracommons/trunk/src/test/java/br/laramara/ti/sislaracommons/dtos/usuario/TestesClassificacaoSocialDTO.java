package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ClassificacaoSocialDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesClassificacaoSocialDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void classificacaosocialdto_foi_construida_com_sucesso() {
		String classificacaoSocial = "IP";

		ClassificacaoSocialDTO classificacaoSocialDto = new ClassificacaoSocialDTO(
				classificacaoSocial);

		Assert.assertEquals(classificacaoSocialDto.toString(),
				classificacaoSocial);
	}
}
