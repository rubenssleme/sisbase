package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.seguranca.CredencialDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoCredencial;
import br.laramara.sistelemarketingserver.dominio.seguranca.Credencial;
import br.laramara.sistelemarketingserver.fabricas.CredencialFabrica;

public class TestesCredencialFabrica {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_credencial_converte_objeto_dto_para_dominio() {
		CredencialDTO credencialDto = ContextoCredencial.construirPauloBandeira();

		Credencial credencialCovertido = new CredencialFabrica().converterParaDominio(credencialDto);

		Assert.assertEquals(credencialCovertido.getUsuario(), credencialDto.getUsuario());
		Assert.assertEquals(credencialCovertido.getRamal().getNumero(), credencialDto.getRamalDto().getNumero());
	}
}
