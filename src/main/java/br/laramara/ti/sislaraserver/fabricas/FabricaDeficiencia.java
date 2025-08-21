package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.DeficienciaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Deficiencia;

public class FabricaDeficiencia extends
		FabricaBase<DeficienciaDTO, Deficiencia> {

	public final DeficienciaDTO converterParaDTO(Deficiencia deficiencia) {
		return deficiencia != null ? new DeficienciaDTO(deficiencia.getId(),
				deficiencia.getDescricao(),
				deficiencia.isEtiologiaObrigatorio()) : null;
	}

	public final Deficiencia converterParaDominio(DeficienciaDTO deficienciaDto) {
		return deficienciaDto != null ? new Deficiencia(deficienciaDto.getId(),
				deficienciaDto.toString(),
				deficienciaDto.isEtiologiaObrigatorio()) : null;
	}
}
