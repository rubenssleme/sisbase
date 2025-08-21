package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoCopiaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoCopiaAtendimentoIndividual;

public class TestesFabricaEspecificacaoCopiaAtendimentoIndividual {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_especificacao_copia_atendimento_individual_converte_objeto_de_dto_para_dominio() {

		HorarioDTO horarioDto = new HorarioDTO("09:00", "12:00");
		EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDto = new br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoCopiaAtendimentoIndividualDTO();
		especificacaoCopiaAtendimentoIndividualDto.setHorarioDto(horarioDto);

		EspecificacaoCopiaAtendimentoIndividual especificacaoCopiaAtendimentoIndividual = new FabricaEspecificacaoCopiaAtendimentoIndividual()
				.converterParaDominio(especificacaoCopiaAtendimentoIndividualDto);

		Assert.assertEquals(especificacaoCopiaAtendimentoIndividual
				.getHorario().getHoraInicio(), horarioDto.getHoraInicio());
		Assert.assertEquals(especificacaoCopiaAtendimentoIndividual
				.getHorario().getHoraTermino(), horarioDto.getHoraTermino());
	}
}
