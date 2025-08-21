package br.laramara.ti.sislaracommons.dtos.familiar;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemParentescoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemparentescodto_sem_erro_foi_construido() {
		List<ParentescoDTO> parentescoDto = new ArrayList<>();
		parentescoDto.add(new ParentescoDTO(new Long(1), "Pai"));
		ResultadoListagemParentescoDTO resultadoListagemParentescoDto = new ResultadoListagemParentescoDTO();
		resultadoListagemParentescoDto.efetuadoComSucesso(parentescoDto);

		Assert.assertTrue(resultadoListagemParentescoDto.sucesso());
		Assert.assertFalse(resultadoListagemParentescoDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemparentescodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemParentescoDTO resultadoListagemParentescoDto = new ResultadoListagemParentescoDTO();
		resultadoListagemParentescoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemParentescoDto.sucesso());
		Assert.assertNotNull(resultadoListagemParentescoDto.obterMensagens());
	}
}
