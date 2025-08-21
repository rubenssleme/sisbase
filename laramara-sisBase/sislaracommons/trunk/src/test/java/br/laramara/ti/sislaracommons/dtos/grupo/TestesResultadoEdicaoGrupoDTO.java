package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoGrupoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaogrupodto_sem_erro_foi_construido() {
		ResultadoEdicaoGrupoDTO resultadoEdicaoGrupoDto = new ResultadoEdicaoGrupoDTO();
		resultadoEdicaoGrupoDto.efetuadoComSucesso(new GrupoDTO());

		Assert.assertTrue(resultadoEdicaoGrupoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoGrupoDto.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoGrupoDto.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaogrupodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoEdicaoGrupoDTO resultadoEdicaoGrupoDto = new ResultadoEdicaoGrupoDTO();
		resultadoEdicaoGrupoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoEdicaoGrupoDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoGrupoDto.obterMensagens());
	}
}
