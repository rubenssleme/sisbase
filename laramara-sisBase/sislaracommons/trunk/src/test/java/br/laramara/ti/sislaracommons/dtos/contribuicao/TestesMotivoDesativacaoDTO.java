package br.laramara.ti.sislaracommons.dtos.contribuicao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesMotivoDesativacaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void motivodesativacaodto_foi_construido_com_sucesso() {
		Long id = new Long(1);
		String stringMotivoDesativacaoEsperado = "Crise";

		EstadoCivilDTO motivoDesativacaoDto = new EstadoCivilDTO(id, stringMotivoDesativacaoEsperado);

		Assert.assertEquals(id, motivoDesativacaoDto.getId());
		Assert.assertEquals(stringMotivoDesativacaoEsperado, motivoDesativacaoDto.toString());
	}
}
