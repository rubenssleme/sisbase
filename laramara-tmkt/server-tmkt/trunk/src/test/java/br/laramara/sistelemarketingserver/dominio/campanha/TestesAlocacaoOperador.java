package br.laramara.sistelemarketingserver.dominio.campanha;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoContaAcesso;

public class TestesAlocacaoOperador {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void alocacao_operador_construido_com_sucesso() {
		Long id = new Long(12);
		ContaAcesso contaAcesso = ContextoContaAcesso.fabricarContaAcessoCarlos();
		BigDecimal metaFinanceira = new BigDecimal("1000.00");
		Integer metaQuantidadeLigacoes = new Integer(10000);

		AlocacaoOperador alocacaoOperador = new AlocacaoOperador();
		alocacaoOperador.setId(id);
		alocacaoOperador.setContaAcesso(contaAcesso);
		alocacaoOperador.setMetaFinanceira(metaFinanceira);
		alocacaoOperador.setMetaQuantidadeLigacoes(metaQuantidadeLigacoes);
		alocacaoOperador.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(alocacaoOperador.validado());
		Assert.assertEquals(alocacaoOperador.getId(), id);
		Assert.assertEquals(alocacaoOperador.getContaAcesso(), contaAcesso);
		Assert.assertEquals(alocacaoOperador.getMetaFinanceira(), metaFinanceira);
		Assert.assertEquals(alocacaoOperador.getMetaQuantidadeLigacoes(), metaQuantidadeLigacoes);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void alocacao_operador_com_erro_de_obrigatoriedade() {
		AlocacaoOperador alocacaoOperador = new AlocacaoOperador();
		alocacaoOperador.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(alocacaoOperador.validado());
		Assert.assertTrue(alocacaoOperador.obterDescricaoErros().contains("Insira um operador."));
	}
}
