package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.FinalidadeRelatorioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.solicitacao.FinalidadeRelatorio;

public class TestesFabricaFinalidadeRelatorio {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_finalidade_relatorio_converte_objeto_de_dominio_para_dto() {
		FinalidadeRelatorio finalidadeRelatorio = FinalidadeRelatorio.INSS;

		FinalidadeRelatorioDTO finalidadeRelatorioDTO = new FabricaFinalidadeRelatorio()
				.converterParaDTO(finalidadeRelatorio);

		Assert.assertEquals(finalidadeRelatorioDTO.toString(),
				finalidadeRelatorio.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_finalidade_relatorio_converte_objeto_de_dto_para_dominio() {
		FinalidadeRelatorio finalidadeRelaotorioEsperado = FinalidadeRelatorio.CET;
		FinalidadeRelatorioDTO finalidadeRelatorioDto = new FinalidadeRelatorioDTO(
				finalidadeRelaotorioEsperado.toString());

		FinalidadeRelatorio tipoRelatorioObtido = new FabricaFinalidadeRelatorio()
				.converterParaDominio(finalidadeRelatorioDto);

		Assert.assertEquals(tipoRelatorioObtido, finalidadeRelaotorioEsperado);
	}
}
