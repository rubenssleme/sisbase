package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class FabricaSetor extends FabricaBase<SetorDTO, Setor> {
	public final SetorDTO converterParaDTO(Setor setor) {
		return setor != null ? new SetorDTO(setor.toString()) : null;
	}

	public final Setor converterParaDominio(SetorDTO setor) {
		return setor != null ? Setor.valueOf(setor.toString()) : null;
	}
}
