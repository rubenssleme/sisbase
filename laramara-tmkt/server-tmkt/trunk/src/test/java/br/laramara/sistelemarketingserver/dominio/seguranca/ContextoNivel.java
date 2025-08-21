package br.laramara.sistelemarketingserver.dominio.seguranca;

import java.util.Arrays;

import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelDTO;
import br.laramara.sistelemarketingserver.dominio.seguranca.Nivel;

public class ContextoNivel {

	public static Nivel fabricarNivel() {
		Nivel nivel = new Nivel();
		nivel.setId(new Long(2));
		nivel.setDescricao("Gerente");
		nivel.setPermissoes(Arrays.asList(ContextoPermissao.fabricarPermissao()));
		return nivel;
	}

	public static NivelDTO fabricarNivelDto() {
		NivelDTO nivelDto = new NivelDTO();
		nivelDto.setId(new Long(2));
		nivelDto.setDescricao("Gerente");
		nivelDto.setPermissoesDto(Arrays.asList(ContextoPermissao.fabricarPermissaoDto()));
		return nivelDto;
	}
}
