package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemConvenioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemconveniodto_sem_erro_foi_construido() {
		List<ConvenioDTO> convenioDto = new ArrayList<>();
		convenioDto.add(new ConvenioDTO(new Long(12222), "SESC"));
		ResultadoListagemConvenioDTO resultadoListagemConvenioDto = new ResultadoListagemConvenioDTO();
		resultadoListagemConvenioDto.efetuadoComSucesso(convenioDto);

		Assert.assertTrue(resultadoListagemConvenioDto.sucesso());
		Assert.assertFalse(resultadoListagemConvenioDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemconveniodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemConvenioDTO resultadoListagemConvenioDto = new ResultadoListagemConvenioDTO();
		resultadoListagemConvenioDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemConvenioDto.sucesso());
		Assert.assertNotNull(resultadoListagemConvenioDto.obterMensagens());
	}
}
