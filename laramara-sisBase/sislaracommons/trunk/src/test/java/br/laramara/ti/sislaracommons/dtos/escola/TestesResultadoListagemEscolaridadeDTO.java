package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemEscolaridadeDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemescolaridadedto_sem_erro_foi_construido() {
		List<EscolaridadeDTO> escolaridadeDto = new ArrayList<>();
		escolaridadeDto.add(new EscolaridadeDTO(new Long(1), "SUPERIOR",
				new ArrayList<SerieDTO>()));
		ResultadoListagemEscolaridadeDTO resultadoListagemEscolaridadeDto = new ResultadoListagemEscolaridadeDTO();
		resultadoListagemEscolaridadeDto.efetuadoComSucesso(escolaridadeDto);

		Assert.assertTrue(resultadoListagemEscolaridadeDto.sucesso());
		Assert.assertFalse(resultadoListagemEscolaridadeDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemescolaridadedto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemEscolaridadeDTO resultadoListagemEscolaridadeDto = new ResultadoListagemEscolaridadeDTO();
		resultadoListagemEscolaridadeDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemEscolaridadeDto.sucesso());
		Assert.assertNotNull(resultadoListagemEscolaridadeDto.obterMensagens());
	}
}
