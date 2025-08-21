package br.laramara.sistelemarketingserver;

import br.laramara.sistelemarketingcommons.dtos.contato.TelefoneDTO;
import br.laramara.sistelemarketingserver.dominio.contato.Telefone;

public class ContextoTelefone {

	public static Telefone fabricar() {
		Telefone telefone = new Telefone();
		telefone.setId(new Long(3834));
		telefone.setDdd("99");
		telefone.setTelefone("12345679");
		return telefone;
	}

	public static TelefoneDTO fabricarDto() {
		TelefoneDTO telefoneDto = new TelefoneDTO();
		telefoneDto.setId(new Long(3834));
		telefoneDto.setDdd("99");
		telefoneDto.setTelefone("12345679");
		return telefoneDto;
	}
}
