package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

public class FabricaRecurso extends FabricaBase<RecursoDTO, Recurso> {

	public final RecursoDTO converterParaDTO(Recurso recurso) {
		return recurso != null ? new RecursoDTO(recurso.getId(), recurso.getDescricao(), recurso.eCartelaDeSelos(),
				recurso.isDisponivelParaDemanda(), recurso.obterValor(),
				new FabricaDescricaoRecurso().converterParaDTO(recurso.getDescricoesRecurso())) : null;
	}

	public final Recurso converterParaDominio(RecursoDTO recursoDto) {
		return recursoDto != null ? new Recurso(recursoDto.getId(), recursoDto.toString(),
				recursoDto.isCartelaDeSelos(), recursoDto.isDisponivelParaDemanda(), recursoDto.getValor()) : null;
	}
}
