package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResponsavelTecnicoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void responsavel_tecnicodto_foi_construida_com_sucesso() {

		Long id = new Long(1222);
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(1), "PAULO", "123");
		boolean principal = true;

		ResponsavelTecnicoDTO responsavelTecnicoDto = new ResponsavelTecnicoDTO();
		responsavelTecnicoDto.setId(id);
		responsavelTecnicoDto.setProfissionalDto(profissionalDTO);
		responsavelTecnicoDto.setPrincipal(principal);

		Assert.assertEquals(responsavelTecnicoDto.getId(), id);
		Assert.assertEquals(responsavelTecnicoDto.getProfissionalDto(), profissionalDTO);
		Assert.assertEquals(responsavelTecnicoDto.isPrincipal(), principal);
	}
}
