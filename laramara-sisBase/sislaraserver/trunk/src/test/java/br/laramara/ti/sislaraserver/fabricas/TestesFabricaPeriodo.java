package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.PeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.escola.Periodo;

public class TestesFabricaPeriodo {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_converte_objeto_de_dominio_para_dto() {
		Periodo periodo = Periodo.MANHA;

		PeriodoDTO periodoDTO = new FabricaPeriodo().converterParaDTO(periodo);

		Assert.assertEquals(periodoDTO.toString(), periodo.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_converte_objeto_de_dto_para_dominio() {
		Periodo periodo = Periodo.MANHA;
		PeriodoDTO periodoDto = new PeriodoDTO(periodo.toString());

		Periodo periodoObtido = new FabricaPeriodo()
				.converterParaDominio(periodoDto);

		Assert.assertEquals(periodoObtido, periodo);
	}

}
