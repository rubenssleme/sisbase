package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.contribuicao.FrequenciaContribuicaoDTO;
import br.laramara.ti.sislaraserver.dominio.contribuicao.FrequenciaContribuicao;

public class FabricaFrequenciaContribuicao extends
		FabricaBase<FrequenciaContribuicaoDTO, FrequenciaContribuicao> {

	public final FrequenciaContribuicaoDTO converterParaDTO(
			FrequenciaContribuicao frequenciaContribuicao) {
		return frequenciaContribuicao != null ? new FrequenciaContribuicaoDTO(
				frequenciaContribuicao.toString()) : null;
	}

	public final FrequenciaContribuicao converterParaDominio(
			FrequenciaContribuicaoDTO frequenciaContribuicao) {
		return frequenciaContribuicao != null ? FrequenciaContribuicao
				.valueOf(FrequenciaContribuicao.class,
						frequenciaContribuicao.toString()) : null;
	}
}
