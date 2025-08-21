package br.laramara.ti.sislaraserver.fachadas.validadores;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoAtendimentoBaseDTO;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoComunidade;

public class ValidadorAtendimentoComunidade extends ValidadorAtendimentoBase {

	@Override
	public ResultadoValidacaoAtendimentoBaseDTO validarAtendimento(AtendimentoBaseDTO atendimentoBaseDTO) {
		return (ResultadoValidacaoAtendimentoBaseDTO) efetuarValidacao(
				(AtendimentoComunidadeDTO) atendimentoBaseDTO, new FabricaAtendimentoComunidade(),
				"Atendimento da Comunidade", new ResultadoValidacaoAtendimentoBaseDTO());
	}
}
