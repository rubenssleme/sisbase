package br.laramara.ti.sislaracommons.utilitarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class TestesSessao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void sessao_armazena_objeto_e_obtem_de_volta() {
		String nome = "Paulo Augusto";
		
		InformacaoEssencialDTO informacaoEssencialDto = new InformacaoEssencialDTO();
		informacaoEssencialDto.setNome(nome);
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setInformacaoEssencialDto(informacaoEssencialDto);
		
		Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_USUARIO, usuarioDto);

		boolean possuiDtoArmazenado = Sessao.obterInstancia()
				.possuiDto((Sessao.CHAVE_USUARIO));
		UsuarioDTO usuarioDtoObtido = (UsuarioDTO) Sessao.obterInstancia()
				.obterDto(Sessao.CHAVE_USUARIO);

		Assert.assertTrue(possuiDtoArmazenado);
		Assert.assertEquals(usuarioDtoObtido.getInformacaoEssencialDto().getNome(), nome);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void sessao_armazena_objeto_e_remove() {

		Sessao.obterInstancia().armazenarDTO(Sessao.CHAVE_USUARIO,
				new UsuarioDTO());

		Sessao.obterInstancia().removerDto(Sessao.CHAVE_USUARIO);

		Assert.assertFalse(Sessao.obterInstancia().possuiDto(
				Sessao.CHAVE_USUARIO));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void sessao_armazena_token_e_obtem_de_volta() {
		TokenDTO tokenDto = new TokenDTO("kjdajddhksa");

		Sessao.obterInstancia().armazenarTokenDTO(tokenDto);

		TokenDTO tokenDtoObtido = (TokenDTO) Sessao.obterInstancia()
				.obterToken();

		Assert.assertEquals(tokenDtoObtido.getToken(), tokenDto.getToken());
	}
}
