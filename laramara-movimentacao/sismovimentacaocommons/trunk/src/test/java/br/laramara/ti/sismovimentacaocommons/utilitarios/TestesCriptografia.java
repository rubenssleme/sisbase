package br.laramara.ti.sismovimentacaocommons.utilitarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.Criptografia;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesCriptografia {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void criptografia_gera_md5() {
		String senha = "teste";
		String md5 = Criptografia.converterParaMD5(senha);

		Assert.assertEquals("698dc19d489c4e4db73e28a713eab07b", md5);
	}
}
