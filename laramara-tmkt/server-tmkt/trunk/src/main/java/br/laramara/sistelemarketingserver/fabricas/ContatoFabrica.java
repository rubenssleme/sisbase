package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.contato.ContatoDTO;
import br.laramara.sistelemarketingserver.dominio.contato.Contato;

public class ContatoFabrica extends FabricaBase<ContatoDTO, Contato> {
	public final ContatoDTO converterParaDTO(Contato contato) {
		ContatoDTO contatoDto = new ContatoDTO();
		if (contato != null) {
			contatoDto.setId(contato.getId());
			contatoDto.setCep(new LogradouroFabrica().converterParaDTO(contato.getCep()));
			contatoDto.setComplemento(contato.getComplemento());
			contatoDto.setCpf(contato.getCpf());
			contatoDto.setEmail(contato.getEmail());
			contatoDto.setNome(contato.getNome());
			contatoDto.setNumero(contato.getNumero());
			contatoDto.setTelefonesDto(new TelefoneFabrica().converterParaDTO(contato.getTelefones()));
		}
		return contatoDto;
	}

	public final Contato converterParaDominio(ContatoDTO contatoDto) {
		return null;
	}

}
