package br.laramara.sistelemarketingserver.dominio.campanha;

import br.laramara.sistelemarketingcommons.dtos.campanha.CriterioDTO;
import br.laramara.sistelemarketingserver.ContextoMunicipio;

public class ContextoCriterio {

	public static Criterio fabricar() {
		Criterio criterio = new Criterio();
		criterio.setId(new Long(12222));
		criterio.setNome("Nome do criterio.");
		criterio.setMunicipio(ContextoMunicipio.fabricarMunicipio());
		criterio.setBairro("Barra Funda");
		criterio.setQuantidadeDistribuir(10000);
		return criterio;
	}

	public static CriterioDTO fabricarDto() {
		CriterioDTO criterioDto = new CriterioDTO();
		criterioDto.setId(new Long(12222));
		criterioDto.setNome("Nome do criterio.");
		criterioDto.setMunicipioDto(ContextoMunicipio.fabricarMunicipioDto());
		criterioDto.setBairro("Barra Funda");
		criterioDto.setQuantidadeAAdistribuir(10000);
		return criterioDto;
	}
}
