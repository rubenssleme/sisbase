package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoVinculo;

public class ContextoTipoVinculo {

	public static TipoVinculo fabricarComTodosOsDados() {
		return new TipoVinculo(new Long(1), "Professor");
	}

	public static TipoVinculoDTO fabricarDTOComTodosOsDados() {
		return new TipoVinculoDTO(new Long(1), "Professor");
	}
	
	public static TipoVinculoDTO fabricarEstudanteDTOComTodosOsDados() {
		return new TipoVinculoDTO(new Long(12), "Estudante");
	}
		
	public static TipoVinculoDTO fabricarPrivadoDTOComTodosOsDados() {
		return new TipoVinculoDTO(new Long(16), "Privado");
	}
}
