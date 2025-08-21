package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPeriodoDeficiencia {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_deficiencia_validacao_com_erro_de_obrigatoriedade() {

		PeriodoDeficiencia deficiencia = new PeriodoDeficiencia();
		deficiencia.setDataInicial(null);
		deficiencia.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(deficiencia.validado());
		Assert.assertEquals(deficiencia.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_deficiencia_validacao_com_erro_de_data_invalida() {
		PeriodoDeficiencia deficiencia = new PeriodoDeficiencia();
		deficiencia.setDataInicial("laramara");
		deficiencia.setDataFinal("marvics");
		deficiencia.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(deficiencia.validado());
		Assert.assertEquals(deficiencia.obterNumeroErros(), 3);
	}
		
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_deficiencia_validacao_com_erro_de_falta_etiologia() {
		PeriodoDeficiencia deficiencia = new PeriodoDeficiencia();
		deficiencia.setDataInicial("31/12/2013");
		deficiencia.setDeficiencia(new Deficiencia(new Long(1), "A", true));
		deficiencia.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(deficiencia.validado());
		Assert.assertEquals(deficiencia.obterNumeroErros(), 1);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_deficiencia_validacao_com_erro_de_data_termino_anteior_a_data_inicial() {
		PeriodoDeficiencia periodoDeficiencia = new PeriodoDeficiencia();
		periodoDeficiencia.setDataInicial("31/12/2012");
		periodoDeficiencia.setDataFinal("01/01/2012");
		periodoDeficiencia.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(periodoDeficiencia.validado());
		Assert.assertEquals(periodoDeficiencia.obterNumeroErros(), 2);
	}
}
