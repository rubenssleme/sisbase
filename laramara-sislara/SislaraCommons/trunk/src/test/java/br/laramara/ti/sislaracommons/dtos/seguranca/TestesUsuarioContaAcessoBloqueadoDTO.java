package br.laramara.ti.sislaracommons.dtos.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesUsuarioContaAcessoBloqueadoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_conta_acesso_bloqueado_foi_construida_com_sucesso() {
		String prontuario = "122222";
		PerfilDTO perfilDTO = new PerfilDTO(new Long(2), "Administrador");
		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setId(new Long(2));
		contaAcessoDto.setUsuario("pabsantos");
		contaAcessoDto.setSenha( "1234");
		contaAcessoDto.setPerfilDto(perfilDTO);

		UsuarioContaAcessoBloqueadoDTO usuarioContaAcessoBloqueadoDTO = new UsuarioContaAcessoBloqueadoDTO(
				prontuario, contaAcessoDto);

		Assert.assertEquals(usuarioContaAcessoBloqueadoDTO.getIdentificacao(),
				prontuario);
		Assert.assertEquals(usuarioContaAcessoBloqueadoDTO.getContaAcessoDTO()
				.getLogin(), contaAcessoDto.getLogin());
	}
}
