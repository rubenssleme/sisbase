package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.contribuicao.FrequenciaContribuicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.contribuicao.FrequenciaContribuicao;

public class TestesFabricaFrequenciaContribuicao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_frequencia_contribuicao_converte_objeto_de_dominio_para_dto() {
		FrequenciaContribuicao frequenciaContribuicao = FrequenciaContribuicao.MENSAL;

		FrequenciaContribuicaoDTO frequenciaContribuicaoDTO = new FabricaFrequenciaContribuicao()
				.converterParaDTO(frequenciaContribuicao);

		Assert.assertEquals(frequenciaContribuicaoDTO.toString(),
				frequenciaContribuicao.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_frequencia_contribuicao_converte_objeto_de_dto_para_dominio() {
		FrequenciaContribuicao frequenciaContribuicaoEsperada = FrequenciaContribuicao.SEMESTRAL;
		FrequenciaContribuicaoDTO frequenciaContribuicaoDto = new FrequenciaContribuicaoDTO(
				frequenciaContribuicaoEsperada.toString());

		FrequenciaContribuicao frequenciaContribuicaoObtido = new FabricaFrequenciaContribuicao()
				.converterParaDominio(frequenciaContribuicaoDto);

		Assert.assertEquals(frequenciaContribuicaoObtido,
				frequenciaContribuicaoEsperada);
	}
}
