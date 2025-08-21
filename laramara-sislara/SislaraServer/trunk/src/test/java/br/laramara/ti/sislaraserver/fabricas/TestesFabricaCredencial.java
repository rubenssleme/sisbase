package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.seguranca.Credencial;

public class TestesFabricaCredencial {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_credencial_converte_objeto_de_dto_para_dominio() {
		Credencial credencialEsperado = new Credencial("usuario", "e8d95a51f3af4a3b134bf6bb680a213a");
		CredencialDTO credencialDto = new CredencialDTO("usuario", "senha");

		Credencial credendialConverdita = new FabricaCredencial()
				.converterParaDominio(credencialDto);

		Assert.assertEquals(credendialConverdita, credencialEsperado);
	}
}
