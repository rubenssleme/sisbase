package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.CustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ItemCustoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Custo;
import br.laramara.ti.sislaraserver.dominio.usuario.ItemCusto;

public class ContextoCusto {

	public static Custo fabricarCustoComTodosOsDados() {
		Custo custo = new Custo();
		custo.setItemCusto(new ItemCusto(new Long(12), "TERAPIAS"));
		custo.setValor("9.999.999,99");
		return custo;
	}

	public static CustoDTO fabricarCustoDTOComTodosOsDados() {
		CustoDTO custoDto = new CustoDTO();
		custoDto.setItemCustoDto(new ItemCustoDTO(new Long(12), "TERAPIAS"));
		custoDto.setValor("9999999,99");
		return custoDto;
	}
}
