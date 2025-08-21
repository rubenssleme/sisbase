package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.seguranca.Credencial;

public class TestesFabricaCredencial {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_credencial_converte_objeto_de_dto_para_dominio() {
		
		String hashSenha1234 = "81dc9bdb52d04dc20036dbd8313ed055";
		
		CredencialDTO credencialDto = new CredencialDTO("usuario", "1234", "1234");

		Credencial credendialConverdita = new FabricaCredencial()
				.converterParaDominio(credencialDto);

		Assert.assertEquals(credendialConverdita.getUsuario(), credencialDto.getUsuario());
		Assert.assertEquals(credendialConverdita.getSenha(), hashSenha1234);
		Assert.assertEquals(credendialConverdita.getConfirmacaoSenha(), hashSenha1234);
	}
}
