package br.laramara.ti.sislaracommons.dtos.atendimento;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemAgendimentoIndividualDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagematendimentoindividualdto_sem_erro_foi_construido() {
		List<AtendimentoIndividualDTO> atendimentoIndividualDto = new ArrayList<>();
		atendimentoIndividualDto.add(new AtendimentoIndividualDTO());
		atendimentoIndividualDto.add(new AtendimentoIndividualDTO());
		ResultadoListagemAtendimentoIndividualDTO resultadoDto = new ResultadoListagemAtendimentoIndividualDTO();
		resultadoDto.efetuadoComSucesso(atendimentoIndividualDto);

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertFalse(resultadoDto.getObjetosDto().isEmpty());
	}
}
