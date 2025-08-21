package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.EncaminhamentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Encaminhamento;

public class FabricaEncaminhamento extends FabricaBase<EncaminhamentoDTO, Encaminhamento> {
	public final EncaminhamentoDTO converterParaDTO(Encaminhamento encaminhamento) {
		return encaminhamento != null ? new EncaminhamentoDTO(encaminhamento.getId(), encaminhamento.getDescricao())
				: null;
	}

	public final Encaminhamento converterParaDominio(EncaminhamentoDTO encaminhamentoDto) {
		return encaminhamentoDto != null ? new Encaminhamento(encaminhamentoDto.getId(), encaminhamentoDto.toString())
				: null;
	}
}
