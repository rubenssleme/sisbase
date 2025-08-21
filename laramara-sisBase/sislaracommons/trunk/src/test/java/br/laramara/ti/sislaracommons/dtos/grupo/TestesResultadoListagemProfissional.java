package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemProfissional {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemprofissionaldto_sem_erro_foi_construido() {
		List<ProfissionalDTO> profissionalDto = new ArrayList<>();
		profissionalDto.add(new ProfissionalDTO(new Long(1000), "Paulo Augusto", "1222"));
		profissionalDto.add(new ProfissionalDTO(new Long(2000),
				"Victor Valitalino", "1222"));
		ResultadoListagemProfissionalDTO resultadoListagemProfissionalDto = new ResultadoListagemProfissionalDTO();
		resultadoListagemProfissionalDto.efetuadoComSucesso(profissionalDto);

		Assert.assertTrue(resultadoListagemProfissionalDto.sucesso());
		Assert.assertFalse(resultadoListagemProfissionalDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemprofissionaldto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemProfissionalDTO resultadoListagemProfissionalDto = new ResultadoListagemProfissionalDTO();
		resultadoListagemProfissionalDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemProfissionalDto.sucesso());
		Assert.assertNotNull(resultadoListagemProfissionalDto.obterMensagens());
	}
}
