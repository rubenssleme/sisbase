package br.laramara.ti.sislaracommons.dtos.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesOpcaoIntegracaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipo_opcao_integracao_dto_foi_construida_com_sucesso() {
		String opcaoIntegracao = "INTEGRACAO";

		OpcaoIntegracaoDTO opcaoIntegracaoDto = new OpcaoIntegracaoDTO(opcaoIntegracao);

		Assert.assertEquals(opcaoIntegracaoDto.toString(), opcaoIntegracao);
	}
}
