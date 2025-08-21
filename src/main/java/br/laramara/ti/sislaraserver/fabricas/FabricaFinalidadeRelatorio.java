package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.solicitacao.FinalidadeRelatorioDTO;
import br.laramara.ti.sislaraserver.dominio.solicitacao.FinalidadeRelatorio;

public class FabricaFinalidadeRelatorio extends
		FabricaBase<FinalidadeRelatorioDTO, FinalidadeRelatorio> {
	public final FinalidadeRelatorioDTO converterParaDTO(
			FinalidadeRelatorio finalidadeRelatorio) {
		return finalidadeRelatorio != null ? new FinalidadeRelatorioDTO(
				finalidadeRelatorio.toString()) : null;
	}

	public final FinalidadeRelatorio converterParaDominio(
			FinalidadeRelatorioDTO finalidadeRelatorio) {
		return finalidadeRelatorio != null ? FinalidadeRelatorio.valueOf(
				FinalidadeRelatorio.class, finalidadeRelatorio.toString())
				: null;
	}
}
