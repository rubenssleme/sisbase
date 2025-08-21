package br.laramara.ti.sislaraserver.dominio.escola;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoEducacional;

public class TestesInformacaoEducacional {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacao_educacional_validacao_sem_erro_de_obrigatoriedade_de_dados() {
		InformacaoEducacional informacaoEducacional = ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados();
		informacaoEducacional.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(informacaoEducacional.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacao_educacional_validacao_com_erro_de_obrigatoriedade_de_dados() {
		InformacaoEducacional informacaoEducacional = new InformacaoEducacional();
		informacaoEducacional.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(informacaoEducacional.validado());
		Assert.assertNotNull(informacaoEducacional.obterDescricaoErros());
		Assert.assertEquals(informacaoEducacional.obterNumeroErros(), 2,
				"Deveria haver 2 infrações de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacao_educacional_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();
		InformacaoEducacional informacaoEducacional = ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados();
		informacaoEducacional.setNomeProfessor(textoGrande);
		informacaoEducacional.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(informacaoEducacional.validado());
		Assert.assertEquals(informacaoEducacional.obterNumeroErros(), 1,
				"Deveria haver 1 infrações de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacao_educacional_com_vigencia_encerrada_nao_marca_como_excluido() {
		InformacaoEducacional informacaoEducacional = ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados();
		informacaoEducacional.encerrarVigencia();
		informacaoEducacional.marcarExcluido();
		
		Assert.assertFalse(informacaoEducacional.excluido());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacao_educacional_remove_identificador_e_gera_data_inicial_vigencia() {
		InformacaoEducacional informacaoEducacional = ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados();
		informacaoEducacional.removerIdentificadorEGeraDataInicialVigencia();

		Assert.assertNull(informacaoEducacional.getId());
		Assert.assertNotNull(informacaoEducacional.getDataInicialVigencia());
	}
}
