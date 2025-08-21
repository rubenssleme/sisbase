package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ServicoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesServicoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void servicodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String servico = "Fisioterapia";

		ServicoDTO servicoDto = new ServicoDTO(id, servico);

		Assert.assertEquals(servicoDto.getId(), id);
		Assert.assertEquals(servicoDto.toString(), servico);
	}
}
