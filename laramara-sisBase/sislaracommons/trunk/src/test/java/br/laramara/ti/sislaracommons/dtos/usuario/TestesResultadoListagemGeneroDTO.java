package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemGeneroDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemgenerodto_sem_erro_foi_construido() {
		List<GeneroDTO> generoDto = new ArrayList<>();
		generoDto.add(new GeneroDTO("MASCULINO"));
		ResultadoListagemGeneroDTO resultadoListagemGeneroDto = new ResultadoListagemGeneroDTO();
		resultadoListagemGeneroDto.efetuadoComSucesso(generoDto);

		Assert.assertTrue(resultadoListagemGeneroDto.sucesso());
		Assert.assertFalse(resultadoListagemGeneroDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemgenerodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemGeneroDTO resultadoListagemGeneroDto = new ResultadoListagemGeneroDTO();
		resultadoListagemGeneroDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemGeneroDto.sucesso());
		Assert.assertNotNull(resultadoListagemGeneroDto.obterMensagens());
	}
}
