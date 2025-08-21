package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemStatusDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemstatusdto_sem_erro_foi_construido() {
		List<StatusDTO> statusDto = new ArrayList<>();
		statusDto.add(new StatusDTO("NOVO"));
		ResultadoListagemStatusDTO resultadoListagemStatusDto = new ResultadoListagemStatusDTO();
		resultadoListagemStatusDto.efetuadoComSucesso(statusDto);

		Assert.assertTrue(resultadoListagemStatusDto.sucesso());
		Assert.assertFalse(resultadoListagemStatusDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemstatusdto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemStatusDTO resultadoListagemStatusDto = new ResultadoListagemStatusDTO();
		resultadoListagemStatusDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemStatusDto.sucesso());
		Assert.assertNotNull(resultadoListagemStatusDto.obterMensagens());
	}
}
