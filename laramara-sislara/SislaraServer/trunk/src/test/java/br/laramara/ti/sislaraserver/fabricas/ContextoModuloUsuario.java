package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloUsuario;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;

public class ContextoModuloUsuario {

	public static ModuloUsuario fabricarComTodosOsDados() {
		ModuloUsuario moduloUsuario = new ModuloUsuario();
		moduloUsuario.setUsuario(ContextoUsuario
				.fabricaUsuarioComTodosOsDadosEProntuario());
		moduloUsuario.setDataInicio("31/01/2011");
		moduloUsuario.setDataOcorrencia("31/12/2011");
		moduloUsuario.setStatus(StatusRelacaoComModulo.INTEGRADO);
		moduloUsuario.setAprovado(true);
		moduloUsuario.setObs("Grande texto de observação.");
		return moduloUsuario;
	}

	public static ModuloUsuarioDTO fabricarModuloUsuarioDTO() {
		ModuloUsuarioDTO moduloUsuarioDTO = new ModuloUsuarioDTO();
		moduloUsuarioDTO.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());
		moduloUsuarioDTO.setDataInicio("31/01/2011");
		moduloUsuarioDTO.setDataOcorrencia("31/12/2011");
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(
				"DESLIGADO"));
		moduloUsuarioDTO.setAprovado(true);
		moduloUsuarioDTO.setObs("Grande texto de observação.");
		return moduloUsuarioDTO;
	}
	
	public static ModuloUsuarioDTO fabricarModuloUsuarioAlternativoDTO() {
		ModuloUsuarioDTO moduloUsuarioDTO = fabricarModuloUsuarioDTO();
		moduloUsuarioDTO.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoA());
		return moduloUsuarioDTO;
	}
}
