package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesPeriodoDoenca {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_doenca_validacao_com_erro_de_obrigatoriedade_e_tamanho_maximo() {

		PeriodoDoenca periodoDoenca = new PeriodoDoenca();
		periodoDoenca.setDataInicial(null);
		periodoDoenca.setObs(ContextoGenerico.obterGrande());
		periodoDoenca.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(periodoDoenca.validado());
		Assert.assertEquals(periodoDoenca.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_doenca_validacao_com_erro_de_data_invalida() {
		PeriodoDoenca periodoDoenca = new PeriodoDoenca();
		periodoDoenca.setDataInicial("laramara");
		periodoDoenca.setDataFinal("marvics");
		periodoDoenca.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(periodoDoenca.validado());
		Assert.assertEquals(periodoDoenca.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_doenca_validacao_com_erro_de_data_termino_anteior_a_data_inicial() {
		PeriodoDoenca periodoDoenca = new PeriodoDoenca();
		periodoDoenca.setDataInicial("31/12/2012");
		periodoDoenca.setDataFinal("01/01/2012");
		periodoDoenca.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(periodoDoenca.validado());
		Assert.assertEquals(periodoDoenca.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_beneficio_validacao_sem_erro() {
		PeriodoDoenca periodoDoenca = new PeriodoDoenca();
		periodoDoenca.setDataInicial("01/01/2012");
		periodoDoenca.setDataFinal("01/02/2012");
		periodoDoenca.setDoenca(new Doenca(new Long(1), "Desc"));
		periodoDoenca.setObs("obs");
		periodoDoenca.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertTrue(periodoDoenca.validado());
	}
}
