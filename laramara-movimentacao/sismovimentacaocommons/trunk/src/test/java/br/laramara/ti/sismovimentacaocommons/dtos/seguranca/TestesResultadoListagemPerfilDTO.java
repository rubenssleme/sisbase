package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemPerfilDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemusuariosbloqueadosdto_sem_erro_foi_construido() {
		ResultadoListagemPerfilDTO resultadoListagemPerfilDto = new ResultadoListagemPerfilDTO();
		List<PerfilDTO> perfilDto = new ArrayList<>();
		perfilDto.add(new PerfilDTO(new Long(1), "Administrador"));
		perfilDto.add(new PerfilDTO(new Long(2), "Operador"));
		resultadoListagemPerfilDto.efetuadoComSucesso(perfilDto);

		Assert.assertTrue(resultadoListagemPerfilDto.sucesso());
		Assert.assertNotNull(resultadoListagemPerfilDto.obterMensagens());
	}
}
