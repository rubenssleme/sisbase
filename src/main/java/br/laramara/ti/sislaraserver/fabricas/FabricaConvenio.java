package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ConvenioDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Convenio;

public class FabricaConvenio extends FabricaBase<ConvenioDTO, Convenio> {

	public final ConvenioDTO converterParaDTO(Convenio convenio) {
		return new ConvenioDTO(convenio.getId(), convenio.getDescricao());
	}

	public final Convenio converterParaDominio(
			ConvenioDTO convenioDto) {
		return new Convenio(convenioDto.getId(), convenioDto.toString());
	}
}
