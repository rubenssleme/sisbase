package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.contato.TelefoneDTO;
import br.laramara.sistelemarketingserver.dominio.contato.Telefone;

public class TelefoneFabrica extends FabricaBase<TelefoneDTO, Telefone> {
	public final TelefoneDTO converterParaDTO(Telefone telefone) {
		TelefoneDTO nivelDto = new TelefoneDTO();
		if (telefone != null) {
			nivelDto.setId(telefone.getId());
			nivelDto.setDdd(telefone.getDdd());
			nivelDto.setTelefone(telefone.getTelefone());
		}
		return nivelDto;
	}

	public final Telefone converterParaDominio(TelefoneDTO nivelDto) {
		Telefone nivel = new Telefone();
		if (nivelDto != null) {
			nivel.setId(nivelDto.getId());
			nivel.setDdd(nivelDto.getDdd());
			nivel.setTelefone(nivelDto.getTelefone());
		}
		return nivel;
	}
}
