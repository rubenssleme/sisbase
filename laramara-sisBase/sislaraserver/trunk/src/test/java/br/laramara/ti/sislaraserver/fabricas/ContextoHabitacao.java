package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.HabitacaoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Habitacao;

public class ContextoHabitacao {

	public static HabitacaoDTO construirhabitacaoDto() {
		return new HabitacaoDTO(new Long(1000), "TESTE");
	}

	public static Habitacao fabricarHabitacao() {
		return new Habitacao(new Long(1000), "TESTE2");
	}

}
