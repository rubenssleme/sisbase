package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemProntuarioEscaneadoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemprontuarioescaneadodto_sem_erro_foi_construido() {
		List<ProntuarioEscaneadoDTO> prontuariosEscaneadosDto = new ArrayList<>();
		prontuariosEscaneadosDto.add(new ProntuarioEscaneadoDTO(
				"Pront 9823 pag 2", new ArquivoDTO()));

		ResultadoListagemProntuarioEscaneadoDTO resultadoListagemProntuarioEscaneadosDto = new ResultadoListagemProntuarioEscaneadoDTO();
		resultadoListagemProntuarioEscaneadosDto
				.efetuadoComSucesso(prontuariosEscaneadosDto);

		Assert.assertTrue(resultadoListagemProntuarioEscaneadosDto.sucesso());
		Assert.assertFalse(resultadoListagemProntuarioEscaneadosDto
				.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemprontuarioescaneadodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemProntuarioEscaneadoDTO resultadoListagemProntuarioEscaneadoDto = new ResultadoListagemProntuarioEscaneadoDTO();
		resultadoListagemProntuarioEscaneadoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemProntuarioEscaneadoDto.sucesso());
		Assert.assertNotNull(resultadoListagemProntuarioEscaneadoDto
				.obterMensagens());
	}
}
