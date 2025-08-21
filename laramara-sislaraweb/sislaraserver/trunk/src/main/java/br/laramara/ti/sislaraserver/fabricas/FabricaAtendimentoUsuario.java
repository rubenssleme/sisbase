package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoUsuario;

public class FabricaAtendimentoUsuario extends
		FabricaRecursiva<AtendimentoUsuarioDTO, AtendimentoUsuario> {

	@Override
	public AtendimentoUsuarioDTO converterParaDTO(
			AtendimentoUsuario atendimentoUsuario) {

		AtendimentoUsuarioDTO atendimentoUsuarioDto = null;
		if (atendimentoUsuario != null) {
			atendimentoUsuarioDto = new AtendimentoUsuarioDTO();
			atendimentoUsuarioDto.setId(atendimentoUsuario.getId());
			atendimentoUsuarioDto.setUsuarioDto(new FabricaUsuario()
					.converterParaDTO(atendimentoUsuario.getUsuario()));
			atendimentoUsuarioDto
					.setInformacaoAtendimentoDto(new FabricaInformacaoAtendimento()
							.converterParaDTO(atendimentoUsuario
									.getInformacaoAtendimento()));
			atendimentoUsuarioDto.setVersao(atendimentoUsuario.getVersao());
			atendimentoUsuarioDto.setArquivos(new FabricaArquivo().converterParaDTO(atendimentoUsuario.getArquivos()));
		}
		return atendimentoUsuarioDto;
	}

	@Override
	public AtendimentoUsuario converterParaDominio(
			AtendimentoUsuarioDTO atendimentoUsuarioDto,
			AtendimentoUsuario atendimentoUsuario) {
		if (atendimentoUsuarioDto != null) {
			if (atendimentoUsuario == null) {
				atendimentoUsuario = new AtendimentoUsuario();
			}
			atendimentoUsuario.setId(atendimentoUsuarioDto.getId());
			atendimentoUsuario.setUsuario(new FabricaUsuario()
					.converterParaDominio(
							atendimentoUsuarioDto.getUsuarioDto(),
							atendimentoUsuario.getUsuario()));
			atendimentoUsuario
					.setInformacaoAtendimento(new FabricaInformacaoAtendimento()
							.converterParaDominio(atendimentoUsuarioDto
									.getInformacaoAtendimentoDto()));
			atendimentoUsuario
					.setArquivos(new FabricaArquivo().converterParaDominio(atendimentoUsuarioDto.getArquivos()));
		}
		return atendimentoUsuario;
	}

	@Override
	public AtendimentoUsuario obterNovo() {
		return new AtendimentoUsuario();
	}
}
