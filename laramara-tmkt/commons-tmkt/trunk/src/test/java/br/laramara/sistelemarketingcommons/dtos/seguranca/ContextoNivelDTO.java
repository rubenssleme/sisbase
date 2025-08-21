package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.Arrays;

import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelDTO;

public class ContextoNivelDTO {

	public static NivelDTO construir() {
		NivelDTO nivelDTO = new NivelDTO();
		nivelDTO.setId(new Long(1));
		nivelDTO.setDescricao("Gerente");
		nivelDTO.setPermissoesDto(Arrays.asList(ContextoPermissaoDTO.construir()));
		return nivelDTO;
	}
}
