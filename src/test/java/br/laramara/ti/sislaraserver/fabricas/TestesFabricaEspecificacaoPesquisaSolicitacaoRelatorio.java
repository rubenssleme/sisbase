package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorio;

public class TestesFabricaEspecificacaoPesquisaSolicitacaoRelatorio {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_especificacao_pesquisa_solicitacao_relatorio_converte_objeto_de_dto_para_dominio() {
		EspecificacaoPesquisaSolicitacaoRelatorioDTO especificacaoPesquisaSolicitacaoRelatorioDto = ContextoEspecificacaoPesquisaSolicitacaoRelatorio
				.fabricarDTOComTodosOsDados();

		EspecificacaoPesquisaSolicitacaoRelatorio especificacaoPesquisaSolicitacaoRelatorio = new FabricaEspecificacaoPesquisaSolicitacaoRelatorio()
				.converterParaDominio(especificacaoPesquisaSolicitacaoRelatorioDto);

		Assert.assertEquals(especificacaoPesquisaSolicitacaoRelatorio
				.getProntuario().toString(),
				especificacaoPesquisaSolicitacaoRelatorioDto.getProntuario()
						.toString());
		Assert.assertEquals(especificacaoPesquisaSolicitacaoRelatorio
				.getProtocolo().toString(),
				especificacaoPesquisaSolicitacaoRelatorioDto.getProtocolo()
						.toString());
		Assert.assertEquals(especificacaoPesquisaSolicitacaoRelatorio
				.getStatus().toString(),
				especificacaoPesquisaSolicitacaoRelatorioDto
						.getStatusSolicitacaoRelatorio().toString());
	}
}
