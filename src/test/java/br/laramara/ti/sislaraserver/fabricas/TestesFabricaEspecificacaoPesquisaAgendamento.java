package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoPesquisaAgendamentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoPesquisaAgendamento;

public class TestesFabricaEspecificacaoPesquisaAgendamento {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_especificacao_pesquisa_agendamento_converte_objeto_de_dto_para_dominio() {
		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto = ContextoEspecificacaoPesquisaAgendamento
				.fabricarDtoComTodosOsDados();
		especificacaoPesquisaAgendamentoDto.marcarDataFutura();

		EspecificacaoPesquisaAgendamento especificacaoPesquisaAgendamento = new FabricaEspecificacaoPesquisaAgendamento()
				.converterParaDominio(especificacaoPesquisaAgendamentoDto);

		Assert.assertEquals(especificacaoPesquisaAgendamento.getProfissional()
				.getId(), especificacaoPesquisaAgendamentoDto
				.getProfissionalDto().getId());
		Assert.assertEquals(especificacaoPesquisaAgendamento
				.getDescricaoTipoAtendimento().getId(),
				especificacaoPesquisaAgendamentoDto
						.getDescricaoTipoAtendimentoDTO().getId());
		Assert.assertEquals(especificacaoPesquisaAgendamento.getModulo()
				.getId(), especificacaoPesquisaAgendamentoDto.getModuloDTO()
				.getId());
		Assert.assertEquals(
				DataHoraUtils.formatarData(especificacaoPesquisaAgendamento
						.getDataInicio()), especificacaoPesquisaAgendamentoDto
						.getDataInicio());
		Assert.assertEquals(
				DataHoraUtils.formatarData(especificacaoPesquisaAgendamento
						.getDataTermino()), especificacaoPesquisaAgendamentoDto
						.getDataTermino());
		Assert.assertEquals(especificacaoPesquisaAgendamento
				.getStatusAgendamento().toString(),
				especificacaoPesquisaAgendamentoDto.getStatusAgendamentoDTO()
						.toString());
		Assert.assertEquals(especificacaoPesquisaAgendamento
				.getProntuario().toString(),
				especificacaoPesquisaAgendamentoDto.getProntuario().toString());
		Assert.assertEquals(
				especificacaoPesquisaAgendamento.getRg(),
				especificacaoPesquisaAgendamentoDto.getRgPreCadastro());
		Assert.assertTrue(especificacaoPesquisaAgendamento.somenteDataFutura());
	}
}
