package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;

public class ContextoTelefone {

	public static TelefoneDTO construirTelefoneDTO() {
		TelefoneDTO telefoneDto = new TelefoneDTO(
				new TipoTelefoneDTO("CELULAR"), "1122224444");
		return telefoneDto;
	}
	
	public static TelefoneDTO construirTelefoneDTOIncompleto() {
		TelefoneDTO telefoneDto = new TelefoneDTO(
				new TipoTelefoneDTO("CELULAR"), "112222");
		return telefoneDto;
	}
}
