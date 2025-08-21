package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoPesquisaAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.StatusAgendamentoDTO;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoPesquisaAgendamento;

public class ContextoEspecificacaoPesquisaAgendamento {

	public static EspecificacaoPesquisaAgendamento fabricarDominioComTodosOsDados() {
		EspecificacaoPesquisaAgendamento especificacaoPesquisaAgendamento = new EspecificacaoPesquisaAgendamento();
		especificacaoPesquisaAgendamento.setProfissional(ContextoProfissional
				.fabricarComTodosOsDados());
		especificacaoPesquisaAgendamento
				.setDescricaoTipoAtendimento(ContextoDescricaoTipoAtendimento
						.fabricarComTodosOsDados());
		return especificacaoPesquisaAgendamento;
	}

	public static EspecificacaoPesquisaAgendamentoDTO fabricarDtoComTodosOsDados() {
		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDTO = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoPesquisaAgendamentoDTO
				.setProfissionalDto(ContextoProfissional
						.construirProfissionalDTO());
		especificacaoPesquisaAgendamentoDTO
				.setDescricaoTipoAtendimentoDTO(ContextoDescricaoTipoAtendimento
						.construirDescricaoTipoAtendimentoDTO());
		especificacaoPesquisaAgendamentoDTO.setModuloDTO(ContextoModulo
				.construirModuloDTO());
		especificacaoPesquisaAgendamentoDTO.setDataInicio("01/12/2012");
		especificacaoPesquisaAgendamentoDTO.setDataTermino("31/12/2012");
		especificacaoPesquisaAgendamentoDTO
				.setStatusAgendamentoDTO(new StatusAgendamentoDTO("AGENDADO"));
		especificacaoPesquisaAgendamentoDTO.setProntuario("1234");
		especificacaoPesquisaAgendamentoDTO.setRgPreCadastro("88274A");
		return especificacaoPesquisaAgendamentoDTO;
	}
}
