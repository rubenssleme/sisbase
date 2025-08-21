package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EpocaIncidenciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoComprometimentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Comprometimento;
import br.laramara.ti.sislaraserver.dominio.usuario.EpocaIncidencia;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoComprometimento;

public class ContextoPeriodoComprometimento {

	public static PeriodoComprometimentoDTO construirPeriodoComprometimentoDTO() {
		PeriodoComprometimentoDTO periodoComprometimentoDTO = new PeriodoComprometimentoDTO();
		periodoComprometimentoDTO.setComprometimentoDto(new ComprometimentoDTO(new Long(1), "TESTE"));
		periodoComprometimentoDTO.setEpocaIncidenciaDto(new EpocaIncidenciaDTO(EpocaIncidencia.ADQUIRIDA.toString()));
		periodoComprometimentoDTO.setDataInicial("01/01/2000");
		periodoComprometimentoDTO.setDataFinal("31/12/2000");
		return periodoComprometimentoDTO;
	}

	public static PeriodoComprometimento construirPeriodoComprometimento() {
		PeriodoComprometimento periodoComprometimento = new PeriodoComprometimento();
		periodoComprometimento.setComprometimento(new Comprometimento(new Long(1), "TESTE"));
		periodoComprometimento.setEpocaIncidencia(EpocaIncidencia.ADQUIRIDA);
		periodoComprometimento.setDataInicial("01/01/2000");
		periodoComprometimento.setDataFinal("31/12/2000");
		return periodoComprometimento;
	}
}
