package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.DescricaoRecursoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.DescricaoRecurso;

public class FabricaDescricaoRecurso extends FabricaBase<DescricaoRecursoDTO, DescricaoRecurso> {

	public final DescricaoRecursoDTO converterParaDTO(DescricaoRecurso recurso) {
		return recurso != null ? new DescricaoRecursoDTO(recurso.getId(), recurso.getDescricao()) : null;
	}

	public final DescricaoRecurso converterParaDominio(DescricaoRecursoDTO recursoDto) {
		return recursoDto != null ? new DescricaoRecurso(recursoDto.getId(), recursoDto.toString()) : null;
	}
}
