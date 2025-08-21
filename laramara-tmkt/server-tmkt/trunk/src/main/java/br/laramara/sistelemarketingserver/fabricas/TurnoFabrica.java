package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.seguranca.TurnoDTO;
import br.laramara.sistelemarketingserver.dominio.seguranca.Turno;

public class TurnoFabrica extends FabricaBase<TurnoDTO, Turno> {
	public final TurnoDTO converterParaDTO(Turno turno) {
		return turno != null ? new TurnoDTO(turno.toString()) : null;
	}

	public final Turno converterParaDominio(TurnoDTO turnoDto) {
		return turnoDto != null ? Turno.valueOf(Turno.class, turnoDto.toString()) : null;
	}
}
