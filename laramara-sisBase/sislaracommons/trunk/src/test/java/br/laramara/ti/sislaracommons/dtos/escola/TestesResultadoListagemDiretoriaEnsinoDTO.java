package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemDiretoriaEnsinoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemdiretoriaensinodto_sem_erro_foi_construido() {
		List<DiretoriaEnsinoDTO> diretoriaEnsinoDto = new ArrayList<>();
		diretoriaEnsinoDto.add(new DiretoriaEnsinoDTO(new Long(1),
				"Região Centro"));
		ResultadoListagemDiretoriaEnsinoDTO resultadoListagemDiretoriaEnsinoDto = new ResultadoListagemDiretoriaEnsinoDTO();
		resultadoListagemDiretoriaEnsinoDto
				.efetuadoComSucesso(diretoriaEnsinoDto);

		Assert.assertTrue(resultadoListagemDiretoriaEnsinoDto.sucesso());
		Assert.assertFalse(resultadoListagemDiretoriaEnsinoDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemdiretoriaensinodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemDiretoriaEnsinoDTO resultadoListagemDiretoriaEnsinoDto = new ResultadoListagemDiretoriaEnsinoDTO();
		resultadoListagemDiretoriaEnsinoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemDiretoriaEnsinoDto.sucesso());
		Assert.assertNotNull(resultadoListagemDiretoriaEnsinoDto
				.obterMensagens());
	}
}
