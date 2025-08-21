package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.InfraestruturaBasicaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.InfraestruturaBasica;

public class TestesFabricaInfraestruturaBasica {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_infraestrutura_basica_converte_objeto_de_dominio_para_dto() {
		InfraestruturaBasica insfraestruturaBasica = new InfraestruturaBasica(new Long(1000), "TESTE");

		InfraestruturaBasicaDTO infraestruturaBasicaDTO = new FabricaInfraestruturaBasica()
				.converterParaDTO(insfraestruturaBasica);

		Assert.assertEquals(infraestruturaBasicaDTO.getId(), insfraestruturaBasica.getId());
		Assert.assertEquals(infraestruturaBasicaDTO.toString(), insfraestruturaBasica.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_infraestrutura_basica_converte_objeto_de_dto_para_dominio() {
		InfraestruturaBasicaDTO infraestruturaBasicaDto = new InfraestruturaBasicaDTO(new Long(1000),
				"TESTE");

		InfraestruturaBasica infraestruturaBasicaObtido = new FabricaInfraestruturaBasica()
				.converterParaDominio(infraestruturaBasicaDto);

		Assert.assertEquals(infraestruturaBasicaObtido.getId(), infraestruturaBasicaDto.getId());
		Assert.assertEquals(infraestruturaBasicaObtido.getDescricao(), infraestruturaBasicaDto.toString());
	}
}
