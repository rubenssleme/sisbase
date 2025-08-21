package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.GrupoEtnicoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.GrupoEtnico;


public class FabricaGrupoEtnico extends FabricaBase<GrupoEtnicoDTO, GrupoEtnico> {
	public final GrupoEtnicoDTO converterParaDTO(
			GrupoEtnico grupoEtnico) {
		return grupoEtnico != null ? new GrupoEtnicoDTO(grupoEtnico.toString()) : null;
	}

	public final GrupoEtnico converterParaDominio(
			GrupoEtnicoDTO grupoEtnicoDTO) {
		return grupoEtnicoDTO != null ? GrupoEtnico.valueOf(GrupoEtnico.class,
				grupoEtnicoDTO.toString()) : null;
	}
}
