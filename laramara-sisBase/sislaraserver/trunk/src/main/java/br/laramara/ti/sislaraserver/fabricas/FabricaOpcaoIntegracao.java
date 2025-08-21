package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.OpcaoIntegracaoDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.OpcaoIntegracao;

public class FabricaOpcaoIntegracao extends FabricaBase<OpcaoIntegracaoDTO, OpcaoIntegracao> {
	public final OpcaoIntegracaoDTO converterParaDTO(OpcaoIntegracao opcaoIntegracao) {
		return opcaoIntegracao != null ? new OpcaoIntegracaoDTO(opcaoIntegracao.toString()) : null;
	}

	public final OpcaoIntegracao converterParaDominio(OpcaoIntegracaoDTO opcaoIntegracao) {
		return opcaoIntegracao != null ? OpcaoIntegracao.valueOf(OpcaoIntegracao.class, opcaoIntegracao.toString())
				: null;
	}
}
