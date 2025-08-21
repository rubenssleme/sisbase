package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.contribuicao.StatusContribuinteDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.contribuicao.StatusContribuinte;

public class TestesFabricaStatusContribuinte {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_contribuinte_converte_objeto_de_dominio_para_dto() {
		StatusContribuinte status = StatusContribuinte.DESATIVADO;

		StatusContribuinteDTO statusDTO = new FabricaStatusContribuinte().converterParaDTO(status);

		Assert.assertEquals(statusDTO.toString(), status.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_contribuinte_converte_objeto_de_dto_para_dominio() {
		StatusContribuinte statusEsperado = StatusContribuinte.ATIVADO;
		StatusContribuinteDTO statusDto = new StatusContribuinteDTO(statusEsperado.toString());

		StatusContribuinte statusObtido = new FabricaStatusContribuinte().converterParaDominio(statusDto);

		Assert.assertEquals(statusObtido, statusEsperado);
	}
}
