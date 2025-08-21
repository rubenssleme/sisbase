package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemGrupoEtnicoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemgrupoetnicodto_sem_erro_foi_construido() {
		List<GrupoEtnicoDTO> grupoEtnicoDto = new ArrayList<>();
		grupoEtnicoDto.add(new GrupoEtnicoDTO("BRANCO"));
		
		ResultadoListagemGrupoEtnicoDTO resultadoListagemGrupoEtnicoDto = new ResultadoListagemGrupoEtnicoDTO();
		resultadoListagemGrupoEtnicoDto.efetuadoComSucesso(grupoEtnicoDto);

		Assert.assertTrue(resultadoListagemGrupoEtnicoDto.sucesso());
		Assert.assertFalse(resultadoListagemGrupoEtnicoDto.getObjetosDto()
				.isEmpty());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistegemgrupoetnicodto_com_erro_foi_construido() {
		String mensagemErro = "Houve algum erro";

		ResultadoListagemGrupoEtnicoDTO resultadoListagemGrupoEtnicoDto = new ResultadoListagemGrupoEtnicoDTO();
		resultadoListagemGrupoEtnicoDto.adicionarErro(mensagemErro);

		Assert.assertFalse(resultadoListagemGrupoEtnicoDto.sucesso());
		Assert.assertNotNull(resultadoListagemGrupoEtnicoDto.obterMensagens());
	}
	
}
