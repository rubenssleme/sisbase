package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemStatusRelacaoUsuarioModuloDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemstausrelacaousuariomodulodto_sem_erro_foi_construido() {
		List<StatusRelacaoComModuloDTO> statusDto = new ArrayList<>();
		statusDto.add(new StatusRelacaoComModuloDTO("NORMAL"));
		statusDto.add(new StatusRelacaoComModuloDTO("DESLIGADO"));
		ResultadoListagemStatusRelacaoUsuarioModuloDTO resultadoListagemStatusDto = new ResultadoListagemStatusRelacaoUsuarioModuloDTO();
		resultadoListagemStatusDto.efetuadoComSucesso(statusDto);

		Assert.assertTrue(resultadoListagemStatusDto.sucesso());
		Assert.assertFalse(resultadoListagemStatusDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemstausrelacaousuariomodulodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemStatusRelacaoUsuarioModuloDTO resultadoListagemStatusDto = new ResultadoListagemStatusRelacaoUsuarioModuloDTO();
		resultadoListagemStatusDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemStatusDto.sucesso());
		Assert.assertNotNull(resultadoListagemStatusDto.obterMensagens());
	}
}
