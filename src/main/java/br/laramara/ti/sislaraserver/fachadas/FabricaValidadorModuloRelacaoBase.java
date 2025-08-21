package br.laramara.ti.sislaraserver.fachadas;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloUsuarioDTO;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorModuloPreCadastro;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorModuloRelacaoBase;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorModuloUsuario;

public class FabricaValidadorModuloRelacaoBase {

	public ValidadorModuloRelacaoBase obterValidador(ModuloRelacaoBaseDTO moduloRelacaoBaseDto) {
		if (moduloRelacaoBaseDto instanceof ModuloUsuarioDTO) {
			return new ValidadorModuloUsuario();
		} else if (moduloRelacaoBaseDto instanceof ModuloPreCadastroDTO) {
			return new ValidadorModuloPreCadastro();
		} else {
			return null;
		}
	}
}
