package br.laramara.ti.sislaraserver.dominio.inscricao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.externa.ContextoInscricao;

public class TestesInscricao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void inscricao_com_validacao_sem_erro() {
		Inscricao inscricao = ContextoInscricao.construirInscricaoComTodosOsDados();
		inscricao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(inscricao.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void inscricao_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Inscricao inscricao = ContextoInscricao.construirInscricaoInvalida();
		
		inscricao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(inscricao.validado());
		Assert.assertNotNull(inscricao.obterDescricaoErros());
		Assert.assertEquals(inscricao.obterNumeroErros(), 6, "Deveria haver 6 infrações de obrigatoriedade.");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void inscricao_validacao_com_erro_de_tamanhomaximo_de_dados() {
		Inscricao inscricao = ContextoInscricao.construirInscricaoComTodosOsDados();

		inscricao.setNomeParaCracha(ContextoGenerico.obterGrande());
		inscricao.setObservacoes(ContextoGenerico.obterGrande());
		inscricao.setAreaFormacao(ContextoGenerico.obterGrande());
		inscricao.setLocalTrabalho(ContextoGenerico.obterGrande());
		inscricao.setCargoOuFuncao(ContextoGenerico.obterGrande());
		
		inscricao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(inscricao.validado());
		Assert.assertNotNull(inscricao.obterDescricaoErros());
		Assert.assertEquals(inscricao.obterNumeroErros(), 5, "Deveria haver 5 infrações de obrigatoriedade.");
	}
}
