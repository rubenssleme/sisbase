package br.laramara.ti.sislaraserver.fachadas.validadores;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoAtendimentoBaseDTO;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoPreCadastro;

public class ValidadorAtendimentoPreCadastro extends ValidadorAtendimentoBase {

	@Override
	public ResultadoValidacaoAtendimentoBaseDTO validarAtendimento(AtendimentoBaseDTO atendimentoBaseDTO) {
		return (ResultadoValidacaoAtendimentoBaseDTO) efetuarValidacao(
				(AtendimentoPreCadastroDTO) atendimentoBaseDTO, new FabricaAtendimentoPreCadastro(),
				"Atendimento do Pré-Cadastro", new ResultadoValidacaoAtendimentoBaseDTO());
	}
}
