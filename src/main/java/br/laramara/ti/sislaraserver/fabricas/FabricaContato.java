package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaraserver.dominio.Contato;

public class FabricaContato extends FabricaBase<ContatoDTO, Contato> {

	public final Contato converterParaDominio(ContatoDTO contatoDto) {

		Contato contato = new Contato();
		if (contatoDto.getId() != null) {
			contato.setId(new Long(contatoDto.getId()));
		}
		contato.setTelefones(contatoDto.getTelefonesDto() != null ? new FabricaTelefone()
				.converterParaDominio(contatoDto.getTelefonesDto()) : null);
		contato.setRamal(contatoDto.getRamal());
		contato.setNomeContato(contatoDto.getNomeContato());
		contato.setEmail(contatoDto.getEmail());

		return contato;
	}

	public final ContatoDTO converterParaDTO(Contato contato) {
		ContatoDTO contatoDto = null;

		if (contato != null) {
			contatoDto = new ContatoDTO();
			contatoDto.setId(contato.getId());
			contatoDto
					.setTelefonesDto(contato.getTelefones().size() > 0 ? new FabricaTelefone()
							.converterParaDTO(contato.getTelefones()) : null);
			contatoDto.setRamal(contato.getRamal());
			contatoDto.setNomeContato(contato.getNomeContato());
			contatoDto.setEmail(contato.getEmail());
		}
		return contatoDto;
	}
}
