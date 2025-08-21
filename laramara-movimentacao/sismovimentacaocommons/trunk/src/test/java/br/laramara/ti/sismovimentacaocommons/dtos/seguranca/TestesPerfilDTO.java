package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesPerfilDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void perfildto_foi_construida_com_sucesso() {
		Long id = new Long(5);
		String descricao = "Administrador";
		List<PermissaoDTO> permissoes = new ArrayList<>();
		permissoes.add(new PermissaoDTO("CONTA_ACESSO_EDICAO"));

		PerfilDTO perfilDto = new PerfilDTO(id, descricao, permissoes);

		Assert.assertEquals(perfilDto.getId(), id);
		Assert.assertEquals(perfilDto.toString(), descricao);
		Assert.assertFalse(perfilDto.getPermissoesDto().isEmpty());
	}

}
