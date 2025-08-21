package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPeriodoComprometimento {


	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_comprometimento_validacao_com_erro_de_obrigatoriedade() {
		PeriodoComprometimento periodoComprometimento = new PeriodoComprometimento();
		periodoComprometimento.setDataInicial(null);
		periodoComprometimento.setEpocaIncidencia(null);
		periodoComprometimento.setComprometimento(null);
		periodoComprometimento.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(periodoComprometimento.validado());
		Assert.assertEquals(periodoComprometimento.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_comprometimento_validacao_com_erro_de_data_invalida() {
		PeriodoComprometimento periodoCompromentimento = new PeriodoComprometimento();
		periodoCompromentimento.setDataInicial("laramara");
		periodoCompromentimento.setDataFinal("marvics");
		periodoCompromentimento.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(periodoCompromentimento.validado());
		Assert.assertEquals(periodoCompromentimento.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_comprometimento_validacao_com_erro_de_data_termino_anteior_a_data_inicial() {
		PeriodoComprometimento periodoComprometimento = new PeriodoComprometimento();
		periodoComprometimento.setDataInicial("31/12/2012");
		periodoComprometimento.setDataFinal("01/01/2012");
		periodoComprometimento.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(periodoComprometimento.validado());
		Assert.assertEquals(periodoComprometimento.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_comprometimento_validacao_sem_erro() {
		PeriodoComprometimento periodoComprometimento = new PeriodoComprometimento();
		periodoComprometimento.setDataInicial("01/01/2012");
		periodoComprometimento.setDataFinal("01/02/2012");
		periodoComprometimento.setComprometimento(new Comprometimento(new Long(1), "Desc"));
		periodoComprometimento.setEpocaIncidencia(EpocaIncidencia.ADQUIRIDA);
		periodoComprometimento.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertTrue(periodoComprometimento.validado());
	}
}
