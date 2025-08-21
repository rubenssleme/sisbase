package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoDoenca;

public class FabricaPeriodoDoenca extends
		FabricaBase<PeriodoDoencaDTO, PeriodoDoenca> {

	public final PeriodoDoencaDTO converterParaDTO(PeriodoDoenca periodoDoenca) {
		PeriodoDoencaDTO periodoDoencaDTO = new PeriodoDoencaDTO();
		periodoDoencaDTO.setId(periodoDoenca.getId());
		periodoDoencaDTO.setDoencaDto(new FabricaDoenca()
				.converterParaDTO(periodoDoenca.getDoenca()));
		periodoDoencaDTO.setDataInicial(periodoDoenca.getDataInicial());
		periodoDoencaDTO.setDataFinal(periodoDoenca.getDataFinal());
		periodoDoencaDTO.setObs(periodoDoenca.getObs());
		return periodoDoencaDTO;
	}

	public final PeriodoDoenca converterParaDominio(
			PeriodoDoencaDTO periodoDoencaDto) {
		PeriodoDoenca periodoDoenca = new PeriodoDoenca();
		if (periodoDoencaDto.getId() != null) {
			periodoDoenca.setId(periodoDoencaDto.getId());
		}
		periodoDoenca.setDoenca(new FabricaDoenca()
				.converterParaDominio(periodoDoencaDto.getDoencaDto()));
		periodoDoenca.setDataInicial(periodoDoencaDto.getDataInicial());
		periodoDoenca.setDataFinal(periodoDoencaDto.getDataFinal());
		periodoDoenca.setObs(periodoDoencaDto.getObs());
		return periodoDoenca;
	}
}
