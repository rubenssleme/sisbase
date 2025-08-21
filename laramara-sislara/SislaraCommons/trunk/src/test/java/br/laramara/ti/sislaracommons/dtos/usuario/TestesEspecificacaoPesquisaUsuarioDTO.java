package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoPesquisaUsuarioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisausuario_com_um_parametro_construida_com_sucesso() {
		EspecificacaoPesquisaUsuarioDTO especificacaoPesquisa = new EspecificacaoPesquisaUsuarioDTO();
		ChavePesquisaDTO chave = ChavePesquisaDTO.PRONTUARIO;
		String parametro = "Paulo Augusto";

		especificacaoPesquisa.adicionarParametro(chave, parametro);

		Assert.assertTrue(especificacaoPesquisa.existeChave(chave));
		Assert.assertEquals(especificacaoPesquisa.obterParametro(chave),
				parametro);
		Assert.assertEquals(chave.toString(), "Prontuário");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisausuario_retorna_possiveis_chaves_de_pesquisa() {
		List<ChavePesquisaDTO> obterOpcoes = new EspecificacaoPesquisaUsuarioDTO()
				.obterOpcoes();

		Assert.assertTrue(obterOpcoes.size() > 0);
	}
}
