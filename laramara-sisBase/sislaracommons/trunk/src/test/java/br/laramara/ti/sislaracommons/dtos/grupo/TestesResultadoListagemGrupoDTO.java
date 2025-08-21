package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemGrupoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemgrupodto_sem_erro_foi_construido() {
		List<GrupoDTO> listagemGrupoDto = new ArrayList<>();
		listagemGrupoDto.add(new GrupoDTO());
		listagemGrupoDto.add(new GrupoDTO());
		ResultadoListagemGrupoDTO resultadoListagemGrupoDto = new ResultadoListagemGrupoDTO();
		resultadoListagemGrupoDto.efetuadoComSucesso(listagemGrupoDto);

		Assert.assertTrue(resultadoListagemGrupoDto.sucesso());
		Assert.assertFalse(resultadoListagemGrupoDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemgrupodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemGrupoDTO resultadoListagemGrupoDto = new ResultadoListagemGrupoDTO();
		resultadoListagemGrupoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemGrupoDto.sucesso());
		Assert.assertNotNull(resultadoListagemGrupoDto.obterMensagens());
	}
}