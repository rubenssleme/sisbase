package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoPesquisaUsuarioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadopesquisausuariodto_com_usuarios_sem_erro_foi_construido() {
		List<UsuarioDTO> usuarios = new ArrayList<>();
		usuarios.add(new UsuarioDTO());
		usuarios.add(new UsuarioDTO());
		
		ResultadoListagemUsuarioDTO resultado = new ResultadoListagemUsuarioDTO();
		resultado.efetuadoComSucesso(usuarios);
	
		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadopesquisausuariodto_com_um_usuario_sem_erro_foi_construido() {
		
		ResultadoListagemUsuarioDTO resultado = new ResultadoListagemUsuarioDTO();
		resultado.efetuadoComSucesso(new UsuarioDTO());
	
		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadopesquisausuariodto_vazio_foi_construido() {
		
		ResultadoListagemUsuarioDTO resultado = new ResultadoListagemUsuarioDTO();
		resultado.nenhumRegistroEncontrado();
	
		Assert.assertFalse(resultado.sucesso());
		Assert.assertNotNull(resultado.obterMensagens());
	}
}
