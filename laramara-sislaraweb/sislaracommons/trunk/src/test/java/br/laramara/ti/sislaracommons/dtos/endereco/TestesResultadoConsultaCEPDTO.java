package br.laramara.ti.sislaracommons.dtos.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoConsultaCEPDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoconsultacepdto_foi_construido_com_sucesso() {
		ResultadoConsultaCEP resultadoConsultaCep = new ResultadoConsultaCEP();

		resultadoConsultaCep.efetuadoComSucesso(new EnderecoCEPDTO());
		
		Assert.assertFalse(resultadoConsultaCep.toString().isEmpty());
		Assert.assertNotNull(resultadoConsultaCep.getEnderecoCEPDto());
	}
}
