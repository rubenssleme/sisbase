package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTipificacaoServicoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipificacaoservicodto_foi_construida_com_sucesso() {
		Long id = new Long(12222);
		String tipificacaoServico = "Defesa";

		TipificacaoServicoDTO tipificacaoDto = new TipificacaoServicoDTO(id,
				tipificacaoServico);

		Assert.assertEquals(tipificacaoDto.getId(), id);
		Assert.assertEquals(tipificacaoDto.toString(), tipificacaoServico);
	}
}
