package br.laramara.sistelemarketingcommons.dtos.contato;

import br.laramara.sistelemarketingcommons.dtos.LogradouroDTO;

public class ContextoContatoDTO {

	public static ContatoDTO criar() {
		Long id = new Long(2929);
		String cpf = "71894810287";
		String nome = "Paulo Augusto";
		LogradouroDTO logradouroDTO = new LogradouroDTO();
		logradouroDTO.setCep("01151000");
		String numero = "743";
		String complemento = "AP 84";
		String email = "pbansd@ads.com";

		ContatoDTO contatoDto = new ContatoDTO();
		contatoDto.setId(id);
		contatoDto.setCep(logradouroDTO);
		contatoDto.setComplemento(complemento);
		contatoDto.setCpf(cpf);
		contatoDto.setEmail(email);
		contatoDto.setNome(nome);
		contatoDto.setNumero(numero);
		return contatoDto;
	}
}
