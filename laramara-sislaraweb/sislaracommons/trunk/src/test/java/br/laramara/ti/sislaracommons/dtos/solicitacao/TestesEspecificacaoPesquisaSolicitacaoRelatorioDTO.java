package br.laramara.ti.sislaracommons.dtos.solicitacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoPesquisaSolicitacaoRelatorioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisasolicitacaorelatoriodto_sem_erro_foi_construido() {
		String id = "1234";
		StatusSolicitacaoRelatorioDTO status = new StatusSolicitacaoRelatorioDTO(
				"SOLICITADO");

		EspecificacaoPesquisaSolicitacaoRelatorioDTO especificacaoDto = new EspecificacaoPesquisaSolicitacaoRelatorioDTO();
		especificacaoDto.setProntuario(id);
		especificacaoDto.setProtocolo(id);
		especificacaoDto.setStatusSolicitacaoRelatorio(status);

		Assert.assertEquals(especificacaoDto.getProntuario(), id);
		Assert.assertEquals(especificacaoDto.getProtocolo(), id);
		Assert.assertEquals(especificacaoDto.getStatusSolicitacaoRelatorio()
				.toString(), status.toString());
	}
}
