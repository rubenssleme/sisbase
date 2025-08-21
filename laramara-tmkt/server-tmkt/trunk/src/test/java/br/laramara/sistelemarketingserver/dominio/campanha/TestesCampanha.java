package br.laramara.sistelemarketingserver.dominio.campanha;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoGenerico;
import br.laramara.sistelemarketingserver.dominio.campanha.Campanha;

public class TestesCampanha {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void campanha_construido_com_sucesso() {
		Long id = new Long(12);
		String nome = "Nome da campanha";
		String texto = "Grande texto";
		String dataInicio = "01/12/2018";
		String dataTermino = "31/12/2018";
		BigDecimal metaFinanceira = new BigDecimal("15000.50");
		Integer metaQuantidadeLigacoes = new Integer(15000);

		Campanha campanha = new Campanha();
		campanha.setId(id);
		campanha.setNome(nome);
		campanha.setDataInicio(dataInicio);
		campanha.setDataTermino(dataTermino);
		campanha.setDescricao(texto);
		campanha.setMetaFinanceira(metaFinanceira);
		campanha.setMetaQuantidadeLigacoes(metaQuantidadeLigacoes);
		campanha.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(campanha.validado());
		Assert.assertEquals(campanha.getNome(), nome);
		Assert.assertEquals(campanha.getDataInicio(), dataInicio);
		Assert.assertEquals(campanha.getDataTermino(), dataTermino);
		Assert.assertEquals(campanha.getDescricao(), texto);
		Assert.assertEquals(campanha.getMetaFinanceira(), metaFinanceira);
		Assert.assertEquals(campanha.getMetaQuantidadeLigacoes(), metaQuantidadeLigacoes);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void campanha_com_erro_de_obrigatoriedade() {
		Campanha campanha = new Campanha();
		campanha.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(campanha.validado());
		Assert.assertTrue(campanha.obterDescricaoErros().contains("Insira um nome válido."));
		Assert.assertTrue(campanha.obterDescricaoErros().contains("Insira uma data de início."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void campanha_com_erro_de_tamanho_maximo_e_inconsistencia() {
		Campanha campanha = ContextoCampanha.fabricar();
		campanha.setNome(ContextoGenerico.obterGrande());
		campanha.setDescricao(ContextoGenerico.obterGrande());
		campanha.setDataInicio("XXXX");
		campanha.setDataTermino("XXXX");
		campanha.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(campanha.validado());
		Assert.assertTrue(campanha.obterDescricaoErros().contains("Insira um nome contendo até 100 caracteres."));
		Assert.assertTrue(campanha.obterDescricaoErros().contains("Insira uma descrição contendo até 20000 caracteres."));
		Assert.assertTrue(campanha.obterDescricaoErros().contains("Insira uma data de início válida."));
		Assert.assertTrue(campanha.obterDescricaoErros().contains("Insira uma data de término válida."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void campanha_com_erro_de_data_inicio_posterior_a_data_termino() {
		Campanha campanha = ContextoCampanha.fabricar();
		campanha.setDataTermino("01/07/2018");
		campanha.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(campanha.validado());
		Assert.assertTrue(campanha.obterDescricaoErros().contains("Insira uma data de início anterior a data de término."));
	}
}
