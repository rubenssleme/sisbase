package br.laramara.ti.sislaraserver.fabricas.externa;

import br.laramara.ti.sislaracommons.dtos.evento.DescricaoEventoDTO;
import br.laramara.ti.sislaraserver.dominio.evento.DescricaoEvento;
import br.laramara.ti.sislaraserver.fabricas.FabricaBase;

public class FabricaDescricaoEvento extends FabricaBase<DescricaoEventoDTO, DescricaoEvento> {

	public final DescricaoEventoDTO converterParaDTO(DescricaoEvento descricaoEvento) {
		DescricaoEventoDTO descricaoEventoDTO = null;

		if (descricaoEvento != null) {
			descricaoEventoDTO = new DescricaoEventoDTO();

			if (descricaoEvento.getId() != null) {
				descricaoEventoDTO.setId(descricaoEvento.getId());
			}

			descricaoEventoDTO.setTipoDescricaoEvento(
					new FabricaTipoDescricaoEvento().converterParaDTO(descricaoEvento.getTipoDescricaoEvento()));
			descricaoEventoDTO.setConteudo(descricaoEvento.getConteudo());
		}

		return descricaoEventoDTO;
	}

	public final DescricaoEvento converterParaDominio(DescricaoEventoDTO descricaoEventoDto) {
		DescricaoEvento descricaoEvento = null;

		if (descricaoEventoDto != null) {
			descricaoEvento = new DescricaoEvento();

			if (descricaoEventoDto.getId() != null) {
				descricaoEvento.setId(descricaoEventoDto.getId());
			}

			descricaoEvento.setTipoDescricaoEvento(
					new FabricaTipoDescricaoEvento().converterParaDominio(descricaoEventoDto.getTipoDescricaoEvento()));
			descricaoEvento.setConteudo(descricaoEventoDto.getConteudo());
		}

		return descricaoEvento;
	}

}
