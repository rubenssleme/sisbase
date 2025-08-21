package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.campanha.CriterioDTO;
import br.laramara.sistelemarketingserver.dominio.campanha.Criterio;

public class CriterioFabrica extends FabricaBase<CriterioDTO, Criterio> {
	public final CriterioDTO converterParaDTO(Criterio criterio) {
		CriterioDTO criterioDto = new CriterioDTO();
		if (criterio != null) {
			criterioDto.setId(criterio.getId());
			criterioDto.setNome(criterio.getNome());
			criterioDto.setMunicipioDto(new MunicipioFabrica().converterParaDTO(criterio.getMunicipio()));
			criterioDto.setBairro(criterio.getBairro());
			criterioDto.setQuantidadeAAdistribuir(criterio.getQuantidadeDistribuir());
		}
		return criterioDto;
	}

	public final Criterio converterParaDominio(CriterioDTO criterioDto) {
		Criterio criterio = new Criterio();
		if (criterioDto != null) {
			criterio.setId(criterioDto.getId());
			criterio.setNome(criterioDto.getNome());
			criterio.setMunicipio(new MunicipioFabrica().converterParaDominio(criterioDto.getMunicipioDto()));
			criterio.setBairro(criterioDto.getBairro());
			criterio.setQuantidadeDistribuir(criterioDto.getQuantidadeAAdistribuir());
		}
		return criterio;
	}
}
