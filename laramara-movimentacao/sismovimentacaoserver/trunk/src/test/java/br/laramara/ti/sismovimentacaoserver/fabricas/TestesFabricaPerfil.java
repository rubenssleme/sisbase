package br.laramara.ti.sismovimentacaoserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Perfil;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Permissao;

public class TestesFabricaPerfil {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_perfil_converte_objeto_de_dominio_para_dto() {
		List<Permissao> permissoes = new ArrayList<>();
		permissoes.add(Permissao.CONTA_ACESSO_EDICAO);
		permissoes.add(Permissao.DESBLOQUEIO_TELA);
		Perfil perfil = new Perfil(new Long(1), "Paulo", permissoes);

		PerfilDTO perfilDTO = new FabricaPerfil().converterParaDTO(perfil);

		Assert.assertEquals(perfilDTO.getId(), perfil.getId());
		Assert.assertEquals(perfilDTO.toString(), perfil.getDescricao());
		Assert.assertEquals(perfilDTO.getPermissoesDto().size(),
				permissoes.size());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_perfil_converte_objeto_de_dto_para_dominio() {
		List<PermissaoDTO> permissoesDto = new ArrayList<>();
		permissoesDto.add(new PermissaoDTO("CONTA_ACESSO_EDICAO"));
		permissoesDto.add(new PermissaoDTO("DESBLOQUEIO_TELA"));

		PerfilDTO perfilDto = new PerfilDTO(new Long(1), "Paulo", permissoesDto);

		Perfil perfil = new FabricaPerfil().converterParaDominio(perfilDto);

		Assert.assertEquals(perfil.getId(), perfilDto.getId());
		Assert.assertEquals(perfil.getDescricao(), perfilDto.toString());
		Assert.assertEquals(perfil.getPermissoes().size(), permissoesDto.size());
	}
}
