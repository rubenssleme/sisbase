package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.FilialDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Filial;

public class ContextoFilial {

	public static FilialDTO fabricarDto() {
		FilialDTO filialDTO = new FilialDTO();
		filialDTO.setId(new Long(7));
		filialDTO.setCep("01151000");
		filialDTO.setCnpj("67640441000129");
		filialDTO.setEndereco("Rua Conselhero Brotero, 338, Barra Funda, SÃO PAULO");
		return filialDTO;
	}

	public static Filial fabricarDominio() {
		Filial filial = new Filial();
		filial.setId(new Long(7));
		filial.setCep("01151000");
		filial.setCnpj("67640441000129");
		filial.setEndereco("Rua Conselhero Brotero, 338, Barra Funda, SÃO PAULO");
		return filial;
	}
}
