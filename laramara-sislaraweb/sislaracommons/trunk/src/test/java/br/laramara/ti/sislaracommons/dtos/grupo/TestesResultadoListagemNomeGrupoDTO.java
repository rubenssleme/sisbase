package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemNomeGrupoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemnomegrupodto_sem_erro_foi_construido() {
		List<NomeGrupoDTO> nomesGruposDto = new ArrayList<>();
		nomesGruposDto.add(new NomeGrupoDTO(new Long(1000), "G0"));
		nomesGruposDto.add(new NomeGrupoDTO(new Long(1001), "G1"));
		ResultadoListagemNomeGrupoDTO resultadoListagemNomeGrupoDto = new ResultadoListagemNomeGrupoDTO();
		resultadoListagemNomeGrupoDto.efetuadoComSucesso(nomesGruposDto);

		Assert.assertTrue(resultadoListagemNomeGrupoDto.sucesso());
		Assert.assertFalse(resultadoListagemNomeGrupoDto.getObjetosDto()
				.isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemnomegrupodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemNomeGrupoDTO resultadoListagemNomeGrupoDto = new ResultadoListagemNomeGrupoDTO();
		resultadoListagemNomeGrupoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemNomeGrupoDto.sucesso());
		Assert.assertNotNull(resultadoListagemNomeGrupoDto.obterMensagens());
	}
}