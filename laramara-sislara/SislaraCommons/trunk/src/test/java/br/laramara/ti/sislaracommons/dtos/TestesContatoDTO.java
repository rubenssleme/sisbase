package br.laramara.ti.sislaracommons.dtos;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesContatoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contatodto_foi_construida_com_sucesso() {
		Long id = new Long(122222);
		String nomeContato = "JOSEP MEAZA";
		String ramal = "38733";
		TelefoneDTO telResidencial = new TelefoneDTO(new TipoTelefoneDTO(
		"RESIDENCIAL"), "38743443");
		String email = "aaabbb@yahoo.com.br";

		List<TelefoneDTO> telefonesDto = new ArrayList<>();
		telefonesDto.add(telResidencial);
		telefonesDto.add(telResidencial);
		telefonesDto.add(telResidencial);
		
		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setId(id);
		contatoDto.setNomeContato(nomeContato);
		contatoDto.setRamal(ramal);
		contatoDto.setTelefonesDto(telefonesDto);
		contatoDto.setEmail(email);

		Assert.assertEquals(contatoDto.getId(), id);
		Assert.assertEquals(contatoDto.getNomeContato(), nomeContato);
		Assert.assertEquals(contatoDto.getTelefonesDto().size(), 3);
		Assert.assertEquals(contatoDto.getRamal(), ramal);
		Assert.assertEquals(contatoDto.getEmail(), email);
	}
}
