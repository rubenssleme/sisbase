package br.laramara.ti.sislaraserver.dominio.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoProgramacao;

public class TestesProgramacao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void programacao_validacao_com_erro_obrigatoriedade() {
		Programacao programacao = new Programacao();

		programacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(programacao.obterNumeroErros(), 3);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void programacao_validacao_com_erro_de_tamanho_maximo() {
		Programacao programacao = ContextoProgramacao.fabricarProgramacaoComTodosOsDados();
		programacao.setAula("1111111");
		programacao.setTemaConteudo(ContextoGenerico.obterGrande());

		programacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(programacao.obterNumeroErros(), 2);
	}
}
