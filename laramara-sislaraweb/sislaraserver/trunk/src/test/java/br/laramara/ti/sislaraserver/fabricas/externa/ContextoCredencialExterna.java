package br.laramara.ti.sislaraserver.fabricas.externa;

import br.laramara.ti.sislaracommons.dtos.seguranca.externa.CredencialExternaDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.externa.CredencialExterna;

public class ContextoCredencialExterna {

	public static CredencialExternaDTO construirCredencialExternaComPerfilPreenchidoDTO() {
		return new CredencialExternaDTO("usuario.externo@gmail.com", "1234");
	}
	
	public static CredencialExternaDTO construirSegundaCredencialExternaSemPerfilPreenchidoDTO() {
		return new CredencialExternaDTO("carlos_kafka@hotmail.com", "1234");
	}
	
	public static CredencialExternaDTO construirCredencialExternaDTONaoExistente() {
		return new CredencialExternaDTO("usuario.nao.existente@gmail.com", "123456789");
	}
	
	public static CredencialExterna construirCredencialExterna() {
		return new CredencialExterna("carloskafka7@gmail.com", "1234");
	}
	
	public static CredencialExternaDTO construirCredencialExternaValidaDTO() {
		return new CredencialExternaDTO("carloskafka7@gmail.com", "1234");
	}
	
	public static CredencialExternaDTO construirSegundaCredencialExternaValidaDTO() {
		return new CredencialExternaDTO("a@gmail.com", "1234");
	}
}
