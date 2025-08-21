package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemTipoEspecialidadeDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipoespecialidadedto_sem_erro_foi_construido() {
		List<TipoEspecialidadeDTO> tipoEspecialidadeDto = new ArrayList<>();
		tipoEspecialidadeDto.add(new TipoEspecialidadeDTO("DV"));
		ResultadoListagemTipoEspecialidadeDTO resultadoListagemTipoEspecialidadeDto = new ResultadoListagemTipoEspecialidadeDTO();
		resultadoListagemTipoEspecialidadeDto.efetuadoComSucesso(tipoEspecialidadeDto);

		Assert.assertTrue(resultadoListagemTipoEspecialidadeDto.sucesso());
		Assert.assertFalse(resultadoListagemTipoEspecialidadeDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipoespecialidadedto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemTipoEspecialidadeDTO resultadoListagemTipoEspecialidadeDto = new ResultadoListagemTipoEspecialidadeDTO();
		resultadoListagemTipoEspecialidadeDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemTipoEspecialidadeDto.sucesso());
		Assert.assertNotNull(resultadoListagemTipoEspecialidadeDto.obterMensagens());
	}
}
