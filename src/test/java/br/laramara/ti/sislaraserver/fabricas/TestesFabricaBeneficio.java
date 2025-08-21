package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.BeneficioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Beneficio;

public class TestesFabricaBeneficio {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_beneficio_converte_objeto_de_dominio_para_dto() {
		Beneficio beneficio = new Beneficio(new Long(1),
				"Aposentadoria por Invalidez");

		BeneficioDTO beneficioDTO = new FabricaBeneficio()
				.converterParaDTO(beneficio);

		Assert.assertEquals(beneficioDTO.getId(), beneficio.getId());
		Assert.assertEquals(beneficioDTO.toString(), beneficio.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_beneficio_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(1);
		String descricao = "Aposentadoria por Invalidez";

		BeneficioDTO beneficionsDto = new BeneficioDTO(id, descricao);

		Beneficio beneficiosObtido = new FabricaBeneficio()
				.converterParaDominio(beneficionsDto);

		Assert.assertEquals(beneficiosObtido.getDescricao(), descricao);
		Assert.assertEquals(beneficiosObtido.getId(), id);
	}
}
