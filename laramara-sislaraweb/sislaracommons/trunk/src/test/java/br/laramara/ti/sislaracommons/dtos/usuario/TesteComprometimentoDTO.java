package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TesteComprometimentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void comprometimentoDto_foi_construido_com_sucesso() {
		Long id = new Long(12222);
		String descricao = "TESTE";

		ComprometimentoDTO comprometimentoDto = new ComprometimentoDTO(id, descricao);

		Assert.assertEquals(comprometimentoDto.getId(), id);
		Assert.assertEquals(comprometimentoDto.toString(), descricao);
	}
}
