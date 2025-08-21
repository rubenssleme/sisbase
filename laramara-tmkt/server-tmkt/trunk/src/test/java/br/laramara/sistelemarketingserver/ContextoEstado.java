package br.laramara.sistelemarketingserver;

import br.laramara.sistelemarketingcommons.dtos.EstadoDTO;
import br.laramara.sistelemarketingserver.dominio.Estado;

public class ContextoEstado {

	public static Estado fabricarEstado() {
		Estado estado = new Estado();
		estado.setId(new Long(26));
		estado.setDescricao("São Paulo");
		return estado;
	}

	public static EstadoDTO fabricarEstadoDto() {
		EstadoDTO estadoDto = new EstadoDTO();
		estadoDto.setId(new Long(26));
		estadoDto.setDescricao("São Paulo");
		return estadoDto;
	}
}
