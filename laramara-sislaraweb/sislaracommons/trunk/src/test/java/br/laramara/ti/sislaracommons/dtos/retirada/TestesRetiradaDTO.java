package br.laramara.ti.sislaracommons.dtos.retirada;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesRetiradaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void retiradadto_foi_construida_pelo_com_sucesso() {
		Long id = new Long(12222);
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(id, "Vicotr",
				"01");

		RetiradaDTO retiradaDto = new RetiradaDTO();
		retiradaDto.setProfissionalDto(profissionalDTO);
		retiradaDto.setProntuario(id);
		retiradaDto.setVoluntarioDto(profissionalDTO);

		Assert.assertEquals(retiradaDto.getProfissionalDto().getId(), id);
		Assert.assertEquals(retiradaDto.getVoluntarioDto().getId(), id);
		Assert.assertEquals(retiradaDto.getProntuario(), id);
	}
}
