package br.laramara.ti.sislaracommons.dtos.instituicao;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoPesquisaInstituicaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisainstituicao_com_um_parametro_construida_com_sucesso() {
		EspecificacaoPesquisaInstituicaoDTO especificacaoPesquisa = new EspecificacaoPesquisaInstituicaoDTO();
		ChavePesquisaDTO chave = ChavePesquisaDTO.NOME_INSTITUICAO;
		String parametro = "Colégio ABC";

		especificacaoPesquisa.adicionarParametro(chave, parametro);

		Assert.assertTrue(especificacaoPesquisa.existeChave(chave));
		Assert.assertEquals(especificacaoPesquisa.obterParametro(chave),
				parametro);
		Assert.assertEquals(chave.toString(), "Nome da Instituição");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisainstituicao_retorna_possiveis_chaves_de_pesquisa() {
		List<ChavePesquisaDTO> obterOpcoes = new EspecificacaoPesquisaInstituicaoDTO()
				.obterOpcoes();

		Assert.assertTrue(obterOpcoes.size() > 0);
	}
}
