package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.ResponsavelTecnicoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.ResponsavelTecnico;

public class ContextoResponsavelTecnico {
	public static ResponsavelTecnico fabricarProjetoComTodosOsDados() {
		return new ResponsavelTecnico(null, ContextoProfissional.fabricarComTodosOsDados(), true);
	}

	public static ResponsavelTecnicoDTO construirResponsavelTecnicoDTO() {
		ResponsavelTecnicoDTO responsavelTecnicoDTO = new ResponsavelTecnicoDTO();
		responsavelTecnicoDTO.setId(new Long(12222));
		responsavelTecnicoDTO.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());
		responsavelTecnicoDTO.setPrincipal(true);
		return responsavelTecnicoDTO;
	}
}
