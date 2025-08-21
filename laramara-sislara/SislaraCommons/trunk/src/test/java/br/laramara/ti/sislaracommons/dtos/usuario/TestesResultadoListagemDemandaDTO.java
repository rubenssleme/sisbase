package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemDemandaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemDemandaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemdemandadto_sem_erro_foi_construido() {
		List<DemandaDTO> demandaDto = new ArrayList<>();
		demandaDto.add(new DemandaDTO());
		ResultadoListagemDemandaDTO resultadoListagemDemandaDto = new ResultadoListagemDemandaDTO();
		resultadoListagemDemandaDto.efetuadoComSucesso(demandaDto);

		Assert.assertTrue(resultadoListagemDemandaDto.sucesso());
		Assert.assertFalse(resultadoListagemDemandaDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemdemandadto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemDemandaDTO resultadoListagemDemandaDto = new ResultadoListagemDemandaDTO();
		resultadoListagemDemandaDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemDemandaDto.sucesso());
		Assert.assertNotNull(resultadoListagemDemandaDto.obterMensagens());
	}

}
