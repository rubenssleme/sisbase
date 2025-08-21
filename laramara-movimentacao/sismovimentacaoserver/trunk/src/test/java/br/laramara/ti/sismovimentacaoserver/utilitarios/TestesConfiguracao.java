package br.laramara.ti.sismovimentacaoserver.utilitarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesConfiguracao {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void configuracao_carrega_parametro(){
		Configuracao configuracao = new Configuracao();
		String diretorioConfiguracaoEsperado = configuracao.obterConfiguracao(Configuracao.DIRETORIO_RELATORIOS);
		
		Assert.assertNotNull(diretorioConfiguracaoEsperado);
	}
}
