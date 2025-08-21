package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.NecessidadeDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Necessidade;

public class ContextoNecessidade {

	public static NecessidadeDTO construirNecessidadeDTO() {
		return new NecessidadeDTO(new Long(1000), "Texto da necessidade");
	}

	public static Necessidade fabricarNecessidadeComTodosOsDados() {
		return new Necessidade(new Long(1000), "Texto da necessidade");
	}
}
