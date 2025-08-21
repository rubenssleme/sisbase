package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoValidacaoRecursoRelacionamentoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_validacao_recurso_relacionamento_dto_sem_erro_foi_construido() {
		ResultadoValidacaoRecursoRelacionamentoDTO resultadoValidacaoRecursoRelacionamentoDto = new ResultadoValidacaoRecursoRelacionamentoDTO();
		resultadoValidacaoRecursoRelacionamentoDto
				.efetuadoComSucesso(new RecursoRelacionamentoDTO());

		Assert.assertTrue(resultadoValidacaoRecursoRelacionamentoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_validacao_recurso_relacionamento_dto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoValidacaoRecursoRelacionamentoDTO resultadoValidacaoRecursoRelacionamentoDto = new ResultadoValidacaoRecursoRelacionamentoDTO();
		resultadoValidacaoRecursoRelacionamentoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoValidacaoRecursoRelacionamentoDto.sucesso());
		Assert.assertNotNull(resultadoValidacaoRecursoRelacionamentoDto
				.obterMensagens());
	}
}
