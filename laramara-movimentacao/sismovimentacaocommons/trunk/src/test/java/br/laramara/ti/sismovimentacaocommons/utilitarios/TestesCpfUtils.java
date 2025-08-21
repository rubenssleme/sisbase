package br.laramara.ti.sismovimentacaocommons.utilitarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.CpfUtils;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesCpfUtils {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void cpfutils_valida_cpf_correto(){
		String cpf = "54128481173";
				
		Assert.assertTrue(CpfUtils.validarCPF(cpf));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void cpfutils_valida_cpf_incorreto(){
		String cpf = "12312312312";
				
		Assert.assertFalse(CpfUtils.validarCPF(cpf));
	}
}
