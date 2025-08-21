package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.OpcaoIntegracaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.OpcaoIntegracao;

public class TestesFabricaOpcaoIntegracao {
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_opcao_integracao_converte_objeto_de_dominio_para_dto() {

		OpcaoIntegracao opcaoIntegracao = OpcaoIntegracao.INTEGRACAO;

		OpcaoIntegracaoDTO opcaoIntegracaoDtoCovertido = new FabricaOpcaoIntegracao().converterParaDTO(opcaoIntegracao);

		Assert.assertEquals(opcaoIntegracaoDtoCovertido.toString(), opcaoIntegracao.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_opcao_integracao_converte_objeto_dto_para_dominio() {
		OpcaoIntegracaoDTO opcaoIntegracaoDto = new OpcaoIntegracaoDTO("INTEGRACAO");

		OpcaoIntegracao opcaoIntegracaoCovertido = new FabricaOpcaoIntegracao().converterParaDominio(opcaoIntegracaoDto);

		Assert.assertEquals(opcaoIntegracaoCovertido.toString(), opcaoIntegracaoDto.toString());
	}
}
