package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.SimNao;

public class TestesFabricaSimNao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_simnao_converte_objeto_de_dominio_para_dto() {
		SimNao simNao = SimNao.SIM;

		SimNaoDTO simNaoDTO = new FabricaSimNao().converterParaDTO(simNao);

		Assert.assertEquals(simNaoDTO.toString(), simNao.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_simnao_converte_objeto_de_dto_para_dominio() {
		SimNao simNaoEsperado = SimNao.NAO;
		SimNaoDTO simNaoDto = new SimNaoDTO(simNaoEsperado.toString());

		SimNao simNaoObtido = new FabricaSimNao()
				.converterParaDominio(simNaoDto);

		Assert.assertEquals(simNaoObtido, simNaoEsperado);
	}
}
