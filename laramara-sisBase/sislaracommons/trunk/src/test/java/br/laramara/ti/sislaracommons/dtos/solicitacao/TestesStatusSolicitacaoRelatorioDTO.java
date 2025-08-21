package br.laramara.ti.sislaracommons.dtos.solicitacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesStatusSolicitacaoRelatorioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void statussolicitacaorelatoriodto_foi_construida_com_sucesso() {
		String status = "NORMAL";

		StatusSolicitacaoRelatorioDTO statusDto = new StatusSolicitacaoRelatorioDTO(status);

		Assert.assertEquals(statusDto.toString(), status);
	}
}
