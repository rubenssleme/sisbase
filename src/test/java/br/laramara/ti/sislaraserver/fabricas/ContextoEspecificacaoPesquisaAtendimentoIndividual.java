package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoPesquisaAtendimentoIndividualDTO;

public class ContextoEspecificacaoPesquisaAtendimentoIndividual {

	public static EspecificacaoPesquisaAtendimentoIndividualDTO fabricarDtoComTodosOsDados() {
		EspecificacaoPesquisaAtendimentoIndividualDTO especificacaoPesquisaAtendimentoIndividualDTO = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacaoPesquisaAtendimentoIndividualDTO
				.setProfissionalDto(ContextoProfissional
						.construirProfissionalDTO());
		especificacaoPesquisaAtendimentoIndividualDTO
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento
						.construirDescricaoTipoAtendimentoDTO());
		especificacaoPesquisaAtendimentoIndividualDTO
				.setModuloDto(ContextoModulo.construirModuloDTO());
		especificacaoPesquisaAtendimentoIndividualDTO
				.setDataInicio("01/12/2012");
		especificacaoPesquisaAtendimentoIndividualDTO
				.setDataTermino("31/12/2012");
		especificacaoPesquisaAtendimentoIndividualDTO
				.setProfissionalDto(ContextoProfissional
						.construirProfissionalDTO());
		especificacaoPesquisaAtendimentoIndividualDTO.setProntuario("1234");
		return especificacaoPesquisaAtendimentoIndividualDTO;
	}
}
