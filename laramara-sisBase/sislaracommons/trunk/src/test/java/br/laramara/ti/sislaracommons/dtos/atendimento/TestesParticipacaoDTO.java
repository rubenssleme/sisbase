package br.laramara.ti.sislaracommons.dtos.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesParticipacaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void participacaoatendimentodto_foi_construida_com_sucesso() {
		String participacao = "COM_FAMILIA";

		ParticipacaoDTO participacaoDto = new ParticipacaoDTO(participacao);

		Assert.assertEquals(participacaoDto.toString(), participacao);
	}
}
