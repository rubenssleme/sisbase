package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.PeriodoDTO;
import br.laramara.ti.sislaraserver.dominio.escola.Periodo;

public class FabricaPeriodo extends FabricaBase<PeriodoDTO, Periodo> {

	public final PeriodoDTO converterParaDTO(Periodo periodo) {
		return periodo != null ? new PeriodoDTO(periodo.toString()) : null;
	}

	public final Periodo converterParaDominio(PeriodoDTO periodo) {
		return periodo != null ? Periodo.valueOf(Periodo.class,
				periodo.toString()) : null;
	}
}
