package br.laramara.ti.sislaracommons.dtos;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemTipoTelefone {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipotelefonedto_sem_erro_foi_construido() {
		List<TipoTelefoneDTO> tipoTelefoneDto = new ArrayList<>();
		tipoTelefoneDto.add(new TipoTelefoneDTO("RESIDENCIAL"));
		ResultadoListagemTipoTelefoneDTO resultadoListagemTipoTelefoneDto = new ResultadoListagemTipoTelefoneDTO();
		resultadoListagemTipoTelefoneDto.efetuadoComSucesso(tipoTelefoneDto);

		Assert.assertTrue(resultadoListagemTipoTelefoneDto.sucesso());
		Assert.assertFalse(resultadoListagemTipoTelefoneDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipotelefonedto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemTipoTelefoneDTO resultadoListagemTipoTelefoneDto = new ResultadoListagemTipoTelefoneDTO();
		resultadoListagemTipoTelefoneDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemTipoTelefoneDto.sucesso());
		Assert.assertNotNull(resultadoListagemTipoTelefoneDto.obterMensagens());
	}
}
