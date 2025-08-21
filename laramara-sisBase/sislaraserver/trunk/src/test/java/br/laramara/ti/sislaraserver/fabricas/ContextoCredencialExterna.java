package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialExternaDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.CredencialExterna;

public class ContextoCredencialExterna {

	public static CredencialExternaDTO construirCredencialExternaDTO() {
		return new CredencialExternaDTO("usuario.externo@gmail.com", "1234");
	}
	
	public static CredencialExternaDTO construirCredencialExternaDTONaoExistente() {
		return new CredencialExternaDTO("usuario.nao.existente@gmail.com", "123456789");
	}

	public static CredencialExterna construirCredencialExterna() {
		return new CredencialExterna("usuario.externo@gmail.com", "1234");
	}
	
}
