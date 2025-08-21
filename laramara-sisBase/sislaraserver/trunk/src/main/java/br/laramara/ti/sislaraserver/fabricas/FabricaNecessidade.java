package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.NecessidadeDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Necessidade;

public class FabricaNecessidade extends FabricaBase<NecessidadeDTO, Necessidade> {

	public final NecessidadeDTO converterParaDTO(Necessidade necessidade) {
		return necessidade != null ? new NecessidadeDTO(necessidade.getId(), necessidade.getDescricao()) : null;
	}

	public final Necessidade converterParaDominio(NecessidadeDTO necessidadeDto) {
		return necessidadeDto != null ? new Necessidade(necessidadeDto.getId(), necessidadeDto.toString()) : null;
	}
}
