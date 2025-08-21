package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.RecursoDisponivelDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.RecursoDisponivel;

public class FabricaRecursoDisponivel extends FabricaBase<RecursoDisponivelDTO, RecursoDisponivel> {

	public final RecursoDisponivelDTO converterParaDTO(RecursoDisponivel recursoDisponivel) {
		RecursoDisponivelDTO recursoDisponivelDto = new RecursoDisponivelDTO();
		if (recursoDisponivel.getId() != null) {
			recursoDisponivelDto.setId(recursoDisponivel.getId());
		}
		recursoDisponivelDto.setRecursoDto(new FabricaRecurso().converterParaDTO(recursoDisponivel.getRecurso()));
		recursoDisponivelDto.setQuantidade(recursoDisponivel.getQuantidade());
		recursoDisponivelDto.setValorUnitario(recursoDisponivel.getValorUnitario());
		recursoDisponivelDto.setValorTotal(recursoDisponivel.obterValorTotal());
		return recursoDisponivelDto;
	}

	public final RecursoDisponivel converterParaDominio(RecursoDisponivelDTO recursoDisponivelDto) {
		RecursoDisponivel recursoDisponivel = new RecursoDisponivel();
		if (recursoDisponivelDto.getId() != null) {
			recursoDisponivel.setId(recursoDisponivelDto.getId());
		}
		recursoDisponivel.setRecurso(new FabricaRecurso().converterParaDominio(recursoDisponivelDto.getRecursoDto()));
		recursoDisponivel.setQuantidade(recursoDisponivelDto.getQuantidade());
		recursoDisponivel.setValorUnitario(recursoDisponivelDto.getValorUnitario());
		return recursoDisponivel;
	}
}
