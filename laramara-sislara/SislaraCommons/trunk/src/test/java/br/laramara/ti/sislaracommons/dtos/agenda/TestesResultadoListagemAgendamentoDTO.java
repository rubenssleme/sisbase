package br.laramara.ti.sislaracommons.dtos.agenda;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemAgendamentoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemagendamentodto_sem_erro_foi_construido() {
		List<AgendamentoDTO> agendamentosDto = new ArrayList<>();
		agendamentosDto.add(new AgendamentoDTO());
		agendamentosDto.add(new AgendamentoDTO());
		ResultadoListagemAgendamentoDTO resultadoDto = new ResultadoListagemAgendamentoDTO();
		resultadoDto.efetuadoComSucesso(agendamentosDto);

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertFalse(resultadoDto.getObjetosDto().isEmpty());
	}
}
