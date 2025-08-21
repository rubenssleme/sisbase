package br.laramara.ti.sislaracommons.dtos.agenda;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemStatusAgendamentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemstatusagendamentodto_sem_erro_foi_construido() {
		List<StatusAgendamentoDTO> statusAgendamentoDto = new ArrayList<>();
		statusAgendamentoDto.add(new StatusAgendamentoDTO("AGENDADO"));
		statusAgendamentoDto.add(new StatusAgendamentoDTO("DISPONIVEL"));
		ResultadoListagemStatusAgendamentoDTO resultadoDto = new ResultadoListagemStatusAgendamentoDTO();
		resultadoDto.efetuadoComSucesso(statusAgendamentoDto);

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertFalse(resultadoDto.getObjetosDto().isEmpty());
	}
}
