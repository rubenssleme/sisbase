package br.laramara.ti.sislaracommons.dtos.agenda;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoGeracaoCopiaAgendamentoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaogeracaocopiaagendamento_com_um_parametro_construida_com_sucesso() {
		String data = "31/12/2012";
		String horaInicio = "09:00";
		String horaTermino = "12:00";

		EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoAgendamentoDto = new EspecificacaoGeracaoCopiaAgendamentoDTO();
		especificacaoGeracaoAgendamentoDto.setData(data);
		especificacaoGeracaoAgendamentoDto.setHorarioDto(new HorarioDTO(
				horaInicio, horaTermino));

		Assert.assertEquals(especificacaoGeracaoAgendamentoDto.getData(), data);
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto.getHorarioDto()
				.getHoraInicio(), horaInicio);
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto.getHorarioDto()
				.getHoraTermino(), horaTermino);
	}
}
