package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.PrestacaoContaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.PrestacaoConta;

public class TestesFabricaPrestacaoConta {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_prestacao_conta_converte_objeto_de_dominio_para_dto() {
		PrestacaoConta prestacaoConta = PrestacaoConta.MENSAL;

		PrestacaoContaDTO prestacaoContaDTO = new FabricaPrestacaoConta()
				.converterParaDTO(prestacaoConta);

		Assert.assertEquals(prestacaoContaDTO.toString(), prestacaoConta.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_prestacao_conta_converte_objeto_de_dto_para_dominio() {
		PrestacaoConta prestacaoContaEsperado = PrestacaoConta.SEMESTRAL;
		PrestacaoContaDTO prestacaoContaDto = new PrestacaoContaDTO(
				prestacaoContaEsperado.toString());

		PrestacaoConta prestacaoContaObtido = new FabricaPrestacaoConta()
				.converterParaDominio(prestacaoContaDto);

		Assert.assertEquals(prestacaoContaObtido.toString(),
				prestacaoContaEsperado.toString());
	}
}
