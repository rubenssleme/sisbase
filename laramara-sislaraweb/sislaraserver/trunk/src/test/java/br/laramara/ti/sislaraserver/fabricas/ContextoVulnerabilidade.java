package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.VulnerabilidadeDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Vulnerabilidade;

public class ContextoVulnerabilidade {

	public static VulnerabilidadeDTO contruirDto() {
		return new VulnerabilidadeDTO(new Long(1), "Vulnerabilidade");
	}

	public static Vulnerabilidade construir() {
		return new Vulnerabilidade(new Long(1), "Vulnerabilidade");
	}

}
