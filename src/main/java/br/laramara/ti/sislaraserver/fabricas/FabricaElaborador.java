package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.solicitacao.ElaboradorDTO;
import br.laramara.ti.sislaraserver.dominio.solicitacao.Elaborador;

public class FabricaElaborador extends FabricaBase<ElaboradorDTO, Elaborador> {
	public final ElaboradorDTO converterParaDTO(Elaborador elaborador) {
		return elaborador != null ? new ElaboradorDTO(elaborador.toString())
				: null;
	}

	public final Elaborador converterParaDominio(ElaboradorDTO elaborador) {
		return elaborador != null ? Elaborador.valueOf(Elaborador.class,
				elaborador.toString()) : null;
	}
}
