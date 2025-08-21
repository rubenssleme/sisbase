package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoCaptacaoDemandaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoCaptacaoDemanda;

public class FabricaEspecificacaoCaptacaoDemanda
		extends FabricaBase<EspecificacaoCaptacaoDemandaDTO, EspecificacaoCaptacaoDemanda> {

	@Override
	public EspecificacaoCaptacaoDemandaDTO converterParaDTO(EspecificacaoCaptacaoDemanda especificacaoCaptacaoDemanda) {
		return null;
	}

	@Override
	public EspecificacaoCaptacaoDemanda converterParaDominio(
			EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDto) {
		EspecificacaoCaptacaoDemanda especificacaoCaptacaoDemanda = new EspecificacaoCaptacaoDemanda();
		if (especificacaoCaptacaoDemandaDto != null) {
			especificacaoCaptacaoDemanda.setDemanda(
					new FabricaDemanda().converterParaDominio(especificacaoCaptacaoDemandaDto.getDemandaDto()));
			especificacaoCaptacaoDemanda.setValor(especificacaoCaptacaoDemandaDto.getValor());
			especificacaoCaptacaoDemanda.setRecibo(
					new FabricaRecibo().converterParaDominio(especificacaoCaptacaoDemandaDto.getReciboDto()));
		}
		return especificacaoCaptacaoDemanda;
	}
}
