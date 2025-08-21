package br.laramara.ti.sislaraserver.fachadas.validadores;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoAtendimentoBaseDTO;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoUsuario;

public class ValidadorAtendimentoUsuario extends ValidadorAtendimentoBase {

	@Override
	public ResultadoValidacaoAtendimentoBaseDTO validarAtendimento(AtendimentoBaseDTO atendimentoBaseDTO) {
		return (ResultadoValidacaoAtendimentoBaseDTO) efetuarValidacao(
				(AtendimentoUsuarioDTO) atendimentoBaseDTO, new FabricaAtendimentoUsuario(), "Atendimento do Usuário",
				new ResultadoValidacaoAtendimentoBaseDTO());
	}
}
