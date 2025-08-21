package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemInfraestruturaBasicaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_infraestrutura_basica_dto_sem_erro_foi_construido() {
		List<InfraestruturaBasicaDTO> infraestruturaBasicaDto = Arrays
				.asList(new InfraestruturaBasicaDTO(new Long(1000), "TESTE"));
		ResultadoListagemInfraestruturaBasicaDTO resultadoListagemInfraestruturaBasicaDto = new ResultadoListagemInfraestruturaBasicaDTO();
		resultadoListagemInfraestruturaBasicaDto.efetuadoComSucesso(infraestruturaBasicaDto);

		Assert.assertTrue(resultadoListagemInfraestruturaBasicaDto.sucesso());
		Assert.assertFalse(resultadoListagemInfraestruturaBasicaDto.getObjetosDto().isEmpty());
	}
}
