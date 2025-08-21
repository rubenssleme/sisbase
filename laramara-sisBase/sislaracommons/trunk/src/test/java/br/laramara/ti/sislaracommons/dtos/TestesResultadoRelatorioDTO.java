package br.laramara.ti.sislaracommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoRelatorioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_relatoriodto_com_relatorio_sem_erro_foi_construido() {
		byte[] conteudoArquivo = new byte[] { (byte) 1024 };
		String extensao = "pdf";
		ArquivoDTO resultado = new ArquivoDTO(conteudoArquivo, extensao);

		Assert.assertTrue(resultado.sucesso());
		Assert.assertTrue(resultado.obterMensagens().isEmpty());
		Assert.assertNotNull(resultado.obterConteudo());
		Assert.assertEquals(resultado.obterExtensao(), extensao);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_relatoriodto_sem_relatorio_com_erro_foi_construido() {
		ArquivoDTO resultado = new ArquivoDTO();
		resultado.adicionarErro("Erro durante construção de relatório.");

		Assert.assertFalse(resultado.sucesso());
		Assert.assertFalse(resultado.obterMensagens().isEmpty());
		Assert.assertNull(resultado.obterConteudo());
	}
}
