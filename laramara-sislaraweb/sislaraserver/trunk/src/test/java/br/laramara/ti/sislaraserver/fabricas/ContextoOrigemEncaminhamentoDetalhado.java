package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.OrigemEncaminhamentoDetalhadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.OrigemEncaminhamentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamentoDetalhado;
import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamento;

public class ContextoOrigemEncaminhamentoDetalhado {

	public static OrigemEncaminhamentoDetalhado fabricarOrigemEncaminhamentoDetalhado() {
		OrigemEncaminhamentoDetalhado encaminhamento = new OrigemEncaminhamentoDetalhado();
		encaminhamento.setOrigemEncaminhamento(new OrigemEncaminhamento(new Long(1), "Hospital"));
		encaminhamento.setProfissionalLiberal("Joao Siqueira");
		return encaminhamento;
	}

	public static OrigemEncaminhamentoDetalhadoDTO fabricarEncaminhamentoDto() {
		OrigemEncaminhamentoDetalhadoDTO encaminhamentoDto = new OrigemEncaminhamentoDetalhadoDTO();
		encaminhamentoDto.setOrigemEncaminhamentoDto(new OrigemEncaminhamentoDTO(new Long(1), "Hospital"));
		encaminhamentoDto.setProfissionalLiberal("Joao Siqueira");
		return encaminhamentoDto;
	}
}
