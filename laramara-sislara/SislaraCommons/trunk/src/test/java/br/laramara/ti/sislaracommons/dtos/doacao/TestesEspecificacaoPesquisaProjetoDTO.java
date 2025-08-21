package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoPesquisaProjetoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaopesquisaprojetodto_sem_erro_foi_construido() {

		String projeto = "PROJETO";

		EspecificacaoPesquisaProjetoDTO especificacaoDto = new EspecificacaoPesquisaProjetoDTO();
		especificacaoDto.setProjeto(projeto);

		Assert.assertEquals(especificacaoDto.getProjeto(), projeto);
	}
}
