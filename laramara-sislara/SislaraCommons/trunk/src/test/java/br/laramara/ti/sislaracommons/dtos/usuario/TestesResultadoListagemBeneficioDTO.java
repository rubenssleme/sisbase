package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemBeneficioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegembeneficiodto_sem_erro_foi_construido() {
		List<BeneficioDTO> beneficioDto = new ArrayList<>();
		beneficioDto.add(new BeneficioDTO(new Long(12222), "Aposentadoria"));
		ResultadoListagemBeneficioDTO resultadoListagemBeneficioDto = new ResultadoListagemBeneficioDTO();
		resultadoListagemBeneficioDto.efetuadoComSucesso(beneficioDto);

		Assert.assertTrue(resultadoListagemBeneficioDto.sucesso());
		Assert.assertFalse(resultadoListagemBeneficioDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegembeneficiodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemBeneficioDTO resultadoListagemBeneficioDto = new ResultadoListagemBeneficioDTO();
		resultadoListagemBeneficioDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemBeneficioDto.sucesso());
		Assert.assertNotNull(resultadoListagemBeneficioDto.obterMensagens());
	}
}
