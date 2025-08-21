package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesRecursoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recursodto_foi_construida_com_sucesso() {
		Long id = new Long(12222);
		String descricao = "Bengala";
		String valor = "100,00";
		boolean cartelaDeSelos = true;
		RecursoDTO recursoDto = new RecursoDTO(id, descricao, cartelaDeSelos, valor);

		Assert.assertEquals(recursoDto.getId(), id);
		Assert.assertEquals(recursoDto.toString(), descricao);
		Assert.assertEquals(recursoDto.getValor(), valor);
		Assert.assertEquals(recursoDto.isCartelaDeSelos(), cartelaDeSelos);
	}

}
