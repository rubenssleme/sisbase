package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemSituacaoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.SituacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemSituacaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemesituacaodto_sem_erro_foi_construido() {
		List<SituacaoEducacionalDTO> situacaoDto = new ArrayList<>();
		situacaoDto.add(new SituacaoEducacionalDTO("COMPLETO"));
		ResultadoListagemSituacaoDTO resultadoListagemSituacaoDto = new ResultadoListagemSituacaoDTO();
		resultadoListagemSituacaoDto.efetuadoComSucesso(situacaoDto);

		Assert.assertTrue(resultadoListagemSituacaoDto.sucesso());
		Assert.assertFalse(resultadoListagemSituacaoDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemsituacaodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemSituacaoDTO resultadoListagemSituacaoDto = new ResultadoListagemSituacaoDTO();
		resultadoListagemSituacaoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemSituacaoDto.sucesso());
		Assert.assertNotNull(resultadoListagemSituacaoDto.obterMensagens());
	}
}
