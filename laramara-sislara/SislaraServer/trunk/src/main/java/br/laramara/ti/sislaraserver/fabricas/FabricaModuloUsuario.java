package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloUsuarioDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloUsuario;


public class FabricaModuloUsuario extends
		FabricaRecursiva<ModuloUsuarioDTO, ModuloUsuario> {

	@Override
	public ModuloUsuarioDTO converterParaDTO(ModuloUsuario moduloUsuario) {
		ModuloUsuarioDTO moduloUsuarioDto = null;
		if (moduloUsuario != null) {
			moduloUsuarioDto = new ModuloUsuarioDTO();
			moduloUsuarioDto.setId(moduloUsuario.getId());
			moduloUsuarioDto.setUsuarioDto(new FabricaUsuario()
					.converterParaDTO(moduloUsuario.getUsuario()));
			moduloUsuarioDto.setDataInicio(moduloUsuario.getDataInicio());
			moduloUsuarioDto.setDataOcorrencia(moduloUsuario.getDataOcorrencia());
			moduloUsuarioDto
					.setStatusDto(new FabricaStatusRelacaoComModulo()
							.converterParaDTO(moduloUsuario.getStatus()));
			moduloUsuarioDto.setAprovado(moduloUsuario.isAprovado());
			moduloUsuarioDto.setObs(moduloUsuario.getObs());
			moduloUsuarioDto.setIgnorarRegraDeReuniaoDeIntegracao(moduloUsuario.isIgnorarRegraDeReuniaoDeIntegracao());
		}
		return moduloUsuarioDto;
	}

	@Override
	public ModuloUsuario converterParaDominio(
			ModuloUsuarioDTO moduloUsuarioDto, ModuloUsuario moduloUsuario) {
		if (moduloUsuarioDto != null) {
			if (moduloUsuario == null){
				moduloUsuario = new ModuloUsuario();
			}
			moduloUsuario.setId(moduloUsuarioDto.getId());
			moduloUsuario.setUsuario(new FabricaUsuario().converterParaDominio(
					moduloUsuarioDto.getUsuarioDto(),
					moduloUsuario.getUsuario()));
			moduloUsuario.setDataInicio(moduloUsuarioDto.getDataInicio());
			moduloUsuario.setDataOcorrencia(moduloUsuarioDto
					.getDataOcorrencia());
			moduloUsuario.setStatus(new FabricaStatusRelacaoComModulo()
					.converterParaDominio(moduloUsuarioDto.getStatusDto()));
			moduloUsuario.setAprovado(moduloUsuarioDto.isAprovado());
			moduloUsuario.setObs(moduloUsuarioDto.getObs());
			moduloUsuario.setIgnorarRegraDeReuniaoDeIntegracao(moduloUsuarioDto.isIgnorarRegraDeReuniaoDeIntegracao());
		}
		return moduloUsuario;
	}

	@Override
	public ModuloUsuario obterNovo() {
		return new ModuloUsuario();
	}
}