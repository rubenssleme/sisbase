package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.familiar.ParentescoDTO;
import br.laramara.ti.sislaraserver.dominio.familiar.Parentesco;

public class FabricaParentesco extends FabricaBase<ParentescoDTO, Parentesco> {
	public final ParentescoDTO converterParaDTO(
			Parentesco parentesco) {
		return new ParentescoDTO(parentesco.getId(), parentesco.getDescricao());
	}

	public final Parentesco converterParaDominio(
			ParentescoDTO parentescoDto) {
		return parentescoDto != null ? new Parentesco(parentescoDto.getId(),
				parentescoDto.toString()) : null;
	}
}