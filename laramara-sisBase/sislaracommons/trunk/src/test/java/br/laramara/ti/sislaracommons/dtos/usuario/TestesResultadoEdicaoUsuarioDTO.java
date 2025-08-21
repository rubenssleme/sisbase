package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoEdicaoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoEdicaoUsuarioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaousuariodto_sem_erro_foi_construido() {
		ResultadoEdicaoUsuarioDTO resultadoEdicaoUsuarioDto = new ResultadoEdicaoUsuarioDTO();
		resultadoEdicaoUsuarioDto.efetuadoComSucesso(new UsuarioDTO());

		Assert.assertTrue(resultadoEdicaoUsuarioDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoUsuarioDto.obterObjetoDtoEditado());
		Assert.assertNotNull(resultadoEdicaoUsuarioDto.obterMensagens());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadoedicaousuariodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoEdicaoUsuarioDTO resultadoEdicaoUsuarioDto = new ResultadoEdicaoUsuarioDTO();
		resultadoEdicaoUsuarioDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoEdicaoUsuarioDto.sucesso());
		Assert.assertNotNull(resultadoEdicaoUsuarioDto.obterMensagens());
	}
}
