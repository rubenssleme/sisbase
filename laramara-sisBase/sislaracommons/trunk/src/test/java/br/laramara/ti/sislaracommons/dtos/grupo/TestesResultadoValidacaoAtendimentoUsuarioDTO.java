package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoAtendimentoUsuarioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoatendimentobasedto_sem_erro_foi_construido() {
		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoBaseDto = new ResultadoValidacaoAtendimentoBaseDTO();
		resultadoValidacaoAtendimentoBaseDto
				.efetuadoComSucesso(new AtendimentoUsuarioDTO());

		Assert.assertTrue(resultadoValidacaoAtendimentoBaseDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadovalidacaoatendimentousuariodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoUsuarioDto = new ResultadoValidacaoAtendimentoBaseDTO();
		resultadoValidacaoAtendimentoUsuarioDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoAtendimentoUsuarioDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoAtendimentoUsuarioDto
				.obterMensagens());
	}
}
