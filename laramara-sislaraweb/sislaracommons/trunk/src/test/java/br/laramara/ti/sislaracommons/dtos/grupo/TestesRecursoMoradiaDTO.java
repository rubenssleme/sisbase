package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.RecursoMoradiaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesRecursoMoradiaDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_moradia_dto_foi_construida_com_sucesso() {
		Long id = new Long(12222);
		String descricao = "Escola";
		RecursoMoradiaDTO recursoMoradiaDto = new RecursoMoradiaDTO(id, descricao);

		Assert.assertEquals(recursoMoradiaDto.getId(), id);
		Assert.assertEquals(recursoMoradiaDto.toString(), descricao);
	}
}
