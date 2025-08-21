package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.DiretoriaEnsinoDTO;
import br.laramara.ti.sislaraserver.dominio.escola.DiretoriaEnsino;

public class FabricaDiretoriaEnsino extends
		FabricaBase<DiretoriaEnsinoDTO, DiretoriaEnsino> {

	public final DiretoriaEnsinoDTO converterParaDTO(
			DiretoriaEnsino diretoriaEnsino) {
		return diretoriaEnsino != null ? new DiretoriaEnsinoDTO(
				diretoriaEnsino.getId(), diretoriaEnsino.getNome()) : null;
	}

	public final DiretoriaEnsino converterParaDominio(
			DiretoriaEnsinoDTO diretoriaEnsinoDto) {
		return diretoriaEnsinoDto != null ? new DiretoriaEnsino(
				diretoriaEnsinoDto.getId(), diretoriaEnsinoDto.toString())
				: null;
	}
}
