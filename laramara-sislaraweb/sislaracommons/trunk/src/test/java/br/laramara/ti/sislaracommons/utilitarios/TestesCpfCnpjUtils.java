package br.laramara.ti.sislaracommons.utilitarios;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestesCpfCnpjUtils {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void cpfcnpjutils_valida_cpf_correto(){
		String cpf = "54128481173";
				
		Assert.assertTrue(CpfCnpjUtils.validarCpfCnpj(cpf));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void cpfcnpjutils_valida_cpf_incorreto(){
		String cpfA = "12312312312";
		String cpfB = "00000000000";
				
		Assert.assertFalse(CpfCnpjUtils.validarCpfCnpj(cpfA));
		Assert.assertFalse(CpfCnpjUtils.validarCpfCnpj(cpfB));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void cpfcnpjutils_valida_cnpj_correto(){
		String cnpj = "16007539000102";
				
		Assert.assertTrue(CpfCnpjUtils.validarCpfCnpj(cnpj));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void cpfcnpjutils_valida_cnpj_incorreto(){
		String cnpjA = "16007533000102";
		String cnpjB = "00000000000000";
						
		Assert.assertFalse(CpfCnpjUtils.validarCpfCnpj(cnpjA));
		Assert.assertFalse(CpfCnpjUtils.validarCpfCnpj(cnpjB));
	}
}
