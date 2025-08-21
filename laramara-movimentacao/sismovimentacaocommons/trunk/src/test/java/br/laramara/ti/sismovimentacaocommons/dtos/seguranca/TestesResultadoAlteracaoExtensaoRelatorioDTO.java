package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoAlteracaoExtensaoRelatorioDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesResultadoAlteracaoExtensaoRelatorioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_alteracao_extensao_relatorio_foi_construida_com_sucesso() {
		ResultadoAlteracaoExtensaoRelatorioDTO resultadoAlteracaoExtensaoRelatorioDTO = new ResultadoAlteracaoExtensaoRelatorioDTO();
		resultadoAlteracaoExtensaoRelatorioDTO.efetuadoComSucesso();

		Assert.assertTrue(resultadoAlteracaoExtensaoRelatorioDTO.sucesso());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_alteracao_extensao_relatorio_foi_construida_com_erro() {
		ResultadoAlteracaoExtensaoRelatorioDTO resultadoAlteracaoExtensaoRelatorioDTO = new ResultadoAlteracaoExtensaoRelatorioDTO();
		resultadoAlteracaoExtensaoRelatorioDTO
				.adicionarErro("Erro durante alteração");

		Assert.assertFalse(resultadoAlteracaoExtensaoRelatorioDTO.sucesso());
		Assert.assertNotNull(resultadoAlteracaoExtensaoRelatorioDTO
				.obterMensagens());
	}
}
