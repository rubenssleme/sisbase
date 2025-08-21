package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalVinculoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.grupo.ProfissionalVinculo;

public class TestesFabricaProfissionalVinculo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_profissional_vinculo_converte_objeto_de_dominio_para_dto() {
		ProfissionalVinculo profissionalVinculo = new ProfissionalVinculo();
		profissionalVinculo.setId(new Long(12));
		profissionalVinculo.setProfissional(new Profissional(new Long(12), "A",
				"A"));
		profissionalVinculo.setParticipante(true);

		ProfissionalVinculoDTO profissionalVinculoDto = new FabricaProfissionalVinculo()
				.converterParaDTO(profissionalVinculo);

		Assert.assertEquals(profissionalVinculo.getId(),
				profissionalVinculoDto.getId());
		Assert.assertEquals(profissionalVinculo.getProfissional().getId(),
				profissionalVinculoDto.getProfissionalDto().getId());
		Assert.assertEquals(profissionalVinculo.isParticipante(),
				profissionalVinculoDto.isParticipante());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_profissional_vinculo_converte_objeto_de_dto_para_dominio() {
		ProfissionalVinculoDTO profissionalVinculoDto = new ProfissionalVinculoDTO();
		profissionalVinculoDto.setId(new Long(12));
		profissionalVinculoDto.setProfissionalDto(new ProfissionalDTO(new Long(
				23), "A", "Q"));
		profissionalVinculoDto.setParticipante(true);

		ProfissionalVinculo profissionalVinculo = new FabricaProfissionalVinculo()
				.converterParaDominio(profissionalVinculoDto);

		Assert.assertEquals(profissionalVinculoDto.getId(),
				profissionalVinculo.getId());
		Assert.assertEquals(
				profissionalVinculoDto.getProfissionalDto().getId(),
				profissionalVinculo.getProfissional().getId());
		Assert.assertEquals(profissionalVinculoDto.isParticipante(),
				profissionalVinculo.isParticipante());
	}
}
