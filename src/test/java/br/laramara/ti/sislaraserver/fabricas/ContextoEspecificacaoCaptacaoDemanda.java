package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoCaptacaoDemandaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoCaptacaoDemanda;

public class ContextoEspecificacaoCaptacaoDemanda {

	public static EspecificacaoCaptacaoDemandaDTO fabricarDemandaComTodosOsDadosAlternativo() {
		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(ContextoDemanda.construirDemandaDTO());
		especificacaoCaptacaoDemandaDTO.setValor("500,00");
		return especificacaoCaptacaoDemandaDTO;
	}
	
	public static EspecificacaoCaptacaoDemanda fabricarDemandaComTodosOsDados() {
		EspecificacaoCaptacaoDemanda especificacaoCaptacaoDemanda = new EspecificacaoCaptacaoDemanda();
		especificacaoCaptacaoDemanda.setValor("500,00");
		return especificacaoCaptacaoDemanda;
	}
}
