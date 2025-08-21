package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.LoteRecurso;

public class FabricaLoteRecurso extends
		FabricaBase<LoteRecursoDTO, LoteRecurso> {

	public final LoteRecursoDTO converterParaDTO(LoteRecurso loteRecurso) {
		RecursoDTO recursoDto = new FabricaRecurso().converterParaDTO(loteRecurso.getRecurso());
		return new LoteRecursoDTO(loteRecurso.getId(), recursoDto, loteRecurso.getQuantidade(), loteRecurso.getValor());
	}

	public final LoteRecurso converterParaDominio(
		LoteRecursoDTO loteRecursoDto) {
		LoteRecurso loteRecurso = new LoteRecurso();
		if (loteRecursoDto.getId() != null) {
			loteRecurso.setId(loteRecursoDto.getId());
		}
		loteRecurso.setRecurso(new FabricaRecurso()
				.converterParaDominio(loteRecursoDto.getRecursoDto()));
		loteRecurso.setQuantidade(loteRecursoDto.getQuantidade());
		loteRecurso.setValor(loteRecursoDto.getValor());
		return loteRecurso;
	}
}