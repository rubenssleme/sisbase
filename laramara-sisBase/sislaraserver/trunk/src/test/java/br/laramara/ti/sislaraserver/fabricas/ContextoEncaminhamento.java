package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.EncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.OrigemEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.RedeEncaminhamentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Encaminhamento;
import br.laramara.ti.sislaraserver.dominio.usuario.OrigemEncaminhamento;
import br.laramara.ti.sislaraserver.dominio.usuario.RedeEncaminhamento;

public class ContextoEncaminhamento {

	public static Encaminhamento fabricarEncaminhamento() {
		Encaminhamento encaminhamento = new Encaminhamento();
		encaminhamento.setOrigemEncaminhamento(new OrigemEncaminhamento(new Long(1), "Hospital"));
		encaminhamento.setRedeEncaminhamento(new RedeEncaminhamento(new Long(1), "SUAS"));
		encaminhamento.setProfissionalLiberal("Joao Siqueira");
		return encaminhamento;
	}

	public static EncaminhamentoDTO fabricarEncaminhamentoDto() {
		EncaminhamentoDTO encaminhamentoDto = new EncaminhamentoDTO();
		encaminhamentoDto.setOrigemEncaminhamentoDto(new OrigemEncaminhamentoDTO(new Long(1), "Hospital"));
		encaminhamentoDto.setRedeEncaminhamentoDto(new RedeEncaminhamentoDTO(new Long(1), "SUAS"));
		encaminhamentoDto.setProfissionalLiberal("Joao Siqueira");
		return encaminhamentoDto;
	}
}
