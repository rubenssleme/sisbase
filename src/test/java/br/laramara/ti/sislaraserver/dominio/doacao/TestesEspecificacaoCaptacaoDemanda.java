package br.laramara.ti.sislaraserver.dominio.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoDemanda;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecibo;

public class TestesEspecificacaoCaptacaoDemanda {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_captacao_demanda_construido_com_sucesso() {
		Demanda demanda = ContextoDemanda.fabricarDemandaComTodosOsDados();
		demanda.setCarteloDeSelosEInicializarDataPrazoCaptacao(true);
		String valor = "500,00";

		EspecificacaoCaptacaoDemanda especificacao = new EspecificacaoCaptacaoDemanda();
		especificacao.setDemanda(demanda);
		especificacao.setValor(valor);
		especificacao.setRecibo(ContextoRecibo.fabricarRecibo());
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertTrue(especificacao.validado());
		Assert.assertEquals(especificacao.getDemanda().getId(), demanda.getId());
		Assert.assertEquals(especificacao.getValor().toString(), "500.00");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_captacao_demanda_validacao_com_erro_de_obrigatoriedade_de_dados() {
		EspecificacaoCaptacaoDemanda especificacao = new EspecificacaoCaptacaoDemanda();
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_captacao_demanda_validacao_com_erro_de_recibo_sem_dados() {
		Demanda demanda = ContextoDemanda.fabricarDemandaComTodosOsDados();
		demanda.setCarteloDeSelosEInicializarDataPrazoCaptacao(true);
		String valor = "500,00";
		Recibo recibo = new Recibo();
	
		EspecificacaoCaptacaoDemanda especificacao = new EspecificacaoCaptacaoDemanda();
		especificacao.setDemanda(demanda);
		especificacao.setValor(valor);
		especificacao.setRecibo(recibo);
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertTrue(especificacao.obterDescricaoErros().contains("Insira um número de recibo."));
	}
}
