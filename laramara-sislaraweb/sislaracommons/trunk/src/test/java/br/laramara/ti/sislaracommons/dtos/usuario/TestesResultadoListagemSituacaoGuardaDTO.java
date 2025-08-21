package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemSituacaoGuardaDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemsituacaoguardadto_sem_erro_foi_construido() {
		List<SituacaoGuardaDTO> situacaoGuardaDto = new ArrayList<>();
		situacaoGuardaDto.add(new SituacaoGuardaDTO(new Long(12222), "TUTELA"));
		ResultadoListagemSituacaoGuardaDTO resultadoListagemSituacaoGuardaDto = new ResultadoListagemSituacaoGuardaDTO();
		resultadoListagemSituacaoGuardaDto
				.efetuadoComSucesso(situacaoGuardaDto);

		Assert.assertTrue(resultadoListagemSituacaoGuardaDto.sucesso());
		Assert.assertFalse(resultadoListagemSituacaoGuardaDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemsituacaoguardadto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemSituacaoGuardaDTO resultadoListagemSituacaoGuardaDto = new ResultadoListagemSituacaoGuardaDTO();
		resultadoListagemSituacaoGuardaDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemSituacaoGuardaDto.sucesso());
		Assert.assertNotNull(resultadoListagemSituacaoGuardaDto
				.obterMensagens());
	}
}
