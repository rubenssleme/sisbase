package br.laramara.sistelemarketingserver;

import br.laramara.sistelemarketingcommons.dtos.LogradouroDTO;
import br.laramara.sistelemarketingserver.dominio.Logradouro;

public class ContextoLogradouro {

	public static Logradouro fabricarLogradouro() {
		Logradouro logradouro = new Logradouro();
		logradouro.setCep("01151000");
		logradouro.setDescricao("Rua Brigadeiro Galvão");
		logradouro.setBairro("Barra Funda");
		logradouro.setMunicipio(ContextoMunicipio.fabricarMunicipio());
		return logradouro;
	}

	public static LogradouroDTO fabricarLogradouroDto() {
		LogradouroDTO logradouroDto = new LogradouroDTO();
		logradouroDto.setCep("01151000");
		logradouroDto.setDescricao("Rua Brigadeiro Galvão");
		logradouroDto.setBairro("Barra Funda");
		logradouroDto.setMunicipioDto(ContextoMunicipio.fabricarMunicipioDto());
		return logradouroDto;
	}

}
