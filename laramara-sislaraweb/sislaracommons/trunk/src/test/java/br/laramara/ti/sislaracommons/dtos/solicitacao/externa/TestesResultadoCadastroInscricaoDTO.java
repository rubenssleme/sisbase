package br.laramara.ti.sislaracommons.dtos.solicitacao.externa;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoCadastroInscricaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadocadastroinscricaodto_foi_construido_com_sucesso() {
		String mensagem = "Inscrição realizada com sucesso.";
		ResultadoCadastroInscricaoDTO resultadoCadastroInscricaoDto = new ResultadoCadastroInscricaoDTO();

		resultadoCadastroInscricaoDto.efetuadoComSucesso();

		Assert.assertFalse(resultadoCadastroInscricaoDto.toString().isEmpty());
		Assert.assertTrue(resultadoCadastroInscricaoDto.sucesso());
		Assert.assertTrue(resultadoCadastroInscricaoDto.getMensagem().contains(mensagem));
	}
}
