package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.EstadoDTO;
import br.laramara.sistelemarketingserver.dominio.Estado;

public class EstadoFabrica extends FabricaBase<EstadoDTO, Estado> {
	public final EstadoDTO converterParaDTO(Estado estado) {
		EstadoDTO estadoDto = new EstadoDTO();
		if (estado != null) {
			estadoDto.setId(estado.getId());
			estadoDto.setDescricao(estado.getDescricao());
		}
		return estadoDto;
	}

	public final Estado converterParaDominio(EstadoDTO estadoDto) {
		Estado estado = new Estado();
		if (estadoDto != null) {
			estado.setId(estadoDto.getId());
			estado.setDescricao(estadoDto.getDescricao());
		}
		return estado;
	}

}
