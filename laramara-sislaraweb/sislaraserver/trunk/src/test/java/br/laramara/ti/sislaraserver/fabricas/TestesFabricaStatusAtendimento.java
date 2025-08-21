package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.StatusAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.StatusAtendimento;

public class TestesFabricaStatusAtendimento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_converte_objeto_de_dominio_para_dto() {
		StatusAtendimento status = StatusAtendimento.NORMAL;

		StatusAtendimentoDTO statusDTO = new FabricaStatusAtendimento()
				.converterParaDTO(status);

		Assert.assertEquals(statusDTO.toString(), status.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_status_converte_objeto_de_dto_para_dominio() {
		StatusAtendimento statusEsperado = StatusAtendimento.CANCELADO;
		StatusAtendimentoDTO statusDto = new StatusAtendimentoDTO(
				statusEsperado.toString());

		StatusAtendimento statusObtido = new FabricaStatusAtendimento()
				.converterParaDominio(statusDto);

		Assert.assertEquals(statusObtido, statusEsperado);
	}
}
