package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesProfissionalVinculoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void profissional_vinculo_dto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		ProfissionalDTO profissionalDto = new ProfissionalDTO(id, "A", "A");
		boolean observador = true;

		ProfissionalVinculoDTO profissionalVinculoDto = new ProfissionalVinculoDTO();
		profissionalVinculoDto.setId(id);
		profissionalVinculoDto.setProfissionalDto(profissionalDto);
		profissionalVinculoDto.setParticipante(observador);

		Assert.assertEquals(profissionalVinculoDto.getId(), id);
		Assert.assertEquals(
				profissionalVinculoDto.getProfissionalDto().getId(), id);
		Assert.assertEquals(profissionalVinculoDto.isParticipante(), observador);
	}
}
