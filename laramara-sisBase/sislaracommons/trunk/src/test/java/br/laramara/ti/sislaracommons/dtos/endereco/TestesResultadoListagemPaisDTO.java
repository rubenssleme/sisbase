package br.laramara.ti.sislaracommons.dtos.endereco;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemPaisDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemPaisDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegempaisdto_sem_erro_foi_construido() {
		List<PaisDTO> paisesDto = new ArrayList<>();
		paisesDto.add(new PaisDTO(new Long(1), "CTO"));
		ResultadoListagemPaisDTO resultadoListagemPaisDto = new ResultadoListagemPaisDTO();
		resultadoListagemPaisDto.efetuadoComSucesso(paisesDto);

		Assert.assertTrue(resultadoListagemPaisDto.sucesso());
		Assert.assertFalse(resultadoListagemPaisDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegempaisdto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemPaisDTO resultadoListagemPaisDto = new ResultadoListagemPaisDTO();
		resultadoListagemPaisDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemPaisDto.sucesso());
		Assert.assertNotNull(resultadoListagemPaisDto.obterMensagens());
	}
}
