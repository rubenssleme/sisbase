package br.laramara.sistelemarketingcommons.dtos.seguranca;

import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoDTO;

public class ContextoPermissaoDTO {

	public static PermissaoDTO construir() {
		PermissaoDTO permissaoDTO = new PermissaoDTO();
		permissaoDTO.setId(new Long(1));
		permissaoDTO.setDescricao("Descrição");
		return permissaoDTO;
	}
}
