package br.laramara.ti.sislaracommons.dtos.seguranca;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemPermissaoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagempermissaodto_sem_erro_foi_construido() {
		ResultadoListagemPermissaoDTO resultadoListagemPermissaoDto = new ResultadoListagemPermissaoDTO();
		List<PermissaoDTO> permissaoDto = new ArrayList<>();
		permissaoDto.add(new PermissaoDTO("PERMISAO_A"));
		permissaoDto.add(new PermissaoDTO("PERMISAO_B"));
		resultadoListagemPermissaoDto.efetuadoComSucesso(permissaoDto);

		Assert.assertTrue(resultadoListagemPermissaoDto.sucesso());
		Assert.assertNotNull(resultadoListagemPermissaoDto.obterMensagens());
	}
}
