package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesRecursoMoradiaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_moradia_dto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String texto = "TEXTO";

		RecursoMoradiaDTO recursoProximoMoradiaDto = new RecursoMoradiaDTO(id, texto);

		Assert.assertEquals(recursoProximoMoradiaDto.getId(), id);
		Assert.assertEquals(recursoProximoMoradiaDto.toString(), texto);
	}
}
