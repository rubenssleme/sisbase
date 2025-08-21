package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.agenda.MotivoCancelamento;

public class TestesFabricaMotivoCancelamento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_motivo_cancelamento_converte_objeto_de_dominio_para_dto() {
		MotivoCancelamento motivoCancelamento = new MotivoCancelamento(
				new Long(1), "Sem Justifica");

		MotivoCancelamentoDTO motivoCancelamentoDTO = new FabricaMotivoCancelamento()
				.converterParaDTO(motivoCancelamento);

		Assert.assertEquals(motivoCancelamentoDTO.getId(),
				motivoCancelamento.getId());
		Assert.assertEquals(motivoCancelamentoDTO.toString(),
				motivoCancelamento.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_motivo_cancelamento_converte_objeto_de_dto_para_domino() {
		MotivoCancelamentoDTO motivoCancelamentoDto = new MotivoCancelamentoDTO(
				new Long(1), "São Paulo");

		MotivoCancelamento motivoCancelamento = new FabricaMotivoCancelamento()
				.converterParaDominio(motivoCancelamentoDto);

		Assert.assertEquals(motivoCancelamento.getId(), motivoCancelamentoDto.getId());
		Assert.assertEquals(motivoCancelamento.getDescricao(),
				motivoCancelamentoDto.toString());
	}
}
