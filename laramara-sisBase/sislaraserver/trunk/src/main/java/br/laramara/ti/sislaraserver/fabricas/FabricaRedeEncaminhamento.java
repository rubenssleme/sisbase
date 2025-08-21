package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.RedeEncaminhamentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.RedeEncaminhamento;

public class FabricaRedeEncaminhamento extends FabricaBase<RedeEncaminhamentoDTO, RedeEncaminhamento> {

	public final RedeEncaminhamentoDTO converterParaDTO(RedeEncaminhamento redeEncaminhamento) {
		return redeEncaminhamento != null
				? new RedeEncaminhamentoDTO(redeEncaminhamento.getId(), redeEncaminhamento.getDescricao()) : null;
	}

	public final RedeEncaminhamento converterParaDominio(RedeEncaminhamentoDTO redeEncaminhamentoDto) {
		return redeEncaminhamentoDto != null
				? new RedeEncaminhamento(redeEncaminhamentoDto.getId(), redeEncaminhamentoDto.toString()) : null;
	}
}
