package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEmpresaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void empresadto_foi_construida_com_sucesso() {

		Long id = new Long(1222);
		String nome = "Projeto Alcoa Teste";

		EmpresaDTO empresaDto = new EmpresaDTO(id, nome);

		Assert.assertEquals(empresaDto.getId(), id);
		Assert.assertEquals(empresaDto.toString(), nome);
	}
}
