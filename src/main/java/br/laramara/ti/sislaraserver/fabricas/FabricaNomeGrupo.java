package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;

public class FabricaNomeGrupo extends FabricaBase<NomeGrupoDTO, NomeGrupo> {
	public final NomeGrupoDTO converterParaDTO(NomeGrupo nomeGrupo) {
		return nomeGrupo != null ? new NomeGrupoDTO(nomeGrupo.getId(),
				nomeGrupo.getNome()) : null;
	}

	public final NomeGrupo converterParaDominio(NomeGrupoDTO nomeGrupoDto) {
		return nomeGrupoDto != null ? new NomeGrupo(nomeGrupoDto.getId(),
				nomeGrupoDto.toString()) : null;
	}
}
