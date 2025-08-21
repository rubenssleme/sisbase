package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoUsuario;

public class ContextoAtendimentoUsuario {

	public static AtendimentoUsuario fabricarAtendimentoUsuarioComTodosOsDados() {
		AtendimentoUsuario atendimentoUsuario = new AtendimentoUsuario();
		atendimentoUsuario.setId(new Long(1222));
		atendimentoUsuario.setUsuario(ContextoUsuario
				.fabricarUsuarioComTodosOsDados());
		atendimentoUsuario
				.setInformacaoAtendimento(ContextoInformacaoAtendimento
						.fabricarInformacaoAtendimentoComTodosOsDados());
		return atendimentoUsuario;
	}
	
	public static AtendimentoUsuario fabricarAtendimentoUsuarioATComTodosOsDados() {
		AtendimentoUsuario atendimentoUsuario = fabricarAtendimentoUsuarioComTodosOsDados();
		atendimentoUsuario.setInformacaoAtendimento(
				ContextoInformacaoAtendimento.fabricarInformacaoAtendimentoATComTodosOsDados());
		return atendimentoUsuario;
	}

	public static AtendimentoUsuarioDTO construirAtendimentoUsuarioDTO() {
		AtendimentoUsuarioDTO atendimentoUsuarioDTO = new AtendimentoUsuarioDTO();
		atendimentoUsuarioDTO.setId(new Long(1222));
		atendimentoUsuarioDTO.setUsuarioDto(ContextoUsuario
				.construirUsuarioDTO());
		atendimentoUsuarioDTO
				.setInformacaoAtendimentoDto(ContextoInformacaoAtendimento
						.construirInformacaoAtendimentoDTO());
		return atendimentoUsuarioDTO;
	}
}
