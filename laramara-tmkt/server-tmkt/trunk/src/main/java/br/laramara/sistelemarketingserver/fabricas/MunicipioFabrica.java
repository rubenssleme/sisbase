package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.MunicipioDTO;
import br.laramara.sistelemarketingserver.dominio.Municipio;

public class MunicipioFabrica extends FabricaBase<MunicipioDTO, Municipio> {
	public final MunicipioDTO converterParaDTO(Municipio municipio) {
		MunicipioDTO municipioDto = new MunicipioDTO();
		if (municipio != null) {
			municipioDto.setId(municipio.getId());
			municipioDto.setDescricao(municipio.getDescricao());
			municipioDto.setEstadoDto(new EstadoFabrica().converterParaDTO(municipio.getEstado()));
		}
		return municipioDto;
	}

	public final Municipio converterParaDominio(MunicipioDTO municipioDto) {
		Municipio municipio = new Municipio();
		if (municipioDto != null) {
			municipio.setId(municipioDto.getId());
			municipio.setDescricao(municipioDto.getDescricao());
			municipio.setEstado(new EstadoFabrica().converterParaDominio(municipioDto.getEstadoDto()));
		}
		return municipio;
	}
}
