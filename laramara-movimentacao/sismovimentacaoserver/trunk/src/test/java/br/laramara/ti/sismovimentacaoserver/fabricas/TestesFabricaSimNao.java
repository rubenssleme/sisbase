package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.SimNaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.SimNao;

public class TestesFabricaSimNao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_simnao_converte_objeto_de_dominio_para_dto() {
		SimNao simnao = SimNao.NAO;

		SimNaoDTO simNaoDto = new FabricaSimNao().converterParaDTO(simnao);

		Assert.assertEquals(simNaoDto.toString(), simnao.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_papel_converte_objeto_de_dto_para_dominio() {
		SimNao simNao = SimNao.SIM;
		SimNaoDTO simNaoDto = new SimNaoDTO(simNao.toString());

		SimNao simNaoObtido = new FabricaSimNao()
				.converterParaDominio(simNaoDto);

		Assert.assertEquals(simNaoObtido, simNao);
	}
}
