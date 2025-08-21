package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemDiaSemanaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemdiasemanadto_sem_erro_foi_construido() {
		List<DiaSemanaDTO> diaSemanaDto = new ArrayList<>();
		diaSemanaDto.add(new DiaSemanaDTO("SEGUNDA"));
		diaSemanaDto.add(new DiaSemanaDTO("TERCA"));
		ResultadoListagemDiaSemanaDTO resultadoListagemDiaSemanaDto = new ResultadoListagemDiaSemanaDTO();
		resultadoListagemDiaSemanaDto.efetuadoComSucesso(diaSemanaDto);

		Assert.assertTrue(resultadoListagemDiaSemanaDto.sucesso());
		Assert.assertFalse(resultadoListagemDiaSemanaDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemdiasemanadto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemDiaSemanaDTO resultadoListagemDiaSemanaDto = new ResultadoListagemDiaSemanaDTO();
		resultadoListagemDiaSemanaDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemDiaSemanaDto.sucesso());
		Assert.assertNotNull(resultadoListagemDiaSemanaDto.obterMensagens());
	}
}