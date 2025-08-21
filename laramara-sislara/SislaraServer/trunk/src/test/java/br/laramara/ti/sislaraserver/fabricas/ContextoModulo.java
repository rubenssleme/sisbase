package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;

public class ContextoModulo {

	public static ModuloDTO construirModuloDTO() {
		return new ModuloDTO(new Long(1), "Atendimento Especifico Especializado");
	}
	
	public static ModuloDTO construirModuloDTOTriagemOftalmologia() {
		return new ModuloDTO(new Long(130), "Triagem Oftalmologica");
	}

	public static Modulo fabricarComTodosOsDados() {
		return new Modulo(new Long(1), "Atendimento Especifico Especializado");
	}

	public static ModuloDTO construirModuloInglesDTO() {
		return new ModuloDTO(new Long(2), "Ingles");
	}

	public static ModuloDTO construirModuloAvaliacaoETriagemDTO() {
		return new ModuloDTO(new Long(37), "Avaliação e Triagem");
	}

	public static ModuloDTO construirModuloAEE() {
		return new ModuloDTO(new Long(1), "Atendimento Especifico Especialidado");
	}

	public static ModuloDTO construirModuloExcessoDeFaltasDTO() {
		return new ModuloDTO(new Long(129), "Excesso de Faltas");
	}

	public static ModuloDTO construirModuloAcompanhamentoDTO() {
		return new ModuloDTO(new Long(38), "Acompanhamento");
	}

	public static Modulo contruirAvaliacaoETriagem() {
		return new Modulo(Modulo.ID_MODULO_AVALIACAO_TRIAGEM, "");
	}
}
