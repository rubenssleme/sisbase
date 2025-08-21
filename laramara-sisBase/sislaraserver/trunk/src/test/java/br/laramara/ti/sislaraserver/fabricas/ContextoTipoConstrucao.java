package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.TipoConstrucaoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoConstrucao;

public class ContextoTipoConstrucao {

	public static TipoConstrucaoDTO construirTipoConstrucao() {
		return new TipoConstrucaoDTO(new Long(1000), "TESTE");
	}

	public static TipoConstrucao fabricarTipoConstrucao() {
		return new TipoConstrucao(new Long(1000), "TESTE3");
	}

}
