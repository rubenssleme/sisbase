package br.laramara.ti.sislaraserver.dominio.solicitacao;

import java.util.ArrayList;
import java.util.List;

public enum StatusSolicitacaoRelatorio {
	SOLICITADO, ENCAMINHADO_PELA_RECEPCAO, EMITIDO_PELO_PROFISSIONAL, CANCELADO, ENTREGUE_PARA_RECEPCAO, ENTREGUE_PARA_CORREIOS, ENTREGUE_PARA_FAMILIA;

	public static List<StatusSolicitacaoRelatorio> obterStatusEntrega() {
		List<StatusSolicitacaoRelatorio> status = new ArrayList<>();
		status.add(StatusSolicitacaoRelatorio.ENTREGUE_PARA_RECEPCAO);
		status.add(StatusSolicitacaoRelatorio.ENTREGUE_PARA_CORREIOS);
		status.add(StatusSolicitacaoRelatorio.ENTREGUE_PARA_FAMILIA);
		return status;
	}
}
