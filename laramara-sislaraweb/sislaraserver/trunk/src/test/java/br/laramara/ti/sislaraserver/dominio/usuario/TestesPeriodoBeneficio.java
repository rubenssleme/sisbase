package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPeriodoBeneficio {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_beneficio_validacao_com_erro_de_obrigatoriedade() {

		PeriodoBeneficio beneficio = new PeriodoBeneficio();
		beneficio.setDataInicial(null);
		beneficio.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(beneficio.validado());
		Assert.assertEquals(beneficio.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_beneficio_validacao_com_erro_de_data_invalida() {
		PeriodoBeneficio beneficio = new PeriodoBeneficio();
		beneficio.setDataInicial("laramara");
		beneficio.setDataFinal("marvics");
		beneficio.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(beneficio.validado());
		Assert.assertEquals(beneficio.obterNumeroErros(), 4);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_beneficio_validacao_com_erro_de_data_termino_anteior_a_data_inicial() {
		PeriodoBeneficio beneficio = new PeriodoBeneficio();
		beneficio.setDataInicial("31/12/2012");
		beneficio.setDataFinal("01/01/2012");
		beneficio.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(beneficio.validado());
		Assert.assertEquals(beneficio.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_beneficio_validacao_sem_erro() {
		PeriodoBeneficio beneficio = new PeriodoBeneficio();
		beneficio.setDataInicial("01/01/2012");
		beneficio.setDataFinal("01/02/2012");
		beneficio.setBeneficio(new Beneficio(new Long(1), "Desc"));
		beneficio.setStatus(StatusBeneficio.ENCAMINHADO);
		beneficio.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertTrue(beneficio.validado());
	}
}
