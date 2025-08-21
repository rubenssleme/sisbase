package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemTipoApoioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipoapoiodto_sem_erro_foi_construido() {
		List<TipoApoioDTO> tipoApoioDto = new ArrayList<>();
		tipoApoioDto.add(new TipoApoioDTO("DV"));
		ResultadoListagemTipoApoioDTO resultadoListagemTipoApoioDto = new ResultadoListagemTipoApoioDTO();
		resultadoListagemTipoApoioDto.efetuadoComSucesso(tipoApoioDto);

		Assert.assertTrue(resultadoListagemTipoApoioDto.sucesso());
		Assert.assertFalse(resultadoListagemTipoApoioDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipoapoiodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemTipoApoioDTO resultadoListagemTipoApoioDto = new ResultadoListagemTipoApoioDTO();
		resultadoListagemTipoApoioDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemTipoApoioDto.sucesso());
		Assert.assertNotNull(resultadoListagemTipoApoioDto.obterMensagens());
	}
}
