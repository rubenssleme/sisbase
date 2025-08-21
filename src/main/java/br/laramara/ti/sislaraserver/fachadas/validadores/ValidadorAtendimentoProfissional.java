package br.laramara.ti.sislaraserver.fachadas.validadores;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoAtendimentoBaseDTO;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoProfissional;

public class ValidadorAtendimentoProfissional extends ValidadorAtendimentoBase {

	@Override
	public ResultadoValidacaoAtendimentoBaseDTO validarAtendimento(AtendimentoBaseDTO atendimentoBaseDTO) {
		return (ResultadoValidacaoAtendimentoBaseDTO) efetuarValidacao(
				(AtendimentoProfissionalDTO) atendimentoBaseDTO, new FabricaAtendimentoProfissional(),
				"Atendimento do Profissional", new ResultadoValidacaoAtendimentoBaseDTO());
	}
}
