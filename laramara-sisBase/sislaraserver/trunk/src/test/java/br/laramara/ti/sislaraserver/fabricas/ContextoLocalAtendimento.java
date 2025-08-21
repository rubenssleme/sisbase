package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;

public class ContextoLocalAtendimento {

	public static LocalAtendimentoDTO construirLocalAtendimentoDTO() {
		LocalAtendimentoDTO localAtendimentoDTO = new LocalAtendimentoDTO(
				new Long(1), "Sala 1");
		return localAtendimentoDTO;
	}

	public static LocalAtendimento fabricarComTodosOsDados() {
		return new LocalAtendimento(new Long(1), "Sala 1");
	}
}
