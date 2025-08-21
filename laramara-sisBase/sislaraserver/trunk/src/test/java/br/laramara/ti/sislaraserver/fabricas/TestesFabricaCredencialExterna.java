package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialExternaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.seguranca.CredencialExterna;

public class TestesFabricaCredencialExterna {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_credencial_externa_converte_objeto_de_dto_para_dominio() {
		String hashSenha1234 = "81dc9bdb52d04dc20036dbd8313ed055";
		
		CredencialExterna credencialExternaEsperado = new CredencialExterna("usuario.externo@gmail.com", "1234");
		CredencialExternaDTO credencialExternaDto = new CredencialExternaDTO("usuario.externo@gmail.com", "1234");

		CredencialExterna credendialConvertida = new FabricaCredencialExterna()
				.converterParaDominio(credencialExternaDto);

		Assert.assertEquals(credendialConvertida.getEmail(), credencialExternaEsperado.getEmail());
		Assert.assertEquals(credendialConvertida.getSenha(), hashSenha1234);
	}
}
