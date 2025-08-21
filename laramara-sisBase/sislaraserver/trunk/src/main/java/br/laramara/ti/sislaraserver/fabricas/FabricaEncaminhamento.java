package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.EncaminhamentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Encaminhamento;

public class FabricaEncaminhamento extends FabricaBase<EncaminhamentoDTO, Encaminhamento> {

	public final EncaminhamentoDTO converterParaDTO(Encaminhamento encaminhamento) {
		EncaminhamentoDTO encaminhamentoDTO = new EncaminhamentoDTO();
		encaminhamentoDTO.setId(encaminhamento.getId());
		encaminhamentoDTO.setProfissionalLiberal(encaminhamento.getProfissionalLiberal());
		encaminhamentoDTO.setOrigemEncaminhamentoDto(
				new FabricaOrigemEncaminhamento().converterParaDTO(encaminhamento.getOrigemEncaminhamento()));
		encaminhamentoDTO.setRedeEncaminhamentoDto(
				new FabricaRedeEncaminhamento().converterParaDTO(encaminhamento.getRedeEncaminhamento()));
		return encaminhamentoDTO;
	}

	public final Encaminhamento converterParaDominio(EncaminhamentoDTO encaminhamentoDto) {
		Encaminhamento encaminhamento = new Encaminhamento();
		encaminhamento.setId(encaminhamentoDto.getId());
		encaminhamento.setProfissionalLiberal(encaminhamentoDto.getProfissionalLiberal());
		encaminhamento.setOrigemEncaminhamento(
				new FabricaOrigemEncaminhamento().converterParaDominio(encaminhamentoDto.getOrigemEncaminhamentoDto()));
		encaminhamento.setRedeEncaminhamento(
				new FabricaRedeEncaminhamento().converterParaDominio(encaminhamentoDto.getRedeEncaminhamentoDto()));
		return encaminhamento;
	}
}
