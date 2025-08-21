package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;

public class FabricaModuloPeriodo extends
		FabricaRecursiva<ModuloPeriodoDTO, ModuloPeriodo> {

	@Override
	public ModuloPeriodoDTO converterParaDTO(ModuloPeriodo moduloPeriodo) {
		ModuloPeriodoDTO moduloPeriodoDto = null;
		if (moduloPeriodo != null) {
			moduloPeriodoDto = new ModuloPeriodoDTO();
			moduloPeriodoDto.setId(moduloPeriodo.getId());
			moduloPeriodoDto.setVersao(moduloPeriodo.getVersao());
			moduloPeriodoDto.setModuloDto(new FabricaModulo()
					.converterParaDTO(moduloPeriodo.getModulo()));
			moduloPeriodoDto
					.setProfissionaisDto(new FabricaProfissional()
							.converterParaDTO(moduloPeriodo
									.obterSomenteProfissionais()));
			moduloPeriodoDto.setProfissionaisVinculoDto(new FabricaProfissionalVinculo()
					.converterParaDTO(moduloPeriodo.getProfissionaisVinculo()));
			moduloPeriodoDto
					.setLocalAtendimentoDTO(new FabricaLocalAtendimento()
							.converterParaDTO(moduloPeriodo
									.getLocalAtendimento()));
			moduloPeriodoDto.setVagas(moduloPeriodo.getVagas());
			moduloPeriodoDto.setCargaHoraria(moduloPeriodo.getCargaHoraria());
			moduloPeriodoDto.setCargaHorariaMinima(moduloPeriodo
					.getCargaHorariaMinima());
			moduloPeriodoDto.setProgramacaoDto(new FabricaProgramacao()
					.converterParaDTO(moduloPeriodo.getProgramacoes()));
			moduloPeriodoDto
					.setAtendimentosGrupoDto(new FabricaAtendimentoGrupo()
							.converterParaDTO(moduloPeriodo
									.getAtendimentosGrupo()));
			moduloPeriodoDto.setModulosUsuariosDto(new FabricaModuloUsuario()
					.converterParaDTO(moduloPeriodo.getModulosUsuarios()));
			moduloPeriodoDto
					.setModulosPreCadastroDto(new FabricaModuloPreCadastro()
							.converterParaDTO(moduloPeriodo
									.getModulosPreCadastro()));
			moduloPeriodoDto.setDiasSemanaEHorariosDaAtividadeDto(
					new FabricaDiaSemanaEHorario().converterParaDTO(moduloPeriodo.getDiasSemanaEHorariosDaAtividade()));
		}
		return moduloPeriodoDto;
	}

	@Override
	public ModuloPeriodo converterParaDominio(
			ModuloPeriodoDTO moduloPeriodoDto, ModuloPeriodo moduloPeriodo) {
		if (moduloPeriodoDto != null) {
			if (moduloPeriodo == null) {
				moduloPeriodo = new ModuloPeriodo();
			}
			moduloPeriodo.setId(moduloPeriodoDto.getId());
			moduloPeriodo.setModulo(new FabricaModulo()
					.converterParaDominio(moduloPeriodoDto.getModuloDto()));
			moduloPeriodo.setProfissionaisVinculo(new FabricaProfissionalVinculo()
					.converterParaDominio(moduloPeriodoDto
							.getProfissionaisVinculoDto()));
			moduloPeriodo.setLocalAtendimento(new FabricaLocalAtendimento()
					.converterParaDominio(moduloPeriodoDto
							.getLocalAtendimentoDTO()));
			moduloPeriodo.setVagas(moduloPeriodoDto.getVagas());
			moduloPeriodo.setCargaHoraria(moduloPeriodoDto.getCargaHoraria());
			moduloPeriodo.setCargaHorariaMinima(moduloPeriodoDto
					.getCargaHorariaMinima());
			moduloPeriodo.setProgramacoes(new FabricaProgramacao()
					.converterParaDominio(moduloPeriodoDto.getProgramacaoDto(),
							moduloPeriodo.getProgramacoes()));
			moduloPeriodo.setAtendimentosGrupo(new FabricaAtendimentoGrupo()
					.converterParaDominio(
							moduloPeriodoDto.getAtendimentosGrupoDto(),
							moduloPeriodo.getAtendimentosGrupo()));
			moduloPeriodo.setModulosUsuarios(new FabricaModuloUsuario()
					.converterParaDominio(
							moduloPeriodoDto.getModulosUsuariosDto(),
							moduloPeriodo.getModulosUsuarios()));
			moduloPeriodo.setModulosPreCadastro(new FabricaModuloPreCadastro()
					.converterParaDominio(
							moduloPeriodoDto.getModulosPreCadastroDto(),
							moduloPeriodo.getModulosPreCadastro()));
			moduloPeriodo.setDiasSemanaEHorariosDaAtividade(new FabricaDiaSemanaEHorario()
					.converterParaDominio(moduloPeriodoDto.getDiasSemanaEHorariosDaAtividadeDto()));
		}
		return moduloPeriodo;
	}

	@Override
	public ModuloPeriodo obterNovo() {
		return new ModuloPeriodo();
	}
}
