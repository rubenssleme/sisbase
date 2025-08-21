package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.StatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.solicitacao.StatusSolicitacaoRelatorio;

public class TestesFabricaStatusSolicitacaoRelatorio {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_converte_objeto_de_dominio_para_dto() {
		StatusSolicitacaoRelatorio status = StatusSolicitacaoRelatorio.CANCELADO;

		StatusSolicitacaoRelatorioDTO statusDTO = new FabricaStatusSolicitacaoRelatorio()
				.converterParaDTO(status);

		Assert.assertEquals(statusDTO.toString(), status.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_converte_objeto_de_dto_para_dominio() {
		StatusSolicitacaoRelatorio statusEsperado = StatusSolicitacaoRelatorio.ENCAMINHADO_PELA_RECEPCAO;
		StatusSolicitacaoRelatorioDTO statusDto = new StatusSolicitacaoRelatorioDTO(
				statusEsperado.toString());

		StatusSolicitacaoRelatorio statusObtido = new FabricaStatusSolicitacaoRelatorio()
				.converterParaDominio(statusDto);

		Assert.assertEquals(statusObtido, statusEsperado);
	}
}
