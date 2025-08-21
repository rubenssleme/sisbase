package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoGeracaoDemandaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadogeracaodemandadto_sem_erro_foi_construido() {
		List<DemandaDTO> demandaDto = new ArrayList<>();
		demandaDto.add(new DemandaDTO());
		demandaDto.add(new DemandaDTO());
		ResultadoGeracaoDemandaDTO resultadoDemandaDto = new ResultadoGeracaoDemandaDTO();
		resultadoDemandaDto.efetuadoComSucesso(demandaDto);

		Assert.assertTrue(resultadoDemandaDto.sucesso());
		Assert.assertFalse(resultadoDemandaDto.getObjetosDto().isEmpty());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadogeracaodemandadto_sem_erro_foi_construido_alternativo() {
		ResultadoGeracaoDemandaDTO resultadoDemandaDto = new ResultadoGeracaoDemandaDTO();
		resultadoDemandaDto.efetuadoComSucesso(new DemandaDTO());

		Assert.assertTrue(resultadoDemandaDto.sucesso());
		Assert.assertFalse(resultadoDemandaDto.getObjetosDto().isEmpty());
	}

}
