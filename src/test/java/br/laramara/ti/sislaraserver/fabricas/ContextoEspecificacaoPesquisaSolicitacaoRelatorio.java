package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.StatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaraserver.dominio.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.dominio.solicitacao.StatusSolicitacaoRelatorio;

public class ContextoEspecificacaoPesquisaSolicitacaoRelatorio {

	public static EspecificacaoPesquisaSolicitacaoRelatorio fabricarDominioComTodosOsDados() {
		EspecificacaoPesquisaSolicitacaoRelatorio especificacaoPesquisaSolicitacaoRelatorio = new EspecificacaoPesquisaSolicitacaoRelatorio();
		especificacaoPesquisaSolicitacaoRelatorio.setProntuario("92222");
		especificacaoPesquisaSolicitacaoRelatorio.setProtocolo("12222");
		especificacaoPesquisaSolicitacaoRelatorio.setStatus(StatusSolicitacaoRelatorio.SOLICITADO);
		return especificacaoPesquisaSolicitacaoRelatorio;
	}

	public static EspecificacaoPesquisaSolicitacaoRelatorioDTO fabricarDTOComTodosOsDados() {
		EspecificacaoPesquisaSolicitacaoRelatorioDTO especificacao = new EspecificacaoPesquisaSolicitacaoRelatorioDTO();
		especificacao.setProntuario("1234");
		especificacao.setProtocolo("1234");
		especificacao
				.setStatusSolicitacaoRelatorio(new StatusSolicitacaoRelatorioDTO(
						"SOLICITADO"));
		return especificacao;
	}

}
