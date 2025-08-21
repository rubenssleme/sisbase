package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.AreaFormacaoDTO;
import br.laramara.ti.sislaraserver.dominio.escola.AreaFormacao;

public class FabricaAreaFormacao extends
		FabricaBase<AreaFormacaoDTO, AreaFormacao> {
	public final AreaFormacaoDTO converterParaDTO(AreaFormacao areaFormacao) {
		return areaFormacao != null ? new AreaFormacaoDTO(areaFormacao.getId(),
				areaFormacao.getDescricao()) : null;
	}

	public final AreaFormacao converterParaDominio(
			AreaFormacaoDTO areaFormacaoDto) {
		return areaFormacaoDto != null ? new AreaFormacao(
				areaFormacaoDto.getId(), areaFormacaoDto.toString()) : null;
	}
}
