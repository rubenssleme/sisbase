package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.TipificacaoServicoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.TipificacaoServico;

public class FabricaTipificacaoServico extends
		FabricaBase<TipificacaoServicoDTO, TipificacaoServico> {

	public final TipificacaoServicoDTO converterParaDTO(
			TipificacaoServico tipificacaoServico) {
		return tipificacaoServico != null ? new TipificacaoServicoDTO(
				tipificacaoServico.getId(), tipificacaoServico.getNome())
				: null;
	}

	public final TipificacaoServico converterParaDominio(
			TipificacaoServicoDTO tipificacaoServicoDto) {
		return tipificacaoServicoDto != null ? new TipificacaoServico(
				tipificacaoServicoDto.getId(), tipificacaoServicoDto.toString())
				: null;
	}
}
