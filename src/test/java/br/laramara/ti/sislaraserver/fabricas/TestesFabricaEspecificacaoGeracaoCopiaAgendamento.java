package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoCopiaAgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoGeracaoCopiaAgendamento;

public class TestesFabricaEspecificacaoGeracaoCopiaAgendamento {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_especificacao_geracao_copia_agendamento_converte_objeto_de_dto_para_dominio() {
		EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDto = ContextoEspecificacaoGeracaoCopiaAgendamento
				.fabricarComTodosOsDados();

		EspecificacaoGeracaoCopiaAgendamento especificacaoGeracaoCopiaAgendamento = new FabricaEspecificacaoGeracaoCopiaAgendamento()
				.converterParaDominio(especificacaoGeracaoCopiaAgendamentoDto);

		Assert.assertEquals(especificacaoGeracaoCopiaAgendamento.getData(),
				DataHoraUtils.criar(especificacaoGeracaoCopiaAgendamentoDto
						.getData()));
		Assert.assertEquals(
				especificacaoGeracaoCopiaAgendamento.getHorario().getHoraInicio(),
				especificacaoGeracaoCopiaAgendamentoDto.getHorarioDto().getHoraInicio());
		Assert.assertEquals(
				especificacaoGeracaoCopiaAgendamento.getHorario().getHoraTermino(),
				especificacaoGeracaoCopiaAgendamentoDto.getHorarioDto().getHoraTermino());
	}
}
