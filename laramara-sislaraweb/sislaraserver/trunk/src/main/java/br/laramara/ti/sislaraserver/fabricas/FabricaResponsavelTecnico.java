package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.ResponsavelTecnicoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.ResponsavelTecnico;

public class FabricaResponsavelTecnico extends FabricaBase<ResponsavelTecnicoDTO, ResponsavelTecnico> {

	public final ResponsavelTecnicoDTO converterParaDTO(ResponsavelTecnico responsavelTecnico) {
		ResponsavelTecnicoDTO responsavelTecnicoDto = null;
		if (responsavelTecnico != null) {
			responsavelTecnicoDto = new ResponsavelTecnicoDTO();
			responsavelTecnicoDto.setId(responsavelTecnico.getId());
			responsavelTecnicoDto.setProfissionalDto(new FabricaProfissional().converterParaDTO(responsavelTecnico.getProfissional()));
			responsavelTecnicoDto.setPrincipal(responsavelTecnico.isPrincipal());
		}
		return responsavelTecnicoDto;
	}

	public final ResponsavelTecnico converterParaDominio(ResponsavelTecnicoDTO responsavelTecnicoDto) {
		return responsavelTecnicoDto != null ? new ResponsavelTecnico(responsavelTecnicoDto.getId(),
				new FabricaProfissional().converterParaDominio(responsavelTecnicoDto.getProfissionalDto()),
				responsavelTecnicoDto.isPrincipal()) : null;
	}
}
