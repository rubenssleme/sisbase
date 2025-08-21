package br.laramara.sistelemarketingcommons.dtos.contato;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesTelefoneDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefone_dto_foi_construida_com_sucesso() {
		Long id = new Long(2929);
		String ddd = "11";
		String telefone = "123456789";

		TelefoneDTO telefoneDto = new TelefoneDTO();
		telefoneDto.setId(id);
		telefoneDto.setDdd(ddd);
		telefoneDto.setTelefone(telefone);
		
		Assert.assertEquals(telefoneDto.getId(), id);
		Assert.assertEquals(telefoneDto.getDdd(), ddd);
		Assert.assertEquals(telefoneDto.getTelefone(), telefone);
	}
}
