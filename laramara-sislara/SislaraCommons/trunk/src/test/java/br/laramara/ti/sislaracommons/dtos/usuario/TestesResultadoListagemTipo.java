package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemTipoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.TipoInstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemTipo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemtipodto_sem_erro_foi_construido() {
		List<TipoInstituicaoDTO> listaTipoInstituicaoDto = new ArrayList<>();
		listaTipoInstituicaoDto.add(new TipoInstituicaoDTO("FACULDADE"));
		ResultadoListagemTipoInstituicaoDTO resultadoListagemTipoDto = new ResultadoListagemTipoInstituicaoDTO();
		resultadoListagemTipoDto.efetuadoComSucesso(listaTipoInstituicaoDto);

		Assert.assertTrue(resultadoListagemTipoDto.sucesso());
		Assert.assertFalse(resultadoListagemTipoDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemtipodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemTipoInstituicaoDTO resultadoListagemTipoDto = new ResultadoListagemTipoInstituicaoDTO();
		resultadoListagemTipoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemTipoDto.sucesso());
		Assert.assertNotNull(resultadoListagemTipoDto.obterMensagens());
	}

}
