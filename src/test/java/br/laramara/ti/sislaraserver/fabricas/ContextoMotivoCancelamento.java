package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;

public class ContextoMotivoCancelamento {

	public static MotivoCancelamentoDTO construirMotivoCancelamentoDto() {
		return new MotivoCancelamentoDTO(new Long(1), "");
	}

}
