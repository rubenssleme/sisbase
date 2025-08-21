package br.laramara.ti.sislaracommons.dtos.grupo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesLocalAtendimentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void localatendimentodto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		String nomeLocalAtendimento = "SALA 1";

		LocalAtendimentoDTO nomeGrupoDto = new LocalAtendimentoDTO(id,
				nomeLocalAtendimento);

		Assert.assertEquals(nomeGrupoDto.getId(), id);
		Assert.assertEquals(nomeGrupoDto.toString(), nomeLocalAtendimento);
	}
}
