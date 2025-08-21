package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemLocalAtendimentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemlocalatendimentodto_sem_erro_foi_construido() {
		List<LocalAtendimentoDTO> localAtendimentoDto = new ArrayList<>();
		localAtendimentoDto.add(new LocalAtendimentoDTO(new Long(1), "SALA 1"));
		localAtendimentoDto.add(new LocalAtendimentoDTO(new Long(2), "SALA 2"));
		ResultadoListagemLocalAtendimentoDTO resultadoListagemLocalAtendimentoDto = new ResultadoListagemLocalAtendimentoDTO();
		resultadoListagemLocalAtendimentoDto
				.efetuadoComSucesso(localAtendimentoDto);

		Assert.assertTrue(resultadoListagemLocalAtendimentoDto.sucesso());
		Assert.assertFalse(resultadoListagemLocalAtendimentoDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemlocalatendimentodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemLocalAtendimentoDTO resultadoListagemLocalAtendimentoDto = new ResultadoListagemLocalAtendimentoDTO();
		resultadoListagemLocalAtendimentoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemLocalAtendimentoDto.sucesso());
		Assert.assertNotNull(resultadoListagemLocalAtendimentoDto
				.obterMensagens());
	}
}