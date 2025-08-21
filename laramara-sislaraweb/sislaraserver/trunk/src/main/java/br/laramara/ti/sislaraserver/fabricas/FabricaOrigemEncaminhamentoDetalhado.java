package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.OrigemEncaminhamentoDetalhadoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamentoDetalhado;

public class FabricaOrigemEncaminhamentoDetalhado extends FabricaBase<OrigemEncaminhamentoDetalhadoDTO, OrigemEncaminhamentoDetalhado> {

	public final OrigemEncaminhamentoDetalhadoDTO converterParaDTO(OrigemEncaminhamentoDetalhado encaminhamento) {
		OrigemEncaminhamentoDetalhadoDTO encaminhamentoDTO = new OrigemEncaminhamentoDetalhadoDTO();
		encaminhamentoDTO.setId(encaminhamento.getId());
		encaminhamentoDTO.setProfissionalLiberal(encaminhamento.getProfissionalLiberal());
		encaminhamentoDTO.setOrigemEncaminhamentoDto(
				new FabricaOrigemEncaminhamento().converterParaDTO(encaminhamento.getOrigemEncaminhamento()));
		return encaminhamentoDTO;
	}

	public final OrigemEncaminhamentoDetalhado converterParaDominio(OrigemEncaminhamentoDetalhadoDTO encaminhamentoDto) {
		OrigemEncaminhamentoDetalhado encaminhamento = new OrigemEncaminhamentoDetalhado();
		encaminhamento.setId(encaminhamentoDto.getId());
		encaminhamento.setProfissionalLiberal(encaminhamentoDto.getProfissionalLiberal());
		encaminhamento.setOrigemEncaminhamento(
				new FabricaOrigemEncaminhamento().converterParaDominio(encaminhamentoDto.getOrigemEncaminhamentoDto()));
		return encaminhamento;
	}
}
