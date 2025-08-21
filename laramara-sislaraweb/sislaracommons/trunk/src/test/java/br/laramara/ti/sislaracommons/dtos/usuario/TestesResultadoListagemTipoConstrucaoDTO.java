package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemTipoConstrucaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultado_listagem_tipo_construcao_dto_sem_erro_foi_construido() {
		List<TipoConstrucaoDTO> tipoConstruacaoDto = Arrays.asList(new TipoConstrucaoDTO(new Long(1000), "TESTE"));
		ResultadoListagemTipoConstrucaoDTO resultadoListagemTipoConstrucaoDto = new ResultadoListagemTipoConstrucaoDTO();
		resultadoListagemTipoConstrucaoDto.efetuadoComSucesso(tipoConstruacaoDto);

		Assert.assertTrue(resultadoListagemTipoConstrucaoDto.sucesso());
		Assert.assertFalse(resultadoListagemTipoConstrucaoDto.getObjetosDto().isEmpty());
	}
}
