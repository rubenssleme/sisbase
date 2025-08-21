package br.laramara.ti.sislaraserver.dominio.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesChaveBloqueioUsuario {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void chave_bloqueio_usuario_gera_por_usuario() {
		UsuarioDTO usuarioDto = new UsuarioDTO(new Long(123456));
		ChaveBloqueioUsuario chave = new ChaveBloqueioUsuario(usuarioDto);

		Assert.assertEquals(chave.toString(), "123456");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void chave_bloqueio_usuario_gera_por_identificacao() {
		String identificacao = "233333";
		ChaveBloqueioUsuario chave = new ChaveBloqueioUsuario(identificacao);

		Assert.assertEquals(chave.toString(), identificacao);
	}
}
