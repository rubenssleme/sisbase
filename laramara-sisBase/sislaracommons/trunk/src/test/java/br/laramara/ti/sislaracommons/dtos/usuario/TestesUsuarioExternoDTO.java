package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesUsuarioExternoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuarioexternodto_foi_construido_com_sucesso() {
		Long id = 111111L;
		String email = "usuario.externo@gmail.com";
		String senha = "1234";
		boolean bloqueado = false;
		String versao = "1";

		UsuarioExternoDTO usuarioExternoDto = new UsuarioExternoDTO();
		
		usuarioExternoDto.setId(id);
		usuarioExternoDto.setEmail(email);
		usuarioExternoDto.setSenha(senha);
		usuarioExternoDto.setBloqueado(bloqueado);
		usuarioExternoDto.setVersao(versao);
		
		Assert.assertEquals(usuarioExternoDto.getId(), id);
		Assert.assertEquals(usuarioExternoDto.getEmail(), email);
		Assert.assertEquals(usuarioExternoDto.getSenha(), senha);
		Assert.assertEquals(usuarioExternoDto.isBloqueado(), bloqueado);
		Assert.assertEquals(usuarioExternoDto.getVersao(), versao);
	}
}
