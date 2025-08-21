package br.laramara.sistelemarketingserver;

import br.laramara.sistelemarketingcommons.dtos.MunicipioDTO;
import br.laramara.sistelemarketingserver.dominio.Municipio;

public class ContextoMunicipio {

	public static Municipio fabricarMunicipio() {
		Municipio municipio = new Municipio();
		municipio.setId(new Long(8966));
		municipio.setDescricao("São Paulo");
		return municipio;
	}

	public static MunicipioDTO fabricarMunicipioDto() {
		MunicipioDTO municipioDto = new MunicipioDTO();
		municipioDto.setId(new Long(8966));
		municipioDto.setDescricao("São Paulo");
		return municipioDto;
	}
}
