package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoDTO;
import br.laramara.sistelemarketingserver.dominio.seguranca.Permissao;

public class PermissaoFabrica extends FabricaBase<PermissaoDTO, Permissao> {
	public final PermissaoDTO converterParaDTO(Permissao permissao) {
		PermissaoDTO permisssaoDto = new PermissaoDTO();
		if (permissao != null) {
			permisssaoDto.setId(permissao.getId());
			permisssaoDto.setDescricao(permissao.getDescricao());
		}
		return permisssaoDto;
	}

	public final Permissao converterParaDominio(PermissaoDTO permissaoDto) {
		Permissao permissao = new Permissao();
		if (permissaoDto != null) {
			permissao.setId(permissaoDto.getId());
			permissao.setDescricao(permissaoDto.getDescricao());
		}
		return permissao;
	}
}
