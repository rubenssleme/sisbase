package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesPermissaoResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void permissao_resultado_dto_foi_construida_com_sucesso() {

		List<PermissaoDTO> permissoesDTO = Arrays.asList(ContextoPermissaoDTO.construir());

		PermissaoResultadoDTO permissaoResultadoDTO = new PermissaoResultadoDTO();
		permissaoResultadoDTO.efetuadoComSucesso(permissoesDTO);

		Assert.assertEquals(permissaoResultadoDTO.getPermissoesDto().size(), permissoesDTO.size());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void permissao_resultado_listagem_dto_foi_construida_com_sucesso() {

		List<PermissaoDTO> permissoesDTO = Arrays.asList(ContextoPermissaoDTO.construir());

		PermissaoResultadoDTO permissoesResultadoDTO = new PermissaoResultadoDTO();
		permissoesResultadoDTO.efetuadoComSucesso(permissoesDTO);

		Assert.assertEquals(permissoesResultadoDTO.getPermissoesDto().size(),
				permissoesResultadoDTO.getPermissoesDto().size());
	}
}
