package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemStatusDemandaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemstatusdemandadto_sem_erro_foi_construido() {
		List<StatusDemandaDTO> statusDemandaDto = new ArrayList<>();
		statusDemandaDto.add(new StatusDemandaDTO("DOADA"));
		
		ResultadoListagemStatusDemandaDTO resultadoListagemStatusDemandaDto = new ResultadoListagemStatusDemandaDTO();
		resultadoListagemStatusDemandaDto.efetuadoComSucesso(statusDemandaDto);

		Assert.assertTrue(resultadoListagemStatusDemandaDto.sucesso());
		Assert.assertFalse(resultadoListagemStatusDemandaDto.getObjetosDto().isEmpty());
	}
}
