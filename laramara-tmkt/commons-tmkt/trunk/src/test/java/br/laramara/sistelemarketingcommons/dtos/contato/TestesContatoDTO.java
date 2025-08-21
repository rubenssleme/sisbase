package br.laramara.sistelemarketingcommons.dtos.contato;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.LogradouroDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesContatoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contato_dto_foi_construida_com_sucesso() {
		Long id = new Long(2929);
		String cpf = "71894810287";
		String nome = "Paulo Augusto";
		LogradouroDTO logradouroDTO = new LogradouroDTO();
		logradouroDTO.setCep("01151000");
		String numero = "743";
		String complemento = "AP 84";
		String email = "pbansd@ads.com";
		TelefoneDTO telefoneDto = new TelefoneDTO();
		telefoneDto.setId(id);
		List<TelefoneDTO> telefonesDto = Arrays.asList(telefoneDto); 

		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setId(id);
		contatoDto.setCep(logradouroDTO);
		contatoDto.setComplemento(complemento);
		contatoDto.setCpf(cpf);
		contatoDto.setEmail(email);
		contatoDto.setNome(nome);
		contatoDto.setNumero(numero);
		contatoDto.setTelefonesDto(telefonesDto);

		Assert.assertEquals(contatoDto.getId(), id);
		Assert.assertEquals(contatoDto.getCep().getCep(), logradouroDTO.getCep());
		Assert.assertEquals(contatoDto.getComplemento(), complemento);
		Assert.assertEquals(contatoDto.getCpf(), cpf);
		Assert.assertEquals(contatoDto.getEmail(), email);
		Assert.assertEquals(contatoDto.getNome(), nome);
		Assert.assertEquals(contatoDto.getNumero(), numero);
		Assert.assertEquals(contatoDto.getTelefonesDto().size(), telefonesDto.size());
	}
}
