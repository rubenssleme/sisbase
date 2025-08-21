package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.TipificacaoServicoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.TipificacaoServico;

public class ContextoTipificacaoServico {
	public static TipificacaoServicoDTO construirTipificacaoServicoDTO() {
		TipificacaoServicoDTO tipificacaoServicoDTO = new TipificacaoServicoDTO(
				new Long(1000), "Assessoramento");
		return tipificacaoServicoDTO;
	}

	public static TipificacaoServico fabricarComTodosOsDados() {
		return new TipificacaoServico(new Long(1000), "Assessoramento");
	}
}
