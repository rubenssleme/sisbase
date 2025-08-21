package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.CustoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Custo;

public class TestesFabricaCusto {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_custo_converte_objeto_de_dominio_para_dto() {
		Custo custo = ContextoCusto.fabricarCustoComTodosOsDados();

		CustoDTO custoDTO = new FabricaCusto().converterParaDTO(custo);

		Assert.assertEquals(custoDTO.getId(), custo.getId());
		Assert.assertEquals(custoDTO.getItemCustoDto().getId(), custo
				.getItemCusto().getId());
		Assert.assertEquals(custoDTO.getValor(), custo.getValor());
	}	

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_custo_converte_objeto_de_dto_para_dominio() {

		CustoDTO custoDto = ContextoCusto.fabricarCustoDTOComTodosOsDados();

		Custo custo = new FabricaCusto().converterParaDominio(custoDto);

		Assert.assertEquals(custo.getId(), custoDto.getId());
		Assert.assertEquals(custo.getItemCusto().getId(), custoDto
				.getItemCustoDto().getId());
		Assert.assertEquals(custo.getValor(), custoDto.getValor());
	}
}
