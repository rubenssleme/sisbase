package br.laramara.ti.sislaracommons.dtos;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTelefoneDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefonedto_foi_construida_com_sucesso() {
		TipoTelefoneDTO tipoTelefoneDto = new TipoTelefoneDTO("CELULAR");
		String telefone = "12345678";
		TelefoneDTO telefoneDto = new TelefoneDTO(tipoTelefoneDto, telefone);

		Assert.assertEquals(telefoneDto.getTipoTelefoneDto().toString(),
				tipoTelefoneDto.toString());
		Assert.assertEquals(telefoneDto.getDDDTelefone(), telefone);
	}
}
