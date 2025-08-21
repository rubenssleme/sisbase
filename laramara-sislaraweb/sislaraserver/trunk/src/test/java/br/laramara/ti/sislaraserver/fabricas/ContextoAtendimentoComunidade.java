package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoComunidade;

public class ContextoAtendimentoComunidade {
	public static AtendimentoComunidade fabricarComTodosOsDados() {
		AtendimentoComunidade atendimentoComunidade = new AtendimentoComunidade();
		atendimentoComunidade.setId(new Long(1222));
		atendimentoComunidade
				.setInformacaoAtendimento(ContextoInformacaoAtendimento
						.fabricarInformacaoAtendimentoComTodosOsDados());
		atendimentoComunidade.setPreCadastro(ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados());
		atendimentoComunidade.setTipoVinculo(ContextoTipoVinculo
				.fabricarComTodosOsDados());
		return atendimentoComunidade;
	}

	public static AtendimentoComunidadeDTO construirDTO() {
		AtendimentoComunidadeDTO atendimentoComunidadelDTO = new AtendimentoComunidadeDTO();
		atendimentoComunidadelDTO
				.setInformacaoAtendimentoDto(ContextoInformacaoAtendimento
						.construirInformacaoAtendimentoDTO());
		atendimentoComunidadelDTO.setPreCadastroDto(ContextoPreCadastro
				.construirPreCadastroDTO());
		atendimentoComunidadelDTO.setTipoVinculoDto(ContextoTipoVinculo
				.fabricarDTOComTodosOsDados());
		return atendimentoComunidadelDTO;
	}
}
