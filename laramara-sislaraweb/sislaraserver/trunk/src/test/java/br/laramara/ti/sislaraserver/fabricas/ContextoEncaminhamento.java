package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.EncaminhamentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Encaminhamento;

public class ContextoEncaminhamento {

	public static Encaminhamento fabricarEncaminhamento() {
		return new Encaminhamento(new Long(1), "TESTE");
	}

	public static EncaminhamentoDTO fabricarEncaminhamentoDto() {
		return new EncaminhamentoDTO(new Long(1), "TESTE");
	}

}
