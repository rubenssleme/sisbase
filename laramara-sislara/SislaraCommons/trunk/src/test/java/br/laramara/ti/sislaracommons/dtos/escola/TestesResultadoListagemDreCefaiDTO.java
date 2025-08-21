package br.laramara.ti.sislaracommons.dtos.escola;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemDreCefaiDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemdrecefaidto_sem_erro_foi_construido() {
		List<DreCefaiDTO> dreCefaiDto = new ArrayList<>();
		dreCefaiDto.add(new DreCefaiDTO(new Long(1), "Butantan"));
		ResultadoListagemDreCefaiDTO resultadoListagemDreCefaiDto = new ResultadoListagemDreCefaiDTO();
		resultadoListagemDreCefaiDto.efetuadoComSucesso(dreCefaiDto);

		Assert.assertTrue(resultadoListagemDreCefaiDto.sucesso());
		Assert.assertFalse(resultadoListagemDreCefaiDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemdrecefaidto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemDreCefaiDTO resultadoListagemDreCefaiDto = new ResultadoListagemDreCefaiDTO();
		resultadoListagemDreCefaiDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemDreCefaiDto.sucesso());
		Assert.assertNotNull(resultadoListagemDreCefaiDto.obterMensagens());
	}
}
