package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.DoencaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Doenca;

public class FabricaDoenca extends FabricaBase<DoencaDTO, Doenca> {

	public final DoencaDTO converterParaDTO(Doenca doenca) {
		return doenca != null ? new DoencaDTO(doenca.getId(),
				doenca.getDescricao()) : null;
	}

	public final Doenca converterParaDominio(DoencaDTO doencaDto) {
		return doencaDto != null ? new Doenca(doencaDto.getId(),
				doencaDto.toString()) : null;
	}
}
