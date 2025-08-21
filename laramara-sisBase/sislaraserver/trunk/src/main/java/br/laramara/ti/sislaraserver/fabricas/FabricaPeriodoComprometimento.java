package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoComprometimentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoComprometimento;

public class FabricaPeriodoComprometimento extends FabricaBase<PeriodoComprometimentoDTO, PeriodoComprometimento> {

	public final PeriodoComprometimentoDTO converterParaDTO(PeriodoComprometimento periodoComprometimento) {
		PeriodoComprometimentoDTO periodoComprometimentoDTO = new PeriodoComprometimentoDTO();
		periodoComprometimentoDTO.setId(periodoComprometimento.getId());
		periodoComprometimentoDTO.setComprometimentoDto(
				new FabricaComprometimento().converterParaDTO(periodoComprometimento.getComprometimento()));
		periodoComprometimentoDTO.setEpocaIncidenciaDto(
				new FabricaEpocaIncidencia().converterParaDTO(periodoComprometimento.getEpocaIncidencia()));
		periodoComprometimentoDTO.setDataInicial(periodoComprometimento.getDataInicial());
		periodoComprometimentoDTO.setDataFinal(periodoComprometimento.getDataFinal());
		return periodoComprometimentoDTO;
	}

	public final PeriodoComprometimento converterParaDominio(PeriodoComprometimentoDTO periodoComprometimentoDto) {
		PeriodoComprometimento periodoComprometimento = new PeriodoComprometimento();
		if (periodoComprometimentoDto.getId() != null) {
			periodoComprometimento.setId(periodoComprometimentoDto.getId());
		}
		periodoComprometimento.setComprometimento(
				new FabricaComprometimento().converterParaDominio(periodoComprometimentoDto.getComprometimentoDto()));
		periodoComprometimento.setEpocaIncidencia(
				new FabricaEpocaIncidencia().converterParaDominio(periodoComprometimentoDto.getEpocaIncidenciaDto()));
		periodoComprometimento.setDataInicial(periodoComprometimentoDto.getDataInicial());
		periodoComprometimento.setDataFinal(periodoComprometimentoDto.getDataFinal());
		return periodoComprometimento;
	}

}
