package br.laramara.sistelemarketingcommons.dtos;

import br.laramara.sistelemarketingcommons.dtos.contato.TelefoneDTO;

public class ContextoTelefoneDTO {

	public static TelefoneDTO criar() {
		TelefoneDTO telefoneDto = new TelefoneDTO();
		telefoneDto.setId(new Long(545));
		telefoneDto.setDdd("99");
		telefoneDto.setTelefone("957878870");
		return telefoneDto;
	}
}
