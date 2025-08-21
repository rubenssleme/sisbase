package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDescricaoRecursoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void descricao_recurso_dto_foi_construida_com_sucesso() {
		Long id = new Long(122222);
		String descricao = "TESTE";

		DescricaoRecursoDTO descricaoRecursoDTO = new DescricaoRecursoDTO(id, descricao);

		Assert.assertEquals(descricaoRecursoDTO.getId(), id);
		Assert.assertEquals(descricaoRecursoDTO.toString(), descricao);
	}
}
