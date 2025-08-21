package br.laramara.ti.sislaraserver.dominio.seguranca;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesChaveBloqueioGrupo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void chave_bloqueio_grupo_gera_por_grupo() {
		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G02"));
		grupoDto.setTurma("01");
		grupoDto.setDataInicio("31/12/2012");

		ChaveBloqueioGrupo chave = new ChaveBloqueioGrupo(grupoDto);

		Assert.assertEquals(chave.toString(), "G02-01-31/12/2012");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void chave_bloqueio_usuario_gera_por_identificacao() {
		String identificacao = "G02-01-31/12/2012";
		ChaveBloqueioUsuario chave = new ChaveBloqueioUsuario(identificacao);

		Assert.assertEquals(chave.toString(), identificacao);
	}
}
