package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.RecursoRelacionamentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.RecursoRelacionamento;

public class FabricaRecursoRelacionamento extends FabricaBase<RecursoRelacionamentoDTO, RecursoRelacionamento> {

	public final RecursoRelacionamentoDTO converterParaDTO(RecursoRelacionamento recursoRelacionamento) {
		RecursoRelacionamentoDTO recursoRelacionamentoDto = new RecursoRelacionamentoDTO();
		if (recursoRelacionamento.getId() != null) {
			recursoRelacionamentoDto.setId(recursoRelacionamento.getId());
		}
		recursoRelacionamentoDto
				.setRecursoDto(new FabricaRecurso().converterParaDTO(recursoRelacionamento.getRecurso()));
		recursoRelacionamentoDto.setPossuiNecessitaDto(
				new FabricaPossuiNecessita().converterParaDTO(recursoRelacionamento.getPossuiNecessita()));
		return recursoRelacionamentoDto;
	}

	public final RecursoRelacionamento converterParaDominio(RecursoRelacionamentoDTO recursoRelacionamentoDto) {
		RecursoRelacionamento recursoRelacionamento = new RecursoRelacionamento();
		if (recursoRelacionamentoDto.getId() != null) {
			recursoRelacionamento.setId(recursoRelacionamentoDto.getId());
		}
		recursoRelacionamento
				.setRecurso(new FabricaRecurso().converterParaDominio(recursoRelacionamentoDto.getRecursoDto()));
		recursoRelacionamento.setPossuiNecessita(
				new FabricaPossuiNecessita().converterParaDominio(recursoRelacionamentoDto.getPossuiNecessitaDto()));
		return recursoRelacionamento;
	}
}
