package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesRecursoEDescricaoRecursoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_e_descricao_recurso_dto_foi_construida_com_sucesso() {
		Long id = new Long(122222);
		String descricao = "TESTE";

		RecursoDTO recursoDto = new RecursoDTO(id, descricao, true, false, "10.10", Arrays.asList());

		DescricaoRecursoDTO descricaoRecursoDTO = new DescricaoRecursoDTO(id, descricao);

		RecursoEDescricaoRecursoDTO recursoEDescricaoRecursoDTO = new RecursoEDescricaoRecursoDTO(recursoDto,
				descricaoRecursoDTO);

		Assert.assertEquals(recursoEDescricaoRecursoDTO.getRecursoDTO().getId(), recursoDto.getId());
		Assert.assertEquals(recursoEDescricaoRecursoDTO.getDescricaoRecursoDTO().getId(), descricaoRecursoDTO.getId());
	}
}
