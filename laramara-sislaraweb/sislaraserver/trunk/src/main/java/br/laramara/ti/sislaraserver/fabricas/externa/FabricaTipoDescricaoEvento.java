package br.laramara.ti.sislaraserver.fabricas.externa;

import br.laramara.ti.sislaracommons.dtos.evento.TipoDescricaoEventoDTO;
import br.laramara.ti.sislaraserver.dominio.evento.TipoDescricaoEvento;
import br.laramara.ti.sislaraserver.fabricas.FabricaBase;

public class FabricaTipoDescricaoEvento extends FabricaBase<TipoDescricaoEventoDTO, TipoDescricaoEvento>{

	@Override
	public TipoDescricaoEvento converterParaDominio(TipoDescricaoEventoDTO objetoDto) {
		return objetoDto != null ? TipoDescricaoEvento.obterPorNome(objetoDto.toString()) : null;
	}

	@Override
	public TipoDescricaoEventoDTO converterParaDTO(TipoDescricaoEvento objetoDominio) {
		return new TipoDescricaoEventoDTO(objetoDominio.toString());
	}

}
