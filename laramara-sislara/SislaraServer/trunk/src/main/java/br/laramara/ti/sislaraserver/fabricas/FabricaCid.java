package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.CidDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Cid;

public class FabricaCid extends FabricaBase<CidDTO, Cid> {

	public final CidDTO converterParaDTO(Cid cid) {
		return cid != null ? new CidDTO(cid.getId(), cid.getDescricao()) : null;
	}

	public final Cid converterParaDominio(CidDTO cidDto) {
		return cidDto != null ? new Cid(cidDto.getId(), cidDto.toString())
				: null;
	}
}
