package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.DoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Doenca;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoDoenca;

public class ContextoPeriodoDoenca {
	
	public static PeriodoDoenca construirComTodosOsDados(){
		PeriodoDoenca periodoDoenca = new PeriodoDoenca();
		periodoDoenca.setDoenca(new Doenca(new Long(1), "HIV"));
		periodoDoenca.setObs("Obs obs");
		periodoDoenca.setDataInicial("01/01/2011");
		periodoDoenca.setDataFinal("31/12/2011");
		return periodoDoenca;
	}

	public static PeriodoDoencaDTO construirPeriodoDoencaDTO() {
		PeriodoDoencaDTO periodoDoencaDto = new PeriodoDoencaDTO();
		periodoDoencaDto.setDoencaDto(new DoencaDTO(new Long(1), "HIV"));
		periodoDoencaDto.setObs("Obsd obs");
		periodoDoencaDto.setDataInicial("01/01/2011");
		periodoDoencaDto.setDataFinal("31/12/2011");
		return periodoDoencaDto;
	}
}
