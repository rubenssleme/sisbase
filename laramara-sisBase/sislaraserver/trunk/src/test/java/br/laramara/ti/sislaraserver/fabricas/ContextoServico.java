package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ServicoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Servico;

public class ContextoServico {

	public static ServicoDTO construirServicoDTO() {
		return new ServicoDTO(new Long(11111), "Hospital");
	}

	public static Servico fabricarServicoComTodosOsDados() {
		return new Servico(new Long(11111), "Hospital");
	}

}
