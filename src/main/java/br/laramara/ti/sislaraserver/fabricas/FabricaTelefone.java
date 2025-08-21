package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;
import br.laramara.ti.sislaraserver.dominio.Telefone;

public class FabricaTelefone extends FabricaBase<TelefoneDTO, Telefone>{
	
	public final TelefoneDTO converterParaDTO(Telefone telefone) {
		TipoTelefoneDTO tipoTelefoneDto = new FabricaTipoTelefone()
				.converterParaDTO(telefone.getTipo());
		return new TelefoneDTO(tipoTelefoneDto, telefone.getDDDTelefone());
	}

	public final Telefone converterParaDominio(
			TelefoneDTO telefoneDto) {
		Telefone telefone = new Telefone(
				telefoneDto.getTipoTelefoneDto() != null ? new FabricaTipoTelefone().converterParaDominio(telefoneDto
						.getTipoTelefoneDto())
						: null, telefoneDto.getDDDTelefone());

		return telefone;
	}
}
