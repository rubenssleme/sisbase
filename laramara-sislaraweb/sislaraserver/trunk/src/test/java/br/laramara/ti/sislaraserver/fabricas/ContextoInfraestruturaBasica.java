package br.laramara.ti.sislaraserver.fabricas;

import java.util.Arrays;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.usuario.InfraestruturaBasicaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.InfraestruturaBasica;

public class ContextoInfraestruturaBasica {

	public static List<InfraestruturaBasicaDTO> construirInfraestruturaBasica() {
		return Arrays.asList(new InfraestruturaBasicaDTO(new Long(1000), "TESTE A"),
				new InfraestruturaBasicaDTO(new Long(2000), "TESTE B"));
	}

	public static List<InfraestruturaBasica> fabricarInfraestruturaBasica() {
		return Arrays.asList(new InfraestruturaBasica(new Long(1000), "TESTEA"), new InfraestruturaBasica(new Long(2000), "TESTEB"));
	}
}
