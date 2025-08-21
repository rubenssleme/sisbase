package br.laramara.ti.sislaracommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoTelefoneDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaotelefonedto_sem_erro_foi_construido() {
		ResultadoValidacaoTelefoneDTO resultadoValidacaoTelefoneDto = new ResultadoValidacaoTelefoneDTO();
		resultadoValidacaoTelefoneDto.efetuadoComSucesso(new TelefoneDTO(
				new TipoTelefoneDTO("CELULAR"), "1122223333", "1234", "Paulo"));

		Assert.assertTrue(resultadoValidacaoTelefoneDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaotelefonedto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoTelefoneDTO resultadoValidacaoTelefoneDto = new ResultadoValidacaoTelefoneDTO();
		resultadoValidacaoTelefoneDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoTelefoneDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoTelefoneDto.obterMensagens());
	}
}
