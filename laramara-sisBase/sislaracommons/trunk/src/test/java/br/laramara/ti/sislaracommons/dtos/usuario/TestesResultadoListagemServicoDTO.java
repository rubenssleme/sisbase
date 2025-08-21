package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemServicoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_servico_dto_sem_erro_foi_construido() {
		List<ServicoDTO> servicoDto = Arrays
				.asList(new ServicoDTO(new Long(1000), "TESTE"));
		ResultadoListagemServicoDTO resultadoListagemServicoDto = new ResultadoListagemServicoDTO();
		resultadoListagemServicoDto.efetuadoComSucesso(servicoDto);

		Assert.assertTrue(resultadoListagemServicoDto.sucesso());
		Assert.assertFalse(resultadoListagemServicoDto.getObjetosDto().isEmpty());
	}
}
