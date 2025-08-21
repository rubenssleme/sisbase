package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalVinculoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.ProfissionalVinculo;

public class ContextoProfissionalVinculo {

	public static ProfissionalVinculo fabricarComTodosOsDados() {
		ProfissionalVinculo profissionalVinculo = new ProfissionalVinculo();
		profissionalVinculo.setId(new Long(1000));
		profissionalVinculo.setProfissional(ContextoProfissional.fabricarComTodosOsDados());
		profissionalVinculo.setParticipante(false);
		return profissionalVinculo;
	}

	public static ProfissionalVinculoDTO construirVinculoProfissionalDTO() {
		ProfissionalVinculoDTO profissionalVinculoDto = new ProfissionalVinculoDTO();
		profissionalVinculoDto.setId(new Long(2000));
		profissionalVinculoDto.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());
		profissionalVinculoDto.setParticipante(false);
		return profissionalVinculoDto;
	}

}
