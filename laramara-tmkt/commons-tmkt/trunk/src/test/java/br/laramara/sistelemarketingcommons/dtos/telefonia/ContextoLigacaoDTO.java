package br.laramara.sistelemarketingcommons.dtos.telefonia;

import br.laramara.sistelemarketingcommons.dtos.ContextoTelefoneDTO;

public class ContextoLigacaoDTO {

	public static LigacaoDTO construir() {
		LigacaoDTO ligacaoDTO = new LigacaoDTO();
		ligacaoDTO.setToken("439843988");
		ligacaoDTO.setTelefoneDto(ContextoTelefoneDTO.criar());
		return ligacaoDTO;
	}
}
