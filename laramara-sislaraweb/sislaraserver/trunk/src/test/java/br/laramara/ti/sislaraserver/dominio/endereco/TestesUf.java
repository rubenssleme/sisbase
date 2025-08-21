package br.laramara.ti.sislaraserver.dominio.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesUf {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void uf_valida_uf_com_sucesso() {
		String uf = "SP";
		
		Assert.assertTrue(UF.existe(uf));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void uf_valida_uf_sem_sucesso() {
		String ufInvalido = "INVALIDO";
		
		Assert.assertFalse(UF.existe(ufInvalido));
	}
}
