package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.RecursoEDescricaoRecursoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.RecursoEDescricaoRecurso;

public class FabricaRecursoEDescricaoDeRecurso
		extends FabricaBase<RecursoEDescricaoRecursoDTO, RecursoEDescricaoRecurso> {

	public RecursoEDescricaoRecurso converterParaDominio(RecursoEDescricaoRecursoDTO recursoEDescricaoRecursoDto) {
		return recursoEDescricaoRecursoDto != null ? new RecursoEDescricaoRecurso(
				new FabricaRecurso().converterParaDominio(recursoEDescricaoRecursoDto.getRecursoDTO()),
				new FabricaDescricaoRecurso()
						.converterParaDominio(recursoEDescricaoRecursoDto.getDescricaoRecursoDTO()))
				: null;
	}

	@Override
	public RecursoEDescricaoRecursoDTO converterParaDTO(RecursoEDescricaoRecurso objetoDominio) {
		return null;
	}
}
