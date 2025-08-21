package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;

public class FabricaAtendimentoGrupo extends
		FabricaRecursiva<AtendimentoGrupoDTO, AtendimentoGrupo> {

	@Override
	public AtendimentoGrupoDTO converterParaDTO(
			AtendimentoGrupo atendimentoGrupo) {
		AtendimentoGrupoDTO atendimentoDto = null;
		if (atendimentoGrupo != null) {
			atendimentoDto = new AtendimentoGrupoDTO();
			atendimentoDto.setId(atendimentoGrupo.getId());
			atendimentoDto.setData(atendimentoGrupo.getData());
			atendimentoDto
					.setStatusAtendimentoDto(new FabricaStatusAtendimento()
							.converterParaDTO(atendimentoGrupo
									.getStatusAtendimento()));
			atendimentoDto.setHorarioDto(new FabricaHorario()
					.converterParaDTO(atendimentoGrupo.getHorario()));
			atendimentoDto.setDescricao(atendimentoGrupo.getDescricao());
			atendimentoDto.setInterdisciplinar(atendimentoGrupo
					.getInterdisciplinar());
			atendimentoDto
					.setAtendimentosUsuariosDto(new FabricaAtendimentoUsuario()
							.converterParaDTO(atendimentoGrupo
									.getAtendimentosUsuarios()));
			atendimentoDto
					.setAtendimentosPreCadastroDto(new FabricaAtendimentoPreCadastro()
							.converterParaDTO(atendimentoGrupo
									.getAtendimentosPreCadastros()));
			atendimentoDto
					.setAtendimentosProfissionaisDto(new FabricaAtendimentoProfissional()
							.converterParaDTO(atendimentoGrupo
									.getAtendimentosProfissionais()));
			atendimentoDto.setVersao(atendimentoGrupo.getVersao());
			atendimentoDto.setArquivos(new FabricaArquivo().converterParaDTO(atendimentoGrupo.getArquivos()));
		}
		return atendimentoDto;
	}

	@Override
	public AtendimentoGrupo converterParaDominio(
			AtendimentoGrupoDTO atendimentoGrupoDto,
			AtendimentoGrupo atendimentoGrupo) {
		if (atendimentoGrupoDto != null) {
			if (atendimentoGrupo == null) {
				atendimentoGrupo = new AtendimentoGrupo();
			}
			atendimentoGrupo.setId(atendimentoGrupoDto.getId());
			atendimentoGrupo.setData(atendimentoGrupoDto.getData());
			atendimentoGrupo
					.setStatusAtendimento(new FabricaStatusAtendimento()
							.converterParaDominio(atendimentoGrupoDto
									.getStatusAtendimentoDto()));
			atendimentoGrupo.setHorario(new FabricaHorario()
					.converterParaDominio(atendimentoGrupoDto.getHorarioDto()));
			atendimentoGrupo.setDescricao(atendimentoGrupoDto.getDescricao());
			atendimentoGrupo.setInterdisciplinar(atendimentoGrupoDto
					.getInterdisciplinar());
			atendimentoGrupo
					.setAtendimentosUsuarios(new FabricaAtendimentoUsuario()
							.converterParaDominio(atendimentoGrupoDto
									.getAtendimentosUsuariosDto(),
									atendimentoGrupo.getAtendimentosUsuarios()));
			atendimentoGrupo
					.setAtendimentosPreCadastro(new FabricaAtendimentoPreCadastro()
							.converterParaDominio(atendimentoGrupoDto
									.getAtendimentosPreCadastroDto(),
									atendimentoGrupo
											.getAtendimentosPreCadastros()));
			atendimentoGrupo
					.setAtendimentosProfissionais(new FabricaAtendimentoProfissional()
							.converterParaDominio(atendimentoGrupoDto
									.getAtendimentosProfissionaisDto()));
			atendimentoGrupo.setArquivos(new FabricaArquivo().converterParaDominio(atendimentoGrupoDto.getArquivos()));
		}
		return atendimentoGrupo;
	}

	@Override
	public AtendimentoGrupo obterNovo() {
		return new AtendimentoGrupo();
	}
}
