package br.laramara.ti.sislaracommons.dtos.precadastro;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoPesquisaPreCadastroDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisaprecadastro_com_um_parametro_construida_com_sucesso() {
		EspecificacaoPesquisaPreCadastroDTO especificacaoPesquisa = new EspecificacaoPesquisaPreCadastroDTO();
		ChavePesquisaDTO chave = ChavePesquisaDTO.PRE_CADASTRO;
		String parametro = "Josep Meaza";

		especificacaoPesquisa.adicionarParametro(chave, parametro);

		Assert.assertTrue(especificacaoPesquisa.existeChave(chave));
		Assert.assertEquals(especificacaoPesquisa.obterParametro(chave),
				parametro);
		Assert.assertEquals(chave.toString(), "Nome do Usuário");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisaprecadastro_retorna_possiveis_chaves_de_pesquisa() {
		List<ChavePesquisaDTO> obterOpcoes = new EspecificacaoPesquisaPreCadastroDTO()
				.obterOpcoes();

		Assert.assertTrue(obterOpcoes.size() > 0);
	}
}
