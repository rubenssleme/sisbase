package br.laramara.ti.sislaracommons.dtos.solicitacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipoRelatorioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tiporelatoriodto_foi_construida_com_sucesso() {
		String tipoRelatorio = "INSS";

		FinalidadeRelatorioDTO tipoRelatorioDto = new FinalidadeRelatorioDTO(tipoRelatorio);

		Assert.assertEquals(tipoRelatorioDto.toString(), tipoRelatorio);
	}
}
