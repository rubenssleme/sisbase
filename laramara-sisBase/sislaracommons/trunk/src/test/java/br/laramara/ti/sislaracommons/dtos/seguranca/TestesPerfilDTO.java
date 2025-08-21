package br.laramara.ti.sislaracommons.dtos.seguranca;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPerfilDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void perfildto_foi_construida_com_sucesso() {
		Long id = new Long(5);
		String descricao = "Administrador";
		List<PermissaoDTO> permissoes = new ArrayList<>();
		permissoes.add(new PermissaoDTO("EDICAO_USUARIO"));

		PerfilDTO perfilDto = new PerfilDTO(id, descricao, permissoes);

		Assert.assertEquals(perfilDto.getId(), id);
		Assert.assertEquals(perfilDto.toString(), descricao);
		Assert.assertFalse(perfilDto.getPermissoesDto().isEmpty());
	}

}
