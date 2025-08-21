package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoDeficiencia;

public class FabricaPeriodoDeficiencia extends
		FabricaBase<PeriodoDeficienciaDTO, PeriodoDeficiencia> {

	public final PeriodoDeficienciaDTO converterParaDTO(
			PeriodoDeficiencia periodoDeficiencia) {
		PeriodoDeficienciaDTO periodoDeficienciaDTO = new PeriodoDeficienciaDTO();
		periodoDeficienciaDTO.setId(periodoDeficiencia.getId());
		periodoDeficienciaDTO.setDeficienciaDto(new FabricaDeficiencia()
				.converterParaDTO(periodoDeficiencia.getDeficiencia()));
		periodoDeficienciaDTO.setCidDto(new FabricaCid()
				.converterParaDTO(periodoDeficiencia.getCid()));
		periodoDeficienciaDTO
				.setEpocaIncidenciaDto(new FabricaEpocaIncidencia()
						.converterParaDTO(periodoDeficiencia
								.getEpocaIncidencia()));
		periodoDeficienciaDTO.setDataInicial(periodoDeficiencia
				.getDataInicial());
		periodoDeficienciaDTO.setDataFinal(periodoDeficiencia.getDataFinal());
		periodoDeficienciaDTO.setEtiologiasDto(new FabricaEtiologia()
				.converterParaDTO(periodoDeficiencia.getEtiologias()));
		return periodoDeficienciaDTO;
	}

	public final PeriodoDeficiencia converterParaDominio(
			PeriodoDeficienciaDTO periodoDeficienciaDto) {
		PeriodoDeficiencia periodoDeficiencia = new PeriodoDeficiencia();
		if (periodoDeficienciaDto.getId() != null) {
			periodoDeficiencia.setId(periodoDeficienciaDto.getId());
		}
		periodoDeficiencia
				.setDeficiencia(new FabricaDeficiencia()
						.converterParaDominio(periodoDeficienciaDto
								.getDeficienciaDto()));
		periodoDeficiencia.setCid(new FabricaCid()
				.converterParaDominio(periodoDeficienciaDto.getCidDto()));
		periodoDeficiencia.setEpocaIncidencia(new FabricaEpocaIncidencia()
				.converterParaDominio(periodoDeficienciaDto
						.getEpocaIncidenciaDto()));
		periodoDeficiencia.setDataInicial(periodoDeficienciaDto
				.getDataInicial());
		periodoDeficiencia.setDataFinal(periodoDeficienciaDto.getDataFinal());
		periodoDeficiencia
				.setEtiologias(new FabricaEtiologia()
						.converterParaDominio(periodoDeficienciaDto
								.getEtiologiasDto()));
		return periodoDeficiencia;
	}
}
