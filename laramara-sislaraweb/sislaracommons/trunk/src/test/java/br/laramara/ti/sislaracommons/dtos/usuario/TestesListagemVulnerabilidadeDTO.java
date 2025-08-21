package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesListagemVulnerabilidadeDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemvulnerabilidadedto_sem_erro_foi_construido() {
		List<VulnerabilidadeDTO> vulnerabilidadesDto = new ArrayList<>();
		vulnerabilidadesDto.add(new VulnerabilidadeDTO(new Long(12222), "Vulnerabilidade"));
		ResultadoListagemVulnerabilidadeDTO resultadoListagemVulnerabilidadeDto = new ResultadoListagemVulnerabilidadeDTO();
		resultadoListagemVulnerabilidadeDto.efetuadoComSucesso(vulnerabilidadesDto);

		Assert.assertTrue(resultadoListagemVulnerabilidadeDto.sucesso());
		Assert.assertFalse(resultadoListagemVulnerabilidadeDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemvulnerabilidadedto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemVulnerabilidadeDTO resultadoListagemVulnerabilidadeDto = new ResultadoListagemVulnerabilidadeDTO();
		resultadoListagemVulnerabilidadeDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemVulnerabilidadeDto.sucesso());
		Assert.assertNotNull(resultadoListagemVulnerabilidadeDto.obterMensagens());
	}
}
