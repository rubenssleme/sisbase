package br.laramara.sistelemarketingserver.fabrica;

import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoDTO;
import br.laramara.sistelemarketingserver.ContextoTelefone;

public class ContextoLigacao {

	public static LigacaoDTO fabricarDto() {
		LigacaoDTO ligacaoDTO = new LigacaoDTO();
		ligacaoDTO.setTelefoneDto(ContextoTelefone.fabricarDto());
		ligacaoDTO.setToken("983272938472398");
		return ligacaoDTO;
	}
}
