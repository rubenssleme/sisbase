package br.laramara.sistelemarketingcommons.dtos.campanha;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesCampanhaResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void campanha_resultado_dto_foi_construida_com_sucesso() {

		CampanhaDTO campanhasDTO = ContextoCampanhaDTO.construir();

		CampanhaResultadoDTO campanhaResultadoDTO = new CampanhaResultadoDTO();
		campanhaResultadoDTO.efetuadoComSucesso(campanhasDTO);

		Assert.assertEquals(campanhaResultadoDTO.obterCampanhaDto().getId(), campanhasDTO.getId());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void campanha_resultado_listagem_dto_foi_construida_com_sucesso() {

		List<CampanhaDTO> campanhasDTO = Arrays.asList(ContextoCampanhaDTO.construir());

		CampanhaResultadoDTO permissoesResultadoDTO = new CampanhaResultadoDTO();
		permissoesResultadoDTO.efetuadoComSucesso(campanhasDTO);

		Assert.assertEquals(permissoesResultadoDTO.getCampanhasDto().size(),
				permissoesResultadoDTO.getCampanhasDto().size());
	}
}
