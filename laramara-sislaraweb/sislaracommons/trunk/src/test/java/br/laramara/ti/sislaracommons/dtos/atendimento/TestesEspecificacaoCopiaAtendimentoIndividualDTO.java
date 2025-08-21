package br.laramara.ti.sislaracommons.dtos.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoCopiaAtendimentoIndividualDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_copia_atendimento_invidualdto_sem_erro_foi_construido() {

		HorarioDTO horarioDto = new HorarioDTO("09:00", "10:00");
		EspecificacaoCopiaAtendimentoIndividualDTO especificacaoDto = new EspecificacaoCopiaAtendimentoIndividualDTO();
		especificacaoDto.setHorarioDto(horarioDto);

		Assert.assertEquals(especificacaoDto.getHorarioDto().getHoraInicio(),
				horarioDto.getHoraInicio());
	}
}
