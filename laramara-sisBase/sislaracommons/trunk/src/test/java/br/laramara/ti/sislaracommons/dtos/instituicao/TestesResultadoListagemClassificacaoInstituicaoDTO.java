package br.laramara.ti.sislaracommons.dtos.instituicao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemClassificacaoInstituicaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemclassificacaoinstituicaodto_sem_erro_foi_construido() {
		List<ClassificacaoInstituicaoDTO> listaDto = new ArrayList<>();
		listaDto.add(new ClassificacaoInstituicaoDTO("FEDERAL"));

		ResultadoListagemClassificacaoInstituicaoDTO resultadoListagemClassificacaoInstituicaoDto = new ResultadoListagemClassificacaoInstituicaoDTO();
		resultadoListagemClassificacaoInstituicaoDto
				.efetuadoComSucesso(listaDto);

		Assert.assertTrue(resultadoListagemClassificacaoInstituicaoDto
				.sucesso());
		Assert.assertFalse(resultadoListagemClassificacaoInstituicaoDto
				.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemclassificacaoinstituicaodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemClassificacaoInstituicaoDTO resultadoListagemClassificacaoInstituicaoDto = new ResultadoListagemClassificacaoInstituicaoDTO();
		resultadoListagemClassificacaoInstituicaoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemClassificacaoInstituicaoDto.sucesso());
		Assert.assertNotNull(resultadoListagemClassificacaoInstituicaoDto.obterMensagens());
	}
}
