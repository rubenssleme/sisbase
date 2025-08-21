package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.EtiologiaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Etiologia;

public class FabricaEtiologia extends FabricaBase<EtiologiaDTO, Etiologia> {

	public final EtiologiaDTO converterParaDTO(Etiologia periodoBeneficio) {
		EtiologiaDTO etiologiaDto = new EtiologiaDTO();
		etiologiaDto.setId(periodoBeneficio.getId());
		etiologiaDto.setCidDto(new FabricaCid()
				.converterParaDTO(periodoBeneficio.getCid()));
		return etiologiaDto;
	}

	public final Etiologia converterParaDominio(EtiologiaDTO etiologiaDto) {
		Etiologia etiologia = new Etiologia();
		if (etiologiaDto.getId() != null) {
			etiologia.setId(etiologiaDto.getId());
		}
		etiologia.setCid(new FabricaCid().converterParaDominio(etiologiaDto
				.getCidDto()));
		return etiologia;
	}
}
