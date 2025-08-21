package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.contato.TelefoneDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoTelefone;
import br.laramara.sistelemarketingserver.dominio.contato.Telefone;
import br.laramara.sistelemarketingserver.fabricas.TelefoneFabrica;

public class TestesTelefoneFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_telefone_converte_objeto_de_dominio_para_dto() {
		Telefone telefone = ContextoTelefone.fabricar();

		TelefoneDTO telefoneDtoCovertido = new TelefoneFabrica().converterParaDTO(telefone);

		Assert.assertEquals(telefoneDtoCovertido.getId(), telefone.getId());
		Assert.assertEquals(telefoneDtoCovertido.getDdd(), telefone.getDdd());
		Assert.assertEquals(telefoneDtoCovertido.getTelefone(), telefone.getTelefone());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_bairro_converte_objeto_dto_para_dominio() {
		TelefoneDTO telefoneDto = ContextoTelefone.fabricarDto();

		Telefone telefoneCovertido = new TelefoneFabrica().converterParaDominio(telefoneDto);

		Assert.assertEquals(telefoneCovertido.getId(), telefoneDto.getId());
		Assert.assertEquals(telefoneCovertido.getDdd(), telefoneDto.getDdd());
		Assert.assertEquals(telefoneCovertido.getTelefone(), telefoneDto.getTelefone());
	}
}
