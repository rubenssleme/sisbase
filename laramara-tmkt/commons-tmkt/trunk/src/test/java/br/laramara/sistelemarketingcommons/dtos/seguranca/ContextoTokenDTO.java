package br.laramara.sistelemarketingcommons.dtos.seguranca;

import br.laramara.sistelemarketingcommons.dtos.seguranca.TokenDTO;

public class ContextoTokenDTO {

	public static TokenDTO criar() {
		TokenDTO tokenDTO = new TokenDTO("d41d8cd98f00b204e9800998ecf8427e");
		return tokenDTO;
	}
	
	public static String criarToken() {
		return "d41d8cd98f00b204e9800998ecf8427e";
	}

}
