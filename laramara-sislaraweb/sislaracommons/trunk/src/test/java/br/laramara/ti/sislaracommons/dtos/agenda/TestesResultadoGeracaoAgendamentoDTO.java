package br.laramara.ti.sislaracommons.dtos.agenda;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoGeracaoAgendamentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadogeracaoagendamentodto_sem_erro_foi_construido() {
		List<AgendamentoDTO> agendamentosDto = new ArrayList<>();
		agendamentosDto.add(new AgendamentoDTO());
		agendamentosDto.add(new AgendamentoDTO());
		ResultadoGeracaoAgendamentoDTO resultadoAgendamentoDto = new ResultadoGeracaoAgendamentoDTO();
		resultadoAgendamentoDto.efetuadoComSucesso(agendamentosDto);

		Assert.assertTrue(resultadoAgendamentoDto.sucesso());
		Assert.assertFalse(resultadoAgendamentoDto.getObjetosDto().isEmpty());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadogeracaoagendamentodto_sem_erro_foi_construido_alternativo() {
		ResultadoGeracaoAgendamentoDTO resultadoAgendamentoDto = new ResultadoGeracaoAgendamentoDTO();
		resultadoAgendamentoDto.efetuadoComSucesso(new AgendamentoDTO());

		Assert.assertTrue(resultadoAgendamentoDto.sucesso());
		Assert.assertFalse(resultadoAgendamentoDto.getObjetosDto().isEmpty());
	}
}
