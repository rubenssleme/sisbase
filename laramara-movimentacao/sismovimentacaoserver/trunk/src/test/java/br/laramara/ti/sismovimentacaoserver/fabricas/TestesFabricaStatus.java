package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.StatusDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Status;

public class TestesFabricaStatus {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_converte_objeto_de_dominio_para_dto() {
		Status status = Status.ARTE_APROVADA;

		StatusDTO statusDTO = new FabricaStatus().converterParaDTO(status);

		Assert.assertEquals(statusDTO.toString(), status.toString());
	}
}
