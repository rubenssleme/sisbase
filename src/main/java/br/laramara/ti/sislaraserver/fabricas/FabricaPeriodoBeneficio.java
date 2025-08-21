package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoBeneficio;

public class FabricaPeriodoBeneficio extends
		FabricaBase<PeriodoBeneficioDTO, PeriodoBeneficio> {

	public final PeriodoBeneficioDTO converterParaDTO(
			PeriodoBeneficio periodoBeneficio) {
		PeriodoBeneficioDTO periodoBeneficioDTO = new PeriodoBeneficioDTO();
		periodoBeneficioDTO.setId(periodoBeneficio.getId());
		periodoBeneficioDTO.setBeneficioDto(new FabricaBeneficio()
				.converterParaDTO(periodoBeneficio.getBeneficio()));
		periodoBeneficioDTO.setDataInicial(periodoBeneficio.getDataInicial());
		periodoBeneficioDTO.setDataFinal(periodoBeneficio.getDataFinal());
		periodoBeneficioDTO.setStatusDto(new FabricaStatusBeneficio()
				.converterParaDTO(periodoBeneficio.getStatus()));
		return periodoBeneficioDTO;
	}

	public final PeriodoBeneficio converterParaDominio(
			PeriodoBeneficioDTO periodoBeneficioDto) {
		PeriodoBeneficio periodoBeneficio = new PeriodoBeneficio();
		if (periodoBeneficioDto.getId() != null) {
			periodoBeneficio.setId(periodoBeneficioDto.getId());
		}
		periodoBeneficio.setBeneficio(new FabricaBeneficio()
				.converterParaDominio(periodoBeneficioDto.getBeneficioDto()));
		periodoBeneficio.setDataInicial(periodoBeneficioDto.getDataInicial());
		periodoBeneficio.setDataFinal(periodoBeneficioDto.getDataFinal());
		periodoBeneficio.setStatus(new FabricaStatusBeneficio()
				.converterParaDominio(periodoBeneficioDto.getStatusDto()));
		return periodoBeneficio;
	}
}
