package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;

public class FabricaMunicipio extends FabricaBase<MunicipioDTO, Municipio> {
	public final MunicipioDTO converterParaDTO(Municipio municipio) {
		return municipio != null ? new MunicipioDTO(municipio.getId(), municipio.getNome(),
				new FabricaUf().converterParaDTO(municipio.getUf())) : null;
	}

	public final Municipio converterParaDominio(
			MunicipioDTO municipioDto) {
		return municipioDto != null ? new Municipio(municipioDto.getId(),
				municipioDto.toString(),
				new FabricaUf().converterParaDominio(municipioDto.getUf()))
				: null;
	}
}
