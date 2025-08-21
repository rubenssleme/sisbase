package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemClassificacaoSocialDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemclassificacaosocialdto_sem_erro_foi_construido() {
		List<ClassificacaoSocialDTO> classificacoesSociaisDto = new ArrayList<>();
		classificacoesSociaisDto.add(new ClassificacaoSocialDTO("IP"));
		ResultadoListagemClassificacaoSocialDTO resultadoListagemClassificacaoDto = new ResultadoListagemClassificacaoSocialDTO();
		resultadoListagemClassificacaoDto
				.efetuadoComSucesso(classificacoesSociaisDto);

		Assert.assertTrue(resultadoListagemClassificacaoDto.sucesso());
		Assert.assertFalse(resultadoListagemClassificacaoDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemclassificacaosocialdto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemClassificacaoSocialDTO resultadoListagemClassificacaoDto = new ResultadoListagemClassificacaoSocialDTO();
		resultadoListagemClassificacaoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemClassificacaoDto.sucesso());
		Assert.assertNotNull(resultadoListagemClassificacaoDto.obterMensagens());
	}
}
