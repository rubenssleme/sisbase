package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ramal;
import br.laramara.sistelemarketingserver.fabricas.RamalFabrica;

public class TestesRamalFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_ramal_converte_objeto_de_dominio_para_dto() {
		Ramal ramal = Ramal.RAMAL_6435;

		RamalDTO ramalDtoCovertido = new RamalFabrica().converterParaDTO(ramal);

		Assert.assertEquals(ramalDtoCovertido.getNumero(), ramal.getNumero());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_ramal_converte_objeto_dto_para_dominio() {
		RamalDTO ramalDto = new RamalDTO("6489");

		Ramal ranalCovertido = new RamalFabrica().converterParaDominio(ramalDto);

		Assert.assertEquals(ranalCovertido.getNumero(), ramalDto.getNumero());
	}
}
