package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.InfraestruturaBasicaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.InfraestruturaBasica;

public class FabricaInfraestruturaBasica extends FabricaBase<InfraestruturaBasicaDTO, InfraestruturaBasica> {

	public final InfraestruturaBasicaDTO converterParaDTO(InfraestruturaBasica infraestruturaBasico) {
		return infraestruturaBasico != null
				? new InfraestruturaBasicaDTO(infraestruturaBasico.getId(), infraestruturaBasico.getDescricao()) : null;
	}

	public final InfraestruturaBasica converterParaDominio(InfraestruturaBasicaDTO infraestruturaBasicoDto) {
		return infraestruturaBasicoDto != null
				? new InfraestruturaBasica(infraestruturaBasicoDto.getId(), infraestruturaBasicoDto.toString()) : null;
	}
}
