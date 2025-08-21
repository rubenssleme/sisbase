package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.CondicaoMoradiaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.CondicaoMoradia;

public class FabricaCondicaoMoradia extends FabricaBase<CondicaoMoradiaDTO, CondicaoMoradia> {

	public final CondicaoMoradiaDTO converterParaDTO(CondicaoMoradia condicaoMoradia) {
		return condicaoMoradia != null ? new CondicaoMoradiaDTO(condicaoMoradia.getId(), condicaoMoradia.getDescricao())
				: null;
	}

	public final CondicaoMoradia converterParaDominio(CondicaoMoradiaDTO condicaoMoradiaDto) {
		return condicaoMoradiaDto != null
				? new CondicaoMoradia(condicaoMoradiaDto.getId(), condicaoMoradiaDto.toString()) : null;
	}
}
