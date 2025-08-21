package br.laramara.sistelemarketingserver.dominio.seguranca;

import br.laramara.sistelemarketingcommons.dtos.seguranca.TurnoDTO;

public class ContextoTurno {

	public static Turno fabricarTurno() {
		return Turno.TARDE;
	}

	public static TurnoDTO fabricarDto() {
		return new TurnoDTO(Turno.MANHA.toString());
	}
}
